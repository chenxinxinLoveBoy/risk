package com.shangyong.backend.dao.tdf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.tdf.TdFacilityGeoIp;

/**
 * TdFacilityGeoIpServiceDao数据库操作接口类bean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
@Mapper
public interface TdFacilityGeoIpServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TdFacilityGeoIp  selectByPrimaryKey(@Param("id") Long id);

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
	int saveEntity(TdFacilityGeoIp record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(TdFacilityGeoIp record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(TdFacilityGeoIp record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(TdFacilityGeoIp record);
	
	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TdFacilityGeoIp  querybyId(String buApplicationId);
	
	
	/**
	 * 
	 * 查询全部数据
	 * 
	 **/
	
	List<TdFacilityGeoIp> getAll();
	
	/**
	 * 
	 * 修改信息
	 * 
	 **/
	int updateTdFacilityGeoIp(TdFacilityGeoIp tdFacilityGeoIp);
}