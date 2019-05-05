package com.shangyong.backend.dao.bqs;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.bqs.BqsForwardInfo;

/**
 * BqsForwardInfoServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Sun Dec 10 15:31:49 CST 2017
 **/
@Mapper
public interface BqsForwardInfoServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsForwardInfo  selectByPrimaryKey(@Param("id") Long id);

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
	int saveEntity(BqsForwardInfo record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsForwardInfo record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsForwardInfo record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsForwardInfo record);

}