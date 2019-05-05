package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScBanControlTpl;
import com.shangyong.backend.entity.ScBanControlTplHis;

/**
 * ScBanControlTplHisDao数据库操作接口类bean
 * @author xiajiyun
 * @date Wed Jul 12 13:50:15 CST 2017
 **/
@Mapper
public interface ScBanControlTplHisDao{ 	

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScBanControlTplHis  getEntityById(Integer id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScBanControlTplHis>  findAll(ScBanControlTplHis scBanControlTplHis);

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
	int saveEntity(ScBanControlTplHis record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScBanControlTplHis record);

	/**
	 * 添加禁止项模版数据到历史表
	 * @param scBanControlTpl
	 */
	int insert(ScBanControlTpl scBanControlTpl);

	/**
	 * 分页查询信息
	 * @param scBanControlTplHis
	 * @return
	 */
	int listAllCount(ScBanControlTplHis scBanControlTplHis);

	ScBanControlTplHis getEntityById(ScBanControlTplHis scBanControlTplHis);

}