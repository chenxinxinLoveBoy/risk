package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.authcenter.dubbo.impl.RiskControlDubboServiceImpl;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.MQConstants;
import com.shangyong.backend.dao.PromoteDetailedDao;
import com.shangyong.backend.entity.PromoteDetailed;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.PushAppPromoteService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.ReadJsonUtils;
import com.shangyong.backend.utils.RiskHttpClientUtil;
import com.shangyong.entity.BaseResult;
import com.shangyong.utils.RedisUtils;
@Service
public class PushAppPromoteServiceImpl implements PushAppPromoteService{
	
	private static Logger logger = LoggerFactory.getLogger("PromoteDetailed");

	@Autowired
	private PromoteDetailedDao promoteDetailedDao;
	
	@Autowired
	private RiskControlDubboServiceImpl riskControlDubboServiceImpl;
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类
	
	@Override
	public void pushAppPromote(PromoteDetailed promoteDetailed) {
		
		try {
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("customerId", promoteDetailed.getCustomerId());
			param.put("taskType", promoteDetailed.getTaskType());
			param.put("isPass", promoteDetailed.getCollectState().equals("1") ? 1 : -1);
			param.put("increaseMoney",StringUtils.isNotBlank(promoteDetailed.getIncreaseMoney()) ? Double.valueOf(promoteDetailed.getIncreaseMoney()).intValue() : 0);
			param.put("appName", Integer.valueOf(promoteDetailed.getAppName()));
			logger.info("APP用户提额申请回调APP端上送数据：" + param.toString());
			
			BaseResult<T> result = riskControlDubboServiceImpl.backExpTask(param);
			logger.info("APP用户提额申请回调APP端应答数据：" + result.toString());
			
			if (result != null && "200".equals(result.getCode())) {
				updatePromote(promoteDetailed);
			} 
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 修改数据库推送状态
	 * @param promoteDetailed
	 */
	public void updatePromote(PromoteDetailed promoteDetailed){
		promoteDetailed.setPushType("1");
		promoteDetailed.setModifyTime(DateUtils.getDate(new Date()));
		promoteDetailedDao.updatePushSateById(promoteDetailed);
	}
	
	/**
	 * 重复执行用户提额申请
	 * @param promoteDetailedId 用户提额申请表id
	 * @return boolean 返回调用mq是否成功
	 */
	public boolean pushMqMsg(String promoteDetailedId){
		
		boolean flag = false;
		
		PromoteDetailed obj = new PromoteDetailed();
		obj.setPromoteDetailedId(promoteDetailedId);
		obj = promoteDetailedDao.getEntityById(promoteDetailedId);
		
			
		try {
			
			if(obj == null){
				throw new RuntimeException("查询无结果");
			}
			if(StringUtils.isNotBlank(obj.getJosnStoragePath())){
				throw new RuntimeException("该提额申请记录已存在MongoDB，请勿重复操作");
			}
			
			SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TEMQ_ANTIFRAUD_CODE);
			String url = sysParam.getParamValueOne(); //请求url
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("customerId", obj.getCustomerId());
			param.put("taskType", obj.getTaskType());
			param.put("taskId", obj.getTaskId());
			param.put("appSerialNumber", obj.getAppSerialNumber());
			param.put("promoteDetailedId", promoteDetailedId);
	
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("exchangeName", MQConstants.MQ_PROMOTE_EXCHANG);
			map.put("messageType", MQConstants.MQ_PROMOTE_ROUTINGKEY);
			map.put("messageBody", ReadJsonUtils.toJson(param));
	
			logger.info("APP用户提额MQ上送参数：" + map.toString());
			
			
			// 重发队列特殊处理
			if(RedisUtils.exists(Constants.REDIS_MQ_TE_ERROR + promoteDetailedId)){
				if("0".equals(RedisUtils.get(Constants.REDIS_MQ_TE_ERROR + promoteDetailedId))){
					logger.error(String.format("promoteDetailedId= 【%s】,已重发过，跳过！", promoteDetailedId));
				}else{
					logger.info(String.format("promoteDetailedId= 【%s】,发送成功！", promoteDetailedId));
					RedisUtils.set(Constants.REDIS_MQ_TE_ERROR + promoteDetailedId, "0");
				}
			}
			
			
			RiskHttpClientUtil.doPostJson(url, ReadJsonUtils.toJson(map));
			
			flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
		return flag;
	}
	
	
	
	/**
	 * 根据条件手工推送提额消息至APP
	 * @param promoteDetailedId 用户提额申请表id
	 * @return boolean 返回调用mq是否成功
	 */
	public boolean pushAppStatusMqMsg(String promoteDetailedId){
		
		boolean flag = false;
		
		PromoteDetailed obj = new PromoteDetailed();
		obj.setPromoteDetailedId(promoteDetailedId);
		obj = promoteDetailedDao.getEntityPushAppById(promoteDetailedId);
			
		try {
			if(obj == null){
				throw new RuntimeException("只能推送已采集过的数据");
			}
			SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TEMQ_ANTIFRAUD_CODE);
			String url = sysParam.getParamValueOne(); //请求url
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("customerId", obj.getCustomerId());
			param.put("taskType", obj.getTaskType());
			param.put("taskId", obj.getTaskId());
			param.put("appSerialNumber", obj.getAppSerialNumber());
			param.put("promoteDetailedId", promoteDetailedId);
			param.put("appName", Integer.valueOf(obj.getAppName()));
			param.put("increaseMoney", StringUtils.isNotBlank(obj.getIncreaseMoney()) ? Double.valueOf(obj.getIncreaseMoney()).intValue() : 0);
			param.put("isPass", obj.getCollectState().equals("1") ? 1 : -1);
	
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("exchangeName", MQConstants.MQ_EXCHANG_PUSHAPP_TE);
			map.put("messageType", MQConstants.MQ_ROUTINGKEY_PUSHAPP_TE);
			map.put("messageBody", ReadJsonUtils.toJson(param));
	
			logger.info("MQ手工推送APP提额上送参数：" + map.toString());
			
			// 重发队列特殊处理
			if(RedisUtils.exists(Constants.REDIS_MQ_TE_ERROR + promoteDetailedId)){
				if("0".equals(RedisUtils.get(Constants.REDIS_MQ_TE_ERROR + promoteDetailedId))){
					logger.error(String.format("promoteDetailedId= 【%s】,已重发过，跳过！", promoteDetailedId));
				}else{
					logger.info(String.format("promoteDetailedId= 【%s】,发送成功！", promoteDetailedId));
					RedisUtils.set(Constants.REDIS_MQ_TE_ERROR + promoteDetailedId, "0");
				}
			}
			RiskHttpClientUtil.doPostJson(url, ReadJsonUtils.toJson(map));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return flag;
	}
	
}
