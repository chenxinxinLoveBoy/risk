package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.CuCustomerEquipmentAndroid;

/**
 * CuCustomerEquipmentAndroidDao数据库操作接口类bean
 * @author hxf
 * @date Wed Aug 02 14:01:34 CST 2017
 **/
@Mapper
public interface CuCustomerEquipmentAndroidDao{

	/**
	 * 
	 * 查询所有（根据customerId和appName查询）
	 * 
	 **/
	CuCustomerEquipmentAndroid  findCuCustomerEquipmentAndroid(CuCustomerEquipmentAndroid cuCustomerEquipmentAndroid);
	
	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CuCustomerEquipmentAndroid  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<CuCustomerEquipmentAndroid>  findAll(@Param("id") String id);

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
	int saveEntity(CuCustomerEquipmentAndroid cuCustomerEquipmentAndroid);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(CuCustomerEquipmentAndroid cuCustomerEquipmentAndroid);

}