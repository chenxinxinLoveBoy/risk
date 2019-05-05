package com.shangyong.backend.dao.bqsrep;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.bqsrep.BqsRepPetitioner;

/**
 * BqsRepPetitionerServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:49 CST 2017
 **/
@Mapper
public interface BqsRepPetitionerServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepPetitioner  selectByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据主键applicationId查询）
	 * 
	 **/
	BqsRepPetitioner  queryById(String applicationId);

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
	int saveEntity(BqsRepPetitioner record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepPetitioner record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepPetitioner record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepPetitioner record);
	
	/**
	 * 根据手机号查询出对应的手机归属地
	 * @param phone
	 * @return
	 */
	String phoneAdress(String mobile);

}