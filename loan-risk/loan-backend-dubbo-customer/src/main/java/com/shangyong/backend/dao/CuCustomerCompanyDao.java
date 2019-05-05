package com.shangyong.backend.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.CuCustomerCompany;

/**
 * CuCustomerCompanyDao数据库操作接口类bean
 * 
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
@Mapper
public interface CuCustomerCompanyDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CuCustomerCompany getEntityById(String platformCustomerId);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	boolean saveEntity(CuCustomerCompany cuCustomerCompany);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateEntity(CuCustomerCompany cuCustomerCompany);

}