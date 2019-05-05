package com.shangyong.backend.dao.tdf;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.tdf.TdFacilityDeviceIos;

/**
 * TdFacilityDeviceIosServiceDao数据库操作接口类bean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
@Mapper
public interface TdFacilityDeviceIosServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TdFacilityDeviceIos  selectByPrimaryKey(@Param("id") Long id);

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
	 int saveEntity(TdFacilityDeviceIos record);


	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(TdFacilityDeviceIos record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(TdFacilityDeviceIos record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(TdFacilityDeviceIos record);
	
	/**
	 * 
	 * 查询（根据buapplicationId查询手机品牌）
	 * 
	 **/
	String queryByBuApplicationId(String buapplicationId);

}