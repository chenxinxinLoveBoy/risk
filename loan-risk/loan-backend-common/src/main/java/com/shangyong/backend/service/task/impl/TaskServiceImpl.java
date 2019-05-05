package com.shangyong.backend.service.task.impl;

import java.util.Map;

import com.shangyong.backend.service.TdLoanInterfaceService;
import com.shangyong.backend.service.tdFacility.NewTonDunFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.bqs.BqsPersonalInfoService;
import com.shangyong.backend.service.bqs.BqsReportInfoService;
import com.shangyong.backend.service.jg.JgInfoCheckService;
import com.shangyong.backend.service.shzx.ShCreditService;
import com.shangyong.backend.service.task.TaskService;
import com.shangyong.backend.service.td.TonDunfqzService;
import com.shangyong.backend.service.tdFacility.TonDunFacilityService;
import com.shangyong.backend.service.tdReport.TdReportService;
import com.shangyong.backend.service.txy.TxyFqzService;
import com.shangyong.backend.service.xczx.XczxApplicationToService;
import com.shangyong.backend.service.yjf.YjfBlackListService;

/**
 * task接口，事务统一好
 * 
 * @author CG
 */
@Service
public class TaskServiceImpl implements TaskService {
	private static Logger tdTasklogger = LoggerFactory.getLogger("tdBackendTask");
	private static Logger xczxLogger = LoggerFactory.getLogger("Xczx");
	private static Logger jgLogger = LoggerFactory.getLogger("jg");
	private static Logger txyFqzLogger = LoggerFactory.getLogger("txyFqz");
	private static Logger tdFacililtyLogger = LoggerFactory.getLogger("tdFacility");
	private static Logger bqsRepLogger = LoggerFactory.getLogger("bqsRep");
	private static Logger bqsInfoLogger = LoggerFactory.getLogger("bqsInfo");
	private static Logger yjfLogger = LoggerFactory.getLogger("yjfBlackList");
	private static Logger shCreditLogger = LoggerFactory.getLogger("shCredit");
	private static Logger tdReportLogger = LoggerFactory.getLogger("tdReportLogger");

	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;

	@Autowired
	private XczxApplicationToService xczxApplicationToService;

	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类

	@Autowired
	private JgInfoCheckService jgInfoCheckService;// 极光定时任务service

	@Autowired
	private TonDunfqzService tonDunfqzService;// 同盾反欺诈定时任务service

	@Autowired
	private TxyFqzService txyFqzService;// 腾讯云反欺诈

	@Autowired
	private NewTonDunFacilityService tonDunFacilityService;// 同盾设备指纹

	@Autowired
	private BqsReportInfoService bqsReportInfoService;// 白骑士资信云报告

	@Autowired
	private BqsPersonalInfoService bqsPersonalInfoService;// 白骑士运营商原始数据

	@Autowired
	private YjfBlackListService yjfBlackListService;// 易极付-黑名单
	
	@Autowired
	private ShCreditService shCreditService;// 上海资信

