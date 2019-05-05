package com.shangyong.backend.service.mq.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangyong.backend.service.TdLoanInterfaceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.authcenter.dubbo.impl.AuthMoneyInfoService;
import com.shangyong.authcenter.dubbo.impl.RiskControlDubboServiceImpl;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.PromoteDetailedDao;
import com.shangyong.backend.entity.PromoteDetailed;
import com.shangyong.backend.service.mq.IMqPushAppStatusService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.entity.BaseResult;
import com.shangyong.utils.RedisUtils;

@Service
public class MqPushAppStatusServiceImpl implements IMqPushAppStatusService {

	private static Logger rabbitmq = LoggerFactory.getLogger("rabbitmqApp");
	private static Logger pushAppStatusLogger = (Logger) LoggerFactory.getLogger("pushAppStatus");

	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;
	@Autowired
	private AuthMoneyInfoService authMoneyInfoService;
	@Autowired
	private RiskControlDubboServiceImpl riskControlDubboServiceImpl;
	@Autowired
	private PromoteDetailedDao promoteDetailedDao;

    /**
     * 添加mq发送记录
     * @param message        消息
     * @return
     * @throws Exception
     */
    @Override
    public void pushByMessage(Map<String, Object> bodyMap) throws Exception {

    	BaseResult<T> baseResult = new BaseResult<T>();
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("customerId", bodyMap.get("customerId").toString());//APP应用客户编号
		params.put("serialNumber", bodyMap.get("appSerialNumber").toString());//申请单号
		params.put("authMoney", String.valueOf(bodyMap.get("creditMoney")));//授信额度
//		params.put("days",  String.valueOf(dataMapList.get(i).get("days")));//周期
		String isPass = bodyMap.get("auditingState").toString();//是否通过
		if(isPass.equals("2")){
			params.put("isPass","1");
		}else{
			params.put("isPass","0");
		}
		params.put("decisionTree", String.valueOf(bodyMap.get("decisionTreeId")==null?"":bodyMap.get("decisionTreeId")));//决策树
		String exceptionType = bodyMap.get("auditResult")==null?"":bodyMap.get("auditResult").toString();////异常类型 1：聚信立异常 2 芝麻异常；
		if(exceptionType.equals(Constants.ZHIMA_DATA_RULE_NAME)){
			params.put("exceptionType", "2");
		}else if(exceptionType.equals(Constants.MF_DATA_RULE_NAME)){
			params.put("exceptionType", "1");
		}else{
			params.put("exceptionType", "");
		}
		params.put("auditMan", bodyMap.get("auditMan")==null?"":bodyMap.get("auditMan").toString());//审批人用户编号
		params.put("isHbaseSyn", String.valueOf(bodyMap.get("isHbaseSyn")==null?"":bodyMap.get("isHbaseSyn")));//是否同步大数据
		// 获取参数值
//		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.PUSH_APP_URL);
//		String url = sysParam.getParamValueOne();
		
		String applicationId = bodyMap.get("applicationId").toString();//申请单编号
			try {
			String s1 = RedisUtils.hget(RedisCons.RISK_APP_CREADIT_REPORT+"_"+applicationId, RedisCons.RISK_APP_CREADIT_WEEK); // APP信用报告7天多头借贷
			String s2 = RedisUtils.hget(RedisCons.RISK_APP_CREADIT_REPORT+"_"+applicationId, RedisCons.RISK_APP_CREADIT_MONTH);// APP信用报告1个月多头借贷
			String s3 = RedisUtils.hget(RedisCons.RISK_APP_CREADIT_REPORT+"_"+applicationId, RedisCons.RISK_APP_CREADIT_THREE_MONTH);// APP信用报告 3个月多头借贷
			String loanPlatform = "";
			List<String> nums = new ArrayList<String>();
			if (StringUtils.isNotEmpty(s1)) {
				nums.add(s1);
			}
			if (StringUtils.isNotEmpty(s2)) {
				nums.add(s2);
			}
			if (StringUtils.isNotEmpty(s3)) {
				nums.add(s3);
			}
			if (nums != null) {
				Collections.sort(nums);
				loanPlatform = nums.get(nums.size() - 1);
			}
			params.put("loanPlatform", loanPlatform);// 借款平台总数的最大值

			String circleOfFriends = RedisUtils.hget(RedisCons.RISK_APP_CREADIT_REPORT+"_"+applicationId, RedisCons.RISK_APP_CREADIT_LOCATION);// 朋友大多数来自哪里
			String fraudNum = RedisUtils.hget(RedisCons.RISK_APP_CREADIT_REPORT+"_"+applicationId, RedisCons.RISK_APP_CREADIT_DIRECT_BLACK);// 疑似欺诈人数
			String twoFraudNum = RedisUtils.hget(RedisCons.RISK_APP_CREADIT_REPORT+"_"+applicationId, RedisCons.RISK_APP_CREADIT_SECOND_BLACK);// 二级疑似欺诈人数
			params.put("circleOfFriends", circleOfFriends);
			params.put("fraudNum", fraudNum);
			params.put("twoFraudNum", twoFraudNum);
		} catch (Exception e) {
			rabbitmq.info("从redis中key【" + RedisCons.RISK_APP_CREADIT_REPORT + "】获取申请单编号为【"+applicationId+"】第三方报告中的几个字段报错--->"+ e.getMessage());
		}
			
			 
		rabbitmq.info("-->APP申请单号：" + params.get("serialNumber"));
		pushAppStatusLogger.info("-->APP申请单号：" +params.get("serialNumber") + "-->参数：" + params);
		
		//DUBBO调用APP的接口，返回审批状态给APP，通知APP是否审批通过
		baseResult = authMoneyInfoService.updateAuthMoney(params);
		rabbitmq.info("-->APP申请单号："+ params.get("serialNumber") + "调用APP接口返回信息 code:"+baseResult.getCode() + ",message:"+baseResult.getMessage());
		pushAppStatusLogger.info("-->APP申请单号：" + params.get("serialNumber") + "调用APP接口返回信息 code:"+baseResult.getCode() + ",message:"+baseResult.getMessage());
		if(StringUtils.isNotBlank(baseResult.getCode())){
			if("200".equals(baseResult.getCode())){//通知App返回成功，更新借款信息表的是否推送字段
				String appId =  bodyMap.get("appSerialNumber").toString();//借款申请单号
				int count = tdLoanInterfaceService.updateIsPushApp(appId);
				pushAppStatusLogger.info("更新审批单推送标志成功：" + count);
			}else{
				rabbitmq.info("审批状态通知APP返回失败, code:"+baseResult.getCode()+",message:"+baseResult.getMessage());
				pushAppStatusLogger.error("审批状态通知APP返回失败, code:"+baseResult.getCode()+",message:"+baseResult.getMessage());
			}
		}
    }
    
    
    
