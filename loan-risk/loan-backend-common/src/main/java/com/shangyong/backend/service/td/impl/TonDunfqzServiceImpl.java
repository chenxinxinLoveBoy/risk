package com.shangyong.backend.service.td.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangyong.backend.entity.redis.fraud1_8.TongDun18Redis;
import com.shangyong.backend.entity.redis.fraud2_0.TongDun20Redis;
import com.shangyong.backend.entity.redis.fraud2_0.BasicInfo20Redis;

import com.shangyong.backend.utils.BanCodeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RedisConstant;
import com.shangyong.backend.common.RuleConstants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.common.enums.FraudBizEnum;
import com.shangyong.backend.dao.td.TdAntiFraudServiceDao;
import com.shangyong.backend.dao.td.TdBlackServiceDao;
import com.shangyong.backend.dao.td.TdCustomDetailServiceDao;
import com.shangyong.backend.dao.td.TdDiscreditServiceDao;
import com.shangyong.backend.dao.td.TdFrequencyDetailServiceDao;
import com.shangyong.backend.dao.td.TdFuzzyBlackServiceDao;
import com.shangyong.backend.dao.td.TdGreyServiceDao;
import com.shangyong.backend.dao.td.TdPlatformDetailServiceDao;
import com.shangyong.backend.dao.td.TdPlatformDimensionServiceDao;
import com.shangyong.backend.dao.td.TdPlatformServiceDao;
import com.shangyong.backend.dao.td.TdRiskItemsServiceDao;
import com.shangyong.backend.dao.td.TdSuspectTeamServiceDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.redis.fraud1_1.BasicInfo11Redis;
import com.shangyong.backend.entity.redis.fraud1_1.TongDun11Redis;
import com.shangyong.backend.entity.redis.fraud1_8.BasicInfo18Redis;
import com.shangyong.backend.entity.td.TdAntiFraud;
import com.shangyong.backend.entity.td.TdBlack;
import com.shangyong.backend.entity.td.TdCustomDetail;
import com.shangyong.backend.entity.td.TdDiscredit;
import com.shangyong.backend.entity.td.TdFrequencyDetail;
import com.shangyong.backend.entity.td.TdFuzzyBlack;
import com.shangyong.backend.entity.td.TdGrey;
import com.shangyong.backend.entity.td.TdPlatform;
import com.shangyong.backend.entity.td.TdPlatformDetail;
import com.shangyong.backend.entity.td.TdPlatformDimension;
import com.shangyong.backend.entity.td.TdRiskItems;
import com.shangyong.backend.entity.td.TdSuspectTeam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.service.td.TonDunfqzService;
import com.shangyong.backend.utils.SpringIocUtils;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RedisFraudUtils;
import com.shangyong.utils.RiskHttpClientUtil;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 同盾反欺诈信息处理service类
 * @author 艾李强
 *
 */
@Service
public class TonDunfqzServiceImpl implements TonDunfqzService{
	
	private static Logger logger = LoggerFactory.getLogger("td");

	@Autowired
	TdAntiFraudServiceDao tdAntiFraudServiceDao;
	@Autowired
	TdRiskItemsServiceDao tdRiskItemsServiceDao;
	@Autowired
	TdCustomDetailServiceDao tdCustomDetailServiceDao;
	@Autowired
	TdSuspectTeamServiceDao tdSuspectTeamServiceDao;
	@Autowired
	TdGreyServiceDao tdGreyServiceDao;
	@Autowired
	TdFuzzyBlackServiceDao tdFuzzyBlackServiceDao;
	@Autowired
	TdFrequencyDetailServiceDao tdFrequencyDetailServiceDao;
	@Autowired
	TdDiscreditServiceDao tdDiscreditServiceDao;
	@Autowired
	TdPlatformServiceDao tdPlatformServiceDao;
	@Autowired
	TdBlackServiceDao tdBlackServiceDao;
	@Autowired
	TdPlatformDetailServiceDao tdPlatformDetailServiceDao;
	@Autowired
	TdPlatformDimensionServiceDao tdPlatformDimensionServiceDao;
	@Autowired
	private JsonReportService jsonReportService;
	@Autowired
	private RiskRuleService riskRuleService;
	
