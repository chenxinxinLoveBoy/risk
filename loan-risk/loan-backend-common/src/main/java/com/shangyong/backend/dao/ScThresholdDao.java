package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScThreshold;

/**
 * ScThresholdDao数据库操作接口类bean
 * @author xxj
 * @date Wed Jul 05 20:55:50 CST 2017
 **/

@Mapper
public interface ScThresholdDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScThreshold  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScThreshold>  findAll(@Param("id") String id);

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
	int saveEntity(ScThreshold record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScThreshold record);

	/**
	 * 
	 * 查询所有（根据ScThreshold查询）
	 * 
	 **/
	List<ScThreshold> findAll(ScThreshold scThreshold);

	int listAllCount(ScThreshold scThreshold);

	ScThreshold getEntityByObj(ScThreshold scThreshold);

	ScThreshold getByScore(@Param("score") String score, @Param("fraudRuleTplId") String fraudRuleTplId);
	
	/**
	 * 根据分数查询
	 * @param scThreshold
	 * @return
	 */
	List<ScThreshold> queryByScore(ScThreshold scThreshold);

}