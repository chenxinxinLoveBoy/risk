package com.shangyong.backend.service;

import com.shangyong.backend.entity.SysParam;

/**
 * 系统参数表 查询接口定义
 * 
 * @author CG
 *
 */
public interface BusinessParamRedisService {

	/**
	 * 
	 * @param paramValue
	 * @return 根据参数编号查询对应的参数信息,如果数据不存在就返回异常错误信息,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	public SysParam querySysParamByParamValueRedis(String paramValue);

}
