package com.shangyong.backend.service.txy.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.shangyong.backend.utils.BanCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RedisConstant;
import com.shangyong.backend.common.RuleConstants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.common.enums.FraudBizEnum;
import com.shangyong.backend.dao.txy.TxyAntiFraudServiceDao;
import com.shangyong.backend.dao.txy.TxyRiskInfoServiceDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.redis.fraud1_1.TencentCloud11Redis;
import com.shangyong.backend.entity.redis.fraud1_8.TencentCloud18Redis;
import com.shangyong.backend.entity.redis.fraud2_0.TencentCloud20Redis;
import com.shangyong.backend.entity.txy.JsonTxyAntiFraud;
import com.shangyong.backend.entity.txy.RiskInfo;
import com.shangyong.backend.entity.txy.TxyAntiFraud;
import com.shangyong.backend.entity.txy.TxyRiskInfo;
import com.shangyong.backend.entity.txy.vo.TxyAntiFraudVo;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.service.txy.TxyFqzService;
import com.shangyong.backend.utils.JacksonUtils;
import com.shangyong.backend.utils.txy.TxyFqzUtils;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RedisFraudUtils;
import com.shangyong.utils.RiskHttpClientUtil;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONObject;

/**
 * 腾讯反欺诈实现类
 * 
 * 
 * 2017-12-9 石明科
 * **/

@Service
public class TxyFqzServiceImpl implements TxyFqzService{
	
	@Autowired
	private TxyAntiFraudServiceDao txyAntiFraudServiceDao;
	
	@Autowired
	private TxyRiskInfoServiceDao txyRiskInfoServiceDao;
	
	@Autowired
	private SysParamRedisService sysParamRedisService;
	
	@Autowired
	private JsonReportService jsonReportService;
	
	@Autowired
	private RiskRuleService riskRuleService;
	private static Logger txyLogger = LoggerFactory.getLogger("txyFqz");

