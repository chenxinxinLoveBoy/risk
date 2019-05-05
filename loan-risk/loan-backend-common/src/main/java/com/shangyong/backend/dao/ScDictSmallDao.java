package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.ScDictSmall;

/**
 * ScDictSmallDao数据库操作接口类bean
 * @author xk
 * @date Fri Jun 02 22:44:25 CST 2017
 **/

@Mapper
public interface ScDictSmallDao{

	@SuppressWarnings("rawtypes")
	public List<Map> getAll(Integer scDictBig);
	
	public int listAllCount(ScDictSmall scDictSmall);

	public List<ScDictSmall> listViewAll(ScDictSmall scDictSmall);

	public boolean update(ScDictSmall scDictSmall);

	public int save(ScDictSmall scm);

	public int getCountDicSmsCodde(ScDictSmall scDictSmall);

	public ScDictSmall getObjectById(ScDictSmall scDictSmall); 

	public boolean deleteAll(ScDictSmall scDictSmall);

	public List<Map> findById(ScDictSmall scDictSmall);

	public ScDictSmall queryBySmallCode(String dicSmallCode);

	/**
	 * 根据大类的编号查询对应的所有小类
	 * @param dicBigCode
	 * @return
	 */
	public List<ScDictSmall> getRedisScDictSmall(String dicBigCode);
	/**
	 * 根据大类的编号查询对应的所有小类
	 * 包含失效的字典
	 * @param dicBigCode
	 * @return
	 */
	public List<ScDictSmall> getRedisScDictSmallStatus(String dicBigCode);

}