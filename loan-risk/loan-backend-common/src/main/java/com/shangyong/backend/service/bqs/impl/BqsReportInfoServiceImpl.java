package com.shangyong.backend.service.bqs.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shangyong.backend.utils.BanCodeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.shangyong.backend.common.BQSConstant;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RedisConstant;
import com.shangyong.backend.common.RuleConstants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.common.enums.FraudBizEnum;
import com.shangyong.backend.dao.bqsrep.BqsRepAntiFraudCloudServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepContactsInfoServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepCrossValidationServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepDataSourceServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepGoOutServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepHighRiskServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepHomeCheckServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoAreasServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoContactAreasServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoExtMobileServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoInfoServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoOneMobileServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoPeriodUsedNewServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoPeriodUsedServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoSevenDayMobileServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoSixMobileServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoThreeDayMobileServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoThreeMobileServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMonthUsedServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepPetitionerServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepServiceInfoServiceDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.bqsrep.BqsRepAntiFraudCloud;
import com.shangyong.backend.entity.bqsrep.BqsRepContactsInfo;
import com.shangyong.backend.entity.bqsrep.BqsRepCrossValidation;
import com.shangyong.backend.entity.bqsrep.BqsRepDataSource;
import com.shangyong.backend.entity.bqsrep.BqsRepGoOut;
import com.shangyong.backend.entity.bqsrep.BqsRepHighRisk;
import com.shangyong.backend.entity.bqsrep.BqsRepHomeCheck;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoAreas;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoContactAreas;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoExtMobile;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoInfo;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoOneMobile;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoPeriodUsed;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoPeriodUsedNew;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoSevenDayMobile;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoSixMobile;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoThreeDayMobile;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoThreeMobile;
import com.shangyong.backend.entity.bqsrep.BqsRepMonthUsed;
import com.shangyong.backend.entity.bqsrep.BqsRepPetitioner;
import com.shangyong.backend.entity.bqsrep.BqsRepServiceInfo;
import com.shangyong.backend.entity.bqsrep.jsonbean.AlipayRegisterTime;
import com.shangyong.backend.entity.bqsrep.jsonbean.BankConnectInfo;
import com.shangyong.backend.entity.bqsrep.jsonbean.BqsAntiFraudCloud;
import com.shangyong.backend.entity.bqsrep.jsonbean.BqsHighRiskList;
import com.shangyong.backend.entity.bqsrep.jsonbean.BqsRepBean;
import com.shangyong.backend.entity.bqsrep.jsonbean.CallActiveArea;
import com.shangyong.backend.entity.bqsrep.jsonbean.CallContactsAreaMatch;
import com.shangyong.backend.entity.bqsrep.jsonbean.CallDuration1to5minSize;
import com.shangyong.backend.entity.bqsrep.jsonbean.CallDuration5to10minSize;
import com.shangyong.backend.entity.bqsrep.jsonbean.CallDurationBigger10minSize;
import com.shangyong.backend.entity.bqsrep.jsonbean.CallDurationLess1minSize;
import com.shangyong.backend.entity.bqsrep.jsonbean.CallMobileBelongMatch;
import com.shangyong.backend.entity.bqsrep.jsonbean.CallRecordsSizeOver200Month;
import com.shangyong.backend.entity.bqsrep.jsonbean.CallSizeOver500Month;
import com.shangyong.backend.entity.bqsrep.jsonbean.CommonlyContacts;
import com.shangyong.backend.entity.bqsrep.jsonbean.ContactsActiveArea;
import com.shangyong.backend.entity.bqsrep.jsonbean.ContactsActiveDegree;
import com.shangyong.backend.entity.bqsrep.jsonbean.ContactsSize;
import com.shangyong.backend.entity.bqsrep.jsonbean.CreditCardConnectInfo;
import com.shangyong.backend.entity.bqsrep.jsonbean.CrossValidation;
import com.shangyong.backend.entity.bqsrep.jsonbean.DataReport;
import com.shangyong.backend.entity.bqsrep.jsonbean.EbUseFrequency;
import com.shangyong.backend.entity.bqsrep.jsonbean.EmergencyContacts;
import com.shangyong.backend.entity.bqsrep.jsonbean.ExceptionalConnectInfoList;
import com.shangyong.backend.entity.bqsrep.jsonbean.ExchangeCallMobileCount;
import com.shangyong.backend.entity.bqsrep.jsonbean.GoOutDatas;
import com.shangyong.backend.entity.bqsrep.jsonbean.HighRiskLists;
import com.shangyong.backend.entity.bqsrep.jsonbean.HomePhoneCheck;
import com.shangyong.backend.entity.bqsrep.jsonbean.Mno3daysCommonlyConnectMobiles;
import com.shangyong.backend.entity.bqsrep.jsonbean.Mno7daysCommonlyConnectMobiles;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoBaseInfo;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoCommonlyConnectAreas;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoCommonlyConnectMobiles;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoCommonlyUsedServices;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoConnectMobilesExt;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoContactsCommonlyConnectAreas;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoMonthUsedInfos;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoOneMonthCommonlyConnectMobiles;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoPayFeesCount;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoPeriodUsedInfos;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoPeriodUsedInfosNew;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoSinglePaymentMax;
import com.shangyong.backend.entity.bqsrep.jsonbean.MnoThreeMonthCommonlyConnectMobiles;
import com.shangyong.backend.entity.bqsrep.jsonbean.NightCallCount;
import com.shangyong.backend.entity.bqsrep.jsonbean.NotCallAndSmsDayCount;
import com.shangyong.backend.entity.bqsrep.jsonbean.Number110ConnectInfo;
import com.shangyong.backend.entity.bqsrep.jsonbean.Number120ConnectInfo;
import com.shangyong.backend.entity.bqsrep.jsonbean.NumberUsedLong;
import com.shangyong.backend.entity.bqsrep.jsonbean.OneselfAdderChangeFrequency;
import com.shangyong.backend.entity.bqsrep.jsonbean.OneselfEbUseFrequency;
import com.shangyong.backend.entity.bqsrep.jsonbean.OpenTime;
import com.shangyong.backend.entity.bqsrep.jsonbean.OriginatingCallDuration;
import com.shangyong.backend.entity.bqsrep.jsonbean.OriginatingCallDurationMax;
import com.shangyong.backend.entity.bqsrep.jsonbean.OriginatingMobileCount;
import com.shangyong.backend.entity.bqsrep.jsonbean.P2pConnectInfo;
import com.shangyong.backend.entity.bqsrep.jsonbean.Petitioner;
import com.shangyong.backend.entity.bqsrep.jsonbean.ReceiveMsgCount;
import com.shangyong.backend.entity.bqsrep.jsonbean.SendMsgCount;
import com.shangyong.backend.entity.bqsrep.jsonbean.ServiceMonthInfos;
import com.shangyong.backend.entity.bqsrep.jsonbean.TerminatingCallCount;
import com.shangyong.backend.entity.bqsrep.jsonbean.TerminatingCallDuration;
import com.shangyong.backend.entity.bqsrep.jsonbean.TerminatingCallDurationMax;
import com.shangyong.backend.entity.bqsrep.jsonbean.UserInfoValidation;
import com.shangyong.backend.entity.bqsrep.jsonbean.WebDataSources;
import com.shangyong.backend.entity.bqsrep.vo.BqsRepPetitionerVo;
import com.shangyong.backend.entity.redis.BqsReportMergeRedis;
import com.shangyong.backend.entity.redis.fraud1_1.BqsReport11Redis;
import com.shangyong.backend.entity.redis.fraud1_8.BqsReport18Redis;
import com.shangyong.backend.entity.redis.fraud2_0.BqsReport20Redis;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.service.bqs.BqsReportInfoService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.utils.BlackCityUtils;
import com.shangyong.backend.utils.JacksonUtils;
import com.shangyong.backend.utils.SpringIocUtils;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RedisFraudUtils;
import com.shangyong.utils.RiskHttpClientUtil;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONObject;
/**
 * 白骑士获取报告实体类
 * 
 * **/
@Service
public class BqsReportInfoServiceImpl implements BqsReportInfoService {
	private static Logger logger = LoggerFactory.getLogger("bqsRep");
	/**注入dao层**/
	@Autowired
	private BqsRepAntiFraudCloudServiceDao bqsCloudServiceDao;
	@Autowired
	private BqsRepDataSourceServiceDao bqsSourceServiceDao;
	@Autowired
	private BqsRepHighRiskServiceDao bqsRiskServiceDao;
	@Autowired
	private BqsRepPetitionerServiceDao bqsRepPetitionerServiceDao;
	@Autowired
	private BqsRepMnoInfoServiceDao bqsInfoServiceDao;
	@Autowired
	private BqsRepContactsInfoServiceDao bqsContactsServiceDao;
	@Autowired
	private BqsRepGoOutServiceDao bqsGoOutServiceDao;
	@Autowired
	private BqsRepMnoAreasServiceDao bqsRepMnoAreasServiceDao;
	@Autowired
	private BqsRepMnoContactAreasServiceDao bqsRepMnoContactAreasServiceDao;
	@Autowired
	private BqsRepMnoExtMobileServiceDao bqsRepMnoExtMobileServiceDao;
	@Autowired
	private BqsRepMnoOneMobileServiceDao bqsRepMnoOneMobileServiceDao;
	@Autowired
	private BqsRepMnoSevenDayMobileServiceDao bqsRepMnoSevenDayMobileServiceDao;
	@Autowired
	private BqsRepMnoSixMobileServiceDao bqsRepMnoSixMobileServiceDao;
	@Autowired
	private BqsRepMnoThreeDayMobileServiceDao bqsRepMnoThreeDayMobileServiceDao;
	@Autowired
	private BqsRepMnoThreeMobileServiceDao bqsRepMnoThreeMobileServiceDao;
	@Autowired
	private BqsRepCrossValidationServiceDao bqsRepCrossValidationServiceDao;
	@Autowired
	private BqsRepHomeCheckServiceDao bqsRepHomeCheckServiceDao;
	@Autowired
	private BqsRepMnoPeriodUsedServiceDao bqsRepMnoPeriodUsedServiceDao;
	@Autowired
	private BqsRepMnoPeriodUsedNewServiceDao bqsRepMnoPeriodUsedNewServiceDao;
	@Autowired
	private BqsRepMonthUsedServiceDao bqsRepMonthUsedServiceDao;
	@Autowired
	private BqsRepServiceInfoServiceDao bqsRepServiceInfoServiceDao;
	@Autowired
	private JsonReportService jsonReportService;
	@Autowired
	private RiskRuleService riskRuleService;

	@Override
	public RuleResult getReport(Application application) {
		
		RuleResult ruleResult = new RuleResult();
		//禁止项
		List<Map<String,Object>> checkList = new ArrayList<Map<String,Object>>();
		
		SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
    	SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.BQS_REPORT_CODE);
		String operatorInfoUrl = sysParam.getParamValueOne();

		logger.info(">>>>调用白骑士获取报告原数据请求地址>>bqsRiskUrl:" + operatorInfoUrl);

		String bqsPartnerId = sysParam.getParamValueTwo();
		String bqsVerifyKey = sysParam.getParamValueThree();
		String applicationId = application.getApplicationId();
		/** 判断证件类型**/ 
		 if (!"1".equals(application.getCertType())) {
			/** 不是身份证,报错**/
			 throw new RuntimeException("证件类型异常,请输入身份证类型:"+application.getCertType());
		}
		Assert.hasText(application.getApplicationId(),"ApplicationId参数为空");
		Assert.hasText(application.getName(),"Name参数为空");
		Assert.hasText(application.getPhoneNum(),"PhoneNum参数为空");
		Assert.hasText(application.getCertCode(),"CertCode参数为空");
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("name", application.getName());
		requestMap.put("mobile", application.getPhoneNum());
		requestMap.put("certNo", application.getCertCode());
		requestMap.put("partnerId", bqsPartnerId);
		requestMap.put("verifyKey", bqsVerifyKey);
		String jsonStr = JSON.toJSONString(requestMap);

		logger.info(">>>>调用白骑士获取报告原数据参数>>jsonStr:" + jsonStr);
		String dateTime = DateUtils.getDate(new Date());

