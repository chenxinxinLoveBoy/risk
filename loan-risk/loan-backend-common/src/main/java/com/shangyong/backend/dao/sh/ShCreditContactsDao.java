package com.shangyong.backend.dao.sh;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.sh.ShCreditContacts;

/**
 * ShCreditContactsDao数据库操作接口类bean
 * @author kenzhao
 * @date Fri Mar 09 15:39:19 CST 2018
 **/
@Mapper
public interface ShCreditContactsDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ShCreditContacts  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ShCreditContacts>  findAll(@Param("id") String id);

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
	int saveEntity(ShCreditContacts record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ShCreditContacts record);
	
	/**
	 * 
	 * 保存所有
	 * 
	 **/
	int saveAllEntity(List<ShCreditContacts> list);

	List<ShCreditContacts> queryByApplicationId(String applicationId);

}