	@Override
	public RuleResult queryFqz(Application application) {
		RuleResult ruleResult = new RuleResult();
		List<Map<String,Object>> checkList = new ArrayList<Map<String,Object>>();

		TongDun11Redis tongDun11Redis = new TongDun11Redis();
		TongDun18Redis tongDun18Redis = new TongDun18Redis();
		TongDun20Redis tongDun20Redis = new TongDun20Redis();

		logger.info("[同盾反欺诈] 开始,applicationId = " + application.getApplicationId());
		try {
			SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
			SysParam tdAntIfRaud = sysParamRedisService.querySysParamByParamValueRedis(Constants.TD_ANTIFRAUD_CODE);

			//判断同盾配置信息返回是否为空
			if (tdAntIfRaud == null) {
				logger.error("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 配置参数获取失败:" + Constants.TD_ANTIFRAUD_CODE);
				throw new RuntimeException("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 配置参数获取失败");
			}

			// 系统配置参数
			String restUrl=tdAntIfRaud.getParamValueOne();
			String partnerKey = tdAntIfRaud.getParamValueTwo();
			String source = application.getSource();
			String appName = null;
			if (StringUtils.isBlank(source)) {
				throw new RuntimeException("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 传参错误,请按照:申请来源（0——Android；1——IOS）");
			}

			if ("0".equals(application.getSource())) {//anndroid
				appName =tdAntIfRaud.getParamValueThree();
			}else{//ios
				appName =tdAntIfRaud.getParamValueFive();
			}

			String partnerCode = tdAntIfRaud.getParamValueFour();
			String url = restUrl+"?partner_code="+partnerCode+"&partner_key="+partnerKey+"&app_name="+appName;
			
			// 正常请求数据
			Map<String,String> params = new HashMap<String,String>();
			String certType = application.getCertType();//身份证类型
			String certCode = application.getCertCode();//身份证号
			String phoneNum = application.getPhoneNum();//手机号码
			String name = application.getName();//客户名称
			String applicationId = application.getApplicationId();//申请id

			//存入redis
			String basicInfoRedisKey = RedisConstant.buildFraudScoresKey(applicationId, FraudBizEnum.BASIC_INFO);//FRAUD:SCORES:申请id:basicInfo
			BasicInfo11Redis basicInfoRedis = new BasicInfo11Redis();
			basicInfoRedis.setBirthProvinceId(certCode);//判断生日年月
			basicInfoRedis.setIsMale(certCode);//判断男女
			RedisFraudUtils.hmset(basicInfoRedisKey, basicInfoRedis.toMap());

		    //1.8版本身份证判断男女,生日省份存入redis
		    String buildFraudScoresKey1_8 = RedisConstant.buildFraudScoresKey1_8(applicationId, FraudBizEnum.BASIC_INFO);
			BasicInfo18Redis basicInfo18Redis = new BasicInfo18Redis();
			basicInfo18Redis.setIsMale(certCode);
			basicInfo18Redis.setBirthProvinceId(certCode);
		    RedisFraudUtils.hmset(buildFraudScoresKey1_8, basicInfoRedis.toMap());

		    //2.0版本身份证判断男女,生日省份存入redis
		    String buildFraudScoresKey2_0 = RedisConstant.buildFraudScoresKey2_0(applicationId, FraudBizEnum.BASIC_INFO);
			BasicInfo20Redis basicInfo20Redis = new BasicInfo20Redis();
			basicInfo20Redis.setIsMale(certCode);
			basicInfo20Redis.setBirthProvinceId(certCode);
		    RedisFraudUtils.hmset(buildFraudScoresKey2_0, basicInfo20Redis.toMap());

			//判断证件类型
			Assert.isTrue("1".equals(certType), "证件类型异常,类型必须是身份证");
			Assert.hasText(certCode, "身份证号码不能为空");
			Assert.hasText(phoneNum, "手机号码不能为空");
			Assert.hasText(name, "客户姓名不能为空");
			Assert.hasText(applicationId, "申请单号不能为空");

			// 添加参数
			params.put("account_name",name);
			params.put("id_number",certCode);
			params.put("account_mobile", phoneNum);
			
			logger.info("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 调用同盾信贷保镖第三方请求参数:" + params);
			String result = RiskHttpClientUtil.doPost(url, params);
			
			Assert.hasText(result, "[同盾反欺诈] [applicationId=" + application.getApplicationId() + "]调用第三方返回数据为空");

			//开始数据解析数据入库
			JSONObject resultJson = JSONObject.fromObject(result);
			Boolean isSuccess=resultJson.getBoolean("success");
			if(!isSuccess){
				throw new RuntimeException("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 配置请求false");
			}

			//保存同盾反欺诈主表
			TdAntiFraud tdAntiFraud = new TdAntiFraud();
			String tdAntiFraudId = UUIDUtils.getUUID();
			tdAntiFraud.setApplicationId(applicationId);
			tdAntiFraud.setTdAntiFraudId(tdAntiFraudId);
			tdAntiFraud.setCreateTime(DateUtils.getDate(new Date()));
			tdAntiFraud.setTdId(resultJson.getString("id"));//同盾请求成功后的进件ID

			JSONObject antiJsonInfo = resultJson.getJSONObject("result_desc").getJSONObject("ANTIFRAUD");

			tdAntiFraud.setFinalDecision(antiJsonInfo.getString("final_decision"));
			tdAntiFraud.setFinalScore(antiJsonInfo.getString("final_score"));

			//添加禁止项数据 同盾反欺诈总分
			BanCodeUtil.addCheckPoint(checkList,BanCodeEnum.TONG_DUN_TOTAL_RISK_SCORE_SAME_SHIELD_AGAINST_BULLYING.getCode(),Integer.valueOf(antiJsonInfo.getString("final_score")));

			//是否命中   ps:这里先写死
			tdAntiFraud.setState("1");
			int count = tdAntiFraudServiceDao.saveEntity(tdAntiFraud);
			if(count > 0){
				logger.info("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 数据入库成功");
				if(antiJsonInfo.has("risk_items")){
					JSONArray riskItemsArray = antiJsonInfo.getJSONArray("risk_items");
					tongDun18Redis.initZero();
					tongDun20Redis.initZero();
					saveRiskItems(tdAntiFraudId, riskItemsArray, checkList,tongDun11Redis, tongDun18Redis,tongDun20Redis);
				}
			}

			//调用禁止项，获取用户校验结果
			ruleResult = riskRuleService.querySafeRuleApi(application,checkList);
			
			if(ruleResult == null){
				throw new RuntimeException("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 设备指纹数据报告获取, 调用taskCallBackService, 返回值 resultObj 为空");
			}

			//存阿里云 mongodb
	        jsonReportService.uploadJson(Constants.TD_UPLOAD_DIR, resultJson, TaskTypeConstants.TD_TASK_TYPE, TaskTypeConstants.TD_TASK_NAME, TaskTypeConstants.TD_TASK_ISEND, application.getApplicationId(), "noext");

			//上传Reids
	        //1.1评分卡
	        String key11 = RedisConstant.buildFraudScoresKey(applicationId, FraudBizEnum.TONG_DUN);
	        RedisFraudUtils.hmset(key11, tongDun11Redis.toMap());
	       
	        //1.8评分卡
	        String key18 = RedisConstant.buildFraudScoresKey1_8(applicationId, FraudBizEnum.TONG_DUN);
	        RedisFraudUtils.hmset(key18, tongDun18Redis.toMap());
	        
	        //2.0评分卡
	        String key20 = RedisConstant.buildFraudScoresKey2_0(applicationId, FraudBizEnum.TONG_DUN);
			RedisFraudUtils.hmset(key20, tongDun20Redis.toMap());
		} catch (Exception e) {
			logger.error("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 异常", e);
			throw new RuntimeException("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 信贷保镖获取数据失败:"+ e.getMessage());
		}
		logger.info("[同盾反欺诈] [applicationId=" + application.getApplicationId() + "] 处理完成");
		return ruleResult;
	}

