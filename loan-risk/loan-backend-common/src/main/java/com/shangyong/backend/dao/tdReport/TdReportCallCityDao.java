package com.shangyong.backend.dao.tdReport;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.tdReport.TdReportCallCity;

/**
 * TdReportCallCityDao数据库操作接口类bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
@Mapper
public interface TdReportCallCityDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TdReportCallCity  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<TdReportCallCity>  findAll(@Param("id") String id);

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
	int saveEntity(TdReportCallCity record);
	
	/**
	 * 
	 * 保存所有
	 * 
	 **/
	int saveAllEntity(List<TdReportCallCity> list);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(TdReportCallCity record);

}