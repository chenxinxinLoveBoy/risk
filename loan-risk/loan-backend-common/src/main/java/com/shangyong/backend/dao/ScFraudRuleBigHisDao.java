package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScFraudRuleBigHis;

/**
 * ScFraudRuleBigHisDao数据库操作接口类bean
 * 
 * @author xxh
 * @date Fri Jul 21 13:39:50 CST 2017
 **/
@Mapper
public interface ScFraudRuleBigHisDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScFraudRuleBigHis getEntityById(@Param("fraudRuleBigHisId") Integer fraudRuleBigHisId);

	List<ScFraudRuleBigHis> findAll(ScFraudRuleBigHis scFraudRuleBigHis);

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
	boolean saveEntity(ScFraudRuleBigHis record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScFraudRuleBigHis record);

	int listAllCount(ScFraudRuleBigHis scFraudRuleBigHis);

}