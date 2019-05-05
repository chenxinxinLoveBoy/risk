package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangyong.backend.dao.BuBlacklistDao;
import com.shangyong.backend.service.RabbitMqServer;
import com.shangyong.backend.service.TdLoanInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.Content;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.dao.ApplicationDao;
import com.shangyong.backend.dao.BuApplicationDetailDao;
import com.shangyong.backend.dubbo.impl.GetCustomerInfoService;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BuApplicationDetail;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.CustomerInfo;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.mongo.common.MongoUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * @author CG
 */
@Service
public class TdLoanInterfaceServicesImpl implements TdLoanInterfaceService {

    private static Logger logger = LoggerFactory.getLogger(TdLoanInterfaceServicesImpl.class);
    
    @Autowired
    private SysParamRedisService sysParamRedisService;
    
    @Autowired
    private GetCustomerInfoService getCustomerInfoService;

	@Autowired
	private RabbitMqServer rabbitMqServer;
    
    @Autowired
    private BuApplicationDetailDao buApplicationDetailDao;
    
	@Autowired
	MongoUtils mongoUtils;
	
    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
	private BuBlacklistDao buBlacklistDao;

    @Override
    public List<Map<String, Object>> getAppLicationList(String isStep) {
        // 获取参数值,查询错误次数小于X的数据，（X错误次数）
        SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.APP_FAILURE_COUNT);
        String failureTimes = sysParam.getParamValueOne();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("auditingState", Constants.DAI_SP_STAATE); // 审核状态：只查询待审核数据（1-待审批、2-审批通过、3-审批未通过）
        //步骤标识（0：待处理、1：同盾设备信息、2：同盾反欺诈、3：白骑士资信云报告、4：极光黑名单、5：腾讯反欺诈、6：91征信查询（多头借贷）、7：91回调、8：白骑士运营商原始数据，98：规则引擎模板分发、99：潘多拉拒绝名单、100：客户授信）
        map.put("isStep", isStep); //
        map.put("failuretimes", failureTimes);//数据错误次数-6
        map.put("isPushApp", Constants.BIG_DATA_SYN_IS_STEP_2);//同步大数据的步骤-已同步到大数据
        return applicationDao.getAppLicationList(map);
    }

	@Override
    public List<Map<String, Object>> getAppLicationList(String isStep,String state) {
        // 获取参数值,查询错误次数小于X的数据，（X错误次数）
        SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.APP_FAILURE_COUNT);
        String failuretimes = sysParam.getParamValueOne();
        String auditingState = Constants.DAI_SP_STAATE;
        if ("1".equals(state)) {
        	auditingState = Constants.AGAIN_SP_STAATE;
		}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("auditingState", auditingState); // 审核状态：只查询待审核数据（1-待审批、2-审批通过、3-审批未通过）
        //步骤标识（0：待处理、1：同盾设备信息、2：同盾反欺诈、3：白骑士资信云报告、4：极光黑名单、5：腾讯反欺诈、6：91征信查询（多头借贷）、7：91回调、8：白骑士运营商原始数据，98：规则引擎模板分发、99：潘多拉拒绝名单、100：客户授信）
        map.put("isStep", isStep); //
        map.put("failuretimes", failuretimes);//数据错误次数-6
        map.put("isPushApp", Constants.BIG_DATA_SYN_IS_STEP_2);//同步大数据的步骤-已同步到大数据
        return applicationDao.getAppLicationList(map);
    }

	@Override
    public Map<String, Object> getCustApplicationList(Map<String, Object> dataMap) {
    	
		String applicationNumber = (String) dataMap.get("applicationNumber");//申请单标号
		String appSerialNumber = dataMap.get("appSerialNumber") == null ? "" : dataMap.get("appSerialNumber").toString();//app申请单号
		String platformId = dataMap.get("platformId") == null ? "" : dataMap.get("platformId").toString();//平台客户编号
		String appLevel = dataMap.get("appLevel") == null ? "" : dataMap.get("appLevel").toString();//客户类型
		String customerId = dataMap.get("customerId") == null ? "" : dataMap.get("customerId").toString();//APP应用客户编号
		String appName = dataMap.get("appName") == null ? "" : dataMap.get("appName").toString();//APP名称：1-闪贷；2-速贷
		String loanIp = dataMap.get("loanIp") == null ? "" : dataMap.get("loanIp").toString();//借款用户公网IP
		String source = dataMap.get("source") == null ? "" : dataMap.get("source").toString();//申请来源（0——Android；1——IOS）
		String certCode = dataMap.get("certCode") == null ? "" : dataMap.get("certCode").toString();//身份证
		String certType = dataMap.get("certType") == null ? "" : dataMap.get("certType").toString();//证件类型 ： 1.身份证 2.护照 3.其他
		String phoneNum = dataMap.get("phoneNum") == null ? "" : dataMap.get("phoneNum").toString();//手机号
		String name = dataMap.get("name") == null ? "" : dataMap.get("name").toString();//客户姓名
		String applicationBuId = dataMap.get("applicationBuId") == null ? "" : dataMap.get("applicationBuId").toString();//申请单业务编号
		String productQuota = dataMap.get("productQuota") == null ? "" : dataMap.get("productQuota").toString();//预期借款额度
		String days = dataMap.get("days") == null ? "" : dataMap.get("days").toString();//周期
		String creditMoney = dataMap.get("creditMoney") == null ? "" : dataMap.get("creditMoney").toString();//授信额度值
		String banCodeTplId = dataMap.get("banCodeTplId") == null ? "" : dataMap.get("banCodeTplId").toString();//禁止项模板主键ID
		String fraudTplId = dataMap.get("fraudTplId") == null ? "" : dataMap.get("fraudTplId").toString();//欺诈项模板主键ID
		String scoreTplId = dataMap.get("scoreTplId") == null ? "" : dataMap.get("scoreTplId").toString();//评分项模板主键ID
		String decisionTreeId = dataMap.get("decisionTreeId") == null ? "" : dataMap.get("decisionTreeId").toString();//决策树主键ID
		String appVersion = dataMap.get("appVersion") == null ? "" : dataMap.get("appVersion").toString();//App版本号
		String isHbaseSyn = dataMap.get("isHbaseSyn") == null ? "" : dataMap.get("isHbaseSyn").toString();//是否同步大数据, 0-否、1-是

		//根据申请单的平台客户编号，获取客户资料
		CuPlatformCustomer cuPlatformCustomer = new CuPlatformCustomer();
//		cuPlatformCustomer.setPlatformCustomerId(application.getPlatformId());
		cuPlatformCustomer.setCustomerId(customerId);
		cuPlatformCustomer.setAppName(appName);
		
		//调用dubbo接口查询客户信息
		CustomerInfo customerInfo = getCustomerInfoService.getCustomerInfoAndCompanyByCustomerIdAndAppName(cuPlatformCustomer);
		if(customerInfo == null || customerInfo.getCuPlatformCustomer() == null){
			throw new RuntimeException("未找到用户信息请确认用户存在，或检查dubbo服务是否正常");
		}
		
		//获取申请单、客户详细信息
    	Map<String, Object> dataAllMap = new HashMap<String, Object>(); 								//待更新申请单对象
    	dataAllMap.put("email", customerInfo.getCuPlatformCustomer().getEmail());						//邮箱
    	dataAllMap.put("age", customerInfo.getCuPlatformCustomer().getAge());							//年龄
    	dataAllMap.put("educationId", customerInfo.getCuPlatformCustomer().getEducationId());			//学历
    	dataAllMap.put("expirationDate", customerInfo.getCuPlatformCustomer().getExpirationDate());		//身份证有效期
    	dataAllMap.put("bankCard", customerInfo.getCuPlatformCustomer().getBankCard());					//银行卡号
    	dataAllMap.put("ifMarriage", customerInfo.getCuPlatformCustomer().getIfMarriage());				//婚姻状况
    	dataAllMap.put("homeAddress", customerInfo.getCuPlatformCustomer().getHomeAddress());			//家庭住址
    	dataAllMap.put("registeredAddress", customerInfo.getCuPlatformCustomer().getRegisteredAddress());//户籍地址
    	dataAllMap.put("contactAddress", customerInfo.getCuPlatformCustomer().getContactAddress());		//通讯地址
    	dataAllMap.put("registerTime", customerInfo.getCuPlatformCustomer().getRegisterTime());			//注册时间
    	dataAllMap.put("certType", certType);															//证件类型 ： 1.身份证 2.护照 3.其他
    	dataAllMap.put("certCode", certCode);															//身份证
    	dataAllMap.put("phoneNum", phoneNum);															//手机号
    	dataAllMap.put("name", name);																	//客户姓名
    	dataAllMap.put("applicationNumber", applicationNumber);											//申请单标号
    	dataAllMap.put("appSerialNumber", appSerialNumber);												//app申请单号
    	dataAllMap.put("applicationBuId", applicationBuId);												//申请单业务编号
    	dataAllMap.put("productQuota", productQuota);													//预期借款额度
    	dataAllMap.put("days", days);																	//周期
    	dataAllMap.put("creditMoney", creditMoney);														//授信额度值
    	dataAllMap.put("platformId", platformId);														//平台客户编号
    	dataAllMap.put("customerId", customerId);														//APP应用客户编号
    	dataAllMap.put("appName", appName);																//APP名称：1-闪贷；2-速贷
    	dataAllMap.put("loanIp", loanIp);																//借款用户公网IP
    	dataAllMap.put("source", source);																//申请来源（0——Android；1——IOS）
    	dataAllMap.put("banCodeTplId", banCodeTplId);													//禁止项模板主键ID
    	dataAllMap.put("fraudTplId", fraudTplId);														//欺诈项模板主键ID
    	dataAllMap.put("scoreTplId", scoreTplId);														//评分项模板主键ID
    	dataAllMap.put("decisionTreeId", decisionTreeId);												//决策树主键ID
    	dataAllMap.put("appLevel", appLevel);															//客户类型
    	dataAllMap.put("appVersion", appVersion);														//App版本号
    	dataAllMap.put("isHbaseSyn", isHbaseSyn);														//是否同步大数据, 0-否、1-是
    	
    	//判断返回的数据是否有公司信息
    	if(customerInfo.getCuCustomerCompany() != null){
    		dataAllMap.put("companyIndustry", customerInfo.getCuCustomerCompany().getCompanyIndustry());//公司行业
        	dataAllMap.put("companyName", customerInfo.getCuCustomerCompany().getCompanyName());//公司名称
        	dataAllMap.put("companyTel", customerInfo.getCuCustomerCompany().getCompanyTel());//公司电话
        	dataAllMap.put("companyAddress", customerInfo.getCuCustomerCompany().getCompanyAddress());//公司地址
        	dataAllMap.put("professionId", customerInfo.getCuCustomerCompany().getProfessionId());//职业编号
        	dataAllMap.put("workingHours", customerInfo.getCuCustomerCompany().getWorkingHours());//客户工作时长
    	}else{
    		dataAllMap.put("companyIndustry", "");//公司行业
        	dataAllMap.put("companyName", "");//公司名称
        	dataAllMap.put("companyTel", "");//公司电话
        	dataAllMap.put("companyAddress", "");//公司地址
        	dataAllMap.put("professionId", "");//职业编号
        	dataAllMap.put("workingHours", "");//客户工作时长
    	}
		return dataAllMap;
    }

	@Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public int updateApplicationByStep(Map<String, Object> map) {
        int flag = applicationDao.updateApplicationByStep(map);
        return flag;
    }

	@Override
    @Transactional
    public int insertBlackById(Map<String, Object> map) {
        int flag = buBlacklistDao.insertBlackById(map);
        return flag;
    }

	@Override
    public List<Application> getAppStatusByPush() {
        return applicationDao.getAppStatusByPush();
    }

	@Override
    public int getRgApprovalCount() {
        int flag = applicationDao.getRgApprovalCount();
        return flag;
    }

	@Override
    @Transactional
    public int updateIsPushApp(String param) {
        int flag = applicationDao.updateIsPushApp(param);
        return flag;
    }

	@Override
    public void updateApplicationByRuleResult(RuleResult result, String applicationId, String currentStep, String nextStepType, String stepName,String appLevel, Logger logger) {
    	try{
	        logger.info("申请编号：" + applicationId + "调用" + stepName + "接口返回信息，isblack：" + result.getIsblack() + "，message：" + result.getMessage());
	        String auditResult = "";
	        String auditingState = "";
	        String banCode = "";
	        if (result.getIsblack()) {
	            auditingState = Constants.NOPASS_SP_STAATE; //表示命中规则，审批不通过
	            auditResult = result.getMessage();
	            banCode = result.getBlackCode();
	        } else {
	            auditingState = Constants.DAI_SP_STAATE;
	        }
	
	        //接口调用完，更新审批状态等字段。
	        Map<String, Object> dataMapParam = new HashMap<String, Object>();
	        dataMapParam.put("isStep", nextStepType); //步骤标识（0：待处理、1：同盾设备信息、2：同盾反欺诈、3：白骑士资信云报告、4：极光黑名单、5：腾讯反欺诈、6：91征信查询（多头借贷）、7：91回调、98：规则引擎模板分发、99：潘多拉拒绝名单、100：客户授信）
	        dataMapParam.put("auditResult", auditResult); //审批结果
	        dataMapParam.put("auditingState", auditingState);//审核状态
	        dataMapParam.put("applicationId", applicationId);//申请编号
	        dataMapParam.put("banCode", banCode);//禁止项规则对应编号
	        dataMapParam.put("currentStep", currentStep);//当前步骤号
	        dataMapParam.put("errorDescription", "");//清空步骤处理异常描述

			dataMapParam.put("blacklistDsSource", result.getDsSource());
			dataMapParam.put("banClassCode", result.getBanCodeClassCode());

	        SysParam isUseAllStep = sysParamRedisService.querySysParamByParamValueRedis(Constants.ISUSE_ALL_STEP);
	        SysParam useFinalStapNum = sysParamRedisService.querySysParamByParamValueRedis(Constants.USE_FINAL_STEP_NUM);
	        String num=useFinalStapNum.getParamValueOne();//获取步骤最大编号
	        String flag=isUseAllStep.getParamValueOne();
	        //插入禁止项撞击表
	        if (result.getIsblack()) {
            	List<Content> contents = result.getContents();
            		if (!(contents != null && contents.size() > 0)) {
            			throw new RuntimeException("当前步骤号："+ Integer.parseInt(nextStepType) +",获取命中项集合失败");
					}
            		//插入所有的命中项
            		for (Content content : contents) {
			        	BuApplicationDetail applicationDetail=new BuApplicationDetail();
			        	applicationDetail.setBuApplicationDetailId(UUIDUtils.getUUID());
			        	applicationDetail.setApplicationId(applicationId);
			        	applicationDetail.setCreateTime(DateUtils.getDate(new Date()));
			        	applicationDetail.setBanCode(content.getBlackCode());
			        	applicationDetail.setState(3);
						applicationDetail.setBanClassCode(content.getBanCodeClassCode());
						applicationDetail.setBlacklistDsSource(content.getDsSource());
			        	applicationDetail.setDescrip(content.getMessage());
			        	applicationDetail.setStepNum(Integer.parseInt(nextStepType));     	
			        	int saveEntity = buApplicationDetailDao.saveEntity(applicationDetail);
			        	if (saveEntity>0) {
			        		logger.info("黑名单备表信息入库成功：当前步骤号" + Integer.parseInt(nextStepType) + "命中信息：" + auditResult);
						}
            	}
            }
	        if("1".equals(flag)){
	        	//判断是不是最后一步
	        	if(nextStepType.equals(num)){
	        		//查询之前步骤是否有被拒绝
	        		int count=buApplicationDetailDao.refuseStepCount(applicationId);
	        		if(count>0){
	        			  dataMapParam.put("auditingState", Constants.NOPASS_SP_STAATE);
	        			  updateApplicationByStep(dataMapParam);
	        			  return;
	        		}else{
	        			//用户未命中禁止项所有的选项，判断是否需要推送人工信审
	        			updateSpStatus(applicationId,nextStepType,appLevel,currentStep);
	        			return;
	        		}
	        	}else{
	        		 dataMapParam.put("auditingState", Constants.DAI_SP_STAATE);
	        		 updateApplicationByStep(dataMapParam);
	        		 return;
	        	}
	        }else{
	        	
	        	if(nextStepType.equals(num)&&Constants.DAI_SP_STAATE.equals(auditingState)){
	        		//如果最后一步成功并且是待审核
	        		updateSpStatus(applicationId,nextStepType,appLevel,currentStep);
	        		return;
	        	}else if(result.getIsblack()){
	        		 dataMapParam.put("auditingState", Constants.NOPASS_SP_STAATE);
	        		 updateApplicationByStep(dataMapParam);
	        		 return;
	        	}
	        }
	         dataMapParam.put("auditingState", Constants.DAI_SP_STAATE);
	         updateApplicationByStep(dataMapParam);
	  	     logger.info(stepName + "接口定时任务结束，审核状态【" + auditingState + "】，审批结果【" + auditResult + "】");
    	}catch(Exception e){
    		logger.error("定时任务审核申请单出现了异常 applicationId:"+applicationId+"执行错误步骤："+nextStepType+">error:"+e.getMessage(), e);
    	}
    }

	@Override
    public void updateSpStatus(String applicationId,String nextStepType,String appLevel,String currentStep){
    	Map<String, Object> dataMapParam = new HashMap<String, Object>();
		//审核状态
		dataMapParam.put("auditingState", Constants.MQ_QZF_STATE);
		//申请编号
		dataMapParam.put("applicationId", applicationId);
		//申请编号
		dataMapParam.put("currentStep", currentStep);
		Integer count = updateApplicationByStep(dataMapParam);
		rabbitMqServer.sendMqForFraudScore(applicationId);
		logger.info("更新状态count=" + count + ",发送消息,applicationId=" +applicationId );
    }
}
