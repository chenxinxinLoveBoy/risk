package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.ScRiskWhiteList;

/**
 * ScRiskWhiteListDao数据库操作接口类bean
 * @author xk
 * @date Mon Jun 19 14:46:07 CST 2017
 **/
@Mapper
public interface ScRiskWhiteListDao{ 
 

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(ScRiskWhiteList scRiskWhiteList);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int saveEntity(ScRiskWhiteList scRiskWhiteList);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateByPrimaryKeySelective(ScRiskWhiteList scRiskWhiteList);
 
	/**
	 * 
	 * 统计分页
	 * 
	 **/
	List<ScRiskWhiteList> findAll(ScRiskWhiteList scRiskWhiteList);
	/**
	 * 
	 * 根据ID查询
	 * 
	 **/
	ScRiskWhiteList findByid(ScRiskWhiteList scRiskWhiteList);

	/**
	 * 
	 * 统计
	 * 
	 **/
	int findAllCount(ScRiskWhiteList scRiskWhiteList);
	
	/**
	 * 
	 * 根据对象查询是否在白名单 - 返回数量
	 * 
	 **/
	int queryWhiteCount(ScRiskWhiteList scRiskWhiteList);
	
	/**
	 * 
	 * 根据身份证查询
	 * 
	 **/
	ScRiskWhiteList findByCode(String certCode);

	/**
	 * 
	 * 根据身份证和AppName查询
	 * 
	 **/
	ScRiskWhiteList findByCodeAppName(ScRiskWhiteList white);
	
	/**
	 * 根据whiteListId 删除白名单用户
	 * @param whiteListId
	 * @return
	 */
	boolean deleteById(Integer whiteListId);

}