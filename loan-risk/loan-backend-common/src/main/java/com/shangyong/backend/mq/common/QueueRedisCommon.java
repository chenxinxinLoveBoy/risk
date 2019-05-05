package com.shangyong.backend.mq.common;

import java.io.Serializable;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.shangyong.backend.common.MQConstants;
import com.shangyong.utils.RedisUtils;

/**
 * 消息队列Redis操作
 * 
 * @author xiajiyun
 *
 */
@Component
public class QueueRedisCommon implements Serializable {

	private static final long serialVersionUID = -5464644641586329366L;
	

	/**
	 * 发送消息前，将‘消息内容‘放入redis
	 * @param messageId	消息编号uuid
	 * @param messageBody 消息内容体
	 */
	public boolean sendSaveRedis(String messageId, String messageBody){
		// 校验必要参数
		if(!validata(messageId)){
			return false;
		}
		// 放入redis缓存
		RedisUtils.hset(MQConstants.MQ_SEND_REDIS_INFO, messageId, messageBody);
		return true;
	}
	
	
	/**
	 * 删除存入’消息内容‘的redis-Key
	 * @param messageId 消息编号uuid
	 */
	public boolean sendDeleteRedis(String messageId){
		// 校验必要参数
		if(!validata(messageId)){
			return false;
		}		
		// 删除redis-key
		if(RedisUtils.hexists(MQConstants.MQ_SEND_REDIS_INFO, messageId)){
			RedisUtils.hdelEx(MQConstants.MQ_SEND_REDIS_INFO, messageId);
		}
		return true;
	}
	
	
	
	/**
	 * 校验必要参数
	 * @param messageId	消息编号uuid
	 */
	private boolean validata(String messageId){
		if(StringUtils.isBlank(messageId)){
			return false;
		}
		return true;
		
	}
	
	
	/**
	 * 根据消息编号从redis获取消息内容体
	 * @param messageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> jsonToMap(String messageId){
		// 获取消息内容体
		 String value = RedisUtils.hget(MQConstants.MQ_SEND_REDIS_INFO, messageId);
	     JSONObject obj = JSONObject.fromObject(value);
	     return (Map<String, String>)obj;
	} 
	
}
