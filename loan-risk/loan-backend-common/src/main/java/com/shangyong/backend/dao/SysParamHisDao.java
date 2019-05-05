package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.SysParam;

/**
 * SysParamHisDao数据库操作接口类bean
 * 
 * @author xk
 * @date Wed Jun 07 20:37:07 CST 2017
 **/
@Mapper
public interface SysParamHisDao {

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(SysParam sysParam);

	public int listAllCount(SysParam sysParam);

	List<SysParam> getAll(SysParam sysParam);

	/**
	 * 
	 * 查询（根据主键查询）
	 * 
	 **/
	public SysParam selectByPrimaryKey(Integer paramHisId);
	
	/**
	 * 添加业务历史表数据
	 * @param sysParam
	 * @return
	 */
	int saveBusinessEntity(SysParam sysParam);

}