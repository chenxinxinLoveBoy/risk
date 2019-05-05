package com.shangyong.backend.dao.bqsrep;

import com.shangyong.backend.entity.bqsrep.BqsRepMnoInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * BqsRepMnoInfoServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:49 CST 2017
 **/
@Mapper
public interface BqsRepMnoInfoServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepMnoInfo  selectByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据主键bqsPetitionerId查询）
	 * 
	 **/
	BqsRepMnoInfo  queryById(String bqsPetitionerId);
	

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(BqsRepMnoInfo record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepMnoInfo record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepMnoInfo record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepMnoInfo record);

}