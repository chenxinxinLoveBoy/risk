package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScFraudScore;

/**
 * ScFraudScoreDao数据库操作接口类bean
 * @author kenzhao
 * @date Sun Apr 08 17:42:44 CST 2018
 **/
@Mapper
public interface ScFraudScoreDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	List<ScFraudScore> getEntityById(ScFraudScore scFraudScore);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScFraudScore>  findAll(@Param("id") String id);

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
	int saveEntity(ScFraudScore record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScFraudScore record);

}