package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.shangyong.backend.service.TdLoanInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RuleConstants;
import com.shangyong.backend.dao.ScFraudScoreDetailDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BigCallBlackInfo;
import com.shangyong.backend.entity.ScFraudScoreDetail;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BigDataCallBackService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.ReadJsonUtils;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * 大数据回调service实现
 * @author CG
 *
 */
@Service
public class BigDataCallBackServiceImpl implements BigDataCallBackService {
	
	private static Logger logger = LoggerFactory.getLogger(BigDataCallBackServiceImpl.class);
	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;
	@Autowired
	private ScFraudScoreDetailDao scFraudScoreDetailDao;
	@Autowired
	private SysParamRedisService sysParamRedisService;
	@Autowired
	private ApplicationServiceImpl applicationService;
	@Autowired
	private QueryScoreInfoServiceImpl queryScoreInfoServiceImpl;

	@Override
	@Transactional
	public boolean updatePassAppState(BigCallBlackInfo bigCallBlackInfo) throws Exception{
		// ken add to 2017/8/17 1:17 desc 检查数据的正确性
		if (!bigCallBackData(bigCallBlackInfo)) return false;
		
		Application application = applicationService.getObjectById(bigCallBlackInfo.getApplication_id());

		// 获取参数值
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.UPDATE_STEP_STATUS);
		String stepStatus = sysParam.getParamValueOne();
		String creditMoney ="";  //授信额度值
		String appraiseMoney=""; //评估分数值
		//信用评分
		String promoteCreditMoney = "";
		try {
			Map<String, Object> map = queryScoreInfoServiceImpl.queryScoreInfoApi(bigCallBlackInfo.getApplication_id(), application.getScoreTplId());
			creditMoney =  map.get("amount").toString();  //授信额度值
			appraiseMoney = map.get("score").toString(); //评估分数值
			promoteCreditMoney = (String) map.get("promoteCreditMoney"); //提升授信额度值
			logger.info("大数据回调接口获取信用评分，授信额度值：【"+creditMoney+"】，评估分数值：【" +appraiseMoney+"】申请单编号：【"+bigCallBlackInfo.getApplication_id()+"】");
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("大数据回调接口获取信用评分出错，错误信息："+e.getMessage());
		}
		
		Map<String, Object> dataMapParam = new HashMap<String, Object>();
		dataMapParam.put("auditResult", "大数据审核通过"); //审批结果
		dataMapParam.put("auditingState", Constants.PASS_SP_STAATE);//审核状态(通过)
		dataMapParam.put("applicationId",bigCallBlackInfo.getApplication_id());//申请编号
		dataMapParam.put("isPushApp","3");//is_push_app：0-否、1-是、2：已发送、3：已回调 
		if("1".equals(application.getAppLevel())){
			dataMapParam.put("creditMoney", Double.parseDouble(creditMoney)+Double.parseDouble(promoteCreditMoney));//授信额度值
		}else{
			dataMapParam.put("creditMoney",creditMoney);//授信额度值
		}
		dataMapParam.put("appraiseMoney",appraiseMoney);//评估分数值
		dataMapParam.put("currentStep",stepStatus);//当前步骤号
		
		//接口走完，更新步骤标识、审批状态、审批结果描述
		int count = tdLoanInterfaceService.updateApplicationByStep(dataMapParam);
		if(count == 0){
			throw new RuntimeException("更新大数据返回审核记录失败，当前数据步骤号不是最新步骤");
		}
		logger.info("单笔更新大数据回调接口审批通过状态处理结束,申请单编号【"+bigCallBlackInfo.getApplication_id()+"】,currentStep【"+stepStatus+"】更新记录数：" + count);
		
		//插入欺诈评分
		qizhaScore(bigCallBlackInfo);

