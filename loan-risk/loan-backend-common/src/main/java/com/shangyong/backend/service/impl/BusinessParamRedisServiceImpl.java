package com.shangyong.backend.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.BusinessParamDao;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BusinessParamRedisService;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;

/**
 * 系统参数表 查询接口实现
 * 
 * @author CG
 *
 */
@Service
public class BusinessParamRedisServiceImpl implements BusinessParamRedisService{
	@Autowired
	private BusinessParamDao businessParamDao;
	private Logger logger = LoggerFactory.getLogger(BusinessParamRedisServiceImpl.class);


	public SysParam querySysParamByParamValueRedis(String paramValue) {

		SysParam sysParam = new SysParam();
		// 判断系统参数信息是否已存在
		String value = RedisUtils.hget(RedisCons.RISK_BUSINESS_PARAM_INFO, paramValue);
		if (StringUtils.isNotBlank(value)) {
			try {
				// 转换redis中的json为参数实体
				sysParam = (SysParam) SysParamUtils.JsonToBean(value, SysParam.class);
				if (Constants.STATE_FORBIDDEN.equals(sysParam.getStatue())) {
					logger.error("根据参数编号查询该参数状态为无效");
					throw new RuntimeException("根据参数编号【" + paramValue + "】查询该参数状态为无效");
				}
			} catch (Throwable e) {
				logger.error("querySysParamByParamValueRedis()String转Json服务异常：" + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException("转Json服务异常,异常信息：" + e.getMessage());
			}
		} else {
			sysParam = refreshSysParamByParamValueRedis(paramValue);
		}
		return sysParam;
	}

	private SysParam refreshSysParamByParamValueRedis(String paramValue) {
		SysParam sysParam = businessParamDao.queryByParamValueAndStatue(paramValue);//根据参数编号查询状态为正常的参数
		if (sysParam == null) {
			logger.error("根据参数编号查询无数据返回, 请确认参数编号是否已配置");
			throw new RuntimeException("根据参数编号【" + paramValue + "】查询无数据返回, 请确认参数编号是否已配置");
		}
		if (Constants.STATE_FORBIDDEN.equals(sysParam.getStatue())) {
			logger.error("根据参数编号查询该参数状态为无效");
			throw new RuntimeException("根据参数编号【" + paramValue + "】查询该参数状态为无效");
		}
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_BUSINESS_PARAM_INFO, paramValue, SysParamUtils.ObjectToJson(sysParam), 31536000);
		return sysParam;
	}

}
