package com.shangyong.backend.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.CuCustomerOther;

/**
 * CuCustomerOtherDao数据库操作接口类bean
 * 
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
@Mapper
public interface CuCustomerOtherDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CuCustomerOther getEntityById(String otherInfoId);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	boolean saveEntity(CuCustomerOther cuCustomerOther);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateEntity(CuCustomerOther cuCustomerOther);

	/**
	 * 根据平台用户编号删除平台客户其他账号信息
	 * 
	 * @param platformCustomerId
	 * @return
	 */
	int delete(String platformCustomerId);

}