package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.SysParam;

/**
 * SysParamDao数据库操作接口类bean
 * 
 * @author xk
 * @date Wed Jun 07 20:37:07 CST 2017
 **/
@Mapper
public interface BusinessParamDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SysParam selectByPrimaryKey(Integer sysParamId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(String sysParamId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(SysParam sysParam);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	boolean insertSelective(SysParam sysParam);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateByPrimaryKeySelective(SysParam sysParam);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(SysParam sysParam);

	public int listAllCount(SysParam sysParam);

	List<SysParam> getAll(SysParam sysParam);

	/**
	 * 
	 * 查询（根据参数编号查询）
	 * 
	 **/
	SysParam queryByParamValue(String paramValue);

	/**
	 * 
	 * 查询（根据参数编号查询状态为正常的参数）
	 * 
	 **/
	SysParam queryByParamValueAndStatue(String paramValue);
}