		return true;
	}

	/**
	 * 检查大数据的数据合理性
	 * @param bigCallBlackInfo
	 * @return true 合理 false 不合理
	 */
	private boolean bigCallBackData(BigCallBlackInfo bigCallBlackInfo) throws Exception{
		// ken add to 2017/8/17 0:56 desc  检查申请单信息
		if(bigCallBlackInfo != null){
			//查询申请单
			Application application = applicationService.getObjectById(bigCallBlackInfo.getApplication_id());

			// 获取参数值
			SysParam sysParamForMq = sysParamRedisService.querySysParamByParamValueRedis(Constants.BACKEND_MQ_PROJECT);
			//MQ项目地址必须配置
			if(sysParamForMq == null || sysParamForMq.getParamValueOne() == null){
				throw new Exception("BACKEND_MQ_PROJECT，MQ项目地址未配置");
			}

			if(application == null){
				throw new Exception("申请单" + bigCallBlackInfo.getApplication_id() + "未找到");
//				logger.error("大数据回调接口-申请单未找到");
//				// 获取DD通知URL
//				DingdingUtil.setMessageToDingDing(sysParamForMq.getParamValueTwo(), "当前时间：" + com.honglu.backend.utils.DateUtils.parseToDateTimeStr(new Date()) +
//						", MQ消息回调处理异常，申请单编号：" + bigCallBlackInfo.getApplication_id() + ";未找到");
//				return false;
			}
			//检查申请单审核状态-待审核
			if(!Constants.DAI_SP_STAATE.equals(application.getAuditingState())){
				throw new Exception("申请单" + bigCallBlackInfo.getApplication_id() + "审批状态不正确");
//				logger.error("大数据回调接口-审批状态不正确");
//				// 获取DD通知URL
//				DingdingUtil.setMessageToDingDing(sysParamForMq.getParamValueTwo(), "当前时间：" + com.honglu.backend.utils.DateUtils.parseToDateTimeStr(new Date()) +
//						", MQ消息回调处理异常，申请单编号：" + bigCallBlackInfo.getApplication_id() + "; 错误信息：审批状态不正确");
//				return false;
			}
//			检查申请单的步骤号（不需要检查）
			//检查是否已发送至大数据
			if(!Constants.BIG_DATA_SYN_IS_STEP_2.equals(application.getIsPushApp())){
				throw new Exception("申请单" + bigCallBlackInfo.getApplication_id() + "尚未发送至大数据");
//				logger.error("大数据回调接口-申请单尚未发送至大数据");
//				// 获取DD通知URL
//				DingdingUtil.setMessageToDingDing(sysParamForMq.getParamValueTwo(), "当前时间：" + com.honglu.backend.utils.DateUtils.parseToDateTimeStr(new Date()) +
//						", MQ消息回调处理异常，申请单编号：" + bigCallBlackInfo.getApplication_id() + "; 错误信息：申请单尚未发送至大数据");
//
//				return false;
			}
			//检查是否有大数据标记
			if(!Constants.IS_BIG_DATA_SYN.equals(application.getIsHbaseSyn())){
				throw new Exception("申请单" + bigCallBlackInfo.getApplication_id() + "没有大数据标记");
//				// 获取DD通知URL
//				DingdingUtil.setMessageToDingDing(sysParamForMq.getParamValueTwo(), "当前时间：" + com.honglu.backend.utils.DateUtils.parseToDateTimeStr(new Date()) +
//						", MQ消息回调处理异常，申请单编号：" + bigCallBlackInfo.getApplication_id() + "; 错误信息：申请单没有大数据标记");
//
//				logger.error("大数据回调接口-申请单没有大数据标记");
//				return false;
			}
			return true;
		}
		return  false;
	}


	@Override
	@Transactional
	public boolean updateNoPassAppState(BigCallBlackInfo bigCallBlackInfo) throws Exception{
		// ken add to 2017/8/17 1:17 desc 检查数据的正确性
		if (!bigCallBackData(bigCallBlackInfo)) return false;
		// 获取参数值
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.UPDATE_STEP_STATUS);
		String stepStatus = sysParam.getParamValueOne();
		
		Map<String, Object> dataMapParam = new HashMap<String, Object>();
		dataMapParam.put("auditResult", "命中潘多拉大数据拒绝规则"); //审批结果
		dataMapParam.put("auditingState", Constants.NOPASS_SP_STAATE);//审核状态(不通过)
		dataMapParam.put("applicationId",bigCallBlackInfo.getApplication_id());//申请编号
		dataMapParam.put("isPushApp","3");//is_push_app：0-否、1-是、2：已发送、3：已回调 
		dataMapParam.put("banCode", RuleConstants.BIG_DATA_RULE);//禁止项规则对应编号
		dataMapParam.put("currentStep", stepStatus);//当前步骤号

		//接口走完，更新步骤标识、审批状态、审批结果描述
		int count = tdLoanInterfaceService.updateApplicationByStep(dataMapParam);
		logger.info("单笔更新大数据回调接口审批通过状态处理结束,申请单编号【"+bigCallBlackInfo.getApplication_id()+"】,currentStep【"+stepStatus+"】更新记录数：" + count);
		
		//插入欺诈评分
		qizhaScore(bigCallBlackInfo);

		return true;
	}

	/**
	 * @param json
	 * @Auth: kenzhao
	 * @Desc: 检查数据的合法性
	 * @return: 返回是null，则数据格式不合法
	 * @当前时间： 2017/8/17 0:38
	 */
	@Override
	public BigCallBlackInfo bigDataCallBackCheck(String json) throws Exception {
//		Map<String,Object> tempMap = ReadJsonUtils.fromJson(json,Map.class);
		// 获取参数值
		SysParam sysParamForMq = sysParamRedisService.querySysParamByParamValueRedis(Constants.BACKEND_MQ_PROJECT);
		//MQ项目地址必须配置
		if(sysParamForMq == null || sysParamForMq.getParamValueOne() == null){
			throw new Exception("BACKEND_MQ_PROJECT，MQ项目地址未配置");
//			return null;
		}

		try{
//			if (null == tempMap) {
//				logger.error("大数据返回数据为空");
//				// 获取DD通知URL
//				DingdingUtil.setMessageToDingDing(sysParamForMq.getParamValueTwo(), "当前时间：" + com.honglu.backend.utils.DateUtils.parseToDateTimeStr(new Date()) +
//						", MQ消息回调处理异常，大数据返回数据为空");
//
//				return null;
//			}
//			if (tempMap.size() == 0 || MQConstants.BIG_DATA_LEN != tempMap.size()) {
//				// 获取DD通知URL
//				DingdingUtil.setMessageToDingDing(sysParamForMq.getParamValueTwo(), "当前时间：" + com.honglu.backend.utils.DateUtils.parseToDateTimeStr(new Date()) +
//						", MQ消息回调处理异常，大数据返回数据字段个数不正确");
//
//				logger.error("大数据返回数据字段个数不正确");
//				return null;
//			}
//			for (String key:tempMap.keySet()
//				 ) {
//				Object object = tempMap.get(key);
//				if(object==null||object.toString().trim().length() < 1){
//					logger.error("大数据返回数据字段" + key + "为空或空字符");
//					return null;
//				}
//			}
			BigCallBlackInfo bigCallBlackInfo = ReadJsonUtils.fromJson(json,BigCallBlackInfo.class);
			if(bigCallBlackInfo == null
					|| bigCallBlackInfo.getApplication_id() == null
					|| bigCallBlackInfo.getCreate_time() == null
					|| bigCallBlackInfo.getCustomer_id() == null
					|| bigCallBlackInfo.getScore() == null
					|| bigCallBlackInfo.getPass_or_reject() == null ){
					logger.error("大数据返回数据中包含空" + json);
				throw new Exception("大数据返回数据中包含空");
//				// 获取DD通知URL
//				DingdingUtil.setMessageToDingDing(sysParamForMq.getParamValueTwo(), "当前时间：" + com.honglu.backend.utils.DateUtils.parseToDateTimeStr(new Date()) +
//						", MQ error");
//
//				return null;
			}
			String passOrReject = bigCallBlackInfo.getPass_or_reject();
			if(Constants.BIG_DATA_NOPASS_SP_STATE.equals(passOrReject)){
				//审核不通过的
				bigCallBlackInfo.setPass_or_reject(Constants.NOPASS_SP_STAATE);
			}else {
				//审核通过的
				bigCallBlackInfo.setPass_or_reject(Constants.PASS_SP_STAATE);
			}
			return  bigCallBlackInfo;

		}catch (Exception e){
			logger.error("大数据返回数据解析失败" + json);
//			// 获取DD通知URL
//			DingdingUtil.setMessageToDingDing(sysParamForMq.getParamValueTwo(), "当前时间：" + com.honglu.backend.utils.DateUtils.parseToDateTimeStr(new Date()) +
//					", MQ消息回调处理异常，大数据返回数据解析失败");
//
//			return null;
			throw new Exception("大数据返回数据解析失败");
		}
	}
	
	/**  插入欺诈评分           **/
	public void qizhaScore(BigCallBlackInfo bigCallBlackInfo){
		logger.info("插入欺诈评分处理开始,申请单编号【"+bigCallBlackInfo.getApplication_id()+"】");
		/**  插入欺诈评分           **/
		ScFraudScoreDetail scFraudScoreDetail = new ScFraudScoreDetail();
		scFraudScoreDetail.setFraudScoreDetailId(UUIDUtils.getUUID());
		scFraudScoreDetail.setApplicationId(bigCallBlackInfo.getApplication_id());
		scFraudScoreDetail.setFraudRuleId("0");
		scFraudScoreDetail.setRuleName("大数据欺诈评分");
		scFraudScoreDetail.setScore(bigCallBlackInfo.getScore());
		scFraudScoreDetail.setCreateTime(DateUtils.parseToDateTimeStr(new Date()));
		scFraudScoreDetailDao.saveEntity(scFraudScoreDetail);
		
		logger.info("插入欺诈评分处理结束,申请单编号【"+bigCallBlackInfo.getApplication_id()+"】");
	}

	@Override
	@Transactional
	public boolean updateManualAuditAppState(BigCallBlackInfo bigCallBlackInfo) throws Exception {
 		if (!bigCallBackData(bigCallBlackInfo)) return false;
 		
		Application application = applicationService.getObjectById(bigCallBlackInfo.getApplication_id());
		// 获取参数值
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.UPDATE_STEP_STATUS);
		String stepStatus = sysParam.getParamValueOne();
		String creditMoney ="";  //授信额度值
		String appraiseMoney=""; //评估分数值
		//信用评分
		String promoteCreditMoney = "";
		try {
			Map<String, Object> map = queryScoreInfoServiceImpl.queryScoreInfoApi(bigCallBlackInfo.getApplication_id(), application.getScoreTplId());
			creditMoney =  map.get("amount").toString();  //授信额度值
			appraiseMoney = map.get("score").toString(); //评估分数值
			promoteCreditMoney = (String) map.get("promoteCreditMoney"); //提升授信额度值
			logger.info("大数据回调接口获取信用评分，授信额度值：【"+creditMoney+"】，评估分数值：【" +appraiseMoney+"】申请单编号：【"+bigCallBlackInfo.getApplication_id()+"】");
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("大数据回调接口获取信用评分出错，错误信息："+e.getMessage());
		}

		Map<String, Object> dataMapParam = new HashMap<String, Object>();
		dataMapParam.put("auditResult", ""); //审批结果
		dataMapParam.put("auditingState", Constants.DAIRG_SP_STAATE);//审核状态(待人工确定)
		dataMapParam.put("applicationId",bigCallBlackInfo.getApplication_id());//申请编号
		dataMapParam.put("isPushApp","3");//is_push_app：0-否、1-是、2：已发送、3：已回调 
		if("1".equals(application.getAppLevel())){
			dataMapParam.put("creditMoney", Double.parseDouble(creditMoney)+Double.parseDouble(promoteCreditMoney));//授信额度值
		}else{
			dataMapParam.put("creditMoney",creditMoney);//授信额度值
		}
		dataMapParam.put("appraiseMoney",appraiseMoney);//评估分数值
		dataMapParam.put("currentStep",stepStatus);//当前步骤号
		
		//接口走完，更新步骤标识、审批状态、审批结果描述
		int count = tdLoanInterfaceService.updateApplicationByStep(dataMapParam);
		if(count == 0){
			throw new RuntimeException("更新大数据返回审核记录失败，当前数据步骤号不是最新步骤");
		}
		logger.info("单笔更新大数据回调接口人工审核状态处理结束,申请单编号【"+bigCallBlackInfo.getApplication_id()+"】,currentStep【"+stepStatus+"】更新记录数：" + count);
		
		//插入欺诈评分
		qizhaScore(bigCallBlackInfo);

		return true;
	}
}
