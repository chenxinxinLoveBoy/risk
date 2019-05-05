package com.shangyong.backend.dao.mq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.mq.MqQueueInfo;

/**
 * MqQueueInfoDao数据库操作接口类bean
 * @author kenzhao
 * @date Thu Apr 19 17:01:35 CST 2018
 **/
@Mapper
public interface MqQueueInfoDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	MqQueueInfo  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<MqQueueInfo>  findAll(@Param("id") String id);

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
	int saveEntity(MqQueueInfo record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(MqQueueInfo record);
	
	
	
	int listAllCount(MqQueueInfo mqQueueInfo);
	
	List<MqQueueInfo> findAllMq(MqQueueInfo mqQueueInfo);


}