package com.shangyong.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScDictBigDao;
import com.shangyong.backend.dao.ScDictSmallDao;
import com.shangyong.backend.entity.ScDictBig;
import com.shangyong.backend.entity.ScDictSmall;
import com.shangyong.backend.service.ScDictParamRedisService;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;

@Service
public class ScDictParamRedisServiceImpl implements ScDictParamRedisService {
	
	private Logger logger = LoggerFactory.getLogger(ScDictParamRedisServiceImpl.class);
	
	@Autowired
	private ScDictBigDao scDictBigDao;// 大类DAO
	
	@Autowired
	private ScDictSmallDao scDictSmallDao;// 小类DAO

	/**
	 * 查询数据字典大类dicBigCode是否存在
	 */
	@Override
	public ScDictBig quryBigCode(String dicBigCode) throws Throwable {
		ScDictBig sd = new ScDictBig();
		logger.info("判断数据字典是否已存在"+sd +'\t');
		// 判断大类数据字典是否已存在
		String value = RedisUtils.hget(RedisCons.RISK_DICT_BIG_CODE, dicBigCode);
		logger.info("redis返回数据"+ value +'\t');
		if (StringUtils.isNotBlank(value)) {
			try {
				sd = (ScDictBig) SysParamUtils.JsonToBean(value,ScDictBig.class);
			} catch (Throwable e) {
				logger.error("【查询数据字典大类  dicBigCode是否存在】 quryBigCode() String转Json服务异常：" + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException("转Json服务异常,异常信息：" + e.getMessage());
			}
		} else {
			logger.info("大类数据字典不存在"+sd  +'\t');
			sd = refreshScDictBigByCodeRedis(dicBigCode);
		}
		return sd;
	}
	
	/**
	 * 更新数据字典大类redis
	 */
	@Override
	public ScDictBig refreshScDictBigByCodeRedis(String dicBigCode) {
		ScDictBig scDictBig = scDictBigDao.queryByBigCode(dicBigCode);
		if ( scDictBig == null ) {
			logger.error("更新数据字典大类redis 根据dicBigCode号查询无数据返回, 请确认参数编号是否已配置"  +'\t');
			throw new RuntimeException("根据dicBigCode号查询无数据返回, 请确认数据字典大类dicBigCode号是否已配置"  +'\t');
		}
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_DICT_BIG_CODE, dicBigCode,
				SysParamUtils.ObjectToJson(scDictBig), 31536000);
		return scDictBig;
	}

	/**
	 * 查询数据字典小类dicSmallCode是否存在
	 */
	@Override
	public ScDictSmall qurySmallCode(String dicSmallCode) throws Throwable {
		ScDictSmall sdm = new ScDictSmall();
		logger.info("判断数据字典小类是否已存在"+sdm +'\t');
		// 判断数据字典小类是否已存在
		String value = RedisUtils.hget(RedisCons.RISK_DICT_SMALL_CODE, dicSmallCode);
		logger.info("redis返回数据"+ value +'\t');
		if (StringUtils.isNotBlank(value)) {
			try {
				sdm = (ScDictSmall) SysParamUtils.JsonToBean(value,ScDictSmall.class);
			} catch (Throwable e) {
				logger.error(" 【查询数据字典小类dicSmallCode是否存在】  qurySmallCode()String转Json服务异常：" + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException("转Json服务异常,异常信息：" + e.getMessage());
			}
		} else {
			logger.info("数据字典小类不存在"+sdm +'\t');
			sdm = refreshScDictSmallByCodeRedis(dicSmallCode);
		}
		return sdm;
	}

	/**
	 * 更新数据字典小类redis
	 */
	@Override
	public ScDictSmall refreshScDictSmallByCodeRedis(String dicSmallCode) { 
		ScDictSmall scDictSmall = scDictSmallDao.queryBySmallCode(dicSmallCode);
		if ( scDictSmall == null ) {
			logger.error("根据dicSmallCode号查询无数据返回, 请确认参数编号是否已配置"  +'\t');
			throw new RuntimeException("根据dicSmallCode号查询无数据返回, 请确认数据字典小类 dicSmallCode号是否已配置"  +'\t');
		}	
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_DICT_SMALL_CODE, dicSmallCode,
				SysParamUtils.ObjectToJson(scDictSmall), 31536000);
		return scDictSmall;
	}
	
	
	/**
	 * 根据大类编号查询对应的所有小类
	 * @param dicBigCode
	 * @return
	 */
	public String getRedisScDictSmall(String dicBigCode){
		List<ScDictSmall> list = new ArrayList<ScDictSmall>();
		// 为空直接返回
		if(StringUtils.isBlank(dicBigCode)){
			return "";
		}
		
		// 是否存在redis中，如果存在直接返回redis中，否则查库
		if(RedisUtils.hexists(RedisCons.GET_RISK_DICT_SMALL_CODE, dicBigCode)){
			String redisList = RedisUtils.hget(RedisCons.GET_RISK_DICT_SMALL_CODE, dicBigCode);
			return redisList;
		}
		
		list = scDictSmallDao.getRedisScDictSmall(dicBigCode);
		if(CollectionUtils.isNotEmpty(list)){
			// 存入redis中
			RedisUtils.hset(RedisCons.GET_RISK_DICT_SMALL_CODE, dicBigCode, SysParamUtils.ObjectToJson(list));
			return SysParamUtils.ObjectToJson(list);
		}

		return "";
	}

