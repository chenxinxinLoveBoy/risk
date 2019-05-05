package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScScoreTpl;
import com.shangyong.backend.entity.ScScoreTplHis;

/**
 * ScScoreTplHisDao数据库操作接口类bean
 * 
 * @date Thu Jul 27 13:54:27 CST 2017
 **/
@Mapper
public interface ScScoreTplHisDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScScoreTplHis getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScScoreTplHis> findAll(@Param("id") String id);

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
	int saveEntity(ScScoreTplHis record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScScoreTplHis record);

	int insert(ScScoreTpl scScoreTpl);

	/**
	 * 分页查询信息
	 * 
	 */
	int listAllCount(ScScoreTplHis scScoreTplHis);

	List<ScScoreTplHis> findAll(ScScoreTplHis scScoreTplHis);

	ScScoreTplHis getEntityById(ScScoreTplHis scScoreTplHis);

}