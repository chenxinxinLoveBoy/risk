package com.shangyong.backend.dao.tdf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.tdf.TdFacilityPolicySet;

/**
 * TdFacilityPolicySetServiceDao数据库操作接口类bean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
@Mapper
public interface TdFacilityPolicySetServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TdFacilityPolicySet  selectByPrimaryKey(@Param("id") Long id);

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
	int saveEntity(TdFacilityPolicySet record);
	
	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(List<TdFacilityPolicySet> list);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(TdFacilityPolicySet record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(TdFacilityPolicySet record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(TdFacilityPolicySet record);

}