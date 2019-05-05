package com.shangyong.backend.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScRiskWhiteListDao;
import com.shangyong.backend.entity.ScRiskWhiteList;
import com.shangyong.backend.service.ScRiskParamRedisService;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;
/**
 * 白名单Redis相关service实现
 * @author hc
 *
 */
@Service
public class ScRiskParamRedisServiceImpl implements ScRiskParamRedisService {
	
	private Logger logger = LoggerFactory.getLogger(ScRiskParamRedisServiceImpl.class);
	
	@Autowired
	private ScRiskWhiteListDao scRiskWhiteListDao;
	
	@Override
	public ScRiskWhiteList quryRiskWhiteBycertCode(String certCode,String appName) throws Throwable {
		ScRiskWhiteList  riskWhiteList = new ScRiskWhiteList();
		logger.info("查询白名单是否已存在"+riskWhiteList +'\t');
		// 查询白名单是否已存在
		String value = RedisUtils.hget(RedisCons.RISK_SC_WHITE_CERCODE, certCode);
		logger.info("redis返回数据"+ value +'\t');
		if (StringUtils.isNotBlank(value)) {
			try {
				riskWhiteList = (ScRiskWhiteList) SysParamUtils.JsonToBean(value,ScRiskWhiteList.class);
			} catch (Throwable e) {
				logger.error("【查询白名单  certCode是否存在】 quryRiskWhiteBycertCode() String转Json服务异常：" + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException("转Json服务异常,异常信息：" + e.getMessage());
			}
		} else {
			logger.info("白名单不存在"+riskWhiteList  +'\t');
			riskWhiteList = refreshRiskWhiteBycertCodeRedis( certCode, appName);
		}
		return riskWhiteList;
	}

	@Override
	public ScRiskWhiteList refreshRiskWhiteBycertCodeRedis(String certCode,String appName) { 
		if( StringUtils.isNotBlank(certCode) && StringUtils.isNotBlank(appName)){
			ScRiskWhiteList White = new ScRiskWhiteList();
			White.setAppName(appName);
			White.setCertCode(certCode);
			ScRiskWhiteList scRiskWhiteList =  scRiskWhiteListDao.findByCodeAppName(White);
			if ( scRiskWhiteList == null  ) {
				logger.error("更新白名单redis 根据certCode和appName查询无数据返回, 请确认参数编号是否已配置"  +'\t');
				throw new RuntimeException("根据certCode和appName查询无数据返回, 请确认白名单是否已配置"  +'\t');
			}
			// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
			RedisUtils.hsetEx(RedisCons.RISK_SC_WHITE_CERCODE, certCode,SysParamUtils.ObjectToJson(scRiskWhiteList), 31536000);
			return scRiskWhiteList;
		}else{
			logger.error("certCode为 " +certCode +"appName为"+appName+'\t');
			return null;
		}
		
		
	}

}
