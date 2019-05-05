package com.shangyong.backend.dao.approval;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.approval.CuCustomerCollectMessage;

/**
 * CuCustomerCollectMessageDao(客户被收集信息汇总表)数据库操作接口类bean
 * @author hxf
 * @date Thu Aug 24 16:31:50 CST 2017
 **/
@Mapper
public interface CuCustomerCollectMessageDao{

	/**
	 * 
	 * 查询（根据客户编号和平台查询）
	 * 
	 **/ 
	CuCustomerCollectMessage  getEntityByCustomerId(@Param("customerId") String customerId, @Param("appName") int appName);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(CuCustomerCollectMessage record);
}