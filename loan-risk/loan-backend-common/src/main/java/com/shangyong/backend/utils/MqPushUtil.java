package com.shangyong.backend.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.mq.XnMessage;
import com.shangyong.backend.service.SysParamRedisService;

/**
 * mq队列消息推送util
 * @author ailiqiang
 *
 */
public class MqPushUtil {
	
	/**
	 * 向mq队列推送消息
	 * @param applicationId 申请单编号
	 * @param exchangeName 交换机名称
	 * @param messageType  路由键key
	 * @param messageUrl  消息推送地址
	 * @return
	 */
	public String sendPush(String applicationId,String exchangeName,String messageType,String messageUrl){

		XnMessage xnMessage = new XnMessage();
		xnMessage.setExchangeName(exchangeName);
		xnMessage.setMessageType(messageType);
		Map<String, Object> appObj = new HashMap<String, Object>();
		appObj.put("application_id", applicationId);
		xnMessage.setMessageBody(ReadJsonUtils.toJson(appObj));
		
		// 获取参数值
		SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
		SysParam sysParamForMq = sysParamRedisService.querySysParamByParamValueRedis(Constants.BACKEND_MQ_PROJECT);
		
		//MQ项目地址必须配置
		if(sysParamForMq == null || sysParamForMq.getParamValueOne() == null){
			// 获取DD通知URL
			//DingdingUtil.setMessage(sysParamForMq.getParamValueTwo(), "当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", MQ消息推送处理异常，申请单编号：" + applicationId + "; 错误信息：" + "BACKEND_MQ_PROJECT-->MQ项目地址未配置");
			throw new RuntimeException("MQ项目系统参数：BACKEND_MQ_PROJECT-->地址未配置");
		}
		
		String str = RiskHttpClientUtil.doPostJson(sysParamForMq.getParamValueOne() + messageUrl,ReadJsonUtils.toJson(xnMessage));
		return str;
	}
}
