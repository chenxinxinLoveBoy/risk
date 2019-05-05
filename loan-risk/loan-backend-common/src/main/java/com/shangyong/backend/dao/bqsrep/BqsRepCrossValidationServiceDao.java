package com.shangyong.backend.dao.bqsrep;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.bqsrep.BqsRepCrossValidation;

/**
 * BqsRepCrossValidationServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Fri Dec 15 14:16:10 CST 2017
 **/
@Mapper
public interface BqsRepCrossValidationServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepCrossValidation  selectByPrimaryKey(@Param("id") Long id);

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
	int saveEntity(BqsRepCrossValidation record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepCrossValidation record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepCrossValidation record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepCrossValidation record);

	/**
	 * 
	 * 查询（根据bqsPetitionerId查询）
	 * 
	 **/
	List<BqsRepCrossValidation> queryById(String bqsPetitionerId);
	/**
	 * 查询入网时长
	 * @param bqsPetitionerId
	 */
	BqsRepCrossValidation queryRWById(BqsRepCrossValidation bqsRepCrossValidation);

}