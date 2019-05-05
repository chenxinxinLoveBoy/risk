package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.CuPlatformCustomer;

/**
 * PlatformCustomerDao数据库操作接口类bean
 * 
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
@Mapper
public interface CuPlatformCustomerDao {

	/**
	 * 
	 * 根据多个条件获取对象
	 * 
	 **/

	CuPlatformCustomer getEntityById(CuPlatformCustomer cuPlatformCustomer);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	boolean saveEntity(CuPlatformCustomer cuPlatformCustomer);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateEntity(CuPlatformCustomer cuPlatformCustomer);

	int listAllCount(CuPlatformCustomer cuPlatformCustomer);

	List<CuPlatformCustomer> findAll(CuPlatformCustomer cuPlatformCustomert);

	List<CuPlatformCustomer> findAllCustomer();// 查询所有客户信息

}