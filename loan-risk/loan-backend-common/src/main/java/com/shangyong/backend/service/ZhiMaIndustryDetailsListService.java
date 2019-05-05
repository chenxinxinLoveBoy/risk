package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.IndustryDetails;
import com.shangyong.backend.entity.ZhiMaIndustryDetails;

public interface ZhiMaIndustryDetailsListService {
	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	List<ZhiMaIndustryDetails>  getEntityById(String applicationId);
	
	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ZhiMaIndustryDetails>  findAll(ZhiMaIndustryDetails zhiMaIndustryDetailsList);

	
	/**
	 * 
	 * 查询所有（根据应用客户编号查询）
	 * 
	 **/
	List<ZhiMaIndustryDetails>  getCustomerId(String customerId);
	
	/**
	 * 
	 * 添加
	 * 
	 **/
	void saveEntity(String applicationId, String customerId, List<IndustryDetails> zhiMaIndustryDetailsList);

}
