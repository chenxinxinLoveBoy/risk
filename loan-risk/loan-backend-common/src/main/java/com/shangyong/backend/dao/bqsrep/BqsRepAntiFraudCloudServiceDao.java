package com.shangyong.backend.dao.bqsrep;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.bqsrep.BqsRepAntiFraudCloud;

/**
 * BqsRepAntiFraudCloudServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:48 CST 2017
 **/
@Mapper
public interface BqsRepAntiFraudCloudServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepAntiFraudCloud  selectByPrimaryKey(@Param("id") Long id);
	

	/**
	 * 
	 * 查询（根据主键bqsPetitionerId查询）
	 * 
	 **/
	BqsRepAntiFraudCloud  queryByPid(String bqsPetitionerId);

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
	int saveEntity(BqsRepAntiFraudCloud record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepAntiFraudCloud record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepAntiFraudCloud record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepAntiFraudCloud record);

}