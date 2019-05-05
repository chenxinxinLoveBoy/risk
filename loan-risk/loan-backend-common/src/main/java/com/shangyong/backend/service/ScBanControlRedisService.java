package com.shangyong.backend.service;

import com.shangyong.backend.entity.ScBanControl;

/**
 * 禁止项规则控制表 查询接口定义
 * 
 * @author xk
 *
 */
public interface ScBanControlRedisService {


	/**
	 * 根据参数编号查询对应的参数信息
	 * @param decisionTreeId 		决策树序号
	 * @param banCode	禁止项规则编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	ScBanControl queryScBanControlByBanCodeRedis(String decisionTreeId, String banCode);


	/**
	 * 根据参数编号查询对应的参数信息
	 * @param tplId	模板序号
	 * @param banCode	 禁止项规则编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	ScBanControl queryScBanControlByBanCode(String tplId, String banCode);
}
