package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.ScFraudRule;

/**
 * 欺诈评分小类 查询接口定义
 * 
 * @author xk
 *
 */
public interface ScScoreSmallFraudService {
	
	/**
	 * 根据欺诈评分大类序号查询对应的小类评分参数信息
	 * @param decisionTreeId 决策树序号
	 * @param scoreBigCode 大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	public List<ScFraudRule>  queryScFraudScoreSmallByDecisionTree(String decisionTreeId, String bancode);
	/**
	 * 根据欺诈评分大类序号查询对应的小类评分参数信息
	 * 
	 * @param fraudRuleTplId 模板序号
	 * @param scoreBigCode 大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	public List<ScFraudRule> queryScFraudScoreSmallByScoreBigCode(String fraudRuleTplId, String scoreBigCode);

}
