package com.shangyong.backend.service;

import com.shangyong.backend.entity.ScScoreBig;

/**
 * 评分卡大类 查询接口定义
 * 
 * @author xk
 *
 */
public interface ScScoreBigRedisService {

	/**
	 * 根据评分项规则大类编号查询对应的评分卡大类参数信息
	 * @param scoreBigCode  大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	public ScScoreBig queryScScoreBigByScoreBigCodeRedis(String scoreBigCode) throws Throwable;

}
