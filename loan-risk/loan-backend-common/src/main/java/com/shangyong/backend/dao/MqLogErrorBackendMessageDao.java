package com.shangyong.backend.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.MqLogErrorBackendMessage;

import java.util.List;

/**
 * MqLogErrorBackendMessageDao数据库操作接口类bean
 * @author kenzhao
 * @date Tue Sep 12 11:45:04 CST 2017
 **/

public interface MqLogErrorBackendMessageDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	MqLogErrorBackendMessage  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<MqLogErrorBackendMessage> findAll(@Param("id") String id);

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
	int saveEntity(MqLogErrorBackendMessage record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(MqLogErrorBackendMessage record);

}