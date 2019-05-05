package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScAppBigChannel;

/**
 * ScAppBigChannelDao数据库操作接口类bean
 * @author kenzhao
 * @date Thu Sep 14 16:49:03 CST 2017
 **/
@Mapper
public interface ScAppBigChannelDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScAppBigChannel  getEntityById(String channelBigId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScAppBigChannel>  findAll(@Param("id") String id);

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
	int saveEntity(ScAppBigChannel record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScAppBigChannel record);

	/**
	 * 根据大类名称查询
	 * @param bigName
	 * @return
	 */
	List<ScAppBigChannel> findAllByBigName(ScAppBigChannel record);
	
	/**
	 * 统计
	 * @param record
	 * @return
	 */
	int queryAllCountByBigName(ScAppBigChannel record);
}