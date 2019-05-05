package com.shangyong.backend.dao;

import com.shangyong.backend.entity.TdApplication;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * TdApplicationDao数据库操作接口类bean
 * @author kenzhao
 * @date Thu Mar 08 11:20:34 CST 2018
 **/
@Mapper
public interface TdApplicationDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TdApplication  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<TdApplication>  findAll();

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
	int saveEntity(TdApplication record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(TdApplication record);

}