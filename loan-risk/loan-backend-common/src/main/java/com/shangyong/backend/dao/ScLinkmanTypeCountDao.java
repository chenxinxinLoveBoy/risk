package com.shangyong.backend.dao;

import com.shangyong.backend.entity.ScLinkmanTypeCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ScLinkmanTypeCountDao数据库操作接口类bean
 * @author kenzhao
 * @date Fri Apr 20 16:05:58 CST 2018
 **/
@Mapper
public interface ScLinkmanTypeCountDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScLinkmanTypeCount  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScLinkmanTypeCount> findAll(@Param("id") String id);

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
	int saveEntity(ScLinkmanTypeCount record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScLinkmanTypeCount record);

}