	/**
	 * 保存具体风险信息
	 * @param tdAntiFraudId
	 * @param riskItemsArray
	 * @param checkList 
	 */
	private void saveRiskItems(String tdAntiFraudId, JSONArray riskItemsArray, List<Map<String, Object>> checkList,TongDun11Redis tongdun11Redis, TongDun18Redis tongDun18Redis,TongDun20Redis tongDun20Redis) {
		for (int i = 0; i < riskItemsArray.size(); i++) {
			JSONObject riskJson = riskItemsArray.getJSONObject(i);
			TdRiskItems tdRiskItems = new TdRiskItems();
			String riskItemsId = UUIDUtils.getUUID();
			tdRiskItems.setTdRiskItemsId(riskItemsId);
			tdRiskItems.setTdAntiFraudId(tdAntiFraudId);
			tdRiskItems.setRiskName(riskJson.getString("risk_name"));
			tdRiskItems.setRuleId(riskJson.getString("rule_id"));
			tdRiskItems.setScore(riskJson.getString("score"));
			tdRiskItems.setDecision(riskJson.getString("decision"));
			if (!riskJson.has("risk_detail")) {
				continue;
			}
			JSONArray riskDetails = riskJson.getJSONArray("risk_detail");
			String riskType="";
			if(riskDetails.size() > 0){
				riskType = riskDetails.getJSONObject(0).getString("type");
				tdRiskItems.setRiskType(riskType);
			}
			int count = tdRiskItemsServiceDao.saveEntity(tdRiskItems);
			//保存各个不同类型风险详情
			if(count > 0 && !StringUtils.isBlank(riskType)){
				saveRiskDetail(riskItemsId, riskType, riskDetails, checkList,tongdun11Redis, tongDun18Redis,tongDun20Redis);
			}
		}
	}

	/**
	 * 根据不同的风险类型 解析jsonArray  入库数据
	 * @param riskItemsId
	 * @param riskType
	 * @param riskDetails
	 * @param checkList 
	 */
	private void saveRiskDetail(String riskItemsId, String riskType, JSONArray riskDetails, List<Map<String, Object>> checkList,TongDun11Redis tongdun11Redis, TongDun18Redis tongDun18Redis,TongDun20Redis tongDun20Redis) {
		try{
			switch (riskType) {
				case "custom_list":
					saveCustom(riskItemsId,riskDetails);
					break;
				case "suspected_team":
					saveSuspected(riskItemsId,riskDetails);
					break;
				case "grey_list":
					saveGrey(riskItemsId,riskDetails);
					break;
				case "fuzzy_black_list":
					saveFuzzyBlack(riskItemsId,riskDetails);
					break;
				case "frequency_detail":
					saveFrequency(riskItemsId,riskDetails,"frequency_detail_list");
					break;
				case "cross_frequency_detail":
					saveFrequency(riskItemsId,riskDetails,"cross_frequency_detail_list");
					break;
				case "cross_event_detail":
					saveFrequency(riskItemsId,riskDetails,"cross_event_detail_list");
					break;
				case "discredit_count":
					saveDiscredit(riskItemsId,riskDetails);
					break;
				case "platform_detail":
					savePlatform(riskItemsId, riskDetails, checkList,tongdun11Redis,tongDun18Redis,tongDun20Redis);
					break;
				case "black_list":
					saveBlackList(riskItemsId,riskDetails);
					break;
			}
		}catch(Exception e){
			logger.error("同盾信息入库异常riskType:"+riskType+">>message:"+e.getMessage(), e);
			throw new RuntimeException("同盾信息入库异常"+e.getMessage(), e);
		}
	}

