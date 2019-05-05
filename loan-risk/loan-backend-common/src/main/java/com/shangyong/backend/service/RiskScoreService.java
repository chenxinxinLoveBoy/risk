package com.shangyong.backend.service;

/**
 *  信用评分卡规则校验
 * @author xiangxianjin
 *
 */
public interface RiskScoreService {

	/**
	 *  根据 大类编号 计算分数明细，并且入库
	 * @param scoreTplId			信用评分模板编号
	 * @param scoreBigCode		大类编号
	 * @param checkValue			需比对值
	 * @param applicationId		申请单号
	 * @throws Throwable
	 */
	public void queryApplyScoreApi(String scoreTplId, String scoreBigCode, Object checkValue, String applicationId);
}
