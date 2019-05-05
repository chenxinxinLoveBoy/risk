package com.shangyong.backend.service;

/**
 * 黑名单Redis相关service
 * @author hc
 *
 */
public interface BlackParamRedisService { 
	
	/**
	 * 查询黑名单信息缓存中是否存在，存在就是黑名单客户，不再去数据库中查询
	 * @param certCode 		证件号码
	 * @return boolean 		黑名单对象 true-存在， false不存在
	 */
	public boolean quryBuBlackBycertCode(String certCode) throws Throwable;
	 
}
