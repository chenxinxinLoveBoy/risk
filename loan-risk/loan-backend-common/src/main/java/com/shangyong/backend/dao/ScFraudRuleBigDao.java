package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScFraudRuleBig;

/**
 * ScFraudRuleBigDao数据库操作接口类bean
 * @author xxh
 * @date Fri Jul 21 13:39:50 CST 2017
 **/
@Mapper
public interface ScFraudRuleBigDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScFraudRuleBig  getEntityById(Integer fraudRuleBigId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScFraudRuleBig>  findAll(ScFraudRuleBig scFraudRuleBig);

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
	boolean saveEntity(ScFraudRuleBig record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateEntity(ScFraudRuleBig record);

	int listAllCount(ScFraudRuleBig scFraudRuleBig);

	ScFraudRuleBig queryByFraudRuleBigCode(String fraudRuleBigCode);

}