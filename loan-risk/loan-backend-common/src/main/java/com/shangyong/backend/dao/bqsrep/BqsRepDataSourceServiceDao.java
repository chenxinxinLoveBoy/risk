package com.shangyong.backend.dao.bqsrep;

import com.shangyong.backend.entity.bqsrep.BqsRepDataSource;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * BqsRepDataSourceServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:49 CST 2017
 **/
@Mapper
public interface BqsRepDataSourceServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepDataSource  selectByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据主键bqsPetitionerId查询）
	 * 
	 **/
	List<BqsRepDataSource>  queryListById(String bqsPetitionerId);

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
	int saveEntity(BqsRepDataSource record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepDataSource record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepDataSource record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepDataSource record);

}