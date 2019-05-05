package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.ScScoreSmall;

/**
 * 评分卡小类 查询接口定义
 * 
 * @author xk
 *
 */
public interface ScScoreSmallRedisService {

	/**
	 * 根据评分卡大类序号查询对应的小类评分参数信息
	 * 
	 * @param decisionTreeId 	决策树编号
	 * @param scoreBigCode 	大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	public List<ScScoreSmall> queryScScoreSmallByDecisionTree(String decisionTreeId, String scoreBigCode) ;

	/**
	 * 根据评分卡大类序号查询对应的小类评分参数信息
	 * 
	 * @param scoreTplId 		信用评分模板编号
	 * @param scoreBigCode 	大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	public List<ScScoreSmall> queryScScoreSmallByScoreBigIdRedis(Integer scoreTplId, String scoreBigCode) ;

}
