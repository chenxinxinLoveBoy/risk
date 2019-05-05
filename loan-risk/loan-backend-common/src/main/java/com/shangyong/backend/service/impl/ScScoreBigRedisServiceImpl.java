package com.shangyong.backend.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScScoreBigDao;
import com.shangyong.backend.entity.ScScoreBig;
import com.shangyong.backend.service.ScScoreBigRedisService;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;
/**
 * 评分卡大类 查询接口实现
 * @author xk
 *
 */
@Service
public class ScScoreBigRedisServiceImpl implements ScScoreBigRedisService {
	@Autowired
	private ScScoreBigDao scScoreBigDao;
	private Logger logger = LoggerFactory.getLogger(ScScoreBigRedisServiceImpl.class);

	
	/**
	 * 根据评分项规则大类编号查询对应的评分卡大类参数信息
	 * @param scoreBigCode	大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	@Override
	public ScScoreBig queryScScoreBigByScoreBigCodeRedis(String scoreBigCode) throws Throwable {

		ScScoreBig scScoreBig = new ScScoreBig();
		// 判断系统参数信息是否已存在
		String value = RedisUtils.hget(RedisCons.RISK_SC_SCORE_BIG, scoreBigCode);
		if (StringUtils.isNotBlank(value)) {
			try {
				// 转换redis中的json为参数实体
				scScoreBig = (ScScoreBig) SysParamUtils.JsonToBean(value, ScScoreBig.class);
			} catch (Throwable e) {
				logger.error("queryScScoreBigByScoreBigCodeRedis()String转Json服务异常：" + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException("转Json服务异常,异常信息：" + e.getMessage());
			}
		} else {
			scScoreBig = refreshScScoreBigByScoreBigCodeRedis(scoreBigCode);
		}
		return scScoreBig;
	}

	private ScScoreBig refreshScScoreBigByScoreBigCodeRedis(String scoreBigCode) {
		ScScoreBig scScoreBig = scScoreBigDao.queryByScoreBigCode(scoreBigCode);
		if (scScoreBig == null) {
			logger.info("根据评分大类编号【" + scoreBigCode + "】查询无数据，返回null");
			return null;
		}
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_SC_SCORE_BIG, scoreBigCode, SysParamUtils.ObjectToJson(scScoreBig), 31536000);
		return scScoreBig;
	}

}
