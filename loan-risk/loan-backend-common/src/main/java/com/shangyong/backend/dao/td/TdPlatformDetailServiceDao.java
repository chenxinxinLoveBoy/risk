package com.shangyong.backend.dao.td;

import com.shangyong.backend.entity.td.TdPlatformDetail;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * TdPlatformDetailServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Fri Dec 15 21:29:05 CST 2017
 **/
@Mapper
public interface TdPlatformDetailServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TdPlatformDetail  selectByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据主键tdPlatfromId查询）
	 * 
	 **/
	List<TdPlatformDetail>  queryByFormId(String tdPlatfromId);
	
	/**
	 * 
	 * 查询（根据主键tdPlatformDimensionId查询）
	 * 
	 **/
	List<TdPlatformDetail>  queryByFormDimensionId(String tdPlatformDimensionId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(TdPlatformDetail record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(TdPlatformDetail record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(TdPlatformDetail record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(TdPlatformDetail record);
	
	/**
	 * 查询p2p网贷数量(根据applicationId查询)
	 * @param applicationId
	 * @return
	 */
	String queryByAid(String applicationId);

}