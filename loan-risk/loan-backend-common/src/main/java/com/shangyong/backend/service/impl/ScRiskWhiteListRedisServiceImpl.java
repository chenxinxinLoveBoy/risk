package com.shangyong.backend.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.ScRiskWhiteList;
import com.shangyong.backend.service.ScRiskWhiteListRedisService;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;

/**
 * 风控Redis白名单service
 * @author xiangxianjin
 */
@Service
public class ScRiskWhiteListRedisServiceImpl implements ScRiskWhiteListRedisService{
	private Logger logger = LoggerFactory.getLogger(ScRiskWhiteListRedisServiceImpl.class);
	
	/**
	 * 白名单service接口
	 */
	@Autowired
	private ScRiskWhiteListServiceImpl scRiskWhiteListServiceImpl;

	/**
	 * 根据平台，身份证号，有效状态，查询客户名单是否在白名单中 
	 * Redis 永久有效，不存在redis就不是白名单，不再查询数据库
	 * @param appName		平台名称
	 * @param certCode		证件号码
	 * @param state				有效状态 01-有效、02-失效
	 * @return						是否是白名单 ： true - 是， false - 否
	 * @throws Exception 
	 */
	@Override
	public boolean queryRiskWhiterRedis(String appName, String certCode, String state) {
		if(StringUtils.isBlank(appName) || StringUtils.isBlank(certCode) || StringUtils.isBlank(state)){
			logger.error("查询客户名单是否在白名单中接口请求参数不完整");
			throw new RuntimeException("查询客户名单是否在白名单中接口请求参数不完整");
		}
		String scRiskWhite = RedisUtils.hget(RedisCons.RISK_SC_WHITE_CERCODE, certCode+"_"+appName);
		//优先缓存，若存在，直接返回是白名单客户
		if(StringUtils.isNotBlank(scRiskWhite)){
			try {
				ScRiskWhiteList scRiskWhiteBean = (ScRiskWhiteList) SysParamUtils.JsonToBean(scRiskWhite,ScRiskWhiteList.class);
				if(Constants.STATE_NORMAL.equals(scRiskWhiteBean.getState())){
					return true;
				}
			} catch (Exception e) {
				logger.error("查询客户名单是否在白名单中,json转为bean发生错误!");
				e.printStackTrace();
				throw new RuntimeException("查询客户名单是否在白名单中,json转为bean发生错误!");
			}
		}
		return false;
	}

	/**
	 * 根据平台，身份证号，有效状态，查询客户名单是否在白名单中 
	 * 查询数据库
	 * @param appName		平台名称
	 * @param certCode		证件号码
	 * @param state				有效状态 01-有效、02-失效
	 * @return						是否是白名单 ： true - 是， false - 否
	 * @throws Exception 
	 */
	@Override
	public boolean queryRiskWhite(String appName, String certCode, String state) {
		if(StringUtils.isBlank(appName) || StringUtils.isBlank(certCode) || StringUtils.isBlank(state)){
			logger.error("查询客户名单是否在白名单中接口请求参数不完整");
			throw new RuntimeException("查询客户名单是否在白名单中接口请求参数不完整");
		}
		ScRiskWhiteList scRiskWhiteList = new ScRiskWhiteList();
		scRiskWhiteList.setAppName(appName);
		scRiskWhiteList.setCertCode(certCode);
		scRiskWhiteList.setState(state);
		int size= scRiskWhiteListServiceImpl.queryWhiteCount(scRiskWhiteList);
		logger.error("查询客户名单是否在白名单，size="+size);
		if(size > 0){
			return true;
		}
		return false;
	}
}
