package com.shangyong.backend.service.tdReport.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shangyong.backend.utils.BanCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RedisConstant;
import com.shangyong.backend.common.RuleConstants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.common.enums.FraudBizEnum;
import com.shangyong.backend.dao.CuCustomerIceNewDao;
import com.shangyong.backend.dao.tdReport.TdReportAllContactDao;
import com.shangyong.backend.dao.tdReport.TdReportBehaviorAnalysisDao;
import com.shangyong.backend.dao.tdReport.TdReportCall2hourDao;
import com.shangyong.backend.dao.tdReport.TdReportCallCityDao;
import com.shangyong.backend.dao.tdReport.TdReportCarrierConsumptionDao;
import com.shangyong.backend.dao.tdReport.TdReportCarrierMonthDao;
import com.shangyong.backend.dao.tdReport.TdReportCompletenessMonthDao;
import com.shangyong.backend.dao.tdReport.TdReportContactAnalysisDao;
import com.shangyong.backend.dao.tdReport.TdReportContactCityDao;
import com.shangyong.backend.dao.tdReport.TdReportContinueSilenceDao;
import com.shangyong.backend.dao.tdReport.TdReportDetailDao;
import com.shangyong.backend.dao.tdReport.TdReportFinanceContactDao;
import com.shangyong.backend.dao.tdReport.TdReportInfoCheckDao;
import com.shangyong.backend.dao.tdReport.TdReportInfoDao;
import com.shangyong.backend.dao.tdReport.TdReportMobileInfoDao;
import com.shangyong.backend.dao.tdReport.TdReportPerMonthDao;
import com.shangyong.backend.dao.tdReport.TdReportRiskContactDao;
import com.shangyong.backend.dao.tdReport.TdReportSilenceStatsDao;
import com.shangyong.backend.dao.tdReport.TdReportTravelCityDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.CuCustomerIceNew;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.redis.TongDunReportRedis;
import com.shangyong.backend.entity.redis.fraud1_1.TongDunReport11Redis;
import com.shangyong.backend.entity.redis.fraud1_8.TongDunReport18Redis;
import com.shangyong.backend.entity.redis.fraud2_0.TongDunReport20Redis;
import com.shangyong.backend.entity.tdReport.TdReportAllContact;
import com.shangyong.backend.entity.tdReport.TdReportBehaviorAnalysis;
import com.shangyong.backend.entity.tdReport.TdReportCall2hour;
import com.shangyong.backend.entity.tdReport.TdReportCallCity;
import com.shangyong.backend.entity.tdReport.TdReportCarrierConsumption;
import com.shangyong.backend.entity.tdReport.TdReportCarrierMonth;
import com.shangyong.backend.entity.tdReport.TdReportCompletenessMonth;
import com.shangyong.backend.entity.tdReport.TdReportContactAnalysis;
import com.shangyong.backend.entity.tdReport.TdReportContactCity;
import com.shangyong.backend.entity.tdReport.TdReportContinueSilence;
import com.shangyong.backend.entity.tdReport.TdReportDetail;
import com.shangyong.backend.entity.tdReport.TdReportFinanceContact;
import com.shangyong.backend.entity.tdReport.TdReportInfo;
import com.shangyong.backend.entity.tdReport.TdReportInfoCheck;
import com.shangyong.backend.entity.tdReport.TdReportMobileInfo;
import com.shangyong.backend.entity.tdReport.TdReportPerMonth;
import com.shangyong.backend.entity.tdReport.TdReportRiskContact;
import com.shangyong.backend.entity.tdReport.TdReportSilenceStats;
import com.shangyong.backend.entity.tdReport.TdReportTravelCity;
import com.shangyong.backend.entity.tdReport.jsonbean.Active_silence_stats;
import com.shangyong.backend.entity.tdReport.jsonbean.All_contact_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.All_contact_stats;
import com.shangyong.backend.entity.tdReport.jsonbean.All_contact_stats_per_month;
import com.shangyong.backend.entity.tdReport.jsonbean.Behavior_analysis;
import com.shangyong.backend.entity.tdReport.jsonbean.Behavior_score;
import com.shangyong.backend.entity.tdReport.jsonbean.Call_area_stats_per_city;
import com.shangyong.backend.entity.tdReport.jsonbean.Call_duration_holiday_3month;
import com.shangyong.backend.entity.tdReport.jsonbean.Call_duration_holiday_6month;
import com.shangyong.backend.entity.tdReport.jsonbean.Call_duration_stats_2hour;
import com.shangyong.backend.entity.tdReport.jsonbean.Call_duration_workday_3month;
import com.shangyong.backend.entity.tdReport.jsonbean.Call_duration_workday_6month;
import com.shangyong.backend.entity.tdReport.jsonbean.Carrier_consumption_stats;
import com.shangyong.backend.entity.tdReport.jsonbean.Carrier_consumption_stats_per_month;
import com.shangyong.backend.entity.tdReport.jsonbean.Contact_area_stats_per_city;
import com.shangyong.backend.entity.tdReport.jsonbean.Contact_blacklist_analysis;
import com.shangyong.backend.entity.tdReport.jsonbean.Contact_creditscore_analysis;
import com.shangyong.backend.entity.tdReport.jsonbean.Contact_manyheads_analysis;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over15_0call_0msg_send_3month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over15_0call_0msg_send_6month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over15_0call_3month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over15_0call_6month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over15_0call_active_3month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over15_0call_active_6month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over3_0call_0msg_send_3month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over3_0call_0msg_send_6month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over3_0call_3month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over3_0call_6month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over3_0call_active_3month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Continue_silence_day_over3_0call_active_6month_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Data_completeness;
import com.shangyong.backend.entity.tdReport.jsonbean.Data_completeness_per_month;
import com.shangyong.backend.entity.tdReport.jsonbean.Emergency_contact1_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Emergency_contact2_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Finance_contact_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Finance_contact_stats;
import com.shangyong.backend.entity.tdReport.jsonbean.Home_tel_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Info_check;
import com.shangyong.backend.entity.tdReport.jsonbean.Info_match;
import com.shangyong.backend.entity.tdReport.jsonbean.JsonRootBean;
import com.shangyong.backend.entity.tdReport.jsonbean.Mobile_info;
import com.shangyong.backend.entity.tdReport.jsonbean.Report_info;
import com.shangyong.backend.entity.tdReport.jsonbean.Risk_contact_detail;
import com.shangyong.backend.entity.tdReport.jsonbean.Risk_contact_stats;
import com.shangyong.backend.entity.tdReport.jsonbean.Travel_track_analysis_per_city;
import com.shangyong.backend.entity.tdReport.jsonbean.User_info;
import com.shangyong.backend.entity.tdReport.jsonbean.Work_tel_detail;
import com.shangyong.backend.service.BuThirdpartyReportService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.service.tdReport.TdReportService;
import com.shangyong.backend.utils.BlackCityUtils;
import com.shangyong.backend.utils.JacksonUtils;
import com.shangyong.backend.utils.tdReport.gunzipUtils;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RedisFraudUtils;
import com.shangyong.utils.RiskHttpClientUtil;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONObject;
/**
 * 同盾报告获取-数据魔盒报告
 * @author Smk
 *
 */
@Service
public class TdReportServiceImpl implements TdReportService {
	
	@Autowired
	private SysParamRedisService sysParamRedisService;
	
	@Autowired
	private JsonReportService jsonReportService;
	
	@Autowired
	private BuThirdpartyReportService buThirdpartyReportService;
	
	@Autowired
	private CuCustomerIceNewDao cuCustomerIceNewDao;
	
	@Autowired
	private TdReportInfoDao tdReportInfoDao;//主表
	
	@Autowired
	private TdReportCompletenessMonthDao tdReportCompletenessMonthDao;
	
	@Autowired
	private TdReportMobileInfoDao tdReportMobileInfoDao;
	
	@Autowired
	private TdReportInfoCheckDao tdReportInfoCheckDao;
	
	@Autowired
	private TdReportContactAnalysisDao tdReportContactAnalysisDao;
	
	@Autowired
	private TdReportBehaviorAnalysisDao tdReportBehaviorAnalysisDao;
	
	@Autowired
	private TdReportDetailDao tdReportDetailDao;
	
	@Autowired 
	private TdReportAllContactDao tdReportAllContactDao;
	
	@Autowired
	private TdReportPerMonthDao tdReportPerMonthDao;
	
	@Autowired
	private TdReportRiskContactDao tdReportRiskContactDao;
	
	@Autowired
	private TdReportFinanceContactDao tdReportFinanceContactDao;
	
	@Autowired
	private TdReportContactCityDao tdReportContactCityDao;
	
	@Autowired
	private TdReportCarrierConsumptionDao tdReportCarrierConsumptionDao;
	
	@Autowired
	private TdReportCarrierMonthDao tdReportCarrierMonthDao;
	
	@Autowired
	private TdReportCallCityDao tdReportCallCityDao;
	
	@Autowired
	private TdReportSilenceStatsDao tdReportSilenceStatsDao;
	
	@Autowired
	private TdReportContinueSilenceDao tdReportContinueSilenceDao;
	
	@Autowired
	private TdReportCall2hourDao tdReportCall2hourDao;
	
	@Autowired
	private TdReportTravelCityDao tdReportTravelCityDao;
	
	@Autowired
	private RiskRuleService riskRuleService;
	
	private static Logger tdReportLogger = LoggerFactory.getLogger("tdReport");