	@Override
	public RuleResult getTxyFqzInfo(Application application) {
		RuleResult ruleResult = new RuleResult();
		
		List<Map<String,Object>> checkList = new ArrayList<Map<String,Object>>();
		
		txyLogger.info("[腾讯反欺诈] 开始调用, applicationId=" + application.getApplicationId());
		
		/** 从获取具体配置参数**/
		SysParam txyAntIfRaud = sysParamRedisService.querySysParamByParamValueRedis(Constants.TXY_ANTIFRAUD_CODE);
		
		//判断
		if (txyAntIfRaud == null) {
			throw new RuntimeException("[腾讯反欺诈] 从系统参数表获取参数失败,key : TXY_ANTIFRAUD_CODE");
		}
		
		/** 请求地址**/
		String url = txyAntIfRaud.getParamValueOne();
		/** 云id**/
		String secretId = txyAntIfRaud.getParamValueTwo();
		/** 云密匙**/
		String secretKey = txyAntIfRaud.getParamValueThree();
		/** 编码格式**/
		String charset = Constants.UTF8;
		/** 具体操作的指令接口名称**/
		String action = txyAntIfRaud.getParamValueFour();
		/** 服务器地址**/
		String region = txyAntIfRaud.getParamValueFive();
		/** 参数**/
		SortedMap<String, String> arguments = new TreeMap<String, String>();
		
		/** 随机参数,与Timestamp结合,防止重放攻击**/
		 arguments.put("Nonce",String.valueOf((int)(Math.random() * 0x7fffffff)));
		 /** 具体操作的指令接口名称**/
		 arguments.put("Action", action);
		 /** 服务器地址**/
		 arguments.put("Region", region);
		 /** 云id**/
		 arguments.put("SecretId", secretId);
		 /** 当前时间**/
		 arguments.put("Timestamp", String.valueOf(System.currentTimeMillis() / 1000));
		 /** 判断证件类型**/ 
		 if (StringUtils.isBlank(application.getCertType()) && !"1".equals(application.getCertType())) {
			/** 不是身份证,报错**/
			 throw new RuntimeException("[腾讯反欺诈] 证件类型异常,请输入身份证类型:"+application.getCertType());
		 }
		 /** 获取身份证数据**/
		 arguments.put("idNumber", application.getCertCode());

		 //redis
		 TencentCloud11Redis txyRedis11 = new TencentCloud11Redis();
		 TencentCloud18Redis txy18Redis = new TencentCloud18Redis();
		 TencentCloud20Redis txy20Redis = new TencentCloud20Redis();
		 
		 //获取请求参数
		//手机号
		 String phoneNumber = application.getPhoneNum();
		//借款用户公网IP
		 String userIp = application.getLoanIp();
		//客户姓名
		 String name = application.getName();
		//获得申请单号
		 String applicationId = application.getApplicationId();
		 //增加请求参数
		
		 //断言判断参数是否为空
		 Assert.hasText(application.getPhoneNum(),"[腾讯反欺诈] PhoneNum参数为空");
		 Assert.hasText(applicationId,"[腾讯反欺诈] 申请单不能为空");
		 try {
			 
			 //组装请求参数
			 arguments.put("phoneNumber", "0086-"+phoneNumber);
			 if (!StringUtils.isBlank(name)) {
				 arguments.put("name", name);
			 }
			 if (!StringUtils.isBlank(userIp)) {
				 arguments.put("userIp", userIp);
			 }
			 //组装加密key
			 arguments.put("Signature", TxyFqzUtils.hmacSHA1(secretKey, String.format("%s%s?%s", "GET", url, TxyFqzUtils.makeQueryString(arguments, null)), charset));
	 
			 String format = String.format("https://%s?%s", url, TxyFqzUtils.makeQueryString(arguments, charset));
			 txyLogger.info("[腾讯反欺诈] 记录调用txy接口前参数"+format);
			 /** 调用腾讯云第三方接口**/
			 String result = RiskHttpClientUtil.doGet(format);
			 //开始数据解析数据入库
			 JSONObject resultJson=JSONObject.fromObject(result);
			 if(StringUtils.isBlank(result)){
				 throw new RuntimeException("[腾讯反欺诈] 调用腾讯云获取反欺诈数据异常");
			 }
			 txyLogger.info("[腾讯反欺诈] 记录txy接口返回参数"+result);

			 //将第三方返回的字符串转换为java对象
			 JsonTxyAntiFraud jsonTxyAntiFraud = (JsonTxyAntiFraud) JacksonUtils.JsonToBean(result, JsonTxyAntiFraud.class);

			 //评分卡2.0 redisKey
			 String key11 = RedisConstant.buildFraudScoresKey(applicationId, FraudBizEnum.TENCENT_CLOUD);
			 String key18 = RedisConstant.buildFraudScoresKey1_8(applicationId, FraudBizEnum.TENCENT_CLOUD);
			 String key20 = RedisConstant.buildFraudScoresKey2_0(applicationId, FraudBizEnum.TENCENT_CLOUD);
			 if(jsonTxyAntiFraud != null){
				//判断返回参数是否有效
				if(!"0".equals(jsonTxyAntiFraud.getCode())){
					//请求失败 进行逻辑处理
					throw new RuntimeException("[腾讯反欺诈] 调用腾讯云获取欺诈信息失败,失败原因:接口请求返回code不为0," + jsonTxyAntiFraud.toString());
				}
				if ("-1".equals(jsonTxyAntiFraud.getRiskScore())) {
					//未找到相应数据
					throw new RuntimeException("[腾讯反欺诈] 调用腾讯云获取欺诈信息失败,失败原因:欺诈分未找到数据," + jsonTxyAntiFraud.toString());
				}

				//添加禁止项数据
				BanCodeUtil.addCheckPoint(checkList,RuleConstants.TXY_SCORE,Integer.valueOf(jsonTxyAntiFraud.getRiskScore()));

				//调用禁止项
				ruleResult = riskRuleService.querySafeRuleApi(application,checkList);
				
				if(ruleResult == null){
					throw new RuntimeException("[腾讯反欺诈] 腾讯云数据报告获取-->调用taskCallBackService-->resultObj 为空");
				}
				
				String dateTime = DateUtils.getDate(new Date());
				TxyAntiFraud txyAntiFraud = new TxyAntiFraud();
				//复制相同字段
				BeanUtils.copyProperties(jsonTxyAntiFraud, txyAntiFraud);

				String riskScore = txyAntiFraud.getRiskScore();

				 //1.1版本腾讯云欺诈分放入redis
				txyRedis11.setRiskScoreTaf(riskScore);

				//1.8版本腾讯云欺诈分放入redis
				txy18Redis.setRiskScoreTaf(riskScore);
				
				//2.0版本腾讯云欺诈粉放入redis
				txy20Redis.setRiskScoreTaf(riskScore);

				txyAntiFraud.setTxyAntiFraudId(UUIDUtils.getUUID());
				txyAntiFraud.setBuApplicationId(applicationId);
				txyAntiFraud.setCreateTime(dateTime);
				txyAntiFraud.setModifyTime(dateTime);
				//state需要到时候调用其他方法获取
				int state = ruleResult.getState();
				txyAntiFraud.setState(String.valueOf(state));		
				//txyAntiFraud调用dao方法进行数据保存(风险项主表)
				txyLogger.info("[腾讯反欺诈] 腾讯云反欺诈主表插入数据：+"+txyAntiFraud);
				int count = txyAntiFraudServiceDao.saveEntity(txyAntiFraud);
				//判断主表插入主表是否成功
				Assert.isTrue(count>0, "[腾讯反欺诈] 腾讯云反欺诈主表插入失败");
				
				if(jsonTxyAntiFraud.getRiskInfo() != null && jsonTxyAntiFraud.getRiskInfo().size() > 0){
					//主表不为为空附表不为空
					txy18Redis.initZero();
					txy20Redis.initZero();

					int txyHighRiskCntCount = 0;
					int txyLowRiskCntCount = 0;
					int txyX1Count = 0;
					int txyX5Count = 0;
					int txyX503Count = 0;
					int txyX12Count = 0;
					int txyX5031Count = 0;
					int txyX52Count = 0;
					//风险项集合
					List<RiskInfo> txyRiskInfos = jsonTxyAntiFraud.getRiskInfo();
					//创建一个集合(批量插入)
					List<TxyRiskInfo> riskInfos = new ArrayList<TxyRiskInfo>();
					for(RiskInfo obj : txyRiskInfos ){
						
						TxyRiskInfo riskInfo = new TxyRiskInfo();
						
						//复制相同字段值
						BeanUtils.copyProperties(obj, riskInfo);
						
						//腾讯云高风险数（风险详情等于3的时候计数存入redis）
						if("3".equals(riskInfo.getRiskCodeValue())){
							txyHighRiskCntCount++;	
						}
						//腾讯云低风险数（风险详情等于3的时候计数存入redis）
						if("1".equals(riskInfo.getRiskCodeValue())){
							txyLowRiskCntCount++;
						}
						//风险码等于1的时候计数存入redis
						if("1".equals(riskInfo.getRiskCode())){
							txyX1Count++;
						}
						//风险码等于5的时候计数存入redis
						if("5".equals(riskInfo.getRiskCode())){
							txyX5Count++;
						}
						//风险码等于1并且风险详情等于2的时候计数存入redis
						if("1".equals(riskInfo.getRiskCode()) && "2".equals(riskInfo.getRiskCodeValue())){
							txyX12Count++;
						}
						//风险码等于503的时候计数存入redis
						if("503".equals(riskInfo.getRiskCode())){
							txyX503Count++;
						}
						//风险码等于503并且风险详情等于1的时候计数存入redis
						if("503".equals(riskInfo.getRiskCode()) && "1".equals(riskInfo.getRiskCodeValue())){
							txyX5031Count++;
						}
						//风险码等于5并且风险详情等于2的时候计数存入redis
						if("5".equals(riskInfo.getRiskCode()) && "2".equals(riskInfo.getRiskCodeValue())){
							txyX52Count++;
						}
						riskInfo.setTxyRiskInfoId(UUIDUtils.getUUID());
						riskInfo.setTxyAntiFraudId(txyAntiFraud.getTxyAntiFraudId());
						riskInfo.setCreateTime(dateTime);
						riskInfo.setModifyTime(dateTime);
						riskInfos.add(riskInfo);
					}
					//批量插入数据
					this.saveAllRiskInfo(riskInfos);
					String txyHighRiskCnt = String.valueOf(txyHighRiskCntCount);
					txy18Redis.setTxyHighRiskCnt(txyHighRiskCnt);
					txy20Redis.setTxyHighRiskCnt(txyHighRiskCnt);
					
					String txyLowRiskCnt = String.valueOf(txyLowRiskCntCount);
					txy18Redis.setTxyLowRiskCnt(txyLowRiskCnt);
					txy20Redis.setTxyLowRiskCnt(txyLowRiskCnt);
					
					String txyX1 = String.valueOf(txyX1Count);
					txy18Redis.setTxyX1(txyX1);
					txy20Redis.setTxyX1(txyX1);
					
					String txyX5 = String.valueOf(txyX5Count);
					txy18Redis.setTxyX5(txyX5);
					txy20Redis.setTxyX5(txyX5);
					
					String txyX12 = String.valueOf(txyX12Count);
					txy18Redis.setTxyX12(txyX12);
					txy20Redis.setTxyX12(txyX12);
					
					String txyX503 = String.valueOf(txyX503Count);
					txy18Redis.setTxyX503(txyX503);
					txy20Redis.setTxyX503(txyX503);
					
					String txyX5031 = String.valueOf(txyX5031Count);
					txy18Redis.setTxyX5031(txyX5031);
					txy20Redis.setTxyX5031(txyX5031);
					
					String txyX52 = String.valueOf(txyX52Count);
					txy18Redis.setTxyX52(txyX52);
					txy20Redis.setTxyX52(txyX52);
				}

			}

			 //评分卡1.1
			 HashMap<String,String> map = txyRedis11.toMap();
			 RedisFraudUtils.hmset(key11, map);

			 //评分卡1.8
			 HashMap<String,String> map18 = txy18Redis.toMap();
			 RedisFraudUtils.hmset(key18, map18);

			 //评分卡2.0
			 HashMap<String,String> map20 = txy20Redis.toMap();
			 RedisFraudUtils.hmset(key20, map20);

			//存阿里云存mongo
			jsonReportService.uploadJson(Constants.TXY_UPLOAD_DIR, resultJson, TaskTypeConstants.TXY_TASK_TYPE, TaskTypeConstants.TXY_TASK_NAME, TaskTypeConstants.TXY_TASK_ISEND, application.getApplicationId(), "noext");

		} catch (Exception e1) {
			throw new RuntimeException("[腾讯反欺诈] 调用腾讯云获取反欺诈数据异常,异常信息:" + e1.getMessage(),e1);
		}
		 
		txyLogger.info("[腾讯反欺诈] 处理成功, [applicationId=" + application.getApplicationId() + "]");
		return ruleResult;
	}


	@Override
	public TxyAntiFraudVo queryTxyAntFraudVo(String applicationId) {
		TxyAntiFraudVo txyAntiFraudVo = new TxyAntiFraudVo();
		TxyAntiFraud txyAntiFraud = txyAntiFraudServiceDao.queryById(applicationId);
		String txyAntiFraudId = txyAntiFraud.getTxyAntiFraudId();
		BeanUtils.copyProperties(txyAntiFraud, txyAntiFraudVo);
		List<TxyRiskInfo> txyRiskInfos = txyRiskInfoServiceDao.queryById(txyAntiFraudId);
		txyAntiFraudVo.setTxyRiskInfos(txyRiskInfos);
		return txyAntiFraudVo;
	}

	/**
	 * 批量插入
	 * @param riskInfos
	 */
	private void saveAllRiskInfo(List<TxyRiskInfo> riskInfos){
		//riskInfo调用dao方法进行数据保存(风险项)批量插入
		int count = txyRiskInfoServiceDao.saveAllEntity(riskInfos);
		Assert.isTrue(count>0, "[腾讯云反欺诈] 风险表插入失败");
	}
}
