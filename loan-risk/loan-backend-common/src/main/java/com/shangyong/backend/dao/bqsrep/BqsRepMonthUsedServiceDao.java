package com.shangyong.backend.dao.bqsrep;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.bqsrep.BqsRepMonthUsed;

/**
 * BqsRepMonthUsedServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Fri Dec 15 14:16:10 CST 2017
 **/
@Mapper
public interface BqsRepMonthUsedServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepMonthUsed  selectByPrimaryKey(@Param("id") Long id);

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
	int saveEntity(BqsRepMonthUsed record);
	
	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(List<BqsRepMonthUsed> list);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepMonthUsed record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepMonthUsed record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepMonthUsed record);
	/**
	 * 查询月使用信息
	 * @param bqsPetitionerId
	 * @return
	 */
	List<BqsRepMonthUsed> queryById(String bqsPetitionerId);

}