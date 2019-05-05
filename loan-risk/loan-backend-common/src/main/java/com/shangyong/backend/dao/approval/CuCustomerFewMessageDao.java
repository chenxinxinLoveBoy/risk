package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.approval.CuCustomerFewMessage;

/**
 * CuCustomerFewMessageDao(客户手机短信记录表)数据库操作接口类bean
 * @author hxf
 * @date Thu Aug 24 16:31:50 CST 2017
 **/

@Mapper
public interface CuCustomerFewMessageDao{

	/**
	 * 
	 * 添加(批量)
	 * 
	 **/
	int saveAllEntity(@Param("list") List<CuCustomerFewMessage> record);
	
	/**
	 * 
	 * 查询客户手机短信记录（根据customerId和appName查询，只查询最近一条）
	 * 
	 **/
	CuCustomerFewMessage  findlateEntity(@Param("customerId") String customerId, @Param("appName") int appName);
}