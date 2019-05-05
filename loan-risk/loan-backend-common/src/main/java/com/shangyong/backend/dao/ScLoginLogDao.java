package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScLoginLog;

/**
 * ScLoginLogDao数据库操作接口类bean
 * 
 * @author xk
 * @date Tue Jul 04 09:19:38 CST 2017
 **/
@Mapper
public interface ScLoginLogDao {

	public int listAllCount(ScLoginLog scLoginLog);

	List<ScLoginLog> getAll(ScLoginLog scLoginLog);

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScLoginLog getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScLoginLog> findAll(@Param("id") String id);

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
	boolean saveEntity(ScLoginLog record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScLoginLog record);

}