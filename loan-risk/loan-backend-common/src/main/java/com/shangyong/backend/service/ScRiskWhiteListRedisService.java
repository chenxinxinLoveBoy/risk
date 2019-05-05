package com.shangyong.backend.service;

/**
 * 风控Redis白名单service
 * @author xiangxianjin
 *
 */
public interface ScRiskWhiteListRedisService {
	
	/**
	 * 根据平台，身份证号，有效状态，查询客户名单是否在白名单中
	 * Redis 永久有效，不存在redis就不是白名单，不再查询数据库
	 * @param appName		平台名称
	 * @param idCard		证件号码
	 * @param state				有效状态 01-有效、02-失效
	 * @return						是否是白名单 ： true - 是， false - 否
	 * @throws Exception
	 */
	public boolean queryRiskWhiterRedis(String appName, String idCard, String state);

	/**
	 * 根据平台，身份证号，有效状态，查询客户名单是否在白名单中
	 * 查询数据库
	 * @param appName		平台名称
	 * @param certCode		证件号码
	 * @param state				有效状态 01-有效、02-失效
	 * @return						是否是白名单 ： true - 是， false - 否
	 * @throws Exception
	 */
	boolean queryRiskWhite(String appName, String certCode, String state);
	 		
}