	@Autowired
	private TdReportService tdReportService;//同盾数据魔盒报告
	/**
	 * 91征信
	 * 
	 * @param dataMap
	 * @throws Exception
	 * @throws Throwable
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void xczxApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		RuleResult result = new RuleResult();
		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();// 申请单编号
		String certNo = application.getCertCode();// 身份证号码
		String name = application.getName();// 姓名
		String appLevel = application.getAppLevel();

		xczxLogger.info("[91征信] 定时任务服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

		// 获取参数值，所有征信机构是否真正获取报告配置
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.ZHENGXIN_ORG_PASSTWO);
		String paramValueThree = sysParam.getParamValueThree();
		if (Constants.CLOSE.equals(paramValueThree)) {
			result = xczxApplicationToService.sendQuery(applicationId, name, certNo);
			xczxLogger.info("[91征信] 申请编号：" + applicationId + "调用91征信服务接口返回信息：isblack：" + result.getIsblack() + "，message："
					+ result.getMessage());
		} else {
			// 如果跳过91征信，步骤号直接修改为回调后的步骤号
			tdLoanInterfaceService.updateApplicationByRuleResult(result, applicationId, currentStep,
					Constants.XC_91ZX_CALL_STEP, Constants.XC_91ZX_NAME, appLevel, xczxLogger);

			xczxLogger.info("[91征信] 申请编号：" + applicationId + "，系统配置为[paramValueThree="+paramValueThree+"] 跳过91征信");
		}

		// 91征信服务接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result,
				applicationId,
				currentStep,
				Constants.XC_91ZX_STEP,
				Constants.XC_91ZX_NAME,
				appLevel,
				xczxLogger);
		xczxLogger.info("[91征信] 定时任务单笔务处理结束，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());
	}

	/**
	 * 极光黑名单
	 * 
	 * @param dataMap
	 * @throws Exception
	 * @throws Throwable
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void jgApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		RuleResult result = new RuleResult();
		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();// 申请单编号
		String appLevel = application.getAppLevel();
		jgLogger.info("定时任务极光黑名单服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

		result = jgInfoCheckService.getJqInfoCheck(application);
		
		// 极光服务接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result, applicationId, currentStep,
				Constants.JG_BLACK_STEP, Constants.JG_BLACK_NAME, appLevel, jgLogger);
		jgLogger.info("定时任务单笔极光推送征信服务处理结束，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());
	}

	/**
	 * 同盾反欺诈
	 * 
	 * @param dataMap
	 * @throws Exception
	 * @throws Throwable
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void tdApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		RuleResult result = new RuleResult();
		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();// 申请单编号
		String appLevel = application.getAppLevel();

		tdTasklogger.info("定时任务同盾反欺诈服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

		result = tonDunfqzService.queryFqz(application);
		
		// 同盾反欺诈接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result, applicationId, currentStep,
				Constants.TD_ANTI_FRAUD_STEP, Constants.TD_ANTI_FRAUD_NAME, appLevel, tdTasklogger);
		tdTasklogger.info(
				"定时任务单笔同盾反欺诈推送征信服务处理结束，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());
	}

	/**
	 * 白骑士资信云报告
	 * 
	 * @param dataMap
	 * @param currentStep
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void bqsRepApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		RuleResult result = new RuleResult();
		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();// 申请单编号
		String appLevel = application.getAppLevel();

		bqsRepLogger.info("定时任务白骑士资信云服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());
		
		result = bqsReportInfoService.getReport(application);
		
		// 91征信服务接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result, applicationId, currentStep,
				Constants.BQS_ZXY_STEP, Constants.BQS_ZXY_NAME, appLevel, bqsRepLogger);
		bqsRepLogger.info("定时任务白骑士资信云处理结束，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

	}

	/**
	 * 白骑士运营商原始数据
	 * 
	 * @param dataMap
	 * @param currentStep
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void bqsInfoApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();
		String appLevel = application.getAppLevel();

		bqsInfoLogger.info("定时任务白骑士运营商原始数据服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

		RuleResult result = bqsPersonalInfoService.getBqsPersonalInfo(application);
		
		// 91征信服务接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result,
				applicationId,
				currentStep,
				Constants.BQS_INFO_STEP,
				Constants.BQS_INFO_NAME,
				appLevel,
				bqsInfoLogger);

		bqsInfoLogger.info("定时任务白骑士运营商原始数据处理结束，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());
	}

	/**
	 * 腾讯云反欺诈
	 * 
	 * @param dataMap
	 * @throws Exception
	 * @throws Throwable
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void txyApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();
		String appLevel = application.getAppLevel();

		txyFqzLogger.info("定时任务腾讯云反欺诈服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

		RuleResult result = txyFqzService.getTxyFqzInfo(application);
	
		// 同盾反欺诈接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result,
				applicationId,
				currentStep,
				Constants.TENCENT_ANTI_FRAUD_STEP,
				Constants.TENCENT_ANTI_FRAUD_NAME,
				appLevel,
				txyFqzLogger);
		txyFqzLogger.info("定时任务单笔腾讯云反欺诈推送征信服务处理结束，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

	}

	/**
	 * 同盾设备指纹
	 * 
	 * @param dataMap
	 * @throws Exception
	 * @throws Throwable
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void tdFacililtyApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();
		String appLevel = application.getAppLevel();

		tdFacililtyLogger.info("定时任务同盾设备指纹服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

		RuleResult result = tonDunFacilityService.acquire(application);

		// 同盾反欺诈接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result,
				applicationId,
				currentStep,
				Constants.TD_EQUIPMENT_STEP,
				Constants.TD_EQUIPMENT_NAME,
				appLevel,
				tdFacililtyLogger);
		tdFacililtyLogger.info("定时任务同盾设备指纹推送征信服务处理结束，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());
	}

	/**
	 * 易极付-黑名单
	 * 
	 * @param dataMap
	 * @throws Exception
	 * @throws Throwable
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void yjfApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();
		String appLevel = application.getAppLevel();
		yjfLogger.info("定时任务易极付-黑名单服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

		RuleResult result = yjfBlackListService.saveYjfBlackList(application);
		
		// 同盾反欺诈接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result,
				applicationId,
				currentStep,
				Constants.YJF_BLACK_STEP,
				Constants.YJF_BLACK_NAME,
				appLevel,
				yjfLogger);
		yjfLogger.info("定时任务单笔易极付-黑名单推送征信服务处理结束，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

	}

	/**
	 * 上海资信
	 * @param dataMap
	 * @param currentStep
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void shCreditApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		RuleResult result = new RuleResult();

		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();
		String appLevel = application.getAppLevel();
		shCreditLogger.info("定时任务上海资信服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

		//上海资信是否开启开关
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.SH_CREDIT_KEY);
		String state = sysParam.getParamValueOne();
		if ("1".equals(state)) {
			result = shCreditService.queryShCredit(application);
		}else{
			result.setIsblack(false);
			result.setState(0);
		}
		// 同盾反欺诈接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result,
				applicationId,
				currentStep,
				Constants.SH_CREDIT_STEP,
				Constants.SH_CREDIT_NAME,
				appLevel,
				shCreditLogger);
		shCreditLogger.info("定时任务单笔上海资信推送征信服务处理结束，申请单编号：" + applicationId );
	}

	/**
	 * 同盾数据魔盒报告
	 * @param dataMap
	 * @param currentStep
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void tdRepApplicationTask(Map<String, Object> dataMap, String currentStep) throws Exception {
		// 封装申请单信息
		Application application = setApplication(dataMap);
		String applicationId = application.getApplicationId();
		String appLevel = application.getAppLevel();
		tdReportLogger.info("定时任务同盾数据魔盒报告服务处理开始，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

		RuleResult result = tdReportService.queryTdReport(application);
		
		// 同盾反欺诈接口走完，更新步骤标识、审批状态、审批结果描述
		tdLoanInterfaceService.updateApplicationByRuleResult(result,
				applicationId,
				currentStep,
				Constants.TD_REPORT_STEP,
				Constants.TD_REPORT_NAME,
				appLevel,
				tdReportLogger);
		tdReportLogger.info("定时任务单笔同盾数据魔盒报告推送征信服务处理结束，申请单编号：" + applicationId + "，APP应用请求流水号：" + application.getAppSerialNumber());

	}

	/**
	 * 拼装需要的申请单信息 返回申请单对象
	 * 
	 * @param dataMap
	 * @return
	 */
	private Application setApplication(Map<String, Object> dataMap) {
		String applicationNumber = (String) dataMap.get("applicationNumber");// 申请单编号
		String customerId = (String) dataMap.get("customerId");// 客户app账号
		String platformCustomerId = (String) dataMap.get("platformId");// 大后台平台账号
		String idCard = (String) dataMap.get("certCode");// 身份证号码
		String idName = (String) dataMap.get("name");// 身份证姓名
		String cellPhone = (String) dataMap.get("phoneNum");// 手机号码
		String applicationBuId = (String) dataMap.get("applicationBuId");
		String appSerialNumber = (String) dataMap.get("appSerialNumber");
		String loanIp = (String) dataMap.get("loanIp");
		String appName = dataMap.get("appName") == null ? "" : dataMap.get("appName").toString();
		String certType = (String) dataMap.get("certType");
		String source = (String) dataMap.get("source")==null?"": dataMap.get("source").toString();
		String banCodeTplId = dataMap.get("banCodeTplId") == null ? "" : dataMap.get("banCodeTplId").toString();
		String fraudTplId = dataMap.get("fraudTplId") == null ? "" : dataMap.get("fraudTplId").toString();
		String scoreTplId = dataMap.get("scoreTplId") == null ? "" : dataMap.get("scoreTplId").toString();
		String decisionTreeId = dataMap.get("decisionTreeId") == null ? "" : dataMap.get("decisionTreeId").toString();
		String appLevel = dataMap.get("appLevel") == null ? "" : dataMap.get("appLevel").toString();
		String isHbaseSyn = dataMap.get("isHbaseSyn") == null ? "" : dataMap.get("isHbaseSyn").toString();

		Application application = new Application();
		application.setApplicationId(applicationNumber);
		application.setApplicationBuId(applicationBuId);
		application.setAppSerialNumber(appSerialNumber);
		application.setAppName(appName);
		application.setCertCode(idCard);
		application.setCertType(certType);
		application.setCustomerId(customerId);
		application.setPlatformId(platformCustomerId);
		application.setPhoneNum(cellPhone);
		application.setName(idName);
		application.setLoanIp(loanIp);
		application.setSource(source);
		application.setBanCodeTplId(banCodeTplId);
		application.setFraudTplId(fraudTplId);
		application.setScoreTplId(scoreTplId);
		application.setDecisionTreeId(decisionTreeId);
		application.setAppLevel(appLevel);
		application.setIsHbaseSyn(isHbaseSyn);
		return application;
	}

}
