package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.CuCustomerExpand;

/**
 * CuCustomerExpandDao数据库操作接口类bean
 * 
 * @author xuke
 * @date Thu Aug 24 16:59:30 CST 2017
 **/
@Mapper
public interface CuCustomerExpandDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CuCustomerExpand getEntityById(CuCustomerExpand cuCustomerExpand);

	/**
	 * 
	 * 查询所有（根据实体类查询）
	 * 
	 **/
	List<CuCustomerExpand> findAll(CuCustomerExpand cuCustomerExpand);

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
	boolean saveEntity(CuCustomerExpand cuCustomerExpand);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateEntity(CuCustomerExpand cuCustomerExpand);

}