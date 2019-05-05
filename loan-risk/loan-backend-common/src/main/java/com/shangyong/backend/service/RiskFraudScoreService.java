package com.shangyong.backend.service;

/**
 * 欺诈评分接口
 * @author xiangxianjin
 *
 */
public interface RiskFraudScoreService {

	/**
	 * 根据 大类编号 计算分数明细，并且入库
	 * 
	 * @param fraudRuleTplId	欺诈规则模板ID
	 * @param fraudRuleBigCode	欺诈规则大类编号
	 * @param checkValue			需比对值
	 * @param applicationId 		申请单号
	 * @throws Throwable
	 */
	public void queryApplyScoreApi(String fraudRuleTplId, String fraudRuleBigCode, Object checkValue, String applicationId, String type);

}