	@Override
	public RuleResult queryTdReport(Application application) {
		RuleResult ruleResult = new RuleResult();
		List<Map<String,Object>> checkList = new ArrayList<Map<String,Object>>();
		TongDunReportRedis tongDunReportRedis = new TongDunReportRedis();
		String applicationId = application.getApplicationId();
		if (StringUtils.isBlank(applicationId)) {
			throw new RuntimeException("同盾报告获取失败,申请单号为空");
		}
		tdReportLogger.info("开始获取同盾报告,申请单编号:"+application.getApplicationId());
		/** 从获取具体配置参数**/
		SysParam tdReportConfig = sysParamRedisService.querySysParamByParamValueRedis(Constants.TD_REPORT_CODE);
		
		//判断
		if (tdReportConfig == null) {
			tdReportLogger.error("从系统参数表获取参数失败,key : TD_REPORT_CODE");
			throw new RuntimeException("从系统参数表获取参数失败,key : TD_REPORT_CODE");
		}
		//获取配置信息
		String url = tdReportConfig.getParamValueOne();
		String partner_code = tdReportConfig.getParamValueTwo();
		String partner_key = tdReportConfig.getParamValueThree();
		
		//判断配置信息
		Assert.hasText(url, "配置信息url不存在");
		Assert.hasText(partner_code, "配置信息partner_code不存在");
		Assert.hasText(partner_key, "配置信息partner_key不存在");
		//获取任务唯一标号task_id
		BuThirdpartyReport buThirdpartyReport = new BuThirdpartyReport();
		buThirdpartyReport.setBuApplicationId(applicationId);
		buThirdpartyReport.setTaskType(TaskTypeConstants.TD_REPORT_TYPE);
		String taskId = buThirdpartyReportService.getTaskId(buThirdpartyReport);
		//判断task_id是否存在
		Assert.hasText(taskId, "报告任务编号task_id不存在,请重新认证");
	
		//创建参数信息
		Map<String,String> params = new	HashMap<String,String>();
		params.put("partner_code", partner_code);
		params.put("partner_key", partner_key);
		params.put("task_id", taskId);
		//获取联系人
		String customerId = application.getCustomerId();
		Assert.hasText(customerId, "客户编号不存在");
		List<CuCustomerIceNew> findAllByCustomerId = cuCustomerIceNewDao.findAllByCustomerId(customerId);
		for (CuCustomerIceNew cuCustomerIceNew : findAllByCustomerId) {
			String name = cuCustomerIceNew.getIceName();
			String phone = cuCustomerIceNew.getIcePhone();
			String remark = cuCustomerIceNew.getRemark();
			Assert.hasText(name, "紧急联系人名字不存在");
			Assert.hasText(phone, "紧急联系人手机号码不存在");
			Assert.hasText(remark, "紧急联系人未分类");
			if ("1".equals(remark)) {
				params.put("contact1_name", name);
				params.put("contact1_mobile", phone);
			}else {
				params.put("contact2_name", name);
				params.put("contact2_mobile", phone);
			}
		}
		//请求同盾报告接口获取数据
		tdReportLogger.info("开始请求同盾报告接口");
		String content = RiskHttpClientUtil.doPost(url, params);
		JSONObject fromObject = JSONObject.fromObject(content);
		String code = fromObject.getString("code");
		if (!"0".equals(code)) {
			throw new RuntimeException("同盾报告获取失败,失败原因:"+fromObject.getString("msg"));
		}
		//进行同盾解码转换
		String data = fromObject.getString("data");
		String result = gunzipUtils.gunzip(data);
		//进行JacksonUtils转换
		try {
			JsonRootBean jsonToBean = (JsonRootBean) JacksonUtils.JsonToBean(result, JsonRootBean.class);
			//创建时间
			String dateTime = DateUtils.getDate(new Date());
			//主表
			saveReportInfo(jsonToBean, applicationId, dateTime);
			//手机信息表
			saveTdReportMobileInfo(jsonToBean, applicationId, dateTime, checkList, tongDunReportRedis);
			//每个月数据完整性表
			saveTdReportCompletenessMonth(jsonToBean, applicationId, dateTime);
			//信息检测表
			saveTdReportInfoCheck(jsonToBean, applicationId, dateTime, checkList);
			//联系人黑名单分析
			saveTdReportContactAnalysis(jsonToBean, applicationId, dateTime, checkList);
			//行为分析
			saveTdReportBehaviorAnalysis(jsonToBean, applicationId, dateTime);
			//联系人明细
			saveTdReportDetail(jsonToBean, applicationId, dateTime, tongDunReportRedis);
			//全部联系人统计
			saveTdReportAllContact(jsonToBean, applicationId, dateTime, checkList, tongDunReportRedis);
			//每个月联系人统计
			saveTdReportPerMonth(jsonToBean, applicationId, dateTime, tongDunReportRedis);
			//风险联系人统计表
			saveTdReportRiskContact(jsonToBean, applicationId, dateTime, checkList);
			//金融机构联系人统计
			saveTdReportFinanceContact(jsonToBean, applicationId, dateTime);
			//联系人归属地统计（城市）
			saveTdReportContactCity(jsonToBean, applicationId, dateTime, tongDunReportRedis);
			//运营商消费统计
			saveTdReportCarrierConsumption(jsonToBean, applicationId, dateTime, checkList);
			//每个月运营商消费统计
			saveTdReportCarrierMonth(jsonToBean, applicationId, dateTime, tongDunReportRedis);
			//通话地区统计（城市）
			saveTdReportCallCity(jsonToBean, applicationId, dateTime, tongDunReportRedis);
			//静默活跃统计
			saveTdReportSilenceStats(jsonToBean, applicationId, dateTime, tongDunReportRedis);
			//各时间段通话统计（每2小时）
			saveTdReportCall2hour(jsonToBean, applicationId, dateTime);
			//出行记录分析（城市）
			saveTdReportTravelCity(jsonToBean, applicationId, dateTime);

			//调用禁止项，获取用户校验结果
			ruleResult = riskRuleService.querySafeRuleApi(application, checkList);
			
			if(ruleResult == null){
				throw new RuntimeException("腾讯云数据报告获取-->调用taskCallBackService-->resultObj 为空");
			}
			//存阿里云存mongo
	        jsonReportService.uploadJson(Constants.TD_REPORT_DIR, result, TaskTypeConstants.TD_REPORT_TYPE, TaskTypeConstants.TD_REPORT_NAME, TaskTypeConstants.TD_REPORT_ISEND, application.getApplicationId(), "noext");
	        
	        //欺诈1.1
	        TongDunReport11Redis tongDunReport11Redis = new TongDunReport11Redis();
			//欺诈1.8
	        TongDunReport18Redis tongDunReport18Redis = new TongDunReport18Redis();
			// 2.0
	        TongDunReport20Redis tongDunReport20Redis = new TongDunReport20Redis();
			
	        //上传redis
	        // 1.1
	        String key = RedisConstant.buildFraudScoresKey(applicationId, FraudBizEnum.TONG_DUN_REPORT);
	        BeanUtils.copyProperties(tongDunReportRedis,tongDunReport11Redis);
	        RedisFraudUtils.hmset(key, tongDunReport11Redis.toMap());
	        // 1.8
	        String key18 = RedisConstant.buildFraudScoresKey1_8(applicationId, FraudBizEnum.TONG_DUN_REPORT);
	        BeanUtils.copyProperties(tongDunReportRedis,tongDunReport18Redis);
	        RedisFraudUtils.hmset(key18, tongDunReport18Redis.toMap());
	        // 2.0
	        String key20 = RedisConstant.buildFraudScoresKey2_0(applicationId, FraudBizEnum.TONG_DUN_REPORT);
	        BeanUtils.copyProperties(tongDunReportRedis,tongDunReport20Redis);
	        RedisFraudUtils.hmset(key20, tongDunReport20Redis.toMap());

		} catch (Exception e) {
			tdReportLogger.error("同盾报告获取失败,失败原因:"+ e.getMessage(), e);
			throw new RuntimeException("同盾报告获取失败,失败原因:"+ e.getMessage(), e);
		}
		return ruleResult;
	}

	/**
	 * 保存主表
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 */
	private void saveReportInfo(JsonRootBean jsonToBean,String applicationId,String dateTime){
		Report_info reportInfo = jsonToBean.getReport_info();
		TdReportInfo tdReportInfo = new TdReportInfo();
		BeanUtils.copyProperties(reportInfo, tdReportInfo);
		User_info userInfo = jsonToBean.getUser_info();
		BeanUtils.copyProperties(userInfo, tdReportInfo);
		Data_completeness dataCompleteness = jsonToBean.getData_completeness();
		BeanUtils.copyProperties(dataCompleteness, tdReportInfo);
		tdReportInfo.setApplicationId(applicationId);
		tdReportInfo.setReportId(UUIDUtils.getUUID());
		tdReportInfo.setCreateTime(dateTime);
		tdReportInfo.setModifyTime(dateTime);
		//保存
		int count = tdReportInfoDao.saveEntity(tdReportInfo);
		Assert.isTrue(count>0, "同盾魔盒主表保存失败");
	}

	/**
	 * 保存数据的完整性表
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 */
	private void saveTdReportCompletenessMonth(JsonRootBean jsonToBean,String applicationId,String dateTime){
		List<Data_completeness_per_month> months = jsonToBean.getData_completeness().getData_completeness_per_month();
		if (months != null && !months.isEmpty()) {
			List<TdReportCompletenessMonth> list = new ArrayList<TdReportCompletenessMonth>();
			for (Data_completeness_per_month month : months) {
				TdReportCompletenessMonth tdReportCompletenessMonth = new TdReportCompletenessMonth();
				BeanUtils.copyProperties(month, tdReportCompletenessMonth);
				tdReportCompletenessMonth.setApplicationId(applicationId);
				tdReportCompletenessMonth.setCompletenessMonthId(UUIDUtils.getUUID());
				tdReportCompletenessMonth.setCreateTime(dateTime);
				tdReportCompletenessMonth.setModifyTime(dateTime);
				list.add(tdReportCompletenessMonth);
			}
			//批量保存
			int count = tdReportCompletenessMonthDao.saveAllEntity(list);
			Assert.isTrue(count>0,"每个月数据完整性表保存失败");
		}
	}

