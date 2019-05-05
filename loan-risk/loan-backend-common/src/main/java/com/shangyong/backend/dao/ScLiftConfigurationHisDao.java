package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScLiftConfiguration;
import com.shangyong.backend.entity.ScLiftConfigurationHis;

/**
 * ScLiftConfigurationHisDao数据库操作接口类bean
 * 
 * @author xk
 * @date Wed Sep 13 18:51:24 CST 2017
 **/
@Mapper
public interface ScLiftConfigurationHisDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScLiftConfigurationHis getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScLiftConfigurationHis> findAll(@Param("id") String id);

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
	int saveEntity(ScLiftConfigurationHis scLiftConfigurationHis);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScLiftConfigurationHis scLiftConfigurationHis);

	int insert(ScLiftConfiguration scLiftConfiguration);

	/**
	 * 分页查询信息
	 * 
	 */
	int listAllCount(ScLiftConfigurationHis scLiftConfigurationHis);

	List<ScLiftConfigurationHis> findAll(ScLiftConfigurationHis scLiftConfigurationHis);

}