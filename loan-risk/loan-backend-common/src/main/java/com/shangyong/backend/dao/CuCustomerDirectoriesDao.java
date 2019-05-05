package com.shangyong.backend.dao;
 
 

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.CuCustomerDirectories;

/**
 * CuCustomerDirectoriesDao数据库操作接口类bean
 * @author xiajiyun
 * @date Sat Aug 12 22:40:57 CST 2017
 **/

@Mapper
public interface CuCustomerDirectoriesDao{ 
	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	List<CuCustomerDirectories>  getEntityById(CuCustomerDirectories cuCustomerDirectories);

	/**
	 * 统计
	 * @param info
	 * @return
	 */
	int listAllCount(CuCustomerDirectories cuCustomerDirectories);

	 
}