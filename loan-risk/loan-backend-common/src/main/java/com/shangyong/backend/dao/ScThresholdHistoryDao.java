package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.entity.ScThreshold;
import com.shangyong.backend.entity.ScThresholdHistory;

/**
 * ScThresholdHistoryDao数据库操作接口类bean
 * @author xxj
 * @date Wed Jul 05 20:55:50 CST 2017
 **/
@Mapper
public interface ScThresholdHistoryDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScThresholdHistory  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScThresholdHistory>  findAll(ScThresholdHistory scThresholdHistory);

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
	int saveEntity(ScThresholdHistory scThresholdHistory);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	@Transactional
	int updateEntity(ScThresholdHistory scThresholdHistory);

	int listAllCount(ScThresholdHistory scThresholdHistory);

	ScThresholdHistory getEntityByObj(ScThresholdHistory scThresholdHistory);
	
	/**
	 *添加数据到历史表 
	 * @param scThreshold
	 * @return
	 */
	int insert(ScThreshold scThreshold);

}