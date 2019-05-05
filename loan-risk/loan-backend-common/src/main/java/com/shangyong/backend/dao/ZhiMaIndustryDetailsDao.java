package com.shangyong.backend.dao;

import java.util.List;

import com.shangyong.backend.entity.ZhiMaIndustryDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ZhiMaIndustryDetailsDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	List<ZhiMaIndustryDetails>  getEntityById(@Param("applicationId") String applicationId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ZhiMaIndustryDetails> findAll(ZhiMaIndustryDetails zhiMaIndustryDetailsList);
	
	/**
	 * 
	 * 查询所有（根据应用客户编号查询）
	 * 
	 **/
	List<ZhiMaIndustryDetails> getCustomerId(@Param("customerId") String customerId);
	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(@Param("applicationId") String applicationId);

	/**
	 *
	 * @param zhiMaIndustryDetailsList
	 * @return
	 */
	int saveEntityAll(@Param("params") List<ZhiMaIndustryDetails> zhiMaIndustryDetailsList);

}
