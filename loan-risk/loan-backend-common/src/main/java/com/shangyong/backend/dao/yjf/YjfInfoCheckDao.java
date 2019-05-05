package com.shangyong.backend.dao.yjf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.yjf.YjfInfoCheck;

/**
 * YjfInfoCheckDao数据库操作接口类bean
 * @author kenzhao
 * @date Thu Dec 21 16:56:41 CST 2017
 **/
@Mapper
public interface YjfInfoCheckDao{

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	YjfInfoCheck  getEntityById(String id);
	/**
	 * 
	 * 查询（根据applicationId查询）
	 * 
	 **/
	YjfInfoCheck  queryByAId(String applicationId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<YjfInfoCheck>  findAll(String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(YjfInfoCheck record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(YjfInfoCheck record);

}