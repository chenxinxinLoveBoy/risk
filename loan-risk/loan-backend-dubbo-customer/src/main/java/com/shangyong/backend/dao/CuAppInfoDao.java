package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.CuAppInfo;

/**
 * CuAppInfoDao数据库操作接口类bean
 * @author hxf
 * @date Wed Aug 02 14:01:33 CST 2017
 **/
@Mapper
public interface CuAppInfoDao{

	/**
	 * 
	 * 查询所有（根据customerId和appName查询）
	 * 
	 **/
	List<CuAppInfo>  findAppInfo(CuAppInfo cuAppInfo);
	
	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CuAppInfo  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<CuAppInfo>  findAll(@Param("id") String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(@Param("id") String id);
	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(@Param("list") List<CuAppInfo> record);
	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(CuAppInfo cuAppInfo);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(CuAppInfo cuAppInfo);

}