	private void saveBlackList(String riskItemsId, JSONArray riskDetails) {
		for (int i = 0; i < riskDetails.size(); i++) {
			JSONObject blackListJson=riskDetails.getJSONObject(i);
			if(blackListJson.has("court_details")){
				JSONArray blackDetailArray=blackListJson.getJSONArray("court_details");
				for (int j = 0; j < blackDetailArray.size(); j++) {
					JSONObject blackJson=blackDetailArray.getJSONObject(j);
					TdBlack black=new TdBlack();
					black.setTdRiskItemsId(riskItemsId);
					if(blackListJson.has("description")){
						black.setDescription(blackListJson.getString("description"));
					}
					if(blackListJson.has("hit_type_display_name")){
						black.setHitTypeDisplayName(blackListJson.getString("hit_type_display_name"));
					}
                    black.setTdBlackId(UUIDUtils.getUUID());
					if(blackJson.has("value")){
						black.setValue(blackJson.getString("value"));
					}
					if(blackJson.has("fraud_type")){
						black.setFraudType(blackJson.getString("fraud_type"));
					}
					if(blackJson.has("fraud_type_display_name")){
						black.setFraudTypeDisplayName(blackJson.getString("fraud_type_display_name"));
					}
					if(blackJson.has("executed_name")){
						black.setExecutedName(blackJson.getString("executed_name"));
					}
					if(blackJson.has("age")){
						black.setAge(blackJson.getString("age"));
					}
					if(blackJson.has("gender")){
						black.setGender(blackJson.getString("gender"));
					}
					if(blackJson.has("province")){
						black.setProvince(blackJson.getString("province"));
					}
					if(blackJson.has("case_date")){
						black.setCaseDate(blackJson.getString("case_date"));
					}
					if(blackJson.has("execute_court")){
						black.setExecuteCourt(blackJson.getString("execute_court"));
					}
					if(blackJson.has("term_duty")){
						black.setTermDuty(blackJson.getString("term_duty"));
					}
					if(blackJson.has("execute_subject")){
						black.setExecuteSubject(blackJson.getString("execute_subject"));
					}
					if(blackJson.has("execute_status")){
						black.setExecuteStatus(blackJson.getString("execute_status"));
					}
					if(blackJson.has("evidence_court")){
						black.setEvidenceCourt(blackJson.getString("evidence_court"));
					}
					if(blackJson.has("carry_out")){
						black.setCarryOut(blackJson.getString("carry_out"));
					}
					if(blackJson.has("specific_circumstances")){
						black.setSpecificCircumstances(blackJson.getString("specific_circumstances"));
					}
					if(blackJson.has("execute_code")){
						black.setExecuteCode(blackJson.getString("execute_code"));
					}
					if(blackJson.has("case_code")){
						black.setCaseCode(blackJson.getString("case_code"));
					}
					if(blackJson.has("evidence_time")){
						black.setEvidenceTime(blackJson.getString("evidence_time"));
					}
					tdBlackServiceDao.saveEntity(black);
				}
			}
		}
	}

	private void savePlatform(String riskItemsId, JSONArray riskDetails, List<Map<String, Object>> checkList,TongDun11Redis tongDun11Redis, TongDun18Redis tongDun18Redis,TongDun20Redis tongDun20Redis) {
		for (int i = 0; i < riskDetails.size(); i++) {
			JSONObject platformJson = riskDetails.getJSONObject(i);
			TdPlatform tdPlatform = new TdPlatform();
			String platformId = UUIDUtils.getUUID();
			tdPlatform.setTdPlatformId(platformId);
			tdPlatform.setTdRiskItemsId(riskItemsId);
			if(platformJson.has("platform_count")){
				tdPlatform.setPlatformCount(platformJson.getString("platform_count"));
			}

			if(platformJson.has("description")){
				tdPlatform.setDescription(platformJson.getString("description"));
			}

			if ("7天内申请人在多个平台申请借款".equals(platformJson.getString("description"))) {
				String temp = platformJson.getString("platform_count");
				//7天内申请数
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_SEVEN_COUNT, Integer.valueOf(temp));
				//评分卡 1.8 7天多头
				tongDun18Redis.setTdTotal07(temp);
				//评分卡 2.0 7天多头
				tongDun20Redis.setTdTotal07(temp);
			}
			if ("1个月内申请人在多个平台申请借款".equals(platformJson.getString("description"))) {
				String temp = platformJson.getString("platform_count");
				//30天内申请数
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_THIRTY_COUNT, Integer.valueOf(temp));
				//评分卡 1.8 
				tongDun18Redis.setTdTotal30(temp);
				//评分卡 2.0
				tongDun20Redis.setTdTotal30(temp);
				if(platformJson.has("platform_detail")){
					JSONArray detailArray = platformJson.getJSONArray("platform_detail");
					//评分卡 1.8 
					tongDun18Redis.setTdX030(String.valueOf(detailArray.size()));
					//评分卡 2.0
					tongDun20Redis.setTdX030(String.valueOf(detailArray.size()));
					for (int x = 0; x <detailArray.size(); x++) {
						JSONObject platformDetail = detailArray.getJSONObject(x);
						if(platformDetail.has("industry_display_name")){
							if ("一般消费分期平台".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.1 
									tongDun11Redis.setTdX330(platformDetail.getString("count"));
									//评分卡 1.8 
									tongDun18Redis.setTdX330(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX330(platformDetail.getString("count"));
								}
							}
							if("小额贷款公司".equals(platformDetail.getString("industry_display_name"))){
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8 
									tongDun18Redis.setTdX130(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX130(platformDetail.getString("count"));
								}
							}
							if("P2P网贷".equals(platformDetail.getString("industry_display_name"))){
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8
									tongDun18Redis.setTdX230(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX230(platformDetail.getString("count"));
								}
							}
						}
					}
				}
			}
			if ("3个月内申请人在多个平台申请借款".equals(platformJson.getString("description"))) {
				String temp = platformJson.getString("platform_count");
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_NINETY_COUNT,Integer.valueOf(temp));//90天内申请数
				
