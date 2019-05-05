package com.shangyong.backend.service;

import com.shangyong.backend.entity.BigCallBlackInfo;

/**
 * 大数据回调service
 * @author CG
 *
 */
public interface BigDataCallBackService {

	/**
	 * 大数据回调接口走完，审批通过，更新步骤标识、审批状态、审批结果描述
	 */
	public boolean updatePassAppState(BigCallBlackInfo bigCallBlackInfo) throws Exception ;
	
	/**
	 * 大数据回调接口走完，审批未通过，更新步骤标识、审批状态、审批结果描述
	 */
	public boolean updateNoPassAppState(BigCallBlackInfo bigCallBlackInfo) throws Exception ;

	/**
	 * 大数据回调接口走完，审批人工审核的，更新步骤标识、审批状态、审批结果描述
	 */
	public boolean updateManualAuditAppState(BigCallBlackInfo bigCallBlackInfo) throws Exception ;
	
	/**
	* @Auth: kenzhao
	* @Desc: 检查数据的合法性
	 * @param json
	* @return: 返回是null，则数据格式不合法
	* @Time: 2017/8/17 0:38
	*/
	public BigCallBlackInfo bigDataCallBackCheck(String json) throws Exception ;

}
