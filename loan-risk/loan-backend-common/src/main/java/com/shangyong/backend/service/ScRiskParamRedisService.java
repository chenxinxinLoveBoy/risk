package com.shangyong.backend.service;

import com.shangyong.backend.entity.ScRiskWhiteList;

/**
 * 白名单Redis相关service
 * @author hc
 *
 */
public interface ScRiskParamRedisService {
	
	
	/** 
	 *证件号码查询 redis
	 */
	public ScRiskWhiteList quryRiskWhiteBycertCode(String certCode, String appName) throws Throwable;
	 
	
	/** 
	 *刷新参数数据到白名单redis 
	 */
	public ScRiskWhiteList refreshRiskWhiteBycertCodeRedis(String certCode, String appName);
	

}
