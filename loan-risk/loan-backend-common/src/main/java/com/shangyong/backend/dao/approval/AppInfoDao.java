package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.approval.AppInfo;

/**
 * AppInfoDao(客户手机应用列表记录)数据库操作接口类bean
 * @author hxf
 * @date Wed Aug 02 14:01:33 CST 2017
 **/
@Mapper
public interface AppInfoDao{

	/**
	 * 
	 * 查询所有（根据customerId和appName查询）
	 * 
	 **/
	List<AppInfo>  findAppInfo(@Param("customerId") String customerId, @Param("appName") String appName);

	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(@Param("list") List<AppInfo> record);
	
	/**
	 * 
	 * 查询客户手机应用列表记录（根据customerId和appName查询，只查询最近一条）
	 * 
	 **/
	AppInfo  findlateEntity(@Param("customerId") String customerId, @Param("appName") int appName);
}