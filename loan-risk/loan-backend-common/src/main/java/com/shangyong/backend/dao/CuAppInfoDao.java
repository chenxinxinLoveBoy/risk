package com.shangyong.backend.dao;
 

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.CuAppInfo;

/**
 * CuAppInfoDao数据库操作接口类bean
 * @author xiajiyun
 * @date Sat Aug 12 22:40:56 CST 2017
 **/
@Mapper
public interface CuAppInfoDao{ 
	/**
	 * 
	 * 查询 客户手机应用列表记录
	 * 
	 **/
	List<CuAppInfo>  getEntityById(CuAppInfo cuAppInfo);

	/**
	 * 统计
	 * @param cuAppInfo
	 * @return
	 */
	int listAllCount(CuAppInfo cuAppInfo);

	 
}