	/**
	 * 保存手机信息表
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 * @param checkList
	 * @param tongDunReportRedis
	 */
	private void saveTdReportMobileInfo(JsonRootBean jsonToBean, String applicationId, String dateTime, List<Map<String,Object>> checkList, TongDunReportRedis tongDunReportRedis){
		Mobile_info mobileInfo = jsonToBean.getMobile_info();
		Info_match infoMatch = jsonToBean.getInfo_match();
		if (mobileInfo != null||infoMatch != null) {
			TdReportMobileInfo tdReportMobileInfo = new TdReportMobileInfo();
			if (mobileInfo != null) {
				BeanUtils.copyProperties(mobileInfo, tdReportMobileInfo);
			}
			if (infoMatch != null) {
				BeanUtils.copyProperties(infoMatch, tdReportMobileInfo);
			}
			tdReportMobileInfo.setApplicationId(applicationId);
			tdReportMobileInfo.setMobileInfoId(UUIDUtils.getUUID());
			tdReportMobileInfo.setCreateTime(dateTime);
			tdReportMobileInfo.setModifyTime(dateTime);
			//保存
			int count = tdReportMobileInfoDao.saveEntity(tdReportMobileInfo);
			Assert.isTrue(count>0,"手机信息表保存失败");
			//添加禁止项
			if ("不匹配".equals(tdReportMobileInfo.getRealNameCheckYys()) || "无数据".equals(tdReportMobileInfo.getRealNameCheckYys())) {
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_NAME_MISMARCHING,"No");//运营商姓名不匹配
			}
			if (!"正常".equals(tdReportMobileInfo.getAccountStatus())) {
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_ACCOUNT_STATE,"No");//账户状态非正常
			}
			if (StringUtils.isNotBlank(tdReportMobileInfo.getMobileNetAge())) {
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_PHONE_DAY,Integer.valueOf(tdReportMobileInfo.getMobileNetAge()));//最短号码使用天数量
			}else{
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_PHONE_DAY,0);//最短号码使用天数量
			}
			String mobileNetAge = tdReportMobileInfo.getMobileNetAge();//入网时间
			if (StringUtils.isNotBlank(mobileNetAge)) {
				tongDunReportRedis.setCarrierPhoneUsedMonth(mobileNetAge);
			}else{
				tongDunReportRedis.setCarrierPhoneUsedMonth("unknow");
			}

			String mobileNetTime = tdReportMobileInfo.getMobileNetTime();
			//评分卡 1.8
			if (StringUtils.isNotBlank(mobileNetTime)){
				tongDunReportRedis.setCarrierPhoneUsedMonthNew(mobileNetTime);
			}else{
				tongDunReportRedis.setCarrierPhoneUsedMonthNew("0");
			}
		}
	}

	/**
	 * 保存同盾信息检测行为评分表
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 * @param checkList
	 */
	private void saveTdReportInfoCheck(JsonRootBean jsonToBean,String applicationId,String dateTime,List<Map<String,Object>> checkList){
		Info_check infoCheck = jsonToBean.getInfo_check();
		Behavior_score behaviorScore = jsonToBean.getBehavior_score();
		if (infoCheck != null || behaviorScore != null) {
			
		
			TdReportInfoCheck tdReportInfoCheck = new TdReportInfoCheck();
			if (infoCheck != null) {
				BeanUtils.copyProperties(infoCheck, tdReportInfoCheck);
			}
			if (behaviorScore != null) {
				BeanUtils.copyProperties(behaviorScore, tdReportInfoCheck);
			}
			tdReportInfoCheck.setApplicationId(applicationId);
			tdReportInfoCheck.setInfoCheckId(UUIDUtils.getUUID());
			tdReportInfoCheck.setCreateTime(dateTime);
			tdReportInfoCheck.setModifyTime(dateTime);
			//保存
			int count = tdReportInfoCheckDao.saveEntity(tdReportInfoCheck);
			Assert.isTrue(count>0,"信息检测表保存失败");
			//添加禁止项数据
			if (!"是".equals(tdReportInfoCheck.getIsIdentityCodeReliable())) {
				//未实名认证
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_REAL_NAME,"No");
			}

			if (!"是".equals(tdReportInfoCheck.getIsContact1Called6month()) && !"是".equals(tdReportInfoCheck.getIsContact2Called6month())) {
				//与联系人1且和联系人2都没有通话记录
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_PHONE_RECORD,"No");
			}
		}
	}

	/**
	 * 保存同盾联系人黑名单多头分析表
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 * @param checkList
	 */
	private void saveTdReportContactAnalysis(JsonRootBean jsonToBean,String applicationId,String dateTime,List<Map<String,Object>> checkList){
		Contact_blacklist_analysis blacklistAnalysis = jsonToBean.getContact_blacklist_analysis();
		Contact_manyheads_analysis manyheadsAnalysis = jsonToBean.getContact_manyheads_analysis();
		Contact_creditscore_analysis creditscoreAnalysis = jsonToBean.getContact_creditscore_analysis();
		if (blacklistAnalysis != null || manyheadsAnalysis != null || creditscoreAnalysis != null) {
		
			TdReportContactAnalysis tdReportContactAnalysis = new TdReportContactAnalysis();
			if (blacklistAnalysis != null) {
				BeanUtils.copyProperties(blacklistAnalysis, tdReportContactAnalysis);
			}
			if (manyheadsAnalysis != null) {
				BeanUtils.copyProperties(manyheadsAnalysis, tdReportContactAnalysis);
			}
			if (creditscoreAnalysis != null) {
				BeanUtils.copyProperties(creditscoreAnalysis, tdReportContactAnalysis);
			}
			tdReportContactAnalysis.setApplicationId(applicationId);
			tdReportContactAnalysis.setContactAnalysisId(UUIDUtils.getUUID());
			tdReportContactAnalysis.setCreateTime(dateTime);
			tdReportContactAnalysis.setModifyTime(dateTime);
			//保存
			int count = tdReportContactAnalysisDao.saveEntity(tdReportContactAnalysis);
			Assert.isTrue(count>0,"联系人黑名单分析表保存失败");
			Double valueOf = Double.valueOf(tdReportContactAnalysis.getBlackTop10ContactTotalCountRatio());
			valueOf=valueOf*100;
			int intValue = valueOf.intValue();
			//未前10手机联系人命中黑名单占比
			BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_BLACKLIST_ACCOUNTED,intValue);
		}
	}

	/**
	 * 保存同盾行为分析表
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 */
	private void saveTdReportBehaviorAnalysis(JsonRootBean jsonToBean,String applicationId,String dateTime){
		Behavior_analysis behaviorAnalysis = jsonToBean.getBehavior_analysis();
		if (behaviorAnalysis != null) {
		TdReportBehaviorAnalysis tdReportBehaviorAnalysis = new TdReportBehaviorAnalysis();
		BeanUtils.copyProperties(behaviorAnalysis, tdReportBehaviorAnalysis);
		tdReportBehaviorAnalysis.setApplicationId(applicationId);
		tdReportBehaviorAnalysis.setBehaviorAnalysisId(UUIDUtils.getUUID());
		tdReportBehaviorAnalysis.setCreateTime(dateTime);
		tdReportBehaviorAnalysis.setModifyTime(dateTime);
		//保存
		int count = tdReportBehaviorAnalysisDao.saveEntity(tdReportBehaviorAnalysis);
		Assert.isTrue(count>0,"行为分析表保存失败");
		
		}		
	}
	//保存同盾联系人明细表
	private void saveTdReportDetail(JsonRootBean jsonToBean, String applicationId, String dateTime, TongDunReportRedis tongDunReportRedis){
		List<TdReportDetail> list = new ArrayList<TdReportDetail>();
		Integer callCount = null; //紧急联系人主叫次数
		Integer callTimeActive6month = null;//紧急联系人近6个月呼出时长
		Integer callTimeOne = null;//紧急联系人近1个月通话时长

		//联系人明细1
		Emergency_contact1_detail contact1Detail = jsonToBean.getEmergency_contact1_detail();
		if (contact1Detail != null) {
			TdReportDetail tdReportDetail = new TdReportDetail();
			BeanUtils.copyProperties(contact1Detail, tdReportDetail);
			tdReportDetail.setTableType("紧急联系人明细1");
			tdReportDetail.setApplicationId(applicationId);
			tdReportDetail.setDetailId(UUIDUtils.getUUID());
			tdReportDetail.setCreateTime(dateTime);
			tdReportDetail.setModifyTime(dateTime);
			list.add(tdReportDetail);
			if (StringUtils.isNotBlank(tdReportDetail.getCallCountActive6month())) {
				if (null == callCount) {
					callCount = 0;
				}
				callCount = callCount+Integer.valueOf(tdReportDetail.getCallCountActive6month());
			}
			if (StringUtils.isNotBlank(tdReportDetail.getCallTimeActive6month())) {
				if (null == callTimeActive6month) {
					callTimeActive6month = 0;
				}
				callTimeActive6month = callTimeActive6month + Integer.valueOf(tdReportDetail.getCallTimeActive6month());//6个月呼出时长
			}
			if (StringUtils.isNotBlank(tdReportDetail.getCallTime1month())) {
				if (null == callTimeOne) {
					callTimeOne = 0;
				}
				callTimeOne = callTimeOne + Integer.valueOf(tdReportDetail.getCallTime1month());
			}
			
		}
		//联系人明细2
		Emergency_contact2_detail contact2Detail = jsonToBean.getEmergency_contact2_detail();
		if (contact2Detail != null) {
			TdReportDetail tdReportDetail = new TdReportDetail();
			BeanUtils.copyProperties(contact2Detail, tdReportDetail);
			tdReportDetail.setTableType("紧急联系人明细2");
			tdReportDetail.setApplicationId(applicationId);
			tdReportDetail.setDetailId(UUIDUtils.getUUID());
			tdReportDetail.setCreateTime(dateTime);
			tdReportDetail.setModifyTime(dateTime);
			list.add(tdReportDetail);
			if (StringUtils.isNotBlank(tdReportDetail.getCallCountActive6month())) {
				if (null == callCount) {
					callCount = 0;
				}
				callCount = callCount+Integer.valueOf(tdReportDetail.getCallCountActive6month());
			}
			if (StringUtils.isNotBlank(tdReportDetail.getCallTimeActive6month())) {
				if (null == callTimeActive6month) {
					callTimeActive6month = 0;
				}
				callTimeActive6month = callTimeActive6month + Integer.valueOf(tdReportDetail.getCallTimeActive6month());//6个月呼出时长
			}
			if (StringUtils.isNotBlank(tdReportDetail.getCallTime1month())) {
				if (null == callTimeOne) {
					callTimeOne = 0;
				}
				callTimeOne = callTimeOne + Integer.valueOf(tdReportDetail.getCallTime1month());
			}
		}
		//紧急联系人呼出次数(欺诈分)
		tongDunReportRedis.setCarrierIcePersonCallOutCnt6m(null == callCount ? null : String.valueOf(callCount));
		//紧急联系人近6个月呼出时长
		tongDunReportRedis.setCarrierIcePersonCallOutTime6m(null == callTimeActive6month ? null : String.valueOf(callTimeActive6month));
		//紧急联系人近1个月通话时长
		tongDunReportRedis.setCarrierIcePersonCallTime1m(null == callTimeOne ? null : String.valueOf(callTimeOne));

		//紧急联系人呼出次数 (欺诈分) 1.8
		tongDunReportRedis.setCarrierIcePersonCallOutCnt6mNew(null == callCount ? null : String.valueOf(callCount));

		//家庭号码明细
		Home_tel_detail homeDetail = jsonToBean.getHome_tel_detail();
		if (homeDetail != null) {
			TdReportDetail tdReportDetail = new TdReportDetail();
			BeanUtils.copyProperties(homeDetail, tdReportDetail);
			tdReportDetail.setTableType("家庭号码明细");
			tdReportDetail.setApplicationId(applicationId);
			tdReportDetail.setDetailId(UUIDUtils.getUUID());
			tdReportDetail.setCreateTime(dateTime);
			tdReportDetail.setModifyTime(dateTime);
			list.add(tdReportDetail);
		}
		//工作号码明细
		Work_tel_detail workDetail = jsonToBean.getWork_tel_detail();
		if (workDetail != null) {
			TdReportDetail tdReportDetail = new TdReportDetail();
			BeanUtils.copyProperties(workDetail, tdReportDetail);
			tdReportDetail.setTableType("工作号码明细");
			tdReportDetail.setApplicationId(applicationId);
			tdReportDetail.setDetailId(UUIDUtils.getUUID());
			tdReportDetail.setCreateTime(dateTime);
			tdReportDetail.setModifyTime(dateTime);
			list.add(tdReportDetail);
		}
		//全部联系人明细
		List<All_contact_detail> allDetails = jsonToBean.getAll_contact_detail();
		for (All_contact_detail allDetail : allDetails) {
			TdReportDetail tdReportDetail = new TdReportDetail();
			BeanUtils.copyProperties(allDetail, tdReportDetail);
			tdReportDetail.setTableType("全部联系人明细");
			tdReportDetail.setApplicationId(applicationId);
			tdReportDetail.setDetailId(UUIDUtils.getUUID());
			tdReportDetail.setCreateTime(dateTime);
			tdReportDetail.setModifyTime(dateTime);
			list.add(tdReportDetail);
		}
		//风险联系人明细
		List<Risk_contact_detail> riskDetails = jsonToBean.getRisk_contact_detail();
		if (riskDetails != null && !riskDetails.isEmpty()) {
			for (Risk_contact_detail riskDetail : riskDetails) {
				TdReportDetail tdReportDetail = new TdReportDetail();
				BeanUtils.copyProperties(riskDetail, tdReportDetail);
				tdReportDetail.setTableType("风险联系人明细");
				tdReportDetail.setApplicationId(applicationId);
				tdReportDetail.setDetailId(UUIDUtils.getUUID());
				tdReportDetail.setCreateTime(dateTime);
				tdReportDetail.setModifyTime(dateTime);
				list.add(tdReportDetail);
			}
		}
		//金融机构联系人明细
		List<Finance_contact_detail> financedetails = jsonToBean.getFinance_contact_detail();
		if (financedetails != null && !financedetails.isEmpty()) {
			for (Finance_contact_detail financedetail : financedetails) {
				TdReportDetail tdReportDetail = new TdReportDetail();
				BeanUtils.copyProperties(financedetail, tdReportDetail);
				tdReportDetail.setTableType("金融机构联系人明细");
				tdReportDetail.setApplicationId(applicationId);
				tdReportDetail.setDetailId(UUIDUtils.getUUID());
				tdReportDetail.setCreateTime(dateTime);
				tdReportDetail.setModifyTime(dateTime);
				list.add(tdReportDetail);
			}
		}
		//批量保存
		int count = tdReportDetailDao.saveAllEntity(list);
		Assert.isTrue(count>0,"联系人明细保存失败");

		
	}
	//保存全部联系人统计表
	private void saveTdReportAllContact(JsonRootBean jsonToBean, String applicationId, String dateTime, List<Map<String,Object>> checkList, TongDunReportRedis tongDunReportRedis){
		All_contact_stats allStats = jsonToBean.getAll_contact_stats();
		if (allStats != null) {
			TdReportAllContact tdReportAllContact = new TdReportAllContact();
			BeanUtils.copyProperties(allStats, tdReportAllContact);
			tdReportAllContact.setAllContactId(UUIDUtils.getUUID());
			tdReportAllContact.setApplicationId(applicationId);
			tdReportAllContact.setCreateTime(dateTime);
			tdReportAllContact.setModifyTime(dateTime);

			//保存
			int count = tdReportAllContactDao.saveEntity(tdReportAllContact);
			Assert.isTrue(count>0,"全部联系人统计保存失败");

			//调用禁止项
			BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_THREE_MONTH,Integer.valueOf(tdReportAllContact.getCallTimeActive3month()));//近3个月主叫总时长

			if ("0".equals(tdReportAllContact.getCallTimePassive6month()) || "0".equals(tdReportAllContact.getCallCountPassive6month())) {
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_SIX_VERAGE,0);//近6个月平均被叫时长
			}else{
				int a = Integer.valueOf(tdReportAllContact.getCallTimePassive6month());
				int time = a/6;
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_SIX_VERAGE,time);//近6个月平均被叫时长
			}
			if ("0".equals(tdReportAllContact.getCallCountPassive3month())) {
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_THREE_VERAGE,0);//近3个月平均被叫次数
			}else{
				int ave = Integer.valueOf(tdReportAllContact.getCallCountPassive3month());
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_THREE_VERAGE,ave/3);//近3个月平均被叫次数
			}
			String callTimeActive3month = tdReportAllContact.getCallTimeActive3month();
			BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_THREE_MONTH,Integer.valueOf(callTimeActive3month));//近3个月主叫总时长
			//互通过电话号码的数量
			BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_SHORT_NUMBER,Integer.valueOf(tdReportAllContact.getContactCountMutual6month()));

			// 评分卡 1.1
			// 欺诈总通话次数6月获取
			tongDunReportRedis.setCarrierContactCallCntRatio(tdReportAllContact.getCallCount6month());
			// 夜间通话时长
			tongDunReportRedis.setCarrierCallTimeNight(tdReportAllContact.getCallTimeLateNight6month());
			// 互通电话号码个数
			tongDunReportRedis.setCarrierCallInterflowNum(tdReportAllContact.getContactCountMutual6month());
			// 夜间通话次数
			tongDunReportRedis.setCarrierCallCountNight(tdReportAllContact.getCallCountLateNight6month());

			// 评分卡  1.8
			// 夜间通话时长
			tongDunReportRedis.setCarrierCallTimeNight6m(tdReportAllContact.getCallTimeLateNight6month());
			//夜间通话次数
			tongDunReportRedis.setCarrierCallCountNight6m(tdReportAllContact.getCallCountLateNight6month());
			//总呼出次数 6个月
			tongDunReportRedis.setCarrierCallCount6m(tdReportAllContact.getCallCount6month());
			//近6月被叫号码数量
			tongDunReportRedis.setCarrierCallInCnt6m(tdReportAllContact.getContactCountPassive6month());
			//近6月深夜主叫通话次数
			tongDunReportRedis.setCallCountActiveLateNight6Month(tdReportAllContact.getCallCountActiveLateNight6month());
			//近6月主叫通话次数
			tongDunReportRedis.setCallCountActive6Month(tdReportAllContact.getCallCountActive6month());
			//近6月通话次数>=10的号码数量
			tongDunReportRedis.setContactCountCallCountOver106month(tdReportAllContact.getContactCountCallCountOver106month());
			//近6月互通号码数量
			tongDunReportRedis.setContactCountMutual6month(tdReportAllContact.getContactCountMutual6month());
			//最频繁朋友圈通话时长占比 --> 6个月通话总时长
            tongDunReportRedis.setAllContactCallTime6month(StringUtils.isBlank(tdReportAllContact.getCallTime6month()) ?
					"0" : tdReportAllContact.getCallTime6month());
            // 拨出电话号码个数 2.0
            tongDunReportRedis.setCarrierCallOutCnt6m(tdReportAllContact.getContactCountActive6month());
            // 110通话情况 2.0
            tongDunReportRedis.setCarrier110AvgTime(null);
            // callInCnt01VsTotal_callCountActive6month 被除数
            tongDunReportRedis.setCarrierCallInCnt01VsTotal_callCountActive6month(tdReportAllContact.getCallCountActive6month());
            // carrierCallInCnt01VsTotal_callCountPassiveLateNight6month 除数
            tongDunReportRedis.setCarrierCallInCnt01VsTotal_callCountPassiveLateNight6month(tdReportAllContact.getCallCountPassiveLateNight6month());
		}
	}

	//保存每个月联系人统计表
	private void saveTdReportPerMonth(JsonRootBean jsonToBean, String applicationId, String dateTime, TongDunReportRedis tongDunReportRedis){
		List<All_contact_stats_per_month> allMonths = jsonToBean.getAll_contact_stats_per_month();
		if (allMonths != null && !allMonths.isEmpty()) {
			List<TdReportPerMonth> list = new ArrayList<TdReportPerMonth>();
			for (All_contact_stats_per_month month : allMonths) {
				TdReportPerMonth tdReportPerMonth = new TdReportPerMonth();
				BeanUtils.copyProperties(month, tdReportPerMonth);
				tdReportPerMonth.setApplicationId(applicationId);
				tdReportPerMonth.setPerMonthId(UUIDUtils.getUUID());
				tdReportPerMonth.setCreateTime(dateTime);
				tdReportPerMonth.setModifyTime(dateTime);
				list.add(tdReportPerMonth);
			}
			//批量保存
			int count = tdReportPerMonthDao.saveAllEntity(list);
			Assert.isTrue(count>0,"每个月联系人统计保存失败");
			tdReportRedisPerMonth(list, tongDunReportRedis);
		}
	}

	/**
	 * 保存风险联系人统计表
	 *
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 * @param checkList
	 */
	private void saveTdReportRiskContact(JsonRootBean jsonToBean,String applicationId,String dateTime,List<Map<String,Object>> checkList){
		List<Risk_contact_stats> riskStats = jsonToBean.getRisk_contact_stats();
		if (riskStats != null && !riskStats.isEmpty()) {
			List<TdReportRiskContact> list = new ArrayList<TdReportRiskContact>();
			for (Risk_contact_stats riskstats : riskStats) {
				TdReportRiskContact tdReportRiskContact = new TdReportRiskContact();
				BeanUtils.copyProperties(riskstats, tdReportRiskContact);
				tdReportRiskContact.setApplicationId(applicationId);
				tdReportRiskContact.setRiskContactId(UUIDUtils.getUUID());
				tdReportRiskContact.setCreateTime(dateTime);
				tdReportRiskContact.setModifyTime(dateTime);
				list.add(tdReportRiskContact);
				if (tdReportRiskContact.getRiskType().indexOf("催收") != -1) {
					//与催收号码通话次数
					BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_COLLECTION_COUNT,Integer.valueOf(tdReportRiskContact.getContactCount6month()));
				}
			}
			//保存
			int count = tdReportRiskContactDao.saveAllEntity(list);
			Assert.isTrue(count>0,"风险联系人统计表保存失败");
		}
	}

	/**
	 * 保存金融机构联系人统计表
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 */
	private void saveTdReportFinanceContact(JsonRootBean jsonToBean,String applicationId,String dateTime){
		List<Finance_contact_stats> financeStats = jsonToBean.getFinance_contact_stats();
		if (financeStats != null && !financeStats.isEmpty()) {
		List<TdReportFinanceContact> list = new ArrayList<TdReportFinanceContact>();
		for (Finance_contact_stats stats : financeStats) {
			TdReportFinanceContact tdReportFinanceContact = new TdReportFinanceContact();
			BeanUtils.copyProperties(stats, tdReportFinanceContact);
			tdReportFinanceContact.setApplicationId(applicationId);
			tdReportFinanceContact.setFinanceContactId(UUIDUtils.getUUID());
			tdReportFinanceContact.setCreateTime(dateTime);
			tdReportFinanceContact.setModifyTime(dateTime);
			list.add(tdReportFinanceContact);
		}
		//批量保存
		int count = tdReportFinanceContactDao.saveAllEntity(list);
		Assert.isTrue(count>0,"金融机构联系人统计保存失败");
		}
	}

	//保存联系人归属地统计（城市）统计表
	private void saveTdReportContactCity(JsonRootBean jsonToBean, String applicationId, String dateTime, TongDunReportRedis tongDunReportRedis){
		List<Contact_area_stats_per_city> perCitys = jsonToBean.getContact_area_stats_per_city();
		Integer callTime6monthNo1Sum = null; // 6个月 通话时长排名第一 的总和
		if (perCitys != null && !perCitys.isEmpty()) {
			List<TdReportContactCity> list = new ArrayList<TdReportContactCity>();
			Integer callCount6month = null;
			Integer blackCityCall = null;
            callTime6monthNo1Sum = 0;
			for (Contact_area_stats_per_city city : perCitys) {
				TdReportContactCity tdReportContactCity = new TdReportContactCity();
				BeanUtils.copyProperties(city, tdReportContactCity);
				tdReportContactCity.setApplicationId(applicationId);
				tdReportContactCity.setContactCityId(UUIDUtils.getUUID());
				tdReportContactCity.setCreateTime(dateTime);
				tdReportContactCity.setModifyTime(dateTime);
				list.add(tdReportContactCity);
				//获取6个月最大通话次数
				if (StringUtils.isNotBlank(tdReportContactCity.getCallCount6month()) ) {
					if (null == callCount6month) {
						callCount6month = 0;
					}
					if (callCount6month < Integer.valueOf(tdReportContactCity.getCallCount6month())) {
						callCount6month = Integer.valueOf(tdReportContactCity.getCallCount6month());
					}
                    if("1".equals(tdReportContactCity.getContactAreaSeqNo())){
                        callTime6monthNo1Sum += Integer.valueOf(tdReportContactCity.getCallTime6month());
                    }
				}
				//联系人黑名单城市通话次数
				if (StringUtils.isNotBlank(tdReportContactCity.getContactAreaCity()) && BlackCityUtils.checkBlackCity(tdReportContactCity.getContactAreaCity(), BlackCityUtils.BLICKCITY_ARR) && StringUtils.isNotBlank(tdReportContactCity.getCallCount6month())) {
					if (null == blackCityCall) {
						blackCityCall = 0;
					}
					blackCityCall = blackCityCall +Integer.valueOf(tdReportContactCity.getCallCount6month());
				}
			}
			//保存
			int count = tdReportContactCityDao.saveAllEntity(list);
			Assert.isTrue(count>0,"联系人归属地统计（城市）保存失败");

			//获取6个月最大通话次数
			tongDunReportRedis.setCarrierMaxContactAreaRatio(null == callCount6month ? null : String.valueOf(callCount6month));

			//联系人黑名单城市通话次数
			tongDunReportRedis.setCarrierContactAreaBlackCityRatio(null == blackCityCall ? null : String.valueOf(blackCityCall));

            //最频繁朋友圈通话时长占比 --> 通话时长排名第一的 总时长
            tongDunReportRedis.setContactCityCallTime6month(null == callTime6monthNo1Sum ? "0" : String.valueOf(callTime6monthNo1Sum));
		}
	}

	//保存同盾报告运营商消费统计表
	private void saveTdReportCarrierConsumption(JsonRootBean jsonToBean,String applicationId,String dateTime,List<Map<String,Object>> checkList){
		Carrier_consumption_stats consumptionStats = jsonToBean.getCarrier_consumption_stats();
		if (consumptionStats != null) {
			TdReportCarrierConsumption consumption = new TdReportCarrierConsumption();
			BeanUtils.copyProperties(consumptionStats, consumption);
			consumption.setApplicationId(applicationId);
			consumption.setCarrierConsumptionId(UUIDUtils.getUUID());
			consumption.setCreateTime(dateTime);
			consumption.setModifyTime(dateTime);
			//保存
			int count = tdReportCarrierConsumptionDao.saveEntity(consumption);
			Assert.isTrue(count>0,"同盾报告运营商消费统计表保存失败");
			//6个月话费总金额(消费)
			BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_PHONE_MONEY,Integer.valueOf(consumption.getConsumeAmount6month()));
		}
	}

	/**
	 * 保存同盾报告每个月运营商消费统计表
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 * @param tongDunReportRedis
	 */
	private void saveTdReportCarrierMonth(JsonRootBean jsonToBean,String applicationId,String dateTime,TongDunReportRedis tongDunReportRedis){
		List<Carrier_consumption_stats_per_month> carrierMonths = jsonToBean.getCarrier_consumption_stats_per_month();
		if (carrierMonths!=null && !carrierMonths.isEmpty()) {
			List<TdReportCarrierMonth> list = new ArrayList<TdReportCarrierMonth>();
			for (Carrier_consumption_stats_per_month month : carrierMonths) {
				TdReportCarrierMonth tdReportCarrierMonth = new TdReportCarrierMonth();
				BeanUtils.copyProperties(month, tdReportCarrierMonth);
				tdReportCarrierMonth.setApplicationId(applicationId);
				tdReportCarrierMonth.setCarrierMonthId(UUIDUtils.getUUID());
				tdReportCarrierMonth.setCreateTime(dateTime);
				tdReportCarrierMonth.setModifyTime(dateTime);
				list.add(tdReportCarrierMonth);
			}
			//保存
			int count = tdReportCarrierMonthDao.saveAllEntity(list);
			Assert.isTrue(count>0,"每个月运营商消费统计表保存失败");
			//欺诈排序
			tdReportRedisCarrierMonth(list, tongDunReportRedis);
		}
	}

	/**
	 * 保存同盾报告通话地区统计（城市）表
	 */
	private void saveTdReportCallCity(JsonRootBean jsonToBean, String applicationId, String dateTime, TongDunReportRedis tongDunReportRedis){
		List<Call_area_stats_per_city> callCitys = jsonToBean.getCall_area_stats_per_city();

		Integer carrierContactAreaNum = null;

		if (callCitys != null && !callCitys.isEmpty()) {
			List<TdReportCallCity> list = new ArrayList<TdReportCallCity>();
			Set<String> areaSet = new HashSet<>();
			for (Call_area_stats_per_city callCity : callCitys) {
				TdReportCallCity tdReportCallCity = new TdReportCallCity();
				BeanUtils.copyProperties(callCity, tdReportCallCity);
				tdReportCallCity.setApplicationId(applicationId);
				tdReportCallCity.setCallCityId(UUIDUtils.getUUID());
				tdReportCallCity.setCreateTime(dateTime);
				tdReportCallCity.setModifyTime(dateTime);
				list.add(tdReportCallCity);

				areaSet.add(callCity.getCallAreaCity());
			}
			carrierContactAreaNum = areaSet.size();
			//保存
			int count = tdReportCallCityDao.saveAllEntity(list);
			Assert.isTrue(count>0,"同盾报告通话地区统计（城市）表保存失败");
		}
		// 评分卡1.8
		tongDunReportRedis.setCarrierContactAreaNum(null == carrierContactAreaNum ? null : String.valueOf(carrierContactAreaNum));
	}

	//保存同盾报告静默活跃统计表
	private void saveTdReportSilenceStats(JsonRootBean jsonToBean, String applicationId, String dateTime, TongDunReportRedis tongDunReportRedis){
		Active_silence_stats silenceStats = jsonToBean.getActive_silence_stats();
		if (silenceStats != null) {
			TdReportSilenceStats tdReportSilenceStats = new TdReportSilenceStats();
			BeanUtils.copyProperties(silenceStats, tdReportSilenceStats);
			tdReportSilenceStats.setApplicationId(applicationId);
			tdReportSilenceStats.setSilenceStatsId(UUIDUtils.getUUID());
			tdReportSilenceStats.setCreateTime(dateTime);
			tdReportSilenceStats.setModifyTime(dateTime);
			int count = tdReportSilenceStatsDao.saveEntity(tdReportSilenceStats);
			Assert.isTrue(count>0,"同盾报告静默活跃统计表保存失败");
			//上传Redis
			String silenceDay0call0msgSend6month = tdReportSilenceStats.getSilenceDay0call0msgSend6month();
			if (StringUtils.isNotBlank(silenceDay0call0msgSend6month)) {
				tongDunReportRedis.setCarrierSilentAvgDays(silenceDay0call0msgSend6month);//近6月无通话和发送短信静默天数
				tongDunReportRedis.setSilenceDay0call0msgSend6month(silenceDay0call0msgSend6month); //近6月无通话和发送短信静默天数
			}
			String continueSilenceDayOver30call0msgSend6month = tdReportSilenceStats.getContinueSilenceDayOver30call0msgSend6month();
			if (StringUtils.isNotBlank(continueSilenceDayOver30call0msgSend6month)) {
				tongDunReportRedis.setCarrierSilentAvgTimes(continueSilenceDayOver30call0msgSend6month);//近6月连续无通话和发送短信静默>=3天的次数
				tongDunReportRedis.setContinueSilenceDayOver30call0msgSend6month(continueSilenceDayOver30call0msgSend6month); //近6月连续无通话和发送短信静默>=3天的次数
			}
			
			List<TdReportContinueSilence> list = new ArrayList<TdReportContinueSilence>();
			//近3月连续无通话静默>=3天的记录
			List<Continue_silence_day_over3_0call_3month_detail> detail1 = silenceStats.getContinue_silence_day_over3_0call_3month_detail();
			for (Continue_silence_day_over3_0call_3month_detail detail : detail1) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近3月连续无通话静默>=3天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近3月连续无通话静默>=15天的记录
			List<Continue_silence_day_over15_0call_3month_detail> detail6 = silenceStats.getContinue_silence_day_over15_0call_3month_detail();
			for (Continue_silence_day_over15_0call_3month_detail detail : detail6) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近3月连续无通话静默>=15天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近3月连续无主叫通话静默>=3天的记录
			List<Continue_silence_day_over3_0call_active_3month_detail> detail7 = silenceStats.getContinue_silence_day_over3_0call_active_3month_detail();
			for (Continue_silence_day_over3_0call_active_3month_detail detail : detail7) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近3月连续无主叫通话静默>=3天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近3月连续无主叫通话静默>=15天的记录
			List<Continue_silence_day_over15_0call_active_3month_detail> detail8 = silenceStats.getContinue_silence_day_over15_0call_active_3month_detail();
			for (Continue_silence_day_over15_0call_active_3month_detail detail : detail8) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近3月连续无主叫通话静默>=15天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近3月连续无通话和发送短信静默>=3天的记录
			List<Continue_silence_day_over3_0call_0msg_send_3month_detail> detail9 = silenceStats.getContinue_silence_day_over3_0call_0msg_send_3month_detail();
			for (Continue_silence_day_over3_0call_0msg_send_3month_detail detail : detail9) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近3月连续无通话和发送短信静默>=3天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近3月连续无通话和发送短信静默>=15天的记录
			List<Continue_silence_day_over15_0call_0msg_send_3month_detail> detail10 = silenceStats.getContinue_silence_day_over15_0call_0msg_send_3month_detail();
			for (Continue_silence_day_over15_0call_0msg_send_3month_detail detail : detail10) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近3月连续无通话和发送短信静默>=15天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近6月连续无通话静默>=3天的记录
			List<Continue_silence_day_over3_0call_6month_detail> detail11 = silenceStats.getContinue_silence_day_over3_0call_6month_detail();
			for (Continue_silence_day_over3_0call_6month_detail detail : detail11) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近6月连续无通话静默>=3天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近6月连续无通话静默>=15天的记录
			List<Continue_silence_day_over15_0call_6month_detail> detail12 = silenceStats.getContinue_silence_day_over15_0call_6month_detail();
			for (Continue_silence_day_over15_0call_6month_detail detail : detail12) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近6月连续无通话静默>=15天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近6月连续无主叫通话静默>=3天的记录
			List<Continue_silence_day_over3_0call_active_6month_detail> detail2 = silenceStats.getContinue_silence_day_over3_0call_active_6month_detail();
			for (Continue_silence_day_over3_0call_active_6month_detail detail : detail2) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近6月连续无主叫通话静默>=3天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近6月连续无主叫通话静默>=15天的记录
			List<Continue_silence_day_over15_0call_active_6month_detail> detail3 = silenceStats.getContinue_silence_day_over15_0call_active_6month_detail();
			for (Continue_silence_day_over15_0call_active_6month_detail detail : detail3) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近6月连续无主叫通话静默>=15天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近6月连续无通话和发送短信静默>=3天的记录
			List<Continue_silence_day_over3_0call_0msg_send_6month_detail> detail4 = silenceStats.getContinue_silence_day_over3_0call_0msg_send_6month_detail();
			for (Continue_silence_day_over3_0call_0msg_send_6month_detail detail : detail4) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近6月连续无通话和发送短信静默>=3天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			//近6月连续无通话和发送短信静默>=15天的记录
			List<Continue_silence_day_over15_0call_0msg_send_6month_detail> detail5 = silenceStats.getContinue_silence_day_over15_0call_0msg_send_6month_detail();
			for (Continue_silence_day_over15_0call_0msg_send_6month_detail detail : detail5) {
				TdReportContinueSilence tdReportContinueSilence = new TdReportContinueSilence();
				BeanUtils.copyProperties(detail, tdReportContinueSilence);
				tdReportContinueSilence.setContinueSilenceType("近6月连续无通话和发送短信静默>=15天的记录");
				tdReportContinueSilence.setApplicationId(applicationId);
				tdReportContinueSilence.setContinueSilenceId(UUIDUtils.getUUID());
				tdReportContinueSilence.setCreateTime(dateTime);
				tdReportContinueSilence.setModifyTime(dateTime);
				list.add(tdReportContinueSilence);
			}
			if (list != null && !list.isEmpty()) {
				//保存
				saveTdReportContinueSilence(list);
			}
		}
	}

	//保存同盾报告静默活跃时间记录信息表
	private void saveTdReportContinueSilence(List<TdReportContinueSilence> list){
		//保存
		int count = tdReportContinueSilenceDao.saveAllEntity(list);
		Assert.isTrue(count>0,"同盾报告静默活跃时间记录信息表保存失败");
	}

	//保存同盾报告各时间段通话统计（每2小时）表'
	private void saveTdReportCall2hour(JsonRootBean jsonToBean,String applicationId,String dateTime){
		Call_duration_stats_2hour stats2hour = jsonToBean.getCall_duration_stats_2hour();
		if (stats2hour != null) {
			List<TdReportCall2hour> list = new ArrayList<TdReportCall2hour>();
			TdReportCall2hour tdReportCall2hour1 = new TdReportCall2hour();
			Call_duration_holiday_3month holiday3month = stats2hour.getCall_duration_holiday_3month();
			BeanUtils.copyProperties(holiday3month, tdReportCall2hour1);
			tdReportCall2hour1.setCall2hourType("近3月工作日各时间段通话时长");
			tdReportCall2hour1.setApplicationId(applicationId);
			tdReportCall2hour1.setCall2hourId(UUIDUtils.getUUID());
			tdReportCall2hour1.setCreateTime(dateTime);
			tdReportCall2hour1.setModifyTime(dateTime);
			list.add(tdReportCall2hour1);
			Call_duration_holiday_6month holiday6month = stats2hour.getCall_duration_holiday_6month();
			TdReportCall2hour tdReportCall2hour2 = new TdReportCall2hour();
			BeanUtils.copyProperties(holiday6month, tdReportCall2hour2);
			tdReportCall2hour2.setCall2hourType("近6月节假日各时间段通话时长");
			tdReportCall2hour2.setApplicationId(applicationId);
			tdReportCall2hour2.setCall2hourId(UUIDUtils.getUUID());
			tdReportCall2hour2.setCreateTime(dateTime);
			tdReportCall2hour2.setModifyTime(dateTime);
			list.add(tdReportCall2hour2);
			Call_duration_workday_3month workday3month = stats2hour.getCall_duration_workday_3month();
			TdReportCall2hour tdReportCall2hour3 = new TdReportCall2hour();
			BeanUtils.copyProperties(workday3month, tdReportCall2hour3);
			tdReportCall2hour3.setCall2hourType("近3月工作日各时间段通话时长");
			tdReportCall2hour3.setApplicationId(applicationId);
			tdReportCall2hour3.setCall2hourId(UUIDUtils.getUUID());
			tdReportCall2hour3.setCreateTime(dateTime);
			tdReportCall2hour3.setModifyTime(dateTime);
			list.add(tdReportCall2hour3);
			Call_duration_workday_6month workday6month = stats2hour.getCall_duration_workday_6month();
			TdReportCall2hour tdReportCall2hour4 = new TdReportCall2hour();
			BeanUtils.copyProperties(workday6month, tdReportCall2hour4);
			tdReportCall2hour4.setCall2hourType("近6月工作日各时间段通话时长");
			tdReportCall2hour4.setApplicationId(applicationId);
			tdReportCall2hour4.setCall2hourId(UUIDUtils.getUUID());
			tdReportCall2hour4.setCreateTime(dateTime);
			tdReportCall2hour4.setModifyTime(dateTime);
			list.add(tdReportCall2hour4);
			int count = tdReportCall2hourDao.saveAllEntity(list);
			Assert.isTrue(count>0,"同盾报告各时间段通话统计（每2小时）表");
		}
	}

	/**
	 * 保存同盾报告出行记录分析（城市）表
	 * @param jsonToBean
	 * @param applicationId
	 * @param dateTime
	 */
	private void saveTdReportTravelCity(JsonRootBean jsonToBean,String applicationId,String dateTime){
		List<Travel_track_analysis_per_city> travelCitys= jsonToBean.getTravel_track_analysis_per_city();
		if (travelCitys != null && !travelCitys.isEmpty()) {
			List<TdReportTravelCity> list = new ArrayList<TdReportTravelCity>();
			for (Travel_track_analysis_per_city travelCity : travelCitys) {
				TdReportTravelCity tdReportTravelCity = new TdReportTravelCity();
				BeanUtils.copyProperties(travelCity, tdReportTravelCity);
				tdReportTravelCity.setApplicationId(applicationId);
				tdReportTravelCity.setTravelCityId(UUIDUtils.getUUID());
				tdReportTravelCity.setCreateTime(dateTime);
				tdReportTravelCity.setModifyTime(dateTime);
				list.add(tdReportTravelCity);
			}
			//保存
			int count = tdReportTravelCityDao.saveAllEntity(list);
			Assert.isTrue(count>0, "保存同盾报告出行记录分析（城市）表失败");
		}
	}
	
	/**
	 * 欺诈月使用量
	 * @param tdReportPerMonths
	 * @param tongDunReportRedis
	 */
	private void tdReportRedisPerMonth(List<TdReportPerMonth> tdReportPerMonths, TongDunReportRedis tongDunReportRedis){
		Collections.sort(tdReportPerMonths,new Comparator<TdReportPerMonth>() {
            @Override
            public int compare(TdReportPerMonth o1, TdReportPerMonth o2) {
                String a = o1.getMonth();
                String b = o2.getMonth();
                Date d1, d2;
				try {
					d1 = new SimpleDateFormat("yyyy-MM").parse(a);
					d2 = new SimpleDateFormat("yyyy-MM").parse(b);
				} catch (ParseException e) {
					throw new RuntimeException("日期转换错误");
				}
				if (d1.before(d2)) {
					return 1;
				} else {
					return -1;
				}
            }
        });
		Date date1;
		Date date2;
		try {
			if (null != tdReportPerMonths.get(0)) {
				date1 = new SimpleDateFormat("yyyy-MM").parse(tdReportPerMonths.get(0).getMonth()); 
		    	Date today = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
				String format = df.format(today);
				date2 = new SimpleDateFormat("yyyy-MM").parse(format);
				if (date1.getTime() == date2.getTime()) {
					tdReportPerMonths.remove(0);
				}
			}
		} catch (ParseException e) {
			throw new RuntimeException("日期转换错误");
		}
		
		Integer msgSendSum = null;//6个月短信发送
		Integer threeMsgCount = null;//近3个月短信数
		Integer sixMsgCount = null;//近6个月短信数

		Integer threeCallCountPassive = null;//呼入次数:近3月 （从申请上月起）
		Integer threeCallCountActive = null;//呼出次数:近3月 （从申请上月起）

		Integer callCountPassiveSix = null;// 呼入次数:近5月（从申请上月起）
		Integer callCountActiveSix = null;// 呼出次数:近5月（从申请上月起）

		Integer callCountSix = null;// 通话次数:近5月（从申请上月起）
		Integer callCountThree = null; // 通话次数:近3月（从申请上月起）

		Double maxAvgHcTime = 0d;//最大平均呼出时长
		Double avgHcTime = 0d;//平均呼出时长

		Double callInTime3m = 0d; // 呼入时长:近3月（从申请上月起）
		Double callInTime5m = 0d; // 呼入时长:近5月（从申请上月起）

		Integer minHrTime = null; //近5月（从申请上月起），最小月呼入时长
		Integer minCalInCnt6m = null ; // 近5月（从申请上月起）:最小呼入次数

		//第一个月
		String month1 = getMonth(-1);
		//第三个月
		String month3 = getMonth(-4);
		//第6个月
		String month6 = getMonth(-6);

		for (int i = 0; i < tdReportPerMonths.size(); i++) {
			TdReportPerMonth tdReportPerMonth = tdReportPerMonths.get(i);
			if (month6.compareTo(tdReportPerMonth.getMonth()) < 0) {

				if (StringUtils.isNotBlank(tdReportPerMonth.getMsgCountSendMobile())) {
					if (null == msgSendSum) {
						msgSendSum = 0;
					}
					msgSendSum = msgSendSum +Integer.valueOf(tdReportPerMonth.getMsgCountSendMobile());
				}
				if (StringUtils.isNotBlank(tdReportPerMonth.getMsgCount())) {

					if (month3.compareTo(tdReportPerMonth.getMonth()) <0) {
						if (null == threeMsgCount) {
							threeMsgCount = 0;
						}
						threeMsgCount = threeMsgCount + Integer.valueOf(tdReportPerMonth.getMsgCount());
					}
					if (null == sixMsgCount) {
						sixMsgCount = 0;
					}
					sixMsgCount = sixMsgCount + Integer.valueOf(tdReportPerMonth.getMsgCount());
				}
				if (StringUtils.isNotBlank(tdReportPerMonth.getCallCountPassive())) {
					if (month3.compareTo(tdReportPerMonth.getMonth()) < 0) {
						if (null == threeCallCountPassive) {
							threeCallCountPassive = 0;
						}
						threeCallCountPassive = threeCallCountPassive + Integer.valueOf(tdReportPerMonth.getCallCountPassive());

						if(null == threeCallCountActive){
							threeCallCountActive = 0;
						}
						threeCallCountActive = threeCallCountActive + Integer.valueOf(tdReportPerMonth.getCallCountActive());
					}
					if (null == callCountPassiveSix) {
						callCountPassiveSix = 0;
					}
					callCountPassiveSix = callCountPassiveSix + Integer.valueOf(tdReportPerMonth.getCallCountPassive());
				}
				if (StringUtils.isNotBlank(tdReportPerMonth.getCallCountActive())) {
					if (null == callCountActiveSix) {
						callCountActiveSix = 0;
					}
					callCountActiveSix = callCountActiveSix + Integer.valueOf(tdReportPerMonth.getCallCountActive());
				}
				if (StringUtils.isNoneBlank(tdReportPerMonth.getCallCount())) {
					if (null == callCountSix) {
						callCountSix = 0;
					}
					callCountSix = callCountSix + Integer.valueOf(tdReportPerMonth.getCallCount());
					//近 三个月 通话次数
					if (month3.compareTo(tdReportPerMonth.getMonth()) < 0) {
						if (null == callCountThree) {
							callCountThree = 0;
						}
						callCountThree = callCountThree + Integer.valueOf(tdReportPerMonth.getCallCount());
					}
				}

				String callTimeActive = tdReportPerMonth.getCallTimeActive();//呼出时长
				String callCountActive = tdReportPerMonth.getCallCountActive();//呼出次数
				if (StringUtils.isNotBlank(callTimeActive) && StringUtils.isNotBlank(callCountActive)) {
					Double callTime = Double.valueOf(callTimeActive);
					Double callCount = Double.valueOf(callCountActive);
					if (callCount != 0d) {
						avgHcTime = callTime/callCount;
					}
				}
				if (avgHcTime > maxAvgHcTime) {
					maxAvgHcTime = avgHcTime;
				}

				if (StringUtils.isNotBlank(tdReportPerMonth.getCallTimePassive())) {
					Integer callTimePassiveInt = Integer.valueOf(tdReportPerMonth.getCallTimePassive());
					if (null == minHrTime) {
						minHrTime = callTimePassiveInt;
					}
					if (minHrTime > callTimePassiveInt.intValue()) {
						minHrTime = callTimePassiveInt;
					}

					if (month3.compareTo(tdReportPerMonth.getMonth()) < 0) {
						callInTime3m = callInTime3m + callTimePassiveInt;
					}
					callInTime5m = callInTime5m + callTimePassiveInt;
				}

				if(StringUtils.isNotBlank(tdReportPerMonth.getCallCountPassive())){
					Integer tempCallCountPassive = Integer.valueOf(tdReportPerMonth.getCallCountPassive());
					if(null == minCalInCnt6m ){
						minCalInCnt6m = tempCallCountPassive;
					}
					if(minCalInCnt6m.intValue() > tempCallCountPassive.intValue()){
						minCalInCnt6m = tempCallCountPassive;
					}
				}
			}
		}
		//近5月（从申请上月起），最大平均呼出时长
		tongDunReportRedis.setCarrierMaxCallOutAvgTime6m(0d == maxAvgHcTime ? null : String.valueOf(maxAvgHcTime));
		//6个月短信发送记录
		tongDunReportRedis.setCarrierSmsOutCnt6m(null == msgSendSum ? null : String.valueOf(msgSendSum));
		tongDunReportRedis.setCarrierSmOutCnt6m(null == msgSendSum ? null : String.valueOf(msgSendSum));
		if (!CollectionUtils.isEmpty(tdReportPerMonths)) {
			TdReportPerMonth tdReportPerMonth = tdReportPerMonths.get(0);
			if (null != tdReportPerMonth && (month1.compareTo(tdReportPerMonth.getMonth())==0)) {
				tongDunReportRedis.setCarrierCallTime1m(tdReportPerMonth.getCallTime());//1月通话次数
				tongDunReportRedis.setCarrierTotalOut1m(tdReportPerMonth.getCallTimeActive());//1月呼出时间时长
				tongDunReportRedis.setCarrierCallIn1mRatio(tdReportPerMonth.getCallCountPassive());//1月呼入次数
				tongDunReportRedis.setCarrierCallOutCnt1mRatio(tdReportPerMonth.getCallCountActive());//1月呼出次数

				//评分卡 1.8
				tongDunReportRedis.setCarrierCallTime1m(tdReportPerMonth.getCallTime());// 近1月（从申请上月起）:通话时长
				tongDunReportRedis.setCarrierCallCount1m(tdReportPerMonth.getCallCount());// 近1月（从申请上月起）: 通话次数
				tongDunReportRedis.setCarrierCallIn1mRatio(tdReportPerMonth.getCallCountPassive());//1月呼入次数
				tongDunReportRedis.setCarrierCallOutCnt1mRatio(tdReportPerMonth.getCallCountActive());//1月呼出次数
				tongDunReportRedis.setCarrierCallInTime1mRatio(tdReportPerMonth.getCallTimePassive()); //呼入时长:近1月

			}
			//呼入次数:近5月（从申请上月起）
			tongDunReportRedis.setCarrierCallInCnt6mRatio(null == callCountPassiveSix ? null : String.valueOf(callCountPassiveSix));
			//短信数:近3月（从申请上月起）
			tongDunReportRedis.setCarrierSmsCnt3mRatio(null == threeMsgCount ? null : String.valueOf(threeMsgCount));
			//短信数:近5月（从申请上月起）
			tongDunReportRedis.setCarrierSmsCnt6mRatio(null == sixMsgCount ? null : String.valueOf(sixMsgCount));
			//呼入次数:近3月（从申请上月起）
			tongDunReportRedis.setCarrierCallInCnt3mRatio(null == threeCallCountPassive ? null : String.valueOf(threeCallCountPassive));
			//近5月（从申请上月起）:呼出次数
			tongDunReportRedis.setCarrierCallOut6mRatio(null == callCountActiveSix ? null : String.valueOf(callCountActiveSix));
			//近5月（从申请上月起）:通话次数
			tongDunReportRedis.setCarrierCallCnt6mRatio(null == callCountSix ? null : String.valueOf(callCountSix));
			//近5月（从申请上月起），最小月呼入时长
			tongDunReportRedis.setCarrierMinCallInTime6m(null == minHrTime ? null : String.valueOf(minHrTime));

			//评分卡 1.8
			//近3月（从申请上月起）:通话次数
			tongDunReportRedis.setCarrierCallCnt3mRatio(null == callCountThree ? null : String.valueOf(callCountThree));
			//近5月（从申请上月起）:通话次数
			tongDunReportRedis.setCarrierCallCnt6mRatio(null == callCountSix ? null : String.valueOf(callCountSix));

			//呼入次数:近3月（从申请上月起）
			tongDunReportRedis.setCarrierCalInCnt3mRatio(null == threeCallCountPassive ? null : String.valueOf(threeCallCountPassive));
			//呼入次数:近3月（从申请上月起）
			tongDunReportRedis.setCarrierCallOutCnt3mRatio(null == threeCallCountActive ? null : String.valueOf(threeCallCountActive));

			//呼入次数:近5月（从申请上月起）
			tongDunReportRedis.setCarrierCallInCnt6mRatio(null == callCountPassiveSix ? null : String.valueOf(callCountPassiveSix));
			//呼出次数:近5月（从申请上月起）
			tongDunReportRedis.setCarrierCallOutCnt6mRatio(null == callCountActiveSix ? null : String.valueOf(callCountActiveSix));

			//呼入时长:近3月(从申请上月起)
			tongDunReportRedis.setCarrierCallInTime3mRatio(null == callInTime3m ? null : String.valueOf(callInTime3m));
			//呼入时长:近5月(从申请上月起)
			tongDunReportRedis.setCarrierCallInTime5mRatio(null == callInTime5m ? null : String.valueOf(callInTime5m));
			// 近5月（从申请上月起）:最小呼入次数
			tongDunReportRedis.setCarrierMinCallInCnt6m(null == minCalInCnt6m ? null :  String.valueOf(minCalInCnt6m));
		}
	}

	/**
	 * 欺诈月话费使用量
	 * @param tdReportCarrierMonths
	 * @param tongDunReportRedis
	 */
	private void tdReportRedisCarrierMonth(List<TdReportCarrierMonth> tdReportCarrierMonths,TongDunReportRedis tongDunReportRedis){
		Collections.sort(tdReportCarrierMonths,new Comparator<TdReportCarrierMonth>() {
            @Override
            public int compare(TdReportCarrierMonth o1, TdReportCarrierMonth o2) {
                String a = o1.getMonth();
                String b = o2.getMonth();
                Date d1, d2;
				try {
					 d1 = new SimpleDateFormat("yyyy-MM").parse(a); 
					 d2 = new SimpleDateFormat("yyyy-MM").parse(b); 
				} catch (ParseException e) {
					throw new RuntimeException("日期转换错误");
				}
    			 if (d1.before(d2)) {
    		            return 1;
    		        } else {
    		            return -1;
    		        }
            }
        });
		Date date1;
		Date date2;
		try {
			if (null != tdReportCarrierMonths.get(0)) {
				date1 = new SimpleDateFormat("yyyy-MM").parse(tdReportCarrierMonths.get(0).getMonth()); 
		    	Date today = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
				String format = df.format(today);
				date2 = new SimpleDateFormat("yyyy-MM").parse(format);
				if (date1.getTime() == date2.getTime()) {
					tdReportCarrierMonths.remove(0);
				}
			}
			
		} catch (ParseException e) {
			throw new RuntimeException("日期转换错误");
		} 
		Integer maxConsumeAmount = null;//最大话费金额
		Double threeConsumeAmount = null;//3个月话费金额
		Double sixConsumeAmount = null;//6个月话费金额
		//第一个月
		String month1 = getMonth(-1);
		//第三个月
		String month3 = getMonth(-4);
		//第6个月
		String month6 = getMonth(-6);
		for (int i = 0; i < tdReportCarrierMonths.size(); i++) {
			if (month6.compareTo(tdReportCarrierMonths.get(i).getMonth())<0) {
				String consumeAmount = tdReportCarrierMonths.get(i).getConsumeAmount();
			if (StringUtils.isNotBlank(consumeAmount) ) {
				
				if (null == maxConsumeAmount) {
					maxConsumeAmount = 0;
				}
				if (maxConsumeAmount<Integer.valueOf(consumeAmount)) {
					maxConsumeAmount = Integer.valueOf(consumeAmount);
				}
			}
			if (StringUtils.isNotBlank(consumeAmount)) {
			if (month3.compareTo(tdReportCarrierMonths.get(i).getMonth())<0) {
				if (null == threeConsumeAmount) {
					threeConsumeAmount = 0.0d;
				}
					threeConsumeAmount = threeConsumeAmount + Double.valueOf(consumeAmount);
				}
			if (null == sixConsumeAmount) {
				sixConsumeAmount = 0.0d;
			}
			sixConsumeAmount = sixConsumeAmount + Double.valueOf(consumeAmount);
			}
			}
		}
		if (!CollectionUtils.isEmpty(tdReportCarrierMonths)) {
			//近5月（从申请上月起），最大月话费金额
			tongDunReportRedis.setCarrierMaxPhoneBill6m(null == maxConsumeAmount ? null : String.valueOf(maxConsumeAmount));
			//话费金额:近1月（从申请上月起）/近3月（从申请上月起）
			TdReportCarrierMonth tdReportCarrierMonth = tdReportCarrierMonths.get(0);
			if (null != tdReportCarrierMonth&&(month1.compareTo(tdReportCarrierMonth.getMonth())==0)) {
				String consumeAmount = tdReportCarrierMonth.getConsumeAmount();
				//话费金额:近1月（从申请上月起）
				tongDunReportRedis.setCarrierPhoneBill1mRatio(null == consumeAmount ? null : consumeAmount);
			}
			tongDunReportRedis.setCarrierPhoneBill3mRatio(null == threeConsumeAmount ? null : String.valueOf(threeConsumeAmount));
			//话费金额:近5月（从申请上月起）
			tongDunReportRedis.setCarrierPhoneBill6mRatio(null == sixConsumeAmount ? null : String.valueOf(sixConsumeAmount));
		}
	}

	public String getMonth(int a){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, a);
		Date time = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String format = df.format(time);
		return format;
	}
}
