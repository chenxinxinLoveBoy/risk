package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.ScScoreBig;

/**
 * ScScoreBigDao数据库操作接口类bean
 * 
 * @author xk
 * @date Fri Jun 16 20:30:13 CST 2017
 **/
@Mapper
public interface ScScoreBigDao {

	public int listAllCount(ScScoreBig scScoreBig);

	List<ScScoreBig> getAll(ScScoreBig scScoreBig);

	/**
	 * 
	 * 查询（根据评分项规则大类编号查询）
	 * 
	 **/
	ScScoreBig queryByScoreBigCode(String scoreBigCode);

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScScoreBig selectByPrimaryKey(Integer scoreBigId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(String scoreBigId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(ScScoreBig scScoreBig);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	boolean insertSelective(ScScoreBig scScoreBig);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateByPrimaryKeySelective(ScScoreBig scScoreBig);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(ScScoreBig scScoreBig);

}