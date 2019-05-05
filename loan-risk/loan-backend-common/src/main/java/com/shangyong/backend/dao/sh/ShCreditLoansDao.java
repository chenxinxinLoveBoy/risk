package com.shangyong.backend.dao.sh;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.sh.ShCreditLoans;

/**
 * ShCreditLoansDao数据库操作接口类bean
 * @author kenzhao
 * @date Fri Mar 09 15:39:19 CST 2018
 **/
@Mapper
public interface ShCreditLoansDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ShCreditLoans  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ShCreditLoans>  findAll(@Param("id") String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(@Param("id") String id);
	
	/**
	 * 
	 * 保存所有
	 * 
	 **/
	int saveAllEntity(List<ShCreditLoans> list);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(ShCreditLoans record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ShCreditLoans record);

	List<ShCreditLoans> queryByApplicationId(String applicationId);

}