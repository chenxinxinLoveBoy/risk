package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScAppBigChannelHis;

/**
 * ScAppBigChannelHisDao数据库操作接口类bean
 * @author kenzhao
 * @date Mon Sep 18 15:22:24 CST 2017
 **/
@Mapper
public interface ScAppBigChannelHisDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScAppBigChannelHis  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScAppBigChannelHis>  findAll(@Param("id") String id);

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
	int saveEntity(ScAppBigChannelHis record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScAppBigChannelHis record);

}