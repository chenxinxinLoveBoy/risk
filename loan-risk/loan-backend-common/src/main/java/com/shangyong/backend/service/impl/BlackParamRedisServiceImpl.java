package com.shangyong.backend.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangyong.backend.service.BlackParamRedisService;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.utils.RedisUtils;
/**
 * 黑名单Redis相关service实现
 * @author hc
 *
 */
@Service
public class BlackParamRedisServiceImpl implements BlackParamRedisService {
	
	private Logger logger = LoggerFactory.getLogger(BlackParamRedisServiceImpl.class);
	
	/**
	 * 查询黑名单信息缓存中是否存在，存在就是黑名单客户，不再去数据库中查询
	 * @param certCode 		证件号码
	 * @return boolean 		黑名单对象 true-存在， false不存在
	 */
	@Override
	public boolean quryBuBlackBycertCode(String certCode)  {
		logger.info("查询黑名单是否已存在, certCode="+certCode +'\t');
		if(StringUtils.isBlank(certCode)){
			logger.error("查询客户名单是否在黑名单中接口请求参数不完整");
			throw new RuntimeException("查询客户名单是否在黑名单接口请求参数不完整");
		}
		// 查询黑名单是否已存在
		String value = RedisUtils.hget(RedisCons.RISK_BLACK_CERT_CODE, certCode);
		logger.info("redis返回数据"+ value +'\t');
		if (StringUtils.isNotBlank(value)) {
				return true;
		}
		return false;
	}

}
