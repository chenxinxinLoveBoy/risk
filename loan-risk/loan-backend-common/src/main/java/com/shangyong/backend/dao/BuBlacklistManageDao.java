package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.BuBlacklistManage;

/**
 * BuBlacklistManageDao数据库操作接口类bean
 * @author kenzhao
 * @date Mon Oct 09 17:04:54 CST 2017
 **/
@Mapper
public interface BuBlacklistManageDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BuBlacklistManage  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<BuBlacklistManage>  findAll(@Param("id") String id);

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
	int saveEntity(BuBlacklistManage record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(BuBlacklistManage record);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<BuBlacklistManage> listAll(BuBlacklistManage buBlacklistManage);

	/**
	 * 
	 * 统计所有
	 * 
	 **/
	int listAllSum(BuBlacklistManage buBlacklistManage);
}