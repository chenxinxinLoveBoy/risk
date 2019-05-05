package com.shangyong.backend.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.entity.SysParam;

/**
 * 校验白名单信息service
 * 不是白名单再判断是否为黑名单
 * @author CG
 *
 */
@Service
public class CheckCusBlackServiceImpl implements CheckCusBlackService {
	private static Logger logger = LoggerFactory.getLogger(CheckCusBlackServiceImpl.class);
	/** 黑名单接口 **/
	@Autowired
	private BlacklistService blacklistService;

	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;
	/**
	 * 风控Redis白名单service接口
	 */
	@Autowired
	private ScRiskWhiteListRedisService scRiskWhiteListRedisServiceImpl;
	
	@Autowired
    private SysParamRedisService sysParamRedisService;
	
	/**
	 * 禁止项查询接口
	 */
	@Autowired
	private ScBanControlRedisServiceImpl scBanControlRedisServiceImpl;
	
	@Autowired
	private ApplicationServiceImpl applicationServiceImpl;
	
	@Autowired
	private ScDecisionTreeServiceImpl scDecisionTreeService;
	
	/**
	 * 根据证件号码判断是否存在洪澄拒绝名单,如存在，则更新审批单状态为审批不通过
	 * @param banControlTplId 禁止项编号
	 * @param appName	应用编号
	 * @param applicationId  申请单编号
	 * @param certNo	证件号码
	 * @param currentStep 当前步骤号
	 * @param appLevel 老用户标识
	 * @return 在拒绝名单为true,不存在则为false
	 * @throws Throwable 
	 */
	@Override
	public boolean checkCusBlack(String banControlTplId, String appName, String applicationId, String certNo, String currentStep, String appLevel, String phoneNum){
		Application app = applicationServiceImpl.getObjectById(applicationId);
		
		boolean flag = false;
//		if(Constants.APP_LEVEL.equals(appLevel)){
//			String decisionTreeId = app.getDecisionTreeId();
//			ScDecisionTree scDecisionTree = scDecisionTreeService.getEntityByDecisionTreeId(decisionTreeId);
//			if (scDecisionTree == null) {
//				logger.info("黑名单接口查询决策树为空，申请单编号：" + applicationId + ",决策树编号:" + decisionTreeId);
//				throw new RuntimeException("查询决策树为空，申请单编号：" + applicationId + ",决策树编号:" + decisionTreeId);
//			}
//			if (!("01").equals(scDecisionTree.getImplementType())) {// 实施类型(01-大数据)
//		    	logger.info("老用户不检测黑名单");
//				return flag;
//			}	
//		}

		//从系统中获取检测规则对象
		ScBanControl scBanControl = scBanControlRedisServiceImpl.queryScBanControlByBanCode(banControlTplId, BanCodeEnum.BLACKLIST_USER.getCode());
		//规则停用，则继续校验下一个规则
		if(scBanControl != null && Constants.STATE_FORBIDDEN.equals(scBanControl.getState())){
	    	logger.info("用户黑名单检测状态失效，不检测黑名单");
			return flag;
		}

		logger.info("开始校验白名单信息：appNmae=" + appName + ",certCode=" + certNo);
		boolean isWhite = scRiskWhiteListRedisServiceImpl.queryRiskWhite(appName, certNo, Constants.STATE_NORMAL);
		logger.info("白名单信息校验完成：isWhite = "+ isWhite);
		if(!isWhite){
			logger.info("用户非白名单用户，继续黑名单用户检核");
			//根据身份证、手机号、设备id查询黑名单表，如果有数据直接return返回，跟新状态
			boolean blackFlag = blacklistService.isInBlacklist(certNo, phoneNum, "");
	    	logger.info("检测结果blackFlag = " + blackFlag);
	    	//用户存在黑名单中
	    	if (blackFlag) {
	    		flag = true;
	 			SysParam isUseAllStep = sysParamRedisService.querySysParamByParamValueRedis(Constants.ISUSE_ALL_STEP);
	 			String isUseAllStepflag=isUseAllStep.getParamValueOne();
	 		    if("1".equals(isUseAllStepflag)){
	 		       return flag; 	
	 		    }
	        	 Map<String, Object> blackMap = new HashMap<String, Object>();
	        	 blackMap.put("isStep", Constants.NO_PASS_STEP); //步骤标识：99 表示被黑名单拒绝
	        	 blackMap.put("auditResult", "用户存在洪澄拒绝名单");
	        	 blackMap.put("auditingState", Constants.NOPASS_SP_STAATE);//审核不通过
	        	 blackMap.put("banCode",BanCodeEnum.BLACKLIST_USER.getCode());//禁止项规则对应编号
	        	 blackMap.put("applicationId",applicationId);
	        	 blackMap.put("currentStep", currentStep);//当前步骤号

	     		int updateCount = tdLoanInterfaceService.updateApplicationByStep(blackMap);
	        	 logger.info("用户证件号码存在洪澄拒绝名单,更新审批单状态为审批不通过,更新记录数：" + updateCount);
	        }
		}
    	return flag;
	}

