package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.CuPlatformCustomerWeb;

/**
 * CuPlatformCustomerDao数据库操作接口类bean
 * @author kenzhao
 * @date Mon Sep 25 16:48:38 CST 2017
 **/
@Mapper
public interface CuPlatformCustomerDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CuPlatformCustomerWeb  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<CuPlatformCustomerWeb>  findAll(@Param("id") String id);

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
	int saveEntity(CuPlatformCustomerWeb record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(CuPlatformCustomerWeb record);

	
	/**
	 * 通过客户编号查询平台用户编号
	 * @param cuPlatformCustomer
	 * @return
	 */
	CuPlatformCustomerWeb findObjByCustomerId(CuPlatformCustomerWeb cuPlatformCustomer);

	CuPlatformCustomerWeb querybyId(String customerId);
}