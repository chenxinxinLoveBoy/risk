package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.BuGjApplication;

/**
 * BuGjApplicationDao数据库操作接口类bean
 * @author xiajiyun
 * @date Thu Jul 20 20:44:43 CST 2017
 **/
@Mapper
public interface BuGjApplicationDao{


	//查询借款申请表的用户信息给同盾
	public List<Map<String, Object>> getGjAppLicationList(Map<String, Object> map);
	/**
	 * 根据APP应用请求流水号查询订单
	 */
	BuGjApplication  getEntityById(String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<BuGjApplication>  findAll(@Param("id") String id);

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
	boolean saveEntity(BuGjApplication record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(BuGjApplication record);

}