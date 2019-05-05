package com.shangyong.backend.dao.sh;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.sh.ShCreditPrompt;

/**
 * ShCreditPromptDao数据库操作接口类bean
 * @author kenzhao
 * @date Tue Mar 13 17:10:35 CST 2018
 **/
@Mapper
public interface ShCreditPromptDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ShCreditPrompt  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ShCreditPrompt>  findAll(@Param("id") String id);

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
	int saveEntity(ShCreditPrompt record);
	/**
	 * 
	 * 保存所有
	 * 
	 **/
	int saveAllEntity(List<ShCreditPrompt> list);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ShCreditPrompt record);

	List<ShCreditPrompt> queryByApplicationId(String applicationId);

}