package com.shangyong.backend.dao.txy;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.txy.TxyAntiFraud;

/**
 * TxyAntiFraudServiceDao数据库操作接口类bean
 * @author mingke.shi
 * @date Sun Dec 10 17:19:38 CST 2017
 **/
@Mapper
public interface TxyAntiFraudServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TxyAntiFraud  selectByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TxyAntiFraud  queryById(String applicationId);

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
	int saveEntity(TxyAntiFraud record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(TxyAntiFraud record);
	
	

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(TxyAntiFraud record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(TxyAntiFraud record);

}