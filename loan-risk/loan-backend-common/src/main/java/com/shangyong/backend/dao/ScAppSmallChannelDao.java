package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScAppSmallChannel;

/**
 * ScAppSmallChannelDao数据库操作接口类bean
 * @author kenzhao
 * @date Thu Sep 14 16:49:03 CST 2017
 **/
@Mapper
public interface ScAppSmallChannelDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScAppSmallChannel  getEntityById(String channelSmallId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScAppSmallChannel>  findAll(@Param("id") String id);

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
	int saveEntity(ScAppSmallChannel record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScAppSmallChannel record);

	/**
	 * 查询当前大类下的所有小类
	 * @param SmallName
	 * @return
	 */
	List<ScAppSmallChannel> findAllByBigId(ScAppSmallChannel record);
	
	/**
	 * 统计
	 * @param record
	 * @return
	 */
	int queryAllCountByBigId(ScAppSmallChannel record);
	
	/**
	 * 通过小类名称查询单个对象
	 * @param channelSmallName
	 * @return
	 */
	ScAppSmallChannel findObjBySmallName(String channelSmallName);

}