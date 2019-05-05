package com.shangyong.backend.service;

import java.util.Map;

/**
 *  评分 - 分数等级
 * @author xiangxianjin
 *
 */
public interface QueryScoreInfoService {

	/**
	 * 根据申请单号获取 评分和对应的放款金额
	 * @param applicationId	申请单编号
	 * @param scoreTplId		信用评分模板编号
	 * @return map {"score":90, "amount":500}
	 * @throws Throwable
	 */
	public Map<String, Object> queryScoreInfoApi(String applicationId, String scoreTplId) throws Throwable;
}
