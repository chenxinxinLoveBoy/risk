package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScFraudRuleHis;

/**
 * ScFraudRuleHisDao数据库操作接口类bean
 * @author xiajiyun
 * @date Wed Jul 19 11:24:24 CST 2017
 **/
@Mapper
public interface ScFraudRuleHisDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScFraudRuleHis  getEntityById(Integer fraudRuleHisId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScFraudRuleHis>  findAll(ScFraudRuleHis scFraudRuleHis);

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
	boolean saveEntity(ScFraudRuleHis record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScFraudRuleHis record);

	int listAllCount(ScFraudRuleHis scFraudRuleHis);

}