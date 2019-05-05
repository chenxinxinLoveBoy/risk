package com.shangyong.backend.service;

/**
 * 校验白名单信息service
 * 不是白名单在判断是否为黑名单
 * @author CG
 *
 */
public interface CheckCusBlackService {
	
	/**
	 * 根据证件号码判断是否存在闪贷拒绝名单,如存在，则更新审批单状态为审批不通过
	 * @param banControlTplId 模板编号
	 * @param appName	应用编号
	 * @param applicationId  申请单编号
	 * @param certNo	证件号码
	 * @param currentStep 当前步骤号
	 * @param appLevel 老用户标识
	 * @return 存在拒绝名单的条数（大于0则存在）
	 * @throws Exception 
	 * @throws Throwable 
	 */
	public boolean checkCusBlack(String banControlTplId, String appName, String applicationId, String certNo, String currentStep, String appLevel, String phoneNum);


    /**
     * 根据证件号码判断是否存在闪贷拒绝名单,如存在，则更新审批单状态为审批不通过
     * @param banControlTplId 模板编号
     * @param appName	应用编号
     * @param applicationId  申请单编号
     * @param certNo	证件号码
     * @param appLevel 老用户标识
     * @return 存在拒绝名单的条数（大于0则存在）
     * @throws Exception
     * @throws Throwable
     */
	public boolean checkCusBlack(String banControlTplId, String appName, String applicationId, String certNo, String appLevel, String phoneNum);

}
