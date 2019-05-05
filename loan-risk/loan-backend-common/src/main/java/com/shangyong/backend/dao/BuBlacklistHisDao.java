package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.BuBlacklistHis;

/**
 * BuBlacklistHisDao数据库操作接口类bean
 * @author xiajiyun
 * @date Fri Sep 01 22:35:56 CST 2017
 **/
@Mapper
public interface BuBlacklistHisDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BuBlacklistHis  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<BuBlacklistHis>  findAll(@Param("id") String id);

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
	int saveEntity(BuBlacklistHis record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(BuBlacklistHis record);

}