package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.CuCustomerIceNew;

/**
 * CuCustomerIceNewDao数据库操作接口类bean
 * @author kenzhao
 * @date Sat Jan 20 19:21:24 CST 2018
 **/
@Mapper
public interface CuCustomerIceNewDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CuCustomerIceNew  getEntityById(String customerId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<CuCustomerIceNew>  findAllByCustomerId(String customerId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByCustomerId(String customerId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(CuCustomerIceNew record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(CuCustomerIceNew record);

}