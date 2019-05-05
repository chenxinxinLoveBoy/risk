package com.shangyong.backend.dao.jg;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.jg.JgInfoCheck;

/**
 * JgInfoCheckServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Sat Dec 09 19:37:46 CST 2017
 **/
@Mapper
public interface JgInfoCheckServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	JgInfoCheck  selectByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据buApplicationId查询）
	 * 
	 **/
	JgInfoCheck  queryByAid(String buApplicationId);

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
	int saveEntity(JgInfoCheck record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(JgInfoCheck record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(JgInfoCheck record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(JgInfoCheck record);

}