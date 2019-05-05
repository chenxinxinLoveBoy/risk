package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.entity.ScDecisionTreeHis;

/**
 * ScDecisionTreeHisDao数据库操作接口类bean
 * 
 * @author xiajiyun
 * @date Thu Aug 03 21:51:02 CST 2017
 **/
@Mapper
public interface ScDecisionTreeHisDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScDecisionTreeHis getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScDecisionTreeHis> findAll(@Param("id") String id);

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
	int saveEntity(ScDecisionTreeHis record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScDecisionTreeHis record);

	int insert(ScDecisionTree scDecisionTree);

	/**
	 * 分页查询信息
	 * 
	 */
	int listAllCount(ScDecisionTreeHis scDecisionTreeHis);

	List<ScDecisionTreeHis> findAll(ScDecisionTreeHis scDecisionTreeHis);

	ScDecisionTreeHis getEntityById(ScDecisionTreeHis scDecisionTreeHis);

}