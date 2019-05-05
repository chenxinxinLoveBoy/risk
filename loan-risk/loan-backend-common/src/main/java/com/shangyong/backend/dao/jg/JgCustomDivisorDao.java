package com.shangyong.backend.dao.jg;

import com.shangyong.backend.entity.jg.JgCustomDivisor;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * JgCustomDivisorDao数据库操作接口类bean
 * @date Fri May 25 16:32:45 CST 2018
 **/

public interface JgCustomDivisorDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	JgCustomDivisor  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<JgCustomDivisor>  findAll(@Param("id") String id);

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
	int saveEntity(JgCustomDivisor record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(JgCustomDivisor record);

}