package com.shangyong.backend.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.BuBlacklistDevice;

/**
 * BuBlacklistDeviceMapper数据库操作接口类bean
 * @author 曾繁棋
 * @date Wed Aug 01 14:09:41 CST 2018
 **/

public interface BuBlacklistDeviceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BuBlacklistDevice  selectByPrimaryKey(@Param("blacklistId") String blacklistId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("blacklistId") String blacklistId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(BuBlacklistDevice record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BuBlacklistDevice record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BuBlacklistDevice record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BuBlacklistDevice record);

	/**
	 * 
	 * 查询重复的黑名单个数
	 * 
	 **/
	int selectRepeat(@Param("customerId") String customerId, @Param("deviceId") String deviceId, @Param("macAddress") String macAddress, @Param("ipAddress") String ipAddress);
	
	/**
	 * 
	 * 查询是否存在deviceId
	 * 
	 **/
	int selectCountDeviceId(@Param("deviceId") String deviceId);
	
	/**
	 * 
	 * 查询是否有存在trueId
	 * 
	 **/
	int selectCountTrueId(@Param("ipAddress") String ipAddress);
	
	/**
	 * 
	 * 查询是否有存在macAddress
	 * 
	 **/
	int selectCountMacAddress(@Param("macAddress") String macAddress);
	
	
}