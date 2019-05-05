package com.shangyong.backend.dao;

import com.shangyong.backend.entity.IdEntityCard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * IdEntityCardDao数据库操作接口类bean
 * @author kenzhao
 * @date Sun Feb 11 16:33:10 CST 2018
 **/
@Mapper
public interface IdEntityCardDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	String  getEntityById(String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<IdEntityCard>  findAll(@Param("id") String id);

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
	int saveEntity(IdEntityCard record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(IdEntityCard record);

}