	/**
	 * 根据大类编号查询对应的所有小类
	 * @param dicBigCode
	 * @return
	 */
	public Map getRedisScDictSmallByMapKey(String dicBigCode){
		List<ScDictSmall> list = new ArrayList<ScDictSmall>();
		Map<String,String> listMap = new HashMap();
		// 为空直接返回
		if(StringUtils.isBlank(dicBigCode)){
			return listMap;
		}

		// 是否存在redis中，如果存在直接返回redis中，否则查库
		if(RedisUtils.hexists(RedisCons.GET_RISK_DICT_SMALL_CODE_MAP, dicBigCode)){
			String redisList = RedisUtils.hget(RedisCons.GET_RISK_DICT_SMALL_CODE_MAP, dicBigCode);
			listMap = SysParamUtils.JsonToMapString(redisList);
			return listMap;
		}

		list = scDictSmallDao.getRedisScDictSmallStatus(dicBigCode);
		if(CollectionUtils.isNotEmpty(list)){
			//将对象转为map放入缓存
			for (ScDictSmall scDictSmall: list
				 ) {
				listMap.put(scDictSmall.getDicSmallCode(), scDictSmall.getDicSmallValue());
			}

			// 存入redis中
			RedisUtils.hset(RedisCons.GET_RISK_DICT_SMALL_CODE_MAP, dicBigCode, SysParamUtils.ObjectToJson(listMap));
			return listMap;
		}
		return listMap;
	}

	/**
	 * 根据大类编号查询对应的所有小类
	 * 包含失效的字典
	 *
	 * @param dicBigCode
	 * @return
	 */
	@Override
	public String getRedisScDictSmallStatus(String dicBigCode) {
		List<ScDictSmall> list = new ArrayList<ScDictSmall>();
		// 为空直接返回
		if(StringUtils.isBlank(dicBigCode)){
			return "";
		}

		// 是否存在redis中，如果存在直接返回redis中，否则查库
		if(RedisUtils.hexists(RedisCons.GET_RISK_DICT_SMALL_CODE_STATUS, dicBigCode)){
			String redisList = RedisUtils.hget(RedisCons.GET_RISK_DICT_SMALL_CODE_STATUS, dicBigCode);
			return redisList;
		}

		list = scDictSmallDao.getRedisScDictSmallStatus(dicBigCode);
		if(CollectionUtils.isNotEmpty(list)){
			// 存入redis中
			RedisUtils.hset(RedisCons.GET_RISK_DICT_SMALL_CODE_STATUS, dicBigCode, SysParamUtils.ObjectToJson(list));
			return SysParamUtils.ObjectToJson(list);
		}

		return "";
	}
}
