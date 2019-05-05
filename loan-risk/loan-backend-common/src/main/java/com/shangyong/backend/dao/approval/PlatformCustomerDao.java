package com.shangyong.backend.dao.approval;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.CuPlatformCustomer;

/**
 * PlatformCustomerDao数据库操作接口类bean
 * 
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
@Mapper
public interface PlatformCustomerDao {

	/**
	 * 
	 * 查询（根据平台用户编号）
	 * 
	 **/

	CuPlatformCustomer getEntityById(@Param("platformId") String platformId);
}