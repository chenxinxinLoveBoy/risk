package com.shangyong.backend.dao.td;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.td.TdSuspectTeam;

/**
 * TdSuspectTeamServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
@Mapper
public interface TdSuspectTeamServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TdSuspectTeam  selectByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据主键tdRiskItemsId查询）
	 * 
	 **/
	List<TdSuspectTeam>  queryById(String tdRiskItemsId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(TdSuspectTeam record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(TdSuspectTeam record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(TdSuspectTeam record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(TdSuspectTeam record);

}