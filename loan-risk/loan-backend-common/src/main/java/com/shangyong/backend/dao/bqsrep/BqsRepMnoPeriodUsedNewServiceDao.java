package com.shangyong.backend.dao.bqsrep;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.bqsrep.BqsRepMnoPeriodUsedNew;

/**
 * BqsRepMnoPeriodUsedNewServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Fri Dec 15 14:16:10 CST 2017
 **/
@Mapper
public interface BqsRepMnoPeriodUsedNewServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepMnoPeriodUsedNew  selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据主键bqsPetitionerId查询）
	 * 
	 **/
	List<BqsRepMnoPeriodUsedNew>  queryListById(String bqsPetitionerId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(BqsRepMnoPeriodUsedNew record);
	
	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(List<BqsRepMnoPeriodUsedNew> list);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepMnoPeriodUsedNew record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepMnoPeriodUsedNew record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepMnoPeriodUsedNew record);

}