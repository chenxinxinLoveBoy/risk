package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.approval.CustomerDirectories;

/**
 * CuCustomerDirectoriesDao数据库操作接口类bean
 * @author hxf
 * @date Wed Aug 02 14:01:33 CST 2017
 **/
@Mapper
public interface CustomerDirectoriesDao{

	/**
	 * 
	 * 查询所有（根据客户编号查询）
	 * 
	 **/
	List<CustomerDirectories>  findAll(@Param("customerId") String customerId, @Param("appName") String appName);

	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(@Param("list") List<CustomerDirectories> customerDirectories);

	/**
	 * 
	 * 查询客户手机应用列表记录（根据customerId和appName查询，只查询最近一条）
	 * 
	 **/
	CustomerDirectories  findlateEntity(@Param("customerId") String customerId, @Param("appName") int appName);
}