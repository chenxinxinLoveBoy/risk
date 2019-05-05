package com.shangyong.backend.dao.approval;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.CuCustomerCompany;

/**
 * CuCustomerCompanyDao数据库操作接口类bean
 * 
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
@Mapper
public interface CustomerCompanyDao {
	/**
	 * 
	 * 查询（根据用户编号查询）
	 * 
	 **/
	CuCustomerCompany getEntityById(String platformCustomerId);
}