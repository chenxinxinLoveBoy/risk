package com.shangyong.backend.service;

import java.util.Map;

import com.shangyong.backend.entity.ScDictBig;
import com.shangyong.backend.entity.ScDictSmall;

public interface ScDictParamRedisService {
	
	/**
	 * 根据大类数据字典大类编号 dicBigCode
	 * 查询数据字典大类中文名称 dicBigValue
	 * @param dicBigCode
	 * @return
	 * @throws Throwable
	 */
	public ScDictBig quryBigCode(String dicBigCode) throws Throwable;
	 
	
	/**
	 * 刷新参数数据到数据字典大类redis
	 * 
	 * 
	 * @return
	 */
	public ScDictBig refreshScDictBigByCodeRedis(String dicBigCode);
	
	
	/**
	 * 根据小类数据字典大类编号 dicBigCode
	 * 查询数据字典大类中文名称 dicBigValue
	 * @param dicBigCode
	 * @return
	 * @throws Throwable
	 */
	public ScDictSmall qurySmallCode(String dicBigCode) throws Throwable;
	 
	
	/**
	 * 刷新参数数据到数据字典小类redis
	 * 
	 * 
	 * @return
	 */
	public ScDictSmall refreshScDictSmallByCodeRedis(String dicBigCode);

	/**
	 * 根据大类编号查询对应的所有小类
	 * @param dicBigCode
	 * @return
	 */
	public String getRedisScDictSmall(String dicBigCode);

	/**
	 * 根据大类编号查询对应的所有小类
	 * @param dicBigCode
	 * @return
	 */
	public Map getRedisScDictSmallByMapKey(String dicBigCode);


	/**
	 * 根据大类编号查询对应的所有小类
	 * 包含失效的字典
	 * @param dicBigCode
	 * @return
	 */
	public String getRedisScDictSmallStatus(String dicBigCode);

}
