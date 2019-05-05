package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.BuPromoteDetailed;

/**
 * BuPromoteDetailedDao数据库操作接口类bean
 * @author kenzhao
 * @date Fri Sep 15 17:20:26 CST 2017
 **/
@Mapper
public interface BuPromoteDetailedDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BuPromoteDetailed  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<BuPromoteDetailed>  findAll(@Param("id") String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(@Param("id") String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(BuPromoteDetailed record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(BuPromoteDetailed record);

	/**
	 * 
	 * 查询所有
	 * 
	 **/
	List<BuPromoteDetailed>  findAllByObj(BuPromoteDetailed buPromoteDetailed);
	
	/**
	 * 统计
	 * @return
	 */
	int queryAllCount(BuPromoteDetailed buPromoteDetailed);
	
	/**
	 * add: xiajiyun
	 * 查询所有id
	 * 
	 **/
	public Map<String, Object>  findAllId(BuPromoteDetailed buPromoteDetailed);
	
	/**
	 * 
	 * 查询所有异常单子
	 * 
	 **/
	public List<BuPromoteDetailed>  findAllByIsError(BuPromoteDetailed buPromoteDetailed);

	/**
	 * 
	 * 根据taskType customerId 查询信息
	 * 
	 **/
	String getJosnStoragePathBytaskTypeCustomerId(BuPromoteDetailed buPromoteDetailed);
	
}