				//评分卡 1.8
				tongDun18Redis.setTdTotal90(temp);
				//评分卡 2.0
				tongDun20Redis.setTdTotal90(temp);
				
				if(platformJson.has("platform_detail")){
					JSONArray detailArray = platformJson.getJSONArray("platform_detail");
					for (int x = 0; x <detailArray.size(); x++) {
						JSONObject platformDetail = detailArray.getJSONObject(x);
						if(platformDetail.has("industry_display_name")){
							if("小额贷款公司".equals(platformDetail.getString("industry_display_name"))){
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8
									tongDun18Redis.setTdX190(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX190(platformDetail.getString("count"));
								}
							}
							if ("P2P网贷".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8
									tongDun18Redis.setTdX290(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX290(platformDetail.getString("count"));
								}
							}
							if ("一般消费分期平台".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8
									tongDun18Redis.setTdX390(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX390(platformDetail.getString("count"));
								}
							}
							if ("银行消费金融公司".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8
									tongDun18Redis.setTdX590(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX590(platformDetail.getString("count"));
								}
							}
							if ("互联网金融门户".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8
									tongDun18Redis.setTdX790(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX790(platformDetail.getString("count"));
								}
							}
						}
					}
				}
			}
			if ("6个月内申请人在多个平台申请借款".equals(platformJson.getString("description"))) {
				String temp = platformJson.getString("platform_count");
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TD_HALFYEAR_COUNT,Integer.valueOf(temp));//180天内申请数
				//评分卡 1.8
				tongDun18Redis.setTdTotal180(temp);
				//评分卡 2.0
				tongDun20Redis.setTdTotal180(temp);
				
				if(platformJson.has("platform_detail")){
					JSONArray detailArray = platformJson.getJSONArray("platform_detail");
					//评分卡 1.8
					tongDun18Redis.setTdX0180(String.valueOf(detailArray.size()));
					//评分卡 2.0
					tongDun20Redis.setTdX0180(String.valueOf(detailArray.size()));
					for (int x = 0; x <detailArray.size(); x++) {
						JSONObject platformDetail = detailArray.getJSONObject(x);
						if(platformDetail.has("industry_display_name")){
							if ("第三方服务商".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.1
									tongDun11Redis.setTdX10180(platformDetail.getString("count"));
									//评分卡 1.8
									tongDun18Redis.setTdX10180(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX10180(platformDetail.getString("count"));
									
								}
							}
							if ("小额贷款公司".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.1
									tongDun11Redis.setTdX1180(platformDetail.getString("count"));
		
								}
							}
							if ("P2P网贷".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8
									tongDun18Redis.setTdX2180(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX2180(platformDetail.getString("count"));
								}
							}
							if ("一般消费分期平台".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8
									tongDun18Redis.setTdX3180(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX3180(platformDetail.getString("count"));
								}
							}
							if ("互联网金融门户".equals(platformDetail.getString("industry_display_name"))) {
								if(platformDetail.has("count") && StringUtils.isNotBlank(platformDetail.getString("count"))){
									//评分卡 1.8
									tongDun18Redis.setTdX7180(platformDetail.getString("count"));
									//评分卡 2.0
									tongDun20Redis.setTdX7180(platformDetail.getString("count"));
								}
							}
						}
					}
				}
			}
			
			int count=tdPlatformServiceDao.saveEntity(tdPlatform);
			if(count > 0){
				if(platformJson.has("platform_detail_dimension")){
					JSONArray detailDimensionArray=platformJson.getJSONArray("platform_detail_dimension");
					savePlatformDetailDimension(platformId,detailDimensionArray);
				};
				if(platformJson.has("platform_detail")){
					JSONArray detailArray=platformJson.getJSONArray("platform_detail");
					savePlatfromDetail(platformId,"",detailArray);
				};
			}
		}
	}

	private void savePlatformDetailDimension(String platfromId, JSONArray detailArray) {
		for (int i = 0; i <detailArray.size(); i++) {
			JSONObject platFromDimensionJson=detailArray.getJSONObject(i);
			TdPlatformDimension tdPlatformDimension=new TdPlatformDimension();
			tdPlatformDimension.setTdPlatfromId(platfromId);
			String platFormDimensionId=UUIDUtils.getUUID();
			tdPlatformDimension.setTdPlatformDimensionId(platFormDimensionId);
			if(platFromDimensionJson.has("count")){
				tdPlatformDimension.setCount(platFromDimensionJson.getString("count"));
			}
			if(platFromDimensionJson.has("dimension")){
				tdPlatformDimension.setDimension(platFromDimensionJson.getString("dimension"));
			}
			int count=tdPlatformDimensionServiceDao.saveEntity(tdPlatformDimension);
			if(count>0&&platFromDimensionJson.has("detail")){
				JSONArray platformDetailArray=platFromDimensionJson.getJSONArray("detail");
				savePlatfromDetail("",platFormDimensionId , platformDetailArray);
			}
		}
	}

	private void savePlatfromDetail(String platFormId, String platFormDimensionId, JSONArray platfromDetailArray) {
		for (int i = 0; i <platfromDetailArray.size(); i++) {
			JSONObject PlatfromDetail=platfromDetailArray.getJSONObject(i);
			TdPlatformDetail platformDetail=new TdPlatformDetail();
			platformDetail.setTdPlatformDetailId(UUIDUtils.getUUID());
			platformDetail.setTdPlatformDimensionId(platFormDimensionId);
			platformDetail.setTdPlatformId(platFormId);
			if(PlatfromDetail.has("count")){
				platformDetail.setCount(PlatfromDetail.getString("count"));
			}
			if(PlatfromDetail.has("industry_display_name")){
				platformDetail.setIndustryDisplayName(PlatfromDetail.getString("industry_display_name"));
			}
			tdPlatformDetailServiceDao.saveEntity(platformDetail);
		}
	}

	private void saveDiscredit(String riskItemsId, JSONArray riskDetails) {
		for (int i = 0; i < riskDetails.size(); i++) {
			JSONObject discreditJson=riskDetails.getJSONObject(i);
			if(discreditJson.has("overdue_details")){
				JSONArray discreaditDetailArray=discreditJson.getJSONArray("overdue_details");
				for (int j = 0; j < discreaditDetailArray.size(); j++) {
					JSONObject discreaditDetailJson=discreaditDetailArray.getJSONObject(j);
					TdDiscredit tdDiscredit=new TdDiscredit();
					tdDiscredit.setTdRiskItemsId(riskItemsId);
					tdDiscredit.setTdDiscreditId(UUIDUtils.getUUID());
					if(discreditJson.has("description")){
						tdDiscredit.setDescription(discreditJson.getString("description"));
					}
					if(discreditJson.has("discredit_times")){
						tdDiscredit.setDiscreditTimes(discreditJson.getString("discredit_times"));
					}
					if(discreaditDetailJson.has("overdue_time")){
						tdDiscredit.setOverdueTime(discreaditDetailJson.getString("overdue_time"));
					}
					if(discreaditDetailJson.has("overdue_amount_range")){
						tdDiscredit.setOverdueAmountRange(discreaditDetailJson.getString("overdue_amount_range"));
					}
					if(discreaditDetailJson.has("overdue_day_range")){
						tdDiscredit.setOverdueDayRange(discreaditDetailJson.getString("overdue_day_range"));
					}
					if(discreaditDetailJson.has("overdue_count")){
						tdDiscredit.setOverdueCount(discreaditDetailJson.getString("overdue_count"));
					}
					tdDiscreditServiceDao.saveEntity(tdDiscredit);
				}
			}
		}
	}

	private void saveFrequency(String riskItemsId, JSONArray riskDetails,String riskTypeName) {
		for (int i = 0; i < riskDetails.size(); i++) {
			JSONObject frequencyJson=riskDetails.getJSONObject(i);
			if(frequencyJson.has(riskTypeName)){
				JSONArray frequencyArray=frequencyJson.getJSONArray(riskTypeName);
				for (int j = 0; j < frequencyArray.size(); j++) {
					JSONObject frequencyDetailJson=frequencyArray.getJSONObject(j);
					TdFrequencyDetail tdFrequencyDetail=new TdFrequencyDetail();
					tdFrequencyDetail.setTdFrequencyDetailId(UUIDUtils.getUUID());
					tdFrequencyDetail.setTdRiskItemsId(riskItemsId);
					if(frequencyDetailJson.has("data")){
						tdFrequencyDetail.setData(frequencyDetailJson.getString("data"));
					}
					if(frequencyDetailJson.has("detail")){
						tdFrequencyDetail.setDetail(frequencyDetailJson.getString("detail"));
					}
					tdFrequencyDetailServiceDao.saveEntity(tdFrequencyDetail);
				}
				
			}
		}
		
	}


	private void saveFuzzyBlack(String riskItemsId, JSONArray riskDetails) {
		for (int i = 0; i < riskDetails.size(); i++) {
			JSONObject fuzzyBlackJson = riskDetails.getJSONObject(i);
			if (fuzzyBlackJson.has("fuzzy_list_details")) {
				JSONArray fuzzyListArray = fuzzyBlackJson.getJSONArray("fuzzy_list_details");
				for (int j = 0; j < fuzzyListArray.size(); j++) {
					TdFuzzyBlack fuzzyBlack = new TdFuzzyBlack();
					fuzzyBlack.setTdFuzzyBlackId(UUIDUtils.getUUID());
					fuzzyBlack.setTdRiskItemsId(riskItemsId);
					JSONObject fuzzyDetailJson = fuzzyListArray.getJSONObject(j);
					if(fuzzyBlackJson.has("description")){
						fuzzyBlack.setDescription(fuzzyBlackJson.getString("description"));
					}
					if(fuzzyDetailJson.has("fuzzy_id_number")){
						fuzzyBlack.setFuzzyIdNumber(fuzzyDetailJson.getString("fuzzy_id_number"));
					}
					if(fuzzyDetailJson.has("fraud_type")){
						fuzzyBlack.setFraudType(fuzzyDetailJson.getString("fraud_type"));
					}
					if(fuzzyDetailJson.has("fraud_type_display_name")){
						fuzzyBlack.setFraudTypeDisplayName(fuzzyDetailJson.getString("fraud_type_display_name"));
					}
					if(fuzzyDetailJson.has("fuzzy_name")){
						fuzzyBlack.setFuzzyName(fuzzyDetailJson.getString("fuzzy_name"));
					}
					tdFuzzyBlackServiceDao.saveEntity(fuzzyBlack);
				}
			}
		}
		
	}


	private void saveGrey(String riskItemsId, JSONArray riskDetails) {
		for (int i = 0; i < riskDetails.size(); i++) {
			JSONObject greyJson=riskDetails.getJSONObject(i);
			if(greyJson.has("grey_list_details")){
				JSONArray greyDetailArray=greyJson.getJSONArray("grey_list_details");
				for (int j = 0; j < greyDetailArray.size(); j++) {
					JSONObject detailJson=greyDetailArray.getJSONObject(j);
					TdGrey tdGrey=new TdGrey();
					tdGrey.setTdGreyId(UUIDUtils.getUUID());
					tdGrey.setTdRiskItemsId(riskItemsId);
					if(greyJson.has("hit_type_display_name")){
						tdGrey.setHitTypeDisplayName(greyJson.getString("hit_type_display_name"));
					}
					if(greyJson.has("description")){
						tdGrey.setDescription(greyJson.getString("description"));
					}
					if(detailJson.has("evidence_time")){
						tdGrey.setEvidenceTime(detailJson.getString("evidence_time"));
					}
					if(detailJson.has("risk_level")){
						tdGrey.setRiskLevel(detailJson.getString("risk_level"));
					}
					if(detailJson.has("fraud_type")){
						tdGrey.setFraudType(detailJson.getString("fraud_type"));
					}
					if(detailJson.has("fraud_type_display_name")){
						tdGrey.setFraudTypeDisplayName(detailJson.getString("fraud_type_display_name"));
					}
					if(detailJson.has("value")){
						tdGrey.setValue(detailJson.getString("value"));
					}
					tdGreyServiceDao.saveEntity(tdGrey);
				}		
			}
		}	
	}

	private void saveSuspected(String riskItemsId, JSONArray riskDetails) {
		for (int i = 0; i < riskDetails.size(); i++) {
			JSONObject suspectedListJson=riskDetails.getJSONObject(i);
			if(suspectedListJson.has("suspect_team_detail_list")){
				JSONArray suspectedArray=suspectedListJson.getJSONArray("suspect_team_detail_list");
				for (int j = 0; j < suspectedArray.size(); j++) {
					JSONObject suspectedJson=suspectedArray.getJSONObject(j);
					TdSuspectTeam suspectTeam=new TdSuspectTeam();
					suspectTeam.setTdSuspectTeamId(UUIDUtils.getUUID());
					suspectTeam.setTdRiskItemsId(riskItemsId);
					if(suspectedJson.has("group_id")){
						suspectTeam.setGroupId(suspectedJson.getString("group_id"));
					}
					if(suspectedJson.has("dim_type")){
						suspectTeam.setDimType(suspectedJson.getString("dim_type"));
					}
					if(suspectedJson.has("dim_value")){
						suspectTeam.setDimValue(suspectedJson.getString("dim_value"));
					}
					if(suspectedJson.has("node_dist")){
						suspectTeam.setNodeDist(suspectedJson.getString("node_dist"));
					}
					if(suspectedJson.has("fraud_dist")){
						suspectTeam.setFraudDist(suspectedJson.getString("fraud_dist"));
					}
					if(suspectedJson.has("black_rat")){
						suspectTeam.setBlackRat(suspectedJson.getString("black_rat"));
					}
					if(suspectedJson.has("grey_rat")){
						suspectTeam.setGreyRat(suspectedJson.getString("grey_rat"));
					}
					if(suspectedJson.has("degree")){
						suspectTeam.setDegree(suspectedJson.getString("degree"));
					}
					if(suspectedJson.has("total_cnt")){
						suspectTeam.setTotalCnt(suspectedJson.getString("total_cnt"));
					}
					if(suspectedJson.has("black_cnt")){
						suspectTeam.setBlackCnt(suspectedJson.getString("black_cnt"));
					}
					if(suspectedJson.has("grey_cnt")){
						suspectTeam.setGreyCnt(suspectedJson.getString("grey_cnt"));
					}
					if(suspectedJson.has("core_dst")){
						suspectTeam.setCoreDst(suspectedJson.getString("core_dst"));
					}
					if(suspectedJson.has("black_dst")){
						suspectTeam.setBlackDst(suspectedJson.getString("black_dst"));
					}
					if(suspectedJson.has("total_cnt_two")){
						suspectTeam.setTotalCntTwo(suspectedJson.getString("total_cnt_two"));
					}
					if(suspectedJson.has("black_cnt_one")){
						suspectTeam.setBlackCntOne(suspectedJson.getString("black_cnt_one"));
					}
					if(suspectedJson.has("black_cnt_two")){
						suspectTeam.setBlackCntTwo(suspectedJson.getString("black_cnt_two"));
					}
					if(suspectedJson.has("fraud_dist_one")){
						suspectTeam.setFraudDistOne(suspectedJson.getString("fraud_dist_one"));
					}
					if(suspectedJson.has("fraud_dist_two")){
						suspectTeam.setFraudDistTwo(suspectedJson.getString("fraud_dist_two"));
					}
					tdSuspectTeamServiceDao.saveEntity(suspectTeam);
				}
			}

		}

	}

	private void saveCustom(String riskItemsId, JSONArray riskDetails) {
		for (int i = 0; i < riskDetails.size(); i++) {
			JSONObject customJson=riskDetails.getJSONObject(i);
			TdCustomDetail tdCustomDetail=new TdCustomDetail();
			tdCustomDetail.setTdRiskItemsId(riskItemsId);
			tdCustomDetail.setTdCustomDetailId(UUIDUtils.getUUID());
			if(customJson.has("hit_list_datas")){
				tdCustomDetail.setHighRiskAreas(customJson.getString("hit_list_datas"));
			}
			if(customJson.has("high_risk_areas")){
				tdCustomDetail.setHitListDatas(customJson.getString("high_risk_areas"));
			}
			tdCustomDetailServiceDao.saveEntity(tdCustomDetail);
		}
	}


	/**
	 * 获取同盾报告信息
	 */
	/*@Override
	public TdAntiFraudVo queryByAid(String applicationId) {
		Assert.hasText(applicationId, "申请单编号不能为空");
		TdAntiFraudVo tdAntiFraudVo = new TdAntiFraudVo();
		//获取主表
		TdAntiFraud tdAntiFraud = tdAntiFraudServiceDao.queryById(applicationId);
		BeanUtils.copyProperties(tdAntiFraud, tdAntiFraudVo);
		//获取TdRiskItems
		String tdAntiFraudId = tdAntiFraud.getTdAntiFraudId();
		List<TdRiskItems> tdRiskItems = tdRiskItemsServiceDao.queryById(tdAntiFraudId);
		List<TdRiskItemsVo> TdRiskItemsVos = new ArrayList<TdRiskItemsVo>();
		for (TdRiskItems riskItems : tdRiskItems) {
			//获得riskItemsId
			String tdRiskItemsId = riskItems.getTdRiskItemsId();
			if (StringUtils.isBlank(tdRiskItemsId)) {
				continue;
			}
			TdRiskItemsVo tdRiskItemsVo = new TdRiskItemsVo();
			BeanUtils.copyProperties(riskItems, tdRiskItemsVo);
			
			//获取风险详情表
			List<TdSuspectTeam> tdSuspectTeams = tdSuspectTeamServiceDao.queryById(tdRiskItemsId);
			if (tdSuspectTeams != null && tdSuspectTeams.size() > 0) {
				tdRiskItemsVo.setTdSuspectTeams(tdSuspectTeams);
			}
			//关注名单规则
			List<TdGrey> tdGreys = tdGreyServiceDao.queryById(tdRiskItemsId);
			if (tdGreys != null && tdGreys.size() > 0) {
				tdRiskItemsVo.setTdGreys(tdGreys);
			}
			//信贷预期统计
			List<TdDiscredit> tdDiscredits = tdDiscreditServiceDao.queryById(tdRiskItemsId);
			if (tdDiscredits != null && tdDiscredits.size() > 0) {
				tdRiskItemsVo.setTdDiscredits(tdDiscredits);
			}
			//自定义立标规则
			List<TdCustomDetail> tdCustomDetails = tdCustomDetailServiceDao.queryById(tdRiskItemsId);
			if (tdCustomDetails != null && tdCustomDetails.size() > 0) {
				tdRiskItemsVo.setTdCustomDetails(tdCustomDetails);
			}
			//风险名单规则
			List<TdBlack> tdBlacks = tdBlackServiceDao.queryById(tdRiskItemsId);
			tdRiskItemsVo.setTdBlacks(tdBlacks);
			//自定义列表规则
			List<TdPlatform> tdPlatforms = tdPlatformServiceDao.queryById(tdRiskItemsId);
			List<TdPlatformVo> tdPlatformVos = new ArrayList<TdPlatformVo>();
			for (TdPlatform tdPlatform : tdPlatforms) {
				TdPlatformVo tdPlatformVo = new TdPlatformVo();
				
				String tdPlatformId = tdPlatform.getTdPlatformId();
				if (StringUtils.isBlank(tdPlatformId)) {
					continue;
				}
				//不分维度
				List<TdPlatformDetail> tdPlatformDetails = tdPlatformDetailServiceDao.queryByFormId(tdPlatformId);
				if (tdPlatformDetails != null && tdPlatformDetails.size() > 0) {
					tdPlatformVo.setTdPlatformDetails(tdPlatformDetails);
				}
				//各维度
				List<TdPlatformDimension> tdPlatformDimensions = tdPlatformDimensionServiceDao.queryByFormId(tdPlatformId);
				
				List<TdPlatformDimensionVo> tdPlatformDimensionVos = new ArrayList<TdPlatformDimensionVo>();
				
				for (TdPlatformDimension tdPlatformDimension : tdPlatformDimensions) {
					TdPlatformDimensionVo tdPlatformDimensionVo = new TdPlatformDimensionVo();
					BeanUtils.copyProperties(tdPlatformDimension, tdPlatformDimensionVo);
					String tdPlatformDimensionId = tdPlatformDimension.getTdPlatformDimensionId();
					if (StringUtils.isNotBlank(tdPlatformDimensionId)) {
						List<TdPlatformDetail> Details = tdPlatformDetailServiceDao.queryByFormDimensionId(tdPlatformDimensionId);
						tdPlatformDimensionVo.setTdPlatformDetails(Details);
					}
					tdPlatformDimensionVos.add(tdPlatformDimensionVo);
				}
				tdPlatformVos.add(tdPlatformVo);
			}
			TdRiskItemsVos.add(tdRiskItemsVo);
		}
		
		return tdAntiFraudVo;
	}
	*/
}