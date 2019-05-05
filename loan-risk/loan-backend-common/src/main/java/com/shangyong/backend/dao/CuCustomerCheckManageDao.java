package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shangyong.backend.entity.CuCustomerCheckManage;

/**
 * CuCustomerCheckManageDao数据库操作接口类bean
 * @author kenzhao
 * @date Sat Sep 23 18:07:05 CST 2017
 **/
@Mapper
public interface CuCustomerCheckManageDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CuCustomerCheckManage  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<CuCustomerCheckManage>  findAll(@Param("id") String id);

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
	int saveEntity(CuCustomerCheckManage record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(CuCustomerCheckManage record);
	
	/**
	 * 根据条件查询
	 * @return
	 */
	Page<CuCustomerCheckManage> findAllByObj(CuCustomerCheckManage record);
	
	/**
	 * 统计
	 * @param record
	 * @return
	 */
	int listCountAllByObj(CuCustomerCheckManage record);

}