	/**
	 * 根据证件号码判断是否存在洪澄拒绝名单,如存在，则更新审批单状态为审批不通过
	 * @param banControlTplId 禁止项编号
	 * @param appName	应用编号
	 * @param applicationId  申请单编号
	 * @param certNo	证件号码
	 * @param appLevel 老用户标识
	 * @return 在拒绝名单为true,不存在则为false
	 * @throws Throwable
	 */
	@Override
	public boolean checkCusBlack(String banControlTplId, String appName, String applicationId, String certNo, String appLevel, String phoneNum){
//		Application app = applicationServiceImpl.getObjectById(applicationId);
		boolean flag = false;

//		logger.info("开始校验白名单信息：appNmae=" + appName + ",certCode=" + certNo);
//		boolean isWhite = scRiskWhiteListRedisServiceImpl.queryRiskWhite(appName, certNo, Constants.STATE_NORMAL);
//		logger.info("白名单信息校验完成：isWhite = "+ isWhite);
//		if(!isWhite){

			//从系统中获取检测规则对象
			ScBanControl scBanControl = scBanControlRedisServiceImpl.queryScBanControlByBanCode(banControlTplId, BanCodeEnum.BLACKLIST_USER.getCode());
			//有配置规则，规则失效，则继续校验下一个规则
			if(scBanControl == null || Constants.STATE_FORBIDDEN.equals(scBanControl.getState())){
				logger.info("用户黑名单检测状态失效，不检测黑名单");
				return flag;
			}
			logger.info("用户非白名单用户，继续黑名单用户检核");
			//根据身份证、手机号、设备id查询黑名单表，如果有数据直接return返回，跟新状态
			boolean blackFlag = blacklistService.isInBlacklist(certNo, phoneNum, "");
	    	logger.info("检测结果blackFlag = " + blackFlag);
			//用户存在黑名单中
			if (blackFlag) {
				flag = true;
				Map<String, Object> blackMap = new HashMap<String, Object>();
				blackMap.put("isStep", Constants.NO_PASS_STEP); //步骤标识：99 表示被黑名单拒绝
				blackMap.put("auditResult", "用户存在洪澄拒绝名单");
				blackMap.put("auditingState", Constants.NOPASS_SP_STAATE);//审核不通过
				blackMap.put("banCode", BanCodeEnum.BLACKLIST_USER.getCode());//禁止项规则对应编号
				blackMap.put("applicationId",applicationId);
				blackMap.put("currentStep", Constants.SY_RULE_ID);//当前潘多拉黑名单

				int updateCount = tdLoanInterfaceService.updateApplicationByStep(blackMap);
				logger.info("用户证件号码存在洪澄拒绝名单,更新审批单状态为审批不通过,更新记录数：" + updateCount);
			}
//		}
		return flag;
	}
}