    /**
     * 添加mq推送提额至app记录
     * @param message        消息
     * @return
     * @throws Exception
     */
    @Override
    public void pushByTeMessage(Map<String, Object> bodyMap) throws Exception {
    	try {
    		BaseResult<T> result = new BaseResult<T>();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("customerId", bodyMap.get("customerId"));
			param.put("taskType", bodyMap.get("taskType"));
			param.put("isPass", StringUtils.isNotBlank(bodyMap.get("isPass").toString()) ? Double.valueOf(bodyMap.get("isPass").toString()).intValue() : -1);
			param.put("increaseMoney",StringUtils.isNotBlank(bodyMap.get("increaseMoney").toString()) ? Double.valueOf(bodyMap.get("increaseMoney").toString()).intValue() : 0);
			param.put("appName", StringUtils.isNotBlank(bodyMap.get("appName").toString()) ? Double.valueOf(bodyMap.get("appName").toString()).intValue() : 0);
			rabbitmq.info("手工推送提额申请回调APP端上送数据：" + param.toString());
			
			result = riskControlDubboServiceImpl.backExpTask(param);
			rabbitmq.info("手工推送提额申请回调APP端应答数据：" + result.toString());
			
			String promoteDetailedId = bodyMap.get("promoteDetailedId").toString();
			if (result != null && "200".equals(result.getCode())) {
				rabbitmq.info("手工推送提额申请回调APP端应答成功，返回编码：" + result.getCode());
				updatePromote(promoteDetailedId);
			} 
		} catch (Exception e) {
			rabbitmq.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
    }
    
	/**
	 * 修改数据库推送状态
	 * @param promoteDetailed
	 */
	public void updatePromote(String promoteDetailedId){
		rabbitmq.info("进入修改数据库推送状态类！！！！！！！！！！！！！！！！！");
		PromoteDetailed promoteDetailed = new PromoteDetailed();
		promoteDetailed.setPushType("1");
		promoteDetailed.setModifyTime(DateUtils.getDate(new Date()));
		promoteDetailed.setPromoteDetailedId(promoteDetailedId);
		promoteDetailedDao.updatePushSateById(promoteDetailed);
		
		rabbitmq.info("进入修改数据库推送状态类，修改成功！！！！！！！！！！！！！！！！！");
	}
}