			try {
				String content = RiskHttpClientUtil.doPostJson(operatorInfoUrl, jsonStr);
				if (StringUtils.isBlank(content) ) {
					logger.error("获取白骑士资信云报告失败,返回值为null");
					throw new RuntimeException("获取白骑士资信云报告失败,返回值为null");
				}
				
				BqsRepBean bqsReport = (BqsRepBean) JacksonUtils.JsonToBean(content, BqsRepBean.class);	
				if(!"CCOM1000".equals(bqsReport.getResultCode())){
					logger.error("获取白骑士资信云报告失败,返回码:"+bqsReport.getResultCode());
					throw new RuntimeException("获取白骑士资信云报告失败,返回码为:"+bqsReport.getResultCode()+",描述:"+bqsReport.getResultDesc());
				}

				//1、初始化redis的实体
				BqsReportMergeRedis reportRedis = new BqsReportMergeRedis();

				/** 保存主键**/
				BqsRepPetitioner petitioner = new BqsRepPetitioner();
				BeanUtils.copyProperties(bqsReport, petitioner);

				DataReport data = bqsReport.getData();
				Petitioner petit = data.getPetitioner();
				String pid=UUIDUtils.getUUID();
				BeanUtils.copyProperties(petit, petitioner);
				petitioner.setReportSn(data.getReportSn());
				petitioner.setReportTime(data.getReportTime());
				petitioner.setBqsPetitionerId(pid);
				petitioner.setApplicationId(applicationId);
				petitioner.setCreateTime(dateTime);
				petitioner.setModifyTime(dateTime);

				/** 插入主表**/
				bqsRepPetitionerServiceDao.saveEntity(petitioner);

				/** 插入运营商基本信息**/
				MnoBaseInfo mnoBaseInfo = data.getMnoBaseInfo();
				this.saveMnoInfo(mnoBaseInfo,pid);

				/** 插入高风险名单**/
				BqsHighRiskList bqsHighRiskList = data.getBqsHighRiskList();
				this.saveRiskList(bqsHighRiskList,pid,checkList);

				/** 数据来源**/
				List<WebDataSources> dataSources = data.getWebDataSources();
				this.saveDataSource(dataSources,pid,checkList);

				/** 反欺诈云**/ //已处理
				BqsAntiFraudCloud bqsAntiFraudCloud = data.getBqsAntiFraudCloud();
				this.saveAntiFraudCloud(bqsAntiFraudCloud, pid, reportRedis);


				/** 紧急联系人**/ //已处理
				List<EmergencyContacts> emergencyContacts = data.getEmergencyContacts();
				this.saveEmergencyContacts(emergencyContacts,pid,checkList,reportRedis);

				/** 常用联系人**/
				List<CommonlyContacts> commonlyContacts = data.getCommonlyContacts();
				this.saveCommonlyContacts(commonlyContacts, pid);

				/** 出行数据**/
				List<GoOutDatas> goOutDatas = data.getGoOutDatas();
				this.saveGoOutDatas(goOutDatas,pid,reportRedis);

				/** 本人通话活动地区**/
				List<MnoCommonlyConnectAreas> mnoCommonlyConnectAreas = data.getMnoCommonlyConnectAreas();
				this.saveMnoCommonlyConnectAreas(mnoCommonlyConnectAreas,pid);

				/** 联系人通话活动地区**/  //已处理
				List<MnoContactsCommonlyConnectAreas> mnoContactsCommonlyConnectAreas = data.getMnoContactsCommonlyConnectAreas();
				this.saveMnoContactsCommonlyConnectAreas(mnoContactsCommonlyConnectAreas,pid,reportRedis);

				/** 常用联系电话额外**/
				List<MnoConnectMobilesExt> mnoConnectMobilesExt = data.getMnoConnectMobilesExt();
				this.saveMnoConnectMobilesExt(mnoConnectMobilesExt,pid);

				/** 常用联系电话（近1个月)**/
				List<MnoOneMonthCommonlyConnectMobiles> mnoOneMonthCommonlyConnectMobiles = data.getMnoOneMonthCommonlyConnectMobiles();
				this.saveMnoOneMonthCommonlyConnectMobiles(mnoOneMonthCommonlyConnectMobiles,pid);

				/** 常用联系电话（近7天)**/
				List<Mno7daysCommonlyConnectMobiles> mno7daysCommonlyConnectMobiles = data.getMno7daysCommonlyConnectMobiles();
				this.saveMno7daysCommonlyConnectMobiles(mno7daysCommonlyConnectMobiles,pid,reportRedis);

				/** 常用联系电话（近6个月)**/
				List<MnoCommonlyConnectMobiles> mnoCommonlyConnectMobiles = data.getMnoCommonlyConnectMobiles();
				this.saveMnoCommonlyConnectMobiles(mnoCommonlyConnectMobiles,pid);

				/** 常用联系电话（近3天)**/ //已处理
				List<Mno3daysCommonlyConnectMobiles> mno3daysCommonlyConnectMobiles = data.getMno3daysCommonlyConnectMobiles();
				this.saveMno3daysCommonlyConnectMobiles(mno3daysCommonlyConnectMobiles,pid,reportRedis);

				/** 常用联系电话（近3个月)**/
				List<MnoThreeMonthCommonlyConnectMobiles> mnoThreeMonthCommonlyConnectMobiles = data.getMnoThreeMonthCommonlyConnectMobiles();
				this.saveMnoThreeMonthCommonlyConnectMobiles(mnoThreeMonthCommonlyConnectMobiles,pid);

				/**  家庭联系人信息**/
				UserInfoValidation userInfoValidation = data.getUserInfoValidation();
				this.saveUserInfoValidation(userInfoValidation,pid);

				/** 分时间段统计数据**/
				List<MnoPeriodUsedInfos> mnoPeriodUsedInfos = data.getMnoPeriodUsedInfos();
				this.saveMnoPeriodUsedInfos(mnoPeriodUsedInfos,pid);

				/** 新版分时间段统计数据**/  //已处理
				List<MnoPeriodUsedInfosNew> mnoPeriodUsedInfosNew = data.getMnoPeriodUsedInfosNew();
				this.saveMnoPeriodUsedInfosNew(mnoPeriodUsedInfosNew,pid,reportRedis);

				/** 月使用信息**/  //已处理
				List<MnoMonthUsedInfos> mnoMonthUsedInfos = data.getMnoMonthUsedInfos();
				this.saveMnoMonthUsedInfos(mnoMonthUsedInfos,pid,checkList,reportRedis);

				/** 常用服务**/
				List<MnoCommonlyUsedServices> mnoCommonlyUsedServices = data.getMnoCommonlyUsedServices();
				this.saveMnoCommonlyUsedServices(mnoCommonlyUsedServices,pid);

				/** 用户行为，活跃程度，通话行为，特殊通话检测**/
				CrossValidation crossValidation = data.getCrossValidation();
				this.saveCrossValidation(crossValidation,pid,checkList,reportRedis);

				//存阿里云
				jsonReportService.uploadJson(Constants.BQS_REPORT_UPLOAD_DIR,JSONObject.fromObject(content), TaskTypeConstants.BQS_REPORT_UPLOAD_DIR, TaskTypeConstants.BQS_REPORT_UPLOAD_NAME, TaskTypeConstants.BQS_REPORT_UPLOAD_ISEND, application.getApplicationId(), "noext");
				//调用禁止项，获取用户校验结果
				ruleResult = riskRuleService.querySafeRuleApi(application,checkList);
				
				if(ruleResult == null){
					throw new RuntimeException("白骑士报告获取-->调用taskCallBackService-->resultObj 为空");
				}

				//上传Reids
				String key = RedisConstant.buildFraudScoresKey(applicationId, FraudBizEnum.BAI_QI_SHI);
				String key2 = RedisConstant.buildFraudScoresKey1_8(applicationId, FraudBizEnum.BAI_QI_SHI);
				String key3 = RedisConstant.buildFraudScoresKey2_0(applicationId, FraudBizEnum.BAI_QI_SHI);

				//欺诈1.1
				BqsReport11Redis report11Redis = new BqsReport11Redis();
				//欺诈1.8
				BqsReport18Redis report18Redis = new BqsReport18Redis();
				// 2.0
				BqsReport20Redis report20Redis = new BqsReport20Redis();
				BeanUtils.copyProperties(reportRedis, report11Redis);
				BeanUtils.copyProperties(reportRedis, report18Redis);
				BeanUtils.copyProperties(reportRedis, report20Redis);
				RedisFraudUtils.hmset(key, report11Redis.toMap());
				RedisFraudUtils.hmset(key2, report18Redis.toMap());
				RedisFraudUtils.hmset(key3, report20Redis.toMap());

			} catch (Exception e) {
				throw new RuntimeException("调用白骑士报告数据返回异常："+e.getMessage(), e);
			}
			logger.info("白骑士报告,处理完成");
			return ruleResult;
	}
	
	/** dao层用户行为，活跃程度，通话行为，特殊通话检测**/
	private void saveValidation(BqsRepCrossValidation bqsRepCrossValidation, String pid, String type) {
		try {
			bqsRepCrossValidation.setBqsCrossValidationId(UUIDUtils.getUUID());
			bqsRepCrossValidation.setBqsPetitionerId(pid);
			bqsRepCrossValidation.setType(type);
			this.bqsRepCrossValidationServiceDao.saveEntity(bqsRepCrossValidation);
		} catch (Exception e) {
			throw new RuntimeException("白骑士报告用户行为入库异常："+e.getMessage(), e);
		}
	}
	/** 常用服务**/
	private void saveMnoCommonlyUsedServices(List<MnoCommonlyUsedServices> mnoCommonlyUsedServices, String pid) {
		try {
			if(mnoCommonlyUsedServices==null){
				return;
			}
			for (MnoCommonlyUsedServices commonlyUsedServices : mnoCommonlyUsedServices) {
				String seviceType = commonlyUsedServices.getSeviceType();
				String serviceName = commonlyUsedServices.getServiceName();
				List<ServiceMonthInfos> serviceMonthInfos = commonlyUsedServices.getServiceMonthInfos();
				if(serviceMonthInfos==null){
					continue;
				}
				List<BqsRepServiceInfo> loans = new ArrayList<BqsRepServiceInfo>();
				int size = serviceMonthInfos.size();
				for(int i = 0; i < serviceMonthInfos.size(); i ++){
					BqsRepServiceInfo bqsRepServiceInfo = new BqsRepServiceInfo();
					BeanUtils.copyProperties(serviceMonthInfos.get(i), bqsRepServiceInfo);
					bqsRepServiceInfo.setConnectCount(String.valueOf(serviceMonthInfos.get(i).getConnectCount()));
					bqsRepServiceInfo.setServiceName(serviceName);
					bqsRepServiceInfo.setSeviceType(seviceType);
					bqsRepServiceInfo.setBqsServiceInfoId(UUIDUtils.getUUID());
					bqsRepServiceInfo.setBqsPetitionerId(pid);
					loans.add(bqsRepServiceInfo);
					if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
						
						this.bqsRepServiceInfoServiceDao.saveAllEntity(loans);
						loans.clear();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("白骑士报告常用服务入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告常用服务入库异常："+e.getMessage());
		}
	}


	/** 月使用信息
	 * @param checkList **/
	private void saveMnoMonthUsedInfos(List<MnoMonthUsedInfos> mnoMonthUsedInfos, String pid, List<Map<String, Object>> checkList,BqsReportMergeRedis reportRedis) {
		try {
			if(mnoMonthUsedInfos==null){
				return;
			}
			//6个月总消费金额(云)
			Double sumMoney = 0.00;
			//3个月主叫时间:秒
			Double sumCallTime = 0.00;
			//近6个月平均被叫时长(每月):秒
			Double avgCallTime = 0.00;
			//近3个月总被叫次数
			Double sumCount = 0.00;
			//近3个月平均被叫次数(每月):次
			Double avgCount = 0.00;
			//近6个月总被叫时长
			Double sumSixCallTime = 0.00;
			List<BqsRepMonthUsed> loans = new ArrayList<BqsRepMonthUsed>();

			List<String> dataList =  new ArrayList<String>();
			Map<String,MnoMonthUsedInfos> usedInfosMap = new HashMap<String,MnoMonthUsedInfos>();

			String nowDate = DateUtils.formatDate("yyyy-MM",new Date());

			List<String> monthSpace1m =  new ArrayList<String>();//最近1个月
			List<String> monthSpace3ms =  new ArrayList<String>(); //最近3个月
			List<String> monthSpace5ms =  new ArrayList<String>(); //最近5个月
			handlerMnoMonthUsedInfos(dataList,usedInfosMap,mnoMonthUsedInfos,monthSpace1m,monthSpace3ms,monthSpace5ms,nowDate);

			//handlerMnoMonthUsedInfos(dataList,usedInfosMap,mnoMonthUsedInfos);
			List<String> keyList = DateUtils.getSortDate(dataList,"yyyy-MM");

			int size = keyList.size();
			//String nowDate = DateUtils.formatDate("yyyy-MM",new Date());

			//初始化数据
			Double carrierMaxPhoneBill5m = null; // 近5月（从申请上月起），最大月话费金额
			Double callTimeTotalVsOut1m = null; // 近1月（从申请上月起）:通话时间
			Double originatingCallTimeTotalVsOut1m = null; //近1月（从申请上月起）:呼出时间
			Double callCountTotalVsOut1m = null; // 近1月（从申请上月起）:呼入次数
			Double originatingCallCountTotalVsOut1m = null; //近1月（从申请上月起）:呼出次数
			Double carrierPhoneBill1m = null; //话费金额:近1月（从申请上月起）
			Double carrierPhoneBill3m = null; //话费金额:近3月（从申请上月起）
			Double carrierSmsCnt3m = null; //短信数:近3月（从申请上月起）
			Double carrierSmsCnt5m = null; //短信数:近5月（从申请上月起）
			Double carrierMaxCallTime = null; //近5月（从申请上月起），最大平均呼出时间
			Double carrierPhoneBill5m = null;  //话费金额:近5月（从申请上月起）
			Double carrierCallInCnt3m = null; //呼入次数:近3月（从申请上月起）
			Double carrierCallInCnt5m = null; //呼入次数:近5月（从申请上月起）
			Double carrierCallOutCnt5m = null; //近5月（从申请上月起）:呼出次数
			Double callOutCnt5m = null; //近5月（从申请上月起）:通话次数
			Double carrierCallInCnt1m = null; //呼入次数:近1月（从申请上月起）/近5月（从申请上月起
			Double carrierMinCallInTime5m = null; //近5月（从申请上月起），最小月呼入时间
			Double carrierCallCnt1mRatio = null; // 通话次数:近1月（从申请上月起）
			Double carrierCallCnt3mRatio = null; // 通话次数:近3月（从申请上月起）
			Double carrierCallInOutCnt3mRatioCallCountHc = null;//近3月（从申请上月起）:呼出次数
			Double carrierCallInTime1mRatioCallTime = null;//呼入时长:近1月（从申请上月起）
			Double carrierCallInTime3mRatioCallTime = null;//呼入时长:近3月（从申请上月起）
			Double carrierCallInTime6mRatioCallTime = null;//呼入时长:近5月（从申请上月起）
			Double carrierMinCallInCnt6m = null;//近5月（从申请上月起）:最小呼入次数
			
			//int size = mnoMonthUsedInfos.size();
			for(int i = 0; i < size; i ++){
				BqsRepMonthUsed bqsRepMonthUsed = new BqsRepMonthUsed();
				MnoMonthUsedInfos usedInfo = usedInfosMap.get(keyList.get(i));
				if(null == usedInfo){
					continue;
				}

				BeanUtils.copyProperties(usedInfo, bqsRepMonthUsed);
				bqsRepMonthUsed.setBqsRepMonthUsedId(UUIDUtils.getUUID());
				bqsRepMonthUsed.setBqsPetitionerId(pid);
				loans.add(bqsRepMonthUsed);

				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {

					this.bqsRepMonthUsedServiceDao.saveAllEntity(loans);
					loans.clear();
				}
			}

			//处理1个月的值

			for(String month : monthSpace1m){
				MnoMonthUsedInfos bqsRepMonthUsed = usedInfosMap.get(month);
				if(null != bqsRepMonthUsed){
					callTimeTotalVsOut1m = this.countDouble(callTimeTotalVsOut1m,bqsRepMonthUsed.getCallTime());
					originatingCallTimeTotalVsOut1m = this.countDouble(originatingCallTimeTotalVsOut1m,bqsRepMonthUsed.getOriginatingCallTime());
					callCountTotalVsOut1m = this.countDouble(callCountTotalVsOut1m,bqsRepMonthUsed.getTerminatingCallCount());
					originatingCallCountTotalVsOut1m = this.countDouble(originatingCallCountTotalVsOut1m,bqsRepMonthUsed.getOriginatingCallCount());
					carrierPhoneBill1m = this.countDouble(carrierPhoneBill1m,bqsRepMonthUsed.getCostMoney());
					carrierCallInCnt1m = this.countDouble(carrierCallInCnt1m,bqsRepMonthUsed.getTerminatingCallCount());
					carrierCallCnt1mRatio = this.countDouble(carrierCallCnt1mRatio,bqsRepMonthUsed.getCallCount());
					carrierCallInTime1mRatioCallTime = this.countDouble(carrierCallInTime1mRatioCallTime, bqsRepMonthUsed.getTerminatingCallTime());
				}
			}

			//处理3个月的值
			for(String month : monthSpace3ms){
				MnoMonthUsedInfos bqsRepMonthUsed = usedInfosMap.get(month);
				if(null != bqsRepMonthUsed){
					carrierPhoneBill3m = this.countDouble(carrierPhoneBill3m,bqsRepMonthUsed.getCostMoney());
					carrierSmsCnt3m = this.countDouble(carrierSmsCnt3m,bqsRepMonthUsed.getMsgCount());
					carrierCallInCnt3m = this.countDouble(carrierCallInCnt3m,bqsRepMonthUsed.getTerminatingCallCount());
					sumCallTime = this.countDouble(sumCallTime,bqsRepMonthUsed.getOriginatingCallTime());//禁止项:3月主叫时间
					sumCount = this.countDouble(sumCount,bqsRepMonthUsed.getTerminatingCallCount()); //禁止项:3月被叫次数
					carrierCallCnt3mRatio = this.countDouble(carrierCallCnt3mRatio,bqsRepMonthUsed.getCallCount()); 
					carrierCallInOutCnt3mRatioCallCountHc = this.countDouble(carrierCallInOutCnt3mRatioCallCountHc, bqsRepMonthUsed.getOriginatingCallCount());
					carrierCallInTime3mRatioCallTime = this.countDouble(carrierCallInTime3mRatioCallTime, bqsRepMonthUsed.getTerminatingCallTime());
				}
			}
			//处理5个月的值
			for(String month : monthSpace5ms){
				MnoMonthUsedInfos bqsRepMonthUsed = usedInfosMap.get(month);
				if(null != bqsRepMonthUsed){
					carrierMaxPhoneBill5m = this.getDoubleMaxIn5m(carrierMaxPhoneBill5m,bqsRepMonthUsed.getCostMoney());
					carrierSmsCnt5m = this.countDouble(carrierSmsCnt5m,bqsRepMonthUsed.getMsgCount());
					carrierMaxCallTime = this.getDoubleMaxIn5m(carrierMaxCallTime,bqsRepMonthUsed.getAvgCallOutTime());
					carrierPhoneBill5m = this.countDouble(carrierPhoneBill5m,bqsRepMonthUsed.getCostMoney());
					carrierCallInCnt5m = this.countDouble(carrierCallInCnt5m,bqsRepMonthUsed.getTerminatingCallCount());
					carrierCallOutCnt5m = this.countDouble(carrierCallOutCnt5m,bqsRepMonthUsed.getOriginatingCallCount());
					callOutCnt5m = this.countDouble(callOutCnt5m,bqsRepMonthUsed.getCallCount());
					carrierMinCallInTime5m = this.getCarrierMin5m(carrierMinCallInTime5m,bqsRepMonthUsed.getTerminatingCallTime());
					sumMoney = this.countDouble(sumMoney,bqsRepMonthUsed.getCostMoney());
					sumSixCallTime = this.countDouble(sumSixCallTime,bqsRepMonthUsed.getTerminatingCallTime());
					carrierCallInTime6mRatioCallTime = this.countDouble(carrierCallInTime6mRatioCallTime, bqsRepMonthUsed.getTerminatingCallTime());
					carrierMinCallInCnt6m = this.getCarrierMin5m(carrierMinCallInCnt6m,bqsRepMonthUsed.getTerminatingCallCount());
				}
			}


			//reportRedis数据处理
			reportRedis.setCarrierMaxPhoneBill6m(String.valueOf(carrierMaxPhoneBill5m)); //近5月（从申请上月起），最大月话费金额
			reportRedis.setCarrierCallTimeTotal1m(String.valueOf(callTimeTotalVsOut1m)); //近1月（从申请上月起）:通话时间(分子)
			reportRedis.setCarrierCallTimeTotalOut1m(String.valueOf(originatingCallTimeTotalVsOut1m)); //近1月（从申请上月起）:呼出时间（分母）
			reportRedis.setCarrierCallInCnt1m(String.valueOf(callCountTotalVsOut1m));   //近1月（从申请上月起）:呼入次数(分子)
			reportRedis.setCarrierCallOutCnt1m(String.valueOf(originatingCallCountTotalVsOut1m));  //近1月（从申请上月起）:呼出次数（分母）
			reportRedis.setCarrierPhoneBill1mRatio(String.valueOf(carrierPhoneBill1m)); // 话费金额:近1月（从申请上月起）（分子）
			reportRedis.setCarrierPhoneBill3mRatio(String.valueOf(carrierPhoneBill3m)); // 话费金额:近3月（从申请上月起）（分母）
			reportRedis.setCarrierSmsCnt3m(String.valueOf(carrierSmsCnt3m)); // 短信数:近3月（从申请上月起）（分子）
			reportRedis.setCarrierSmsCnt6m(String.valueOf(carrierSmsCnt5m)); // 短信数:近5月（从申请上月起）（分母）
			reportRedis.setCarrierMaxCallOutAvgTime6m(String.valueOf(carrierMaxCallTime));// 近5月（从申请上月起），最大平均呼出时间
			reportRedis.setCarrierPhoneBill3m(String.valueOf(carrierPhoneBill3m));  //话费金额: 近3月（从申请上月起）（分子）
			reportRedis.setCarrierPhoneBill6m(String.valueOf(carrierPhoneBill5m));  //话费金额: 近5月（从申请上月起）（分母）
			reportRedis.setCarrierCallInCnt3mRatio(String.valueOf(carrierCallInCnt3m)); //呼入次数:近3月（从申请上月起）（分子）
			reportRedis.setCarrierCallInCnt6mRatio(String.valueOf(carrierCallInCnt5m)); //呼入次数:近5月（从申请上月起）（分母）
			reportRedis.setCarrierCallOutCntCallOutTimes(String.valueOf(carrierCallOutCnt5m)); //近5月（从申请上月起）:呼出次数（分子）
			reportRedis.setCarrierCallOutCntCallTimes(String.valueOf(callOutCnt5m));   //近5月（从申请上月起）:通话次数（分母）
			reportRedis.setCarrierCallInCnt1mCallOutTimes(String.valueOf(carrierCallInCnt1m)); // 呼入次数: 近1月（从申请上月起）（分子）
			reportRedis.setCarrierCallInCnt6mCallOutTimes(String.valueOf(carrierCallInCnt5m)); // 呼入次数: 近5月（从申请上月起）（分母）
			reportRedis.setCarrierMinCallInTime6m(String.valueOf(carrierMinCallInTime5m)); //近5月（从申请上月起），最小月呼入时间
			reportRedis.setCarrierCallCnt1mRatio(String.valueOf(carrierCallCnt1mRatio));//通话次数:近1月（从申请上月起）(分子)
			reportRedis.setCarrierCallCnt3mRatio(String.valueOf(carrierCallCnt3mRatio));//通话次数:近3月（从申请上月起）(分母)
			reportRedis.setCarrierMinCallInCnt6m(String.valueOf(carrierMinCallInCnt6m));
			reportRedis.setCarrierCallInOutCnt3mRatioCallCountHc(String.valueOf(carrierCallInOutCnt3mRatioCallCountHc));
			reportRedis.setCarrierCallInTime1mRatioCallTime(String.valueOf(carrierCallInTime1mRatioCallTime));
			reportRedis.setCarrierCallInTime3mRatioCallTime(String.valueOf(carrierCallInTime3mRatioCallTime));
			reportRedis.setCarrierCallInTime6mRatioCallTime(String.valueOf(carrierCallInTime6mRatioCallTime));
			BigDecimal bd = new BigDecimal(sumMoney);
			@SuppressWarnings("static-access")
			BigDecimal setScale = bd.setScale(2, bd.ROUND_DOWN);
			
			//添加禁止项数据,6个月的消费总金额
			BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_SIX_MONEY,setScale);
			//添加禁止项数据,3个月的主叫时间
			if (sumCallTime.doubleValue() == 0) {
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_THREE_TOTALTIME,sumCallTime);
			}else{
				sumCallTime = sumCallTime/60;
				BigDecimal callTime = new BigDecimal(sumCallTime);
				@SuppressWarnings("static-access")
				BigDecimal threeCallTime = callTime.setScale(2, callTime.ROUND_DOWN);
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_THREE_TOTALTIME,threeCallTime);
			}
			
			//添加禁止项数据,近6个月平均被叫时长(每月):分钟
			if (sumSixCallTime.doubleValue() == 0) {
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_THREE_TOTALTIME,sumSixCallTime);
			}else{
				avgCallTime = sumSixCallTime/300;
				BigDecimal callTime = new BigDecimal(avgCallTime);
				@SuppressWarnings("static-access")
				BigDecimal sixCallTime = callTime.setScale(2, callTime.ROUND_DOWN);
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_SIX_TIME,sixCallTime);
			}
			
			//添加禁止项数据,近3个月平均被叫次数(每月):次
			if (sumCount.doubleValue() == 0) {
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_THREE_MEANNUMBER,sumCount);
			}else{
				avgCount = sumCount/3;
				BigDecimal threeAvgCount = new BigDecimal(avgCount);
				@SuppressWarnings("static-access")
				BigDecimal threeCount = threeAvgCount.setScale(2, threeAvgCount.ROUND_DOWN);
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_THREE_MEANNUMBER,threeCount);
			}


		} catch (BeansException e) {
			logger.error("白骑士报告月使用信息入库异常"+e.getMessage(), e);
			throw new RuntimeException("白骑士报告月使用信息入库异常："+e.getMessage(), e);
		}
	}


	/**
	 * 处理日期时间
	 * @param dateList
	 * @param usedInfosMap
	 * @param mnoMonthUsedInfos
	 * @param monthSpace1m
	 * @param monthSpace3ms
     * @param monthSpace5ms
     */
	private void handlerMnoMonthUsedInfos(List<String> dateList, Map<String, MnoMonthUsedInfos> usedInfosMap, List<MnoMonthUsedInfos> mnoMonthUsedInfos, List<String> monthSpace1m, List<String> monthSpace3ms, List<String> monthSpace5ms,String nowDate) {
		for(MnoMonthUsedInfos usedInfo : mnoMonthUsedInfos){
			dateList.add(usedInfo.getMonth());
			usedInfosMap.put(usedInfo.getMonth(),usedInfo);

			int monthSpace = this.getMonthSpace(nowDate,usedInfo.getMonth());
			if(1 == monthSpace){ //相差1个月
				monthSpace1m.add(usedInfo.getMonth()) ;
			}
			if(1<=monthSpace && monthSpace<=3){  //相差3个月内
				monthSpace3ms.add(usedInfo.getMonth());
			}
			if(1<=monthSpace && monthSpace<=5){  //相差5个月内
				monthSpace5ms.add(usedInfo.getMonth());
			}
		}
	}

	private int getMonthSpace(String nowDate, String month) {
		int monthSpace = 0;
		try {
			Date date1 = DateUtils.convertStringToDate("yyyy-MM",nowDate);
			Date date2 = DateUtils.convertStringToDate("yyyy-MM",month);
			monthSpace = DateUtils.getMonthSpace(date2,date1);
		} catch (ParseException e) {
			logger.error("白骑士日期转换失败 [nowDate=" + nowDate + "],[month=" + month + "]", e);
		}
		return monthSpace;
	}

	/**
	 * 比较最大的月份金额
	 * @param carrierMax5m
	 * @param costMoney
	 * @return
	 */
	private Double getDoubleMaxIn5m(Double carrierMax5m, String costMoney) {
		if(StringUtils.isNotEmpty(costMoney)){
			if(carrierMax5m != null && (Double.parseDouble(costMoney)>carrierMax5m)){
				carrierMax5m = Double.parseDouble(costMoney);
			}else if(carrierMax5m == null){
				carrierMax5m = Double.parseDouble(costMoney);
			}

		}
		return carrierMax5m;
	}


	private Double getCarrierMin5m(Double firstParms, String secondParam) {
		if(StringUtils.isNotEmpty(secondParam)){
			if(firstParms != null && (Double.parseDouble(secondParam)<firstParms)){
				firstParms = Double.parseDouble(secondParam);
			}else if(firstParms == null){
				firstParms = Double.parseDouble(secondParam);
			}
		}
		return firstParms;
	}


	private void handlerMnoMonthUsedInfos(List<String> dateStr, Map<String, MnoMonthUsedInfos> usedInfosMap, List<MnoMonthUsedInfos> mnoMonthUsedInfos) {
		for(MnoMonthUsedInfos usedInfo : mnoMonthUsedInfos){
			dateStr.add(usedInfo.getMonth());
			usedInfosMap.put(usedInfo.getMonth(),usedInfo);
		}
	}


	/** 新版分时间段统计数据**/
	private void saveMnoPeriodUsedInfosNew(List<MnoPeriodUsedInfosNew> mnoPeriodUsedInfosNew, String pid,BqsReportMergeRedis reportRedis) {
		try {
			if(mnoPeriodUsedInfosNew==null){
				return;
			}
			List<BqsRepMnoPeriodUsedNew> loans = new ArrayList<BqsRepMnoPeriodUsedNew>();
			int size = mnoPeriodUsedInfosNew.size();
			//初始化数据
			Double inTime03 = null;
			Double inCallCount03 = null;
			Double outTime02 = null;
			Double outCallCount02 = null;
			Double carrierCallCnt01 = null;
			Double carrierCallCntTotal = null;
			Double carrierCallInAvgTime05CallTime = null;
			Double carrierCallInAvgTime05CallCount = null;
			Double carrierCallInTime01CallTime = null;
			Double carrierCallOutCnt01CallCount = null;//23:30 ~ 05:59呼出次数
			Double carrierCallOutCnt01CallCountAll = null;//总呼出次数
			Double carrierSmsCnt01 = null;
			Double sum_terminatingCallCount = null;  // 23:30 ~ 05:59  terminatingCallCount呼出次数
			
			for(int i = 0; i < mnoPeriodUsedInfosNew.size(); i ++){
				BqsRepMnoPeriodUsedNew bqsRepMnoPeriodUsedNew = new BqsRepMnoPeriodUsedNew();
				BeanUtils.copyProperties(mnoPeriodUsedInfosNew.get(i), bqsRepMnoPeriodUsedNew);
				bqsRepMnoPeriodUsedNew.setBqsMnoPeriodUsedNewId(UUIDUtils.getUUID());
				bqsRepMnoPeriodUsedNew.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoPeriodUsedNew);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsRepMnoPeriodUsedNewServiceDao.saveAllEntity(loans);
					loans.clear();
				}

				//时间段：08:30 ~ 11:59
				if("08:30 ~ 11:59".equals(bqsRepMnoPeriodUsedNew.getPeriodType())){
					inTime03 = this.countDouble(inTime03,bqsRepMnoPeriodUsedNew.getTerminatingCallTime());
					inCallCount03 = this.countDouble(inCallCount03,bqsRepMnoPeriodUsedNew.getTerminatingCallCount());
				}

				//时间段：06:00 ~ 08:29
				if("06:00 ~ 08:29".equals(bqsRepMnoPeriodUsedNew.getPeriodType())){
					outTime02 = this.countDouble(outTime02,bqsRepMnoPeriodUsedNew.getOriginatingCallTime());
					outCallCount02 = this.countDouble(outCallCount02,bqsRepMnoPeriodUsedNew.getOriginatingCallCount());
				}
				//时间段：23:30 ~ 05:59
				if("23:30 ~ 05:59".equals(bqsRepMnoPeriodUsedNew.getPeriodType())){
					carrierCallCnt01 = this.countDouble(carrierCallCnt01,bqsRepMnoPeriodUsedNew.getTerminatingCallCount());
					carrierCallCnt01 = this.countDouble(carrierCallCnt01,bqsRepMnoPeriodUsedNew.getOriginatingCallCount());
					carrierCallInTime01CallTime = this.countDouble(carrierCallInTime01CallTime, bqsRepMnoPeriodUsedNew.getTerminatingCallTime());
					carrierCallOutCnt01CallCount = this.countDouble(carrierCallOutCnt01CallCount, bqsRepMnoPeriodUsedNew.getOriginatingCallCount());
					carrierSmsCnt01 = this.countDouble(carrierSmsCnt01,bqsRepMnoPeriodUsedNew.getMsgCount());
					sum_terminatingCallCount = this.countDouble(sum_terminatingCallCount, bqsRepMnoPeriodUsedNew.getTerminatingCallCount());   // 2.0
					
				}
				//时间段：14:00 ~ 17:59
				if("14:00 ~ 17:59".equals(bqsRepMnoPeriodUsedNew.getPeriodType())){
					carrierCallInAvgTime05CallTime = this.countDouble(carrierCallInAvgTime05CallTime,bqsRepMnoPeriodUsedNew.getTerminatingCallTime());
					carrierCallInAvgTime05CallCount = this.countDouble(carrierCallInAvgTime05CallCount,bqsRepMnoPeriodUsedNew.getTerminatingCallCount());
				}
				carrierCallCntTotal = this.countDouble(carrierCallCntTotal,bqsRepMnoPeriodUsedNew.getTerminatingCallCount());
				carrierCallCntTotal = this.countDouble(carrierCallCntTotal,bqsRepMnoPeriodUsedNew.getOriginatingCallCount());
				carrierCallOutCnt01CallCountAll = this.countDouble(carrierCallOutCnt01CallCountAll, bqsRepMnoPeriodUsedNew.getOriginatingCallCount());
				
			}

			//处理数据
			reportRedis.setCarrierCallInAvgTimeM(String.valueOf(inTime03)); //分子
			reportRedis.setCarrierCallInAvgTimeD(String.valueOf(inCallCount03)); //分母
			reportRedis.setCarrierCallOutAvgTime02(String.valueOf(outTime02));//分子
			reportRedis.setCarrierCallOutAvgTimes02(String.valueOf(outCallCount02));//分母
			reportRedis.setCarrierCallCnt01(String.valueOf(carrierCallCnt01));//23:30 ~ 05:59通话次数
			reportRedis.setCarrierCallCntTotal(String.valueOf(carrierCallCntTotal));//总通话次数
			reportRedis.setCarrierCallInAvgTime05CallTime(String.valueOf(carrierCallInAvgTime05CallTime));
			reportRedis.setCarrierCallInAvgTime05CallCount(String.valueOf(carrierCallInAvgTime05CallCount));
			reportRedis.setCarrierSmsCnt01(String.valueOf(carrierSmsCnt01));
			reportRedis.setCarrierCallInTime01CallTime(String.valueOf(carrierCallInTime01CallTime));
			reportRedis.setCarrierCallOutCnt01CallCount(String.valueOf(carrierCallOutCnt01CallCount));
			reportRedis.setCarrierCallOutCnt01CallCountAll(String.valueOf(carrierCallOutCnt01CallCountAll));
			reportRedis.setCarrierCallInCnt01VsTotalSumTerminatingCallCount(String.valueOf(sum_terminatingCallCount));  // 2.0   分子
			reportRedis.setCarrierCallInCnt01VsTotalSumOriginatingCallCount(String.valueOf(carrierCallOutCnt01CallCountAll)); // 2.0 分母
			
		} catch (BeansException e) {
			e.printStackTrace();
			logger.error("白骑士报告新版分时间段统计数据入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告新版分时间段统计数据入库异常："+e.getMessage());
		}
	}




	/** 分时间段统计数据**/
	private void saveMnoPeriodUsedInfos(List<MnoPeriodUsedInfos> mnoPeriodUsedInfos, String pid) {
		try {
			if(mnoPeriodUsedInfos==null){
				return;
			}
			List<BqsRepMnoPeriodUsed> loans = new ArrayList<BqsRepMnoPeriodUsed>();
			int size = mnoPeriodUsedInfos.size();
			for(int i = 0; i < mnoPeriodUsedInfos.size(); i ++){
				BqsRepMnoPeriodUsed bqsRepMnoPeriodUsed = new BqsRepMnoPeriodUsed();
				BeanUtils.copyProperties(mnoPeriodUsedInfos.get(i), bqsRepMnoPeriodUsed);
				bqsRepMnoPeriodUsed.setBqsMnoPeriodUsedId(UUIDUtils.getUUID());
				bqsRepMnoPeriodUsed.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoPeriodUsed);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsRepMnoPeriodUsedServiceDao.saveAllEntity(loans);
					loans.clear();
				}
			}
		} catch (BeansException e) {
			e.printStackTrace();
			logger.error("白骑士报告分时间段统计数据入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告分时间段统计数据入库异常："+e.getMessage());
		}
	}


	/** 家庭联系人信息**/
	private void saveUserInfoValidation(UserInfoValidation userInfoValidation, String pid) {
		try {
			if (userInfoValidation == null) {
				return;
			}
			HomePhoneCheck homePhoneCheck = userInfoValidation.getHomePhoneCheck();
			if (homePhoneCheck == null) {
				return;
			}
			BqsRepHomeCheck bqsRepHomeCheck = new BqsRepHomeCheck();
			BeanUtils.copyProperties(homePhoneCheck, bqsRepHomeCheck);
			bqsRepHomeCheck.setBqsHomeCheckId(UUIDUtils.getUUID());
			bqsRepHomeCheck.setBqsPetitionerId(pid);
			this.bqsRepHomeCheckServiceDao.saveEntity(bqsRepHomeCheck);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("白骑士报告家庭联系人数据入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告家庭联系人数据入库异常："+e.getMessage());
		}	
	}


	/** 常用联系电话（近3个月)**/
	private void saveMnoThreeMonthCommonlyConnectMobiles(List<MnoThreeMonthCommonlyConnectMobiles> mnoThreeMonthCommonlyConnectMobiles, String pid) {
		try {
			if(mnoThreeMonthCommonlyConnectMobiles==null){
				return;
			}
			List<BqsRepMnoThreeMobile> loans = new ArrayList<BqsRepMnoThreeMobile>();
			int size = mnoThreeMonthCommonlyConnectMobiles.size();
			for(int i = 0; i < mnoThreeMonthCommonlyConnectMobiles.size(); i ++){
				BqsRepMnoThreeMobile bqsRepMnoThreeMobile = new BqsRepMnoThreeMobile();
				BeanUtils.copyProperties(mnoThreeMonthCommonlyConnectMobiles.get(i), bqsRepMnoThreeMobile);
				bqsRepMnoThreeMobile.setBqsMnoThreeInfoId(UUIDUtils.getUUID());
				bqsRepMnoThreeMobile.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoThreeMobile);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsRepMnoThreeMobileServiceDao.saveAllEntity(loans);
					loans.clear();
				}
			}
		} catch (BeansException e) {
			e.printStackTrace();
			logger.error("白骑士报告常用联系人（近3个月)数据入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告常用联系人（近3个月)数据入库异常："+e.getMessage());
		}
	}
	/** 常用联系电话（近3天)**/
	private void saveMno3daysCommonlyConnectMobiles(List<Mno3daysCommonlyConnectMobiles> mno3daysCommonlyConnectMobiles,String pid,BqsReportMergeRedis reportRedis) {
		try {
			if(mno3daysCommonlyConnectMobiles==null){
				return;
			}
			List<BqsRepMnoThreeDayMobile> loans = new ArrayList<BqsRepMnoThreeDayMobile>();
			int size = mno3daysCommonlyConnectMobiles.size();
			//初始化记录数值
			Double timeCount = null;
			Double carrierCallInCnt3d = null;//三天 求和
			for(int i = 0; i < mno3daysCommonlyConnectMobiles.size(); i ++){
				BqsRepMnoThreeDayMobile bqsRepMnoThreeDayMobile = new BqsRepMnoThreeDayMobile();
				BeanUtils.copyProperties(mno3daysCommonlyConnectMobiles.get(i), bqsRepMnoThreeDayMobile);
				bqsRepMnoThreeDayMobile.setBqsMnoThreeDayInfoId(UUIDUtils.getUUID());
				bqsRepMnoThreeDayMobile.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoThreeDayMobile);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsRepMnoThreeDayMobileServiceDao.saveAllEntity(loans);
					loans.clear();
				}
				timeCount = this.countDouble(timeCount,bqsRepMnoThreeDayMobile.getOriginatingTime());
				carrierCallInCnt3d = this.countDouble(carrierCallInCnt3d, bqsRepMnoThreeDayMobile.getTerminatingCallCount());
				reportRedis.setCarrierCallOutTime3d(String.valueOf(timeCount));
				reportRedis.setCarrierCallInCnt3d(String.valueOf(carrierCallInCnt3d));
			}
		} catch (BeansException e) {
			e.printStackTrace();
			logger.error("白骑士报告常用联系人（近3天)数据入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告常用联系人（近3天)数据入库异常："+e.getMessage());
		}
	}


	/** 常用联系电话（近6个月)**/
	private void saveMnoCommonlyConnectMobiles(List<MnoCommonlyConnectMobiles> mnoCommonlyConnectMobiles, String pid) {
		try {
			if(mnoCommonlyConnectMobiles==null){
				return;
			}
			List<BqsRepMnoSixMobile> loans = new ArrayList<BqsRepMnoSixMobile>();
			int size = mnoCommonlyConnectMobiles.size();
			for(int i = 0; i < mnoCommonlyConnectMobiles.size(); i ++){
				BqsRepMnoSixMobile bqsRepMnoSixMobile = new BqsRepMnoSixMobile();
				BeanUtils.copyProperties(mnoCommonlyConnectMobiles.get(i), bqsRepMnoSixMobile);
				bqsRepMnoSixMobile.setBqsMnoSixInfoId(UUIDUtils.getUUID());
				bqsRepMnoSixMobile.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoSixMobile);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsRepMnoSixMobileServiceDao.saveAllEntity(loans);
					loans.clear();
				}
			}
		} catch (BeansException e) {
			e.printStackTrace();
			logger.error("白骑士报告常用联系人（近6个月)数据入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告常用联系人（近6个月)数据入库异常："+e.getMessage());
		}
	}


	/** 常用联系电话（近7天)**/
	private void saveMno7daysCommonlyConnectMobiles(List<Mno7daysCommonlyConnectMobiles> mno7daysCommonlyConnectMobiles,String pid,BqsReportMergeRedis reportRedis) {
		try {
			if(mno7daysCommonlyConnectMobiles==null){
				return;
			}
			List<BqsRepMnoSevenDayMobile> loans = new ArrayList<BqsRepMnoSevenDayMobile>();
			int size = mno7daysCommonlyConnectMobiles.size();
			Double carrierCallInCnt7d = null;//七天 求和
			for(int i = 0; i < mno7daysCommonlyConnectMobiles.size(); i ++){
				BqsRepMnoSevenDayMobile bqsRepMnoSevenDayMobile = new BqsRepMnoSevenDayMobile();
				BeanUtils.copyProperties(mno7daysCommonlyConnectMobiles.get(i), bqsRepMnoSevenDayMobile);
				bqsRepMnoSevenDayMobile.setBqsMnoSevenDayInfoId(UUIDUtils.getUUID());
				bqsRepMnoSevenDayMobile.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoSevenDayMobile);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					this.bqsRepMnoSevenDayMobileServiceDao.saveAllEntity(loans);
					loans.clear();
				}
				carrierCallInCnt7d = this.countDouble(carrierCallInCnt7d, bqsRepMnoSevenDayMobile.getTerminatingCallCount());
			}
			//上传欺诈项
			reportRedis.setCarrierCallInCnt7d(String.valueOf(carrierCallInCnt7d));
		} catch (BeansException e) {
			e.printStackTrace();
			logger.error("白骑士报告常用联系人（近7天)数据入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告常用联系人（近7天)数据入库异常："+e.getMessage());
		}
	}


	/** 常用联系电话（近1个月)**/
	private void saveMnoOneMonthCommonlyConnectMobiles(
			List<MnoOneMonthCommonlyConnectMobiles> mnoOneMonthCommonlyConnectMobiles, String pid) {
		try {
			if(mnoOneMonthCommonlyConnectMobiles==null){
				return;
			}
			List<BqsRepMnoOneMobile> loans = new ArrayList<BqsRepMnoOneMobile>();
			int size = mnoOneMonthCommonlyConnectMobiles.size();
			for(int i = 0; i < mnoOneMonthCommonlyConnectMobiles.size(); i ++){
				BqsRepMnoOneMobile bqsRepMnoOneMobile = new BqsRepMnoOneMobile();
				BeanUtils.copyProperties(mnoOneMonthCommonlyConnectMobiles.get(i), bqsRepMnoOneMobile);
				bqsRepMnoOneMobile.setBqsMnoOneInfoId(UUIDUtils.getUUID());
				bqsRepMnoOneMobile.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoOneMobile);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsRepMnoOneMobileServiceDao.saveAllEntity(loans);
					loans.clear();
				}
			}
		} catch (BeansException e) {
			e.printStackTrace();
			logger.error("白骑士报告常用联系人（近1个月)数据入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告常用联系人（近1个月)数据入库异常："+e.getMessage());
		}
		 
	}


	/** 常用联系电话额外**/
	private void saveMnoConnectMobilesExt(List<MnoConnectMobilesExt> mnoConnectMobilesExt, String pid) {
		try {
			if(mnoConnectMobilesExt==null){
				return;
			}
			List<BqsRepMnoExtMobile> loans = new ArrayList<BqsRepMnoExtMobile>();
			int size = mnoConnectMobilesExt.size();
			for(int i = 0; i < mnoConnectMobilesExt.size(); i ++){
				BqsRepMnoExtMobile bqsRepMnoExtMobile = new BqsRepMnoExtMobile();
				BeanUtils.copyProperties(mnoConnectMobilesExt.get(i), bqsRepMnoExtMobile);
				bqsRepMnoExtMobile.setBqsMnoSevenDayInfoId(UUIDUtils.getUUID());
				bqsRepMnoExtMobile.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoExtMobile);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsRepMnoExtMobileServiceDao.saveAllEntity(loans);
					loans.clear();
				}
			}
		} catch (BeansException e) {
			e.printStackTrace();
			logger.error("白骑士报告常用联系电话额外数据入库异常"+e.getMessage());
			throw new RuntimeException("白骑士报告常用联系电话额外数据入库异常："+e.getMessage());
		}
		
	}


	/** 联系人通话活动地区**/
	private void saveMnoContactsCommonlyConnectAreas(List<MnoContactsCommonlyConnectAreas> mnoContactsCommonlyConnectAreas, String pid,BqsReportMergeRedis reportRedis) {
		try {
			if(mnoContactsCommonlyConnectAreas==null){
				return;
			}
			List<BqsRepMnoContactAreas> loans = new ArrayList<BqsRepMnoContactAreas>();
			int size = mnoContactsCommonlyConnectAreas.size();

			//初始化数据
			int allCount = 0;       //总次数
			int blackCityCOunt = 0; //黑名单次数
			Set<String> list = new HashSet<String>();//area 去重计数

			for(int i = 0; i < mnoContactsCommonlyConnectAreas.size(); i ++){
				BqsRepMnoContactAreas bqsRepMnoContactAreas = new BqsRepMnoContactAreas();
				BeanUtils.copyProperties(mnoContactsCommonlyConnectAreas.get(i), bqsRepMnoContactAreas);
				bqsRepMnoContactAreas.setBqsRepMnoAreasId(UUIDUtils.getUUID());
				bqsRepMnoContactAreas.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoContactAreas);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsRepMnoContactAreasServiceDao.saveAllEntity(loans);
					loans.clear();
				}

				allCount = countCall(allCount,bqsRepMnoContactAreas.getNumberCount());
				if(StringUtils.isNotEmpty(bqsRepMnoContactAreas.getArea()) && BlackCityUtils.checkBlackCity(bqsRepMnoContactAreas.getArea(),BlackCityUtils.BLICKCITY_ARR)){  //校验是不是黑名单
					blackCityCOunt = countCall(blackCityCOunt,bqsRepMnoContactAreas.getNumberCount());
				}
				list.add(bqsRepMnoContactAreas.getArea());
			}

			//处理黑名单数据
			reportRedis.setCarrierContactAreaBlackCityCallCnt(String.valueOf(blackCityCOunt));//分子
			reportRedis.setCarrierContactCallCnt(String.valueOf(allCount)); //分母
			if (!CollectionUtils.isEmpty(list)) {
				reportRedis.setCarrierContactAreaNum(String.valueOf(list.size()));
			}
		} catch (BeansException e) {
			e.printStackTrace();
			throw new RuntimeException("白骑士报告 联系人通话活动地区入库异常："+e.getMessage());
		}
	}



	/** 本人通话活动地区**/
	private void saveMnoCommonlyConnectAreas(List<MnoCommonlyConnectAreas> mnoCommonlyConnectAreas, String pid) {
		try {
			if(mnoCommonlyConnectAreas==null){
				return;
			}
			List<BqsRepMnoAreas> loans = new ArrayList<BqsRepMnoAreas>();
			int size = mnoCommonlyConnectAreas.size();
			for(int i = 0; i < mnoCommonlyConnectAreas.size(); i ++){
				BqsRepMnoAreas bqsRepMnoAreas = new BqsRepMnoAreas();
				BeanUtils.copyProperties(mnoCommonlyConnectAreas.get(i), bqsRepMnoAreas);
				bqsRepMnoAreas.setBqsRepMnoAreasId(UUIDUtils.getUUID());
				bqsRepMnoAreas.setBqsPetitionerId(pid);
				loans.add(bqsRepMnoAreas);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsRepMnoAreasServiceDao.saveAllEntity(loans);
					loans.clear();
				}
				
			}
		} catch (BeansException e) {
			e.printStackTrace();
			throw new RuntimeException("白骑士报告 本人通话活动地区入库异常："+e.getMessage());
		}
	}
	/** 出行数据**/
	private void saveGoOutDatas(List<GoOutDatas> goOutDatas, String pid,BqsReportMergeRedis reportRedis) {
		try {
			if(CollectionUtils.isEmpty(goOutDatas)){
				return;
			}
			List<BqsRepGoOut> loans = new ArrayList<BqsRepGoOut>();
			int size = goOutDatas.size();
			//利用hashSet去重
			Set<String> list = new HashSet<String>();
			String period = null;
			int number = 0;
			int count = 0;
			for(int i = 0; i < goOutDatas.size(); i ++){
				BqsRepGoOut bqsRepGoOut = new BqsRepGoOut();
				BeanUtils.copyProperties(goOutDatas.get(i), bqsRepGoOut);
				bqsRepGoOut.setBqsRepGoOutId(UUIDUtils.getUUID());
				bqsRepGoOut.setBqsPetitionerId(pid);
				loans.add(bqsRepGoOut);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsGoOutServiceDao.saveAllEntity(loans);
					loans.clear();
				}
				//欺诈项:判断节假日
				period = bqsRepGoOut.getPeriod();
				if (null != period && period.contains("节假日")) {
					number = Integer.valueOf(period.substring(period.indexOf("节假日（")+4, period.lastIndexOf("天"))); 
					if (number>0) {
						list.add(bqsRepGoOut.getDestination());	
					}
				}
				if (StringUtils.isNotBlank(period)&&"至今".equals(period)) {
					count++;
				}
			}
			if (!CollectionUtils.isEmpty(list)) {
				reportRedis.setBqsGoOutAreaCntJjr(String.valueOf(list.size()));	
			}else if (count > 0) {
				reportRedis.setBqsGoOutAreaCntJjr("0");
			}
			
		} catch (BeansException e) {
			e.printStackTrace();
			throw new RuntimeException("白骑士报告  出行数据入库异常："+e.getMessage());
		}
		
	}
	/** 常用联系人**/
	private void saveCommonlyContacts(List<CommonlyContacts> commonlyContacts, String pid) {
		try {
			if(commonlyContacts==null){
				return;
			}
			List<BqsRepContactsInfo> loans = new ArrayList<BqsRepContactsInfo>();
			int size = commonlyContacts.size();
			for(int i = 0; i < commonlyContacts.size(); i ++){
				BqsRepContactsInfo bqsRepContactsInfo = new BqsRepContactsInfo();
				BeanUtils.copyProperties(commonlyContacts.get(i), bqsRepContactsInfo);
				bqsRepContactsInfo.setBqsRepContactsInfoId(UUIDUtils.getUUID());
				bqsRepContactsInfo.setBqsPetitionerId(pid);
				loans.add(bqsRepContactsInfo);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsContactsServiceDao.saveAllEntity(loans);
					loans.clear();
				}
			}
		} catch (BeansException e) {
			e.printStackTrace();
			throw new RuntimeException("白骑士报告  常用联系人异常："+e.getMessage());
		}
		
	}
	/** 紧急联系人
	 * @param checkList **/
	private void saveEmergencyContacts(List<EmergencyContacts> emergencyContacts,String pid, List<Map<String, Object>> checkList,BqsReportMergeRedis reportRedis) {
		try {
			if(emergencyContacts==null){
				return;
			}

			List<BqsRepContactsInfo> loans = new ArrayList<BqsRepContactsInfo>();
			int size = emergencyContacts.size();
			boolean callTime = false;

			//初始化
			int callOutCount = 0;
			Double callOutTime = null;
			Double callTime1m = null;

			for(int i = 0; i < emergencyContacts.size(); i ++){
				BqsRepContactsInfo bqsRepContactsInfo = new BqsRepContactsInfo();
				BeanUtils.copyProperties(emergencyContacts.get(i), bqsRepContactsInfo);
				if (StringUtils.isNotBlank(emergencyContacts.get(i).getLatestConnectTime())) {
					callTime =true;
				}

				bqsRepContactsInfo.setBqsRepContactsInfoId(UUIDUtils.getUUID());
				bqsRepContactsInfo.setBqsPetitionerId(pid);
				loans.add(bqsRepContactsInfo);
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					this.bqsContactsServiceDao.saveAllEntity(loans);
					loans.clear();
				}

				//处理计算数据
				callOutCount = this.countCall(callOutCount,bqsRepContactsInfo.getCallOutCount());
				callOutTime = this.countDouble(callOutTime,bqsRepContactsInfo.getCallOutTime());
				callTime1m = this.countDouble(callTime1m,bqsRepContactsInfo.getThirtyDaysConnectDuration());
			}

			//处理Redis数据
			reportRedis.setCarrierIcePersonCallOutCnt6m(String.valueOf(callOutCount));
			reportRedis.setCarrierIcePersonCallOutTime6m(String.valueOf(callOutTime));
			reportRedis.setCarrierIcePersonCallTime1m(String.valueOf(callTime1m));


			if (callTime) {
				//添加禁止项数据
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_PHONE_RECORD,"Yes");
			}else{
				//添加禁止项数据
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_PHONE_RECORD,"No");
			}
		} catch (BeansException e) {
			throw new RuntimeException("白骑士报告  紧急联系人异常："+e.getMessage(), e);
		}
	}


	/**
	 * 计算呼叫相关数据
	 * @param count
	 * @param countString
     * @return
     */
	private int countCall(int count, String countString) {

		if(StringUtils.isNotEmpty(countString)){
			count = count + Integer.parseInt(countString);
		}
		return count;
	}


	/**
	 * 计算呼叫相关数据double类型
	 * @param count
	 * @param countString
	 * @return
	 */
	private Double countDouble(Double count, String countString){

		if(StringUtils.isNotEmpty(countString)){
			if(count != null){
				count = count + Double.parseDouble(countString);
			}else{
				count = Double.parseDouble(countString);
			}
		}
		return count;
	}

	/** 反欺诈云**/
	private void saveAntiFraudCloud(BqsAntiFraudCloud bqsAntiFraudCloud, String pid,BqsReportMergeRedis reportRedis) {
		try {
			if(bqsAntiFraudCloud.getIdcCount() == null
					&& bqsAntiFraudCloud.getPartnerCount() == null
					&& bqsAntiFraudCloud.getPhoneCount() == null
					&& bqsAntiFraudCloud.getStarnetCount() == null) {
				return;
			}
			BqsRepAntiFraudCloud bqsRepAntiFraudCloud = new BqsRepAntiFraudCloud();
			BeanUtils.copyProperties(bqsAntiFraudCloud, bqsRepAntiFraudCloud);
			bqsRepAntiFraudCloud.setBqsAntiFraudCloudId(UUIDUtils.getUUID());
			bqsRepAntiFraudCloud.setBqsPetitionerId(pid);
			this.bqsCloudServiceDao.saveEntity(bqsRepAntiFraudCloud);
			//bqsMult3m partner_count字段
			reportRedis.setBqsMulti3m(bqsRepAntiFraudCloud.getPartnerCount());
			//redis数据整合
			reportRedis.setBqsStarnetCnt6m(bqsRepAntiFraudCloud.getStarnetCount());
		} catch (BeansException e) {
			throw new RuntimeException("白骑士报告  反欺诈云入库异常："+e.getMessage(), e);
		}
	}
	/**
	 * 信息来源
	 * @param dataSources
	 * @param pid
	 * @param checkList 
	 */
	private void saveDataSource(List<WebDataSources> dataSources, String pid, List<Map<String, Object>> checkList) {
		try {
			if(dataSources==null){
				return;
			}
			for (WebDataSources webDataSources : dataSources) {
		
				BqsRepDataSource bqsRepDataSource = new BqsRepDataSource();
				BeanUtils.copyProperties(webDataSources, bqsRepDataSource);				
				if (webDataSources.getPassRealName()) {
					//添加禁止项数据
					BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_REAL_NAME,"Yes");
				}else{
					//添加禁止项数据
					BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_REAL_NAME,"No");
				}
				if(webDataSources.getEqualToPetitioner()){
					//添加禁止项数据
					BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_NAME_MISMARCHING,"Yes");
				}else{
					//添加禁止项数据
					BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_NAME_MISMARCHING,"No");
				}
				bqsRepDataSource.setPassRealName(String.valueOf(webDataSources.getPassRealName()));
				bqsRepDataSource.setEqualToPetitioner(String.valueOf(webDataSources.getEqualToPetitioner()));
				bqsRepDataSource.setBqsDataSourceId(UUIDUtils.getUUID());
				bqsRepDataSource.setBqsPetitionerId(pid);
				this.bqsSourceServiceDao.saveEntity(bqsRepDataSource);
			}
		} catch (BeansException e) {
			throw new RuntimeException("白骑士报告  信息来源入库异常："+e.getMessage(), e);
		}
	}
	/** 插入高风险名单
	 * @param checkList **/
	private void saveRiskList(BqsHighRiskList bqsHighRiskList, String pid, List<Map<String, Object>> checkList) {
		try {
			if (bqsHighRiskList == null) {
				return;
			}
			List<HighRiskLists> list = bqsHighRiskList.getHighRiskLists();
			for (HighRiskLists highRisk : list) {
				BqsRepHighRisk bqsRepHighRisk = new BqsRepHighRisk();
				BeanUtils.copyProperties(highRisk, bqsRepHighRisk);
				
				if ("信贷行业".equals(bqsRepHighRisk.getFirstType())) {
					//添加禁止项数据
					BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_XINDAI_BLACK,"Yes");
				}
				if ("法院".equals(bqsRepHighRisk.getFirstType())) {
					//添加禁止项数据
					BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_FAYUAN_BLACK,"Yes");
				}
				if ("高风险".equals(bqsRepHighRisk.getRiskGrade())) {//高风险名单命中高风险
					//添加禁止项数据
					BanCodeUtil.addCheckPoint(checkList,BanCodeEnum.BAI_QI_SHI_CREDIT_HIGH_RISK.getCode(),"Yes");
				}
				bqsRepHighRisk.setBqsHighRiskId(UUIDUtils.getUUID());
				bqsRepHighRisk.setBqsPetitionerId(pid);
				this.bqsRiskServiceDao.saveEntity(bqsRepHighRisk);
			}
		} catch (BeansException e) {
			e.printStackTrace();
			throw new RuntimeException("白骑士报告插入高风险名单入库异常："+e.getMessage());
		}
	}

	/** 插入运营商基本信息**/
	private void saveMnoInfo(MnoBaseInfo mnoBaseInfo, String pid) {
		try {
			if (mnoBaseInfo == null) {
				return;
			}
			BqsRepMnoInfo bqsRepMnoInfo = new BqsRepMnoInfo();
			BeanUtils.copyProperties(mnoBaseInfo, bqsRepMnoInfo);
			bqsRepMnoInfo.setBqsMnoInfoId(UUIDUtils.getUUID());
			bqsRepMnoInfo.setBqsPetitionerId(pid);
			this.bqsInfoServiceDao.saveEntity(bqsRepMnoInfo);
		} catch (BeansException e) {
			e.printStackTrace();
			throw new RuntimeException("白骑士报告插入运营商基本信息入库异常："+e.getMessage());
		}
	}
	/** 用户行为，活跃程度，通话行为，特殊通话检测
	 * @param checkList **/
	private void saveCrossValidation(CrossValidation crossValidation, String pid, List<Map<String, Object>> checkList,BqsReportMergeRedis reportRedis) {
		try {
			if (crossValidation == null) {
				return;
			}

			//初始化redisRepot的数据
			reportRedis.setBqsPayCnt("0");

			String carrierSilentAvgDays = null;// 静默天数（分子）
			String carrierSilentAvgTimes = null; //静默次数（分母）

			String carrierPhoneUsedMonth = "0"; //入网月数
			String carrierSmsOutCnt6m = null; //半年发送短信条数
			String carrierMaxContactAreaRatio = "0"; //最频繁朋友圈通话时间占比

			String carrierSilentDays = null; // 静默天数（分子）
			String carrierSilentCntDays = null; // 总天数（分母）

			String carrierCallInAvgTimeNight = null; //夜间呼入时间(分子)
			String carrierCallInAvgCntNight = null;  //夜间呼入次数（分母）

			String carrierCallTotalCntTimeNight = null; //夜间通话时间（分子）
			String carrierCallTotalCntNight = null; //夜间通话次数（分母）

			String carrierCallInTimeNight = null; //夜间呼入时间
			String carrierCallTimeNight = null; //夜间通话时间

			Double carrierPhoneRealUsedMonth = 0.00; //号码有通话时长

 			String carrierCallInterflowNum = null;//互通电话号码个数
			String carrierCallCntNight = null; //夜间通话次数
			
			String carrierCallOutCntNight = null;//夜间呼出次数
			String carrierCallOutTimeRatioNight = null;//夜间通话(00:00 ~ 06:00)夜间呼出时长
			String carrierContactAvgCntHy = null;//朋友圈活跃度
			String carrierContactNum = null;//朋友圈大小
			
			String carrierCallOutCnt6m = null;  // 拨出电话号码的个数

			
			//正则使用
			Pattern pattern1 = Pattern.compile("：([0-9]*?)次");
			Pattern pattern2 = Pattern.compile("：(([0-9]|\\.)*?)分钟");
			Pattern pattern3 = Pattern.compile("([0-9]*?)次");
			Pattern pattern4 = Pattern.compile("在([\\s\\S]*?)时间段内");
			Pattern pattern5 = Pattern.compile("为([\\s\\S]*?)%");
			Pattern pattern6 = Pattern.compile("([0-9]*?)个");
			Pattern pattern7 = Pattern.compile("^*([0-9]{1,}[.][0-9]*)分钟");
			Pattern pattern8 = Pattern.compile("^([0-9]{1,}[.][0-9]*)元$");

			BqsRepCrossValidation bqsRepCrossValidation = null;
			//通话主要活动区域
			CallActiveArea callActiveArea = crossValidation.getCallActiveArea();
			if (callActiveArea != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(callActiveArea, bqsRepCrossValidation);
				String type = BQSConstant.CALLACTIVEAREA;
				saveValidation(bqsRepCrossValidation,pid,type);
			}
			//朋友圈活动区域   
			ContactsActiveArea contactsActiveArea = crossValidation.getContactsActiveArea();
			if (contactsActiveArea != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(contactsActiveArea, bqsRepCrossValidation);
				String type = BQSConstant.CONTACTSACTIVEAREA;
				saveValidation(bqsRepCrossValidation,pid,type);
				String temp = this.getmatcher(bqsRepCrossValidation.getEvidence(),pattern5);
				if(org.apache.commons.lang3.StringUtils.isNotEmpty(temp)){
					Double tempDb = Double.parseDouble(temp);
					tempDb = tempDb/100;
					carrierMaxContactAreaRatio = String.valueOf(tempDb);

				}
			}
			//通话主要活动区域与本人手机号归属地是否一致
			CallMobileBelongMatch callMobileBelongMatch = crossValidation.getCallMobileBelongMatch();
			if (callMobileBelongMatch != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(callMobileBelongMatch, bqsRepCrossValidation);
				String type = BQSConstant.CALLMOBILEBELONGMATCH;
				saveValidation(bqsRepCrossValidation,pid,type);
			}
			//通话主要活动区域与朋友圈活动区域是否一致
			CallContactsAreaMatch callContactsAreaMatch = crossValidation.getCallContactsAreaMatch();
			if (callContactsAreaMatch != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(callContactsAreaMatch, bqsRepCrossValidation);
				String type = BQSConstant.CALLCONTACTSAREAMATCH;
				saveValidation(bqsRepCrossValidation,pid,type);
			}
			//朋友圈大小
			ContactsSize contactsSize = crossValidation.getContactsSize();
			if (contactsSize != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(contactsSize, bqsRepCrossValidation);
				String type = BQSConstant.CONTACTSSIZE;
				saveValidation(bqsRepCrossValidation,pid,type);
				carrierContactNum = bqsRepCrossValidation.getResult();
			}

			//互通电话号码数
			ExchangeCallMobileCount exchangeCallMobileCount = crossValidation.getExchangeCallMobileCount();
			if (exchangeCallMobileCount != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(exchangeCallMobileCount, bqsRepCrossValidation);
				String type = BQSConstant.EXCHANGECALLMOBILECOUNT;
				saveValidation(bqsRepCrossValidation,pid,type);
				String result = bqsRepCrossValidation.getResult();
				String sub = result.substring(0, result.length()-1);
				//添加禁止项数据
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_PHONE_NUMBER,Integer.valueOf(sub));

				//处理缓存数据
				carrierCallInterflowNum =sub;
			}

			//朋友圈活跃度
			ContactsActiveDegree contactsActiveDegree = crossValidation.getContactsActiveDegree();
			if (contactsActiveDegree != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(contactsActiveDegree, bqsRepCrossValidation);
				String type = BQSConstant.CONTACTSACTIVEDEGREE;
				saveValidation(bqsRepCrossValidation,pid,type);
				carrierContactAvgCntHy = bqsRepCrossValidation.getResult();
			}

			//未使用通话与短信的天数
			NotCallAndSmsDayCount notCallAndSmsDayCount = crossValidation.getNotCallAndSmsDayCount();
			if (notCallAndSmsDayCount != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(notCallAndSmsDayCount, bqsRepCrossValidation);
				String type = BQSConstant.NOTCALLANDSMSDAYCOUNT;
				saveValidation(bqsRepCrossValidation,pid,type);

				//静默天数
				String result = bqsRepCrossValidation.getResult();
				carrierSilentAvgDays = result.substring(0,result.length()-1);// 静默天数（分子）
				carrierSilentAvgTimes = this.getmatcher(bqsRepCrossValidation.getEvidence(),pattern3); //静默次数（分母）

				carrierSilentDays = result.substring(0,result.length()-1); // 静默天数（分子）

                String dateTime = this.getmatcher(bqsRepCrossValidation.getEvidence(),pattern4);
				String[] dateArray = dateTime.split("~");

				try {
					Date stateTime1 = DateUtils.convertStringToDate(dateArray[0]);
					Date endTime1 = DateUtils.convertStringToDate(dateArray[1]);
					int daysBetween = DateUtils.daysBetween(stateTime1, endTime1);
					carrierSilentCntDays = String.valueOf(daysBetween+1); // 总天数（分母）
				} catch (Exception e) {
					throw new RuntimeException("总天数转换失败");
				}
			}

			//号码使用时长
			NumberUsedLong numberUsedLong = crossValidation.getNumberUsedLong();
			if (numberUsedLong != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(numberUsedLong, bqsRepCrossValidation);
				String type = BQSConstant.NUMBERUSEDLONG;
				saveValidation(bqsRepCrossValidation,pid,type);

				//处理redis数据
				if(org.apache.commons.lang3.StringUtils.isNotEmpty(bqsRepCrossValidation.getResult())){
					String result = bqsRepCrossValidation.getResult();
					String tempResult = result.substring(2, result.length()-1);
					//月份都除以30
					carrierPhoneRealUsedMonth = (Double.parseDouble(tempResult))/30;

				}
			}

			//入网时间 
			OpenTime openTime = crossValidation.getOpenTime();
			if (openTime != null) {
				String result = openTime.getResult();
				if (!StringUtils.isBlank(result)) {
					try {
						Date smdate = DateUtils.convertStringToDate(result);
						int daysBetween = DateUtils.daysBetween(smdate, new Date());
						//处理入网时间
						double temp = (double)daysBetween;
						double month = (double)(temp/30);
						carrierPhoneUsedMonth = String.valueOf(month);

						//添加禁止项数据
						BanCodeUtil.addCheckPoint(checkList,RuleConstants.BQS_SHORT_NUMBER,daysBetween);
						
					} catch (ParseException e) {
						throw new RuntimeException("入网时间转换失败"+e.getMessage(), e);
					}
				}
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(openTime, bqsRepCrossValidation);
				String type = BQSConstant.OPENTIME;
				saveValidation(bqsRepCrossValidation,pid,type);


			}

			//支付宝注册时间
			AlipayRegisterTime alipayRegisterTime = crossValidation.getAlipayRegisterTime();
			if (alipayRegisterTime != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(alipayRegisterTime, bqsRepCrossValidation);
				String type = BQSConstant.ALIPAYREGISTERTIME;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//拨出电话号码个数
			OriginatingMobileCount originatingMobileCount = crossValidation.getOriginatingMobileCount();
			if (originatingMobileCount != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(originatingMobileCount, bqsRepCrossValidation);
				String type = BQSConstant.ORIGINATINGMOBILECOUNT;
				saveValidation(bqsRepCrossValidation,pid,type);
				carrierCallOutCnt6m = this.getmatcher(bqsRepCrossValidation.getResult(),pattern6);
			}

			//拨入电话号码个数
			TerminatingCallCount terminatingCallCount = crossValidation.getTerminatingCallCount();
			if (terminatingCallCount !=  null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(terminatingCallCount, bqsRepCrossValidation);
				String type = BQSConstant.TERMINATINGCALLCOUNT;
				saveValidation(bqsRepCrossValidation,pid,type);
				reportRedis.setCarrierCallInCnt6m(this.getmatcher(bqsRepCrossValidation.getResult(),pattern6));
			}

			//拨入通话总时长
			TerminatingCallDuration terminatingCallDuration = crossValidation.getTerminatingCallDuration();
			if (terminatingCallDuration != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(terminatingCallDuration, bqsRepCrossValidation);
				String type = BQSConstant.TERMINATINGCALLDURATION;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//拨出通话总时长
			OriginatingCallDuration originatingCallDuration = crossValidation.getOriginatingCallDuration();
			if (originatingCallDuration != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(originatingCallDuration, bqsRepCrossValidation);
				String type = BQSConstant.ORIGINATINGCALLDURATION;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//收到短信条数
			ReceiveMsgCount receiveMsgCount = crossValidation.getReceiveMsgCount();
			if (receiveMsgCount != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(receiveMsgCount, bqsRepCrossValidation);
				String type = BQSConstant.RECEIVEMSGCOUNT;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//发送短信条数
			SendMsgCount sendMsgCount = crossValidation.getSendMsgCount();
			if (sendMsgCount != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(sendMsgCount, bqsRepCrossValidation);
				String type = BQSConstant.SENDMSGCOUNT;
				saveValidation(bqsRepCrossValidation,pid,type);
				//处理缓存
				String result = bqsRepCrossValidation.getResult();
				carrierSmsOutCnt6m = result.substring(0,result.length()-1);
			}

			//话费缴存次数	
			MnoPayFeesCount mnoPayFeesCount = crossValidation.getMnoPayFeesCount();

			if (mnoPayFeesCount != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(mnoPayFeesCount, bqsRepCrossValidation);
				String type = BQSConstant.MNOPAYFEESCOUNT;
				saveValidation(bqsRepCrossValidation,pid,type);
				//欺诈项:bqsPayCnt
				String result = bqsRepCrossValidation.getResult();
				reportRedis.setBqsPayCnt(this.getmatcher(result,pattern3));
			}

			//话费单次缴存最大金额
			MnoSinglePaymentMax mnoSinglePaymentMax = crossValidation.getMnoSinglePaymentMax();
			reportRedis.setBqsMaxPay("0");
			if (mnoSinglePaymentMax != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(mnoSinglePaymentMax, bqsRepCrossValidation);
				String type = BQSConstant.MNOSINGLEPAYMENTMAX;
				saveValidation(bqsRepCrossValidation,pid,type);
				reportRedis.setBqsMaxPay(this.getmatcher(bqsRepCrossValidation.getResult(), pattern8));
			}

			//电商使用频率
			EbUseFrequency ebUseFrequency = crossValidation.getEbUseFrequency();
			if (ebUseFrequency != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(ebUseFrequency, bqsRepCrossValidation);
				String type = BQSConstant.EBUSEFREQUENCY;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//本人地址变化情况
			OneselfAdderChangeFrequency oneselfAdderChangeFrequency = crossValidation.getOneselfAdderChangeFrequency();
			if (oneselfAdderChangeFrequency != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(oneselfAdderChangeFrequency, bqsRepCrossValidation);
				String type = BQSConstant.ONESELFADDERCHANGEFREQUENCY;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//本人电商使用频率 
			OneselfEbUseFrequency oneselfEbUseFrequency = crossValidation.getOneselfEbUseFrequency();
			if (oneselfEbUseFrequency != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(oneselfEbUseFrequency, bqsRepCrossValidation);
				String type = BQSConstant.ONESELFEBUSEFREQUENCY;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//夜间通话次数(00:00 ~ 06:00)
			NightCallCount nightCallCount = crossValidation.getNightCallCount();
			if (nightCallCount != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(nightCallCount, bqsRepCrossValidation);
				String type = BQSConstant.NIGHTCALLCOUNT;
				saveValidation(bqsRepCrossValidation,pid,type);

				String result = bqsRepCrossValidation.getResult();
				//处理redis数据
				if(StringUtils.isNotEmpty(result)){
					//夜间通话次数
					carrierCallCntNight = result;
				}

				if(StringUtils.isNotEmpty(bqsRepCrossValidation.getEvidence())){
					String[] array = bqsRepCrossValidation.getEvidence().split(";");
					String callIn = array[0];
					String callOut = array[1];
					String callInTimes = getmatcher(callIn,pattern1);//呼入次数
					String callInTime = getmatcher(callIn,pattern2);//呼入时间

					String callOutTimes = getmatcher(callOut,pattern1); //呼出次数
					String callOutTime = getmatcher(callOut,pattern2); //呼出时间

					Double callTimes = Double.parseDouble(callInTimes) + Double.parseDouble(callOutTimes);
					Double callTime = Double.parseDouble(callInTime) + Double.parseDouble(callOutTime);

					carrierCallInAvgTimeNight = callInTime; //夜间呼入时间(分子)
					carrierCallInAvgCntNight = callInTimes;  //夜间呼入次数（分母）

					carrierCallTotalCntTimeNight = String.valueOf(callTime); //夜间通话时间（分子）
					carrierCallTotalCntNight = String.valueOf(callTimes); //夜间通话次数（分母）

					carrierCallInTimeNight = callInTime; //夜间呼入时间
					carrierCallTimeNight = String.valueOf(callTime); //夜间通话时间
					carrierCallOutCntNight = callOutTimes;//夜间呼出次数
					carrierCallOutTimeRatioNight = callOutTime;//呼出时长

				}
			}

			//单月通话次数大于200次的月份
			CallRecordsSizeOver200Month callRecordsSizeOver200Month = crossValidation.getCallRecordsSizeOver200Month();
			if (callRecordsSizeOver200Month != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(callRecordsSizeOver200Month, bqsRepCrossValidation);
				String type = BQSConstant.CALLRECORDSSIZEOVER200MONTH;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//单月通话次数大于500次的月份
			CallSizeOver500Month callSizeOver500Month = crossValidation.getCallSizeOver500Month();
			if (callSizeOver500Month != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(callSizeOver500Month, bqsRepCrossValidation);
				String type = BQSConstant.CALLSIZEOVER500MONTH;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//通话时长在1分钟以内的通话记录个数
			CallDurationLess1minSize callDurationLess1minSize = crossValidation.getCallDurationLess1minSize();
			if (callDurationLess1minSize != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(callDurationLess1minSize, bqsRepCrossValidation);
				String type = BQSConstant.CALLDURATIONLESS1MINSIZE;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//通话时长在1~5分钟以内的通话次数
			CallDuration1to5minSize callDuration1to5minSize = crossValidation.getCallDuration1to5minSize();
			if (callDuration1to5minSize != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(callDuration1to5minSize, bqsRepCrossValidation);
				String type = BQSConstant.CALLDURATION1TO5MINSIZE;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//通话时长在5~10分钟以内的通话次数
			CallDuration5to10minSize callDuration5to10minSize = crossValidation.getCallDuration5to10minSize();
			if (callDuration5to10minSize != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(callDuration5to10minSize, bqsRepCrossValidation);
				String type = BQSConstant.CALLDURATION5TO10MINSIZE;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//通话时长在10分钟以上的通话次数
			CallDurationBigger10minSize callDurationBigger10minSize = crossValidation.getCallDurationBigger10minSize();
			if (callDurationBigger10minSize != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(callDurationBigger10minSize, bqsRepCrossValidation);
				String type = BQSConstant.CALLDURATIONBIGGER10MINSIZE;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//单次拨入通话最长时长
			TerminatingCallDurationMax terminatingCallDurationMax = crossValidation.getTerminatingCallDurationMax();
			if (terminatingCallDurationMax != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(terminatingCallDurationMax, bqsRepCrossValidation);
				String type = BQSConstant.TERMINATINGCALLDURATIONMAX;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//单次拨出通话最长时长
			OriginatingCallDurationMax originatingCallDurationMax = crossValidation.getOriginatingCallDurationMax();
			if (originatingCallDurationMax != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(originatingCallDurationMax, bqsRepCrossValidation);
				String type = BQSConstant.ORIGINATINGCALLDURATIONMAX;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//110通话情况
			Number110ConnectInfo number110ConnectInfo = crossValidation.getNumber110ConnectInfo();
			reportRedis.setCarrier110AvgTimeEvidence("0");
			reportRedis.setCarrier110AvgTimeResult("0");
			if (number110ConnectInfo != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(number110ConnectInfo, bqsRepCrossValidation);
				String type = BQSConstant.NUMBER110CONNECTINFO;
				saveValidation(bqsRepCrossValidation,pid,type);

				String tt = this.getmatcher(bqsRepCrossValidation.getEvidence(), pattern7);
				reportRedis.setCarrier110AvgTimeEvidence(tt == null ? "0" : tt); // 2.0 分子
				String avgTime_result = this.getmatcher(bqsRepCrossValidation.getResult(),pattern3);
				reportRedis.setCarrier110AvgTimeResult(avgTime_result == null ? "0" : avgTime_result);  // 2.0 分母
			}

			//120通话情况	
			Number120ConnectInfo number120ConnectInfo = crossValidation.getNumber120ConnectInfo();
			if (number120ConnectInfo != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(number120ConnectInfo, bqsRepCrossValidation);
				String type = BQSConstant.NUMBER120CONNECTINFO;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//贷款类号码使用情况	
			P2pConnectInfo p2pConnectInfo = crossValidation.getP2pConnectInfo();
			if (p2pConnectInfo != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(p2pConnectInfo, bqsRepCrossValidation);
				String type = BQSConstant.P2PCONNECTINFO;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//银行类号码使用情况	
			BankConnectInfo bankConnectInfo = crossValidation.getBankConnectInfo();
			if (bankConnectInfo != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(bankConnectInfo, bqsRepCrossValidation);
				String type = BQSConstant.BANKCONNECTINFO;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//信用卡类号码使用情况
			CreditCardConnectInfo creditCardConnectInfo = crossValidation.getCreditCardConnectInfo();
			if (creditCardConnectInfo != null) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(creditCardConnectInfo, bqsRepCrossValidation);
				String type = BQSConstant.CREDITCARDCONNECTINFO;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

			//处理redisRepot的数据
			reportRedis.setCarrierSilentAvgDays(carrierSilentAvgDays);// 静默天数（分子）
			reportRedis.setCarrierSilentAvgTimes(carrierSilentAvgTimes); //静默次数（分母）

			reportRedis.setCarrierPhoneUsedMonth(carrierPhoneUsedMonth); //入网月数
			reportRedis.setCarrierSmsOutCnt6m(carrierSmsOutCnt6m); //半年发送短信条数
			reportRedis.setCarrierMaxContactAreaRatio(carrierMaxContactAreaRatio); //最频繁朋友圈通话时间占比

			reportRedis.setCarrierSilentDays(carrierSilentDays); // 静默天数（分子）
			reportRedis.setCarrierSilentCntDays(carrierSilentCntDays); // 总天数（分母）

			reportRedis.setCarrierCallInAvgTimeNight(carrierCallInAvgTimeNight); //夜间呼入时间(分子)
			reportRedis.setCarrierCallInAvgCntNight(carrierCallInAvgCntNight);  //夜间呼入次数（分母）

			reportRedis.setCarrierCallTotalCntTimeNight(carrierCallTotalCntTimeNight); //夜间通话时间（分子）
			reportRedis.setCarrierCallTotalCntNight(carrierCallTotalCntNight); //夜间通话次数（分母）

			reportRedis.setCarrierCallInTimeNight(carrierCallInTimeNight); //夜间呼入时间
			reportRedis.setCarrierCallTimeNight(carrierCallTimeNight); //夜间通话时间

			reportRedis.setCarrierPhoneRealUsedMonth(String.valueOf(carrierPhoneRealUsedMonth)); //号码有通话时长

			reportRedis.setCarrierCallInterflowNum(carrierCallInterflowNum);//互通电话号码个数
			reportRedis.setCarrierCallCntNight(carrierCallCntNight); //夜间通话次数
			reportRedis.setCarrierCallOutCntNight(carrierCallOutCntNight);//夜间呼出次数
			reportRedis.setCarrierCallOutTimeRatioNight(carrierCallOutTimeRatioNight);
			reportRedis.setCarrierContactAvgCntHy(carrierContactAvgCntHy);
			reportRedis.setCarrierContactNum(carrierContactNum);
			
			reportRedis.setCarrierCallOutCnt6m(carrierCallOutCnt6m);
			//特殊通话联系情况列表
			List<ExceptionalConnectInfoList> exceptionalConnectInfoList = crossValidation.getExceptionalConnectInfoList();
			if(exceptionalConnectInfoList == null){
				return;
			}
			for (ExceptionalConnectInfoList exceptionalConnectInfoList2 : exceptionalConnectInfoList) {
				bqsRepCrossValidation = new BqsRepCrossValidation();
				BeanUtils.copyProperties(exceptionalConnectInfoList2, bqsRepCrossValidation);
				String type = BQSConstant.EXCEPTIONALCONNECTINFOLIST;
				saveValidation(bqsRepCrossValidation,pid,type);
			}

		} catch (BeansException e) {
			throw new RuntimeException("白骑士报告插入CrossValidation入库异常："+e.getMessage(), e);
		}
	}

	//正则获取数据
	private String getmatcher(String str, Pattern pattern) {
		Matcher matcher1 = pattern.matcher(str);
		while(matcher1.find()){
			String result = matcher1.group(1);
			return result;
		}
		return null;
	}

	/**
	 * 查询白骑士报告
	 * @param applicationId
	 * @return
	 */
	@Override
	public BqsRepPetitionerVo queryById(String applicationId) {
		BqsRepPetitionerVo bqsRepPetitionerVo = new BqsRepPetitionerVo();
		BqsRepPetitioner bqsRepPetitioner = bqsRepPetitionerServiceDao.queryById(applicationId);
		BeanUtils.copyProperties(bqsRepPetitioner, bqsRepPetitionerVo);
		//获取主表主键id
		String pid = bqsRepPetitioner.getBqsPetitionerId();
		//获取数据来源
		List<BqsRepDataSource> bqsRepDataSources = bqsSourceServiceDao.queryListById(pid);
		bqsRepPetitionerVo.setBqsRepDataSources(bqsRepDataSources);
		//高风险名单
		List<BqsRepHighRisk> bqsRepHighRisks = bqsRiskServiceDao.queryListById(pid);
		bqsRepPetitionerVo.setBqsRepHighRisks(bqsRepHighRisks);
		//反欺诈云
		BqsRepAntiFraudCloud bqsRepAntiFraudCloud = bqsCloudServiceDao.queryByPid(pid);
		bqsRepPetitionerVo.setBqsRepAntiFraudCloud(bqsRepAntiFraudCloud);
		//出行数据
		List<BqsRepGoOut> bqsRepGoOuts = bqsGoOutServiceDao.queryListById(pid);
		bqsRepPetitionerVo.setBqsRepGoOuts(bqsRepGoOuts);
		//紧急联系人与常用联系人
		List<BqsRepContactsInfo> bqsRepContactsInfos = bqsContactsServiceDao.queryListById(pid);
		bqsRepPetitionerVo.setBqsRepContactsInfos(bqsRepContactsInfos);
		//分时间统计数据
		List<BqsRepMnoPeriodUsedNew> bqsRepMnoPeriodUsedNews = bqsRepMnoPeriodUsedNewServiceDao.queryListById(pid);
		bqsRepPetitionerVo.setBqsRepMnoPeriodUsedNews(bqsRepMnoPeriodUsedNews);
		
		//用户行为，活跃程度，通话行为，特殊通话检测
		List<BqsRepCrossValidation> bqsRepCrossValidations = bqsRepCrossValidationServiceDao.queryById(pid);
		bqsRepPetitionerVo.setBqsRepCrossValidations(bqsRepCrossValidations);
		
		return bqsRepPetitionerVo;
	}

}
