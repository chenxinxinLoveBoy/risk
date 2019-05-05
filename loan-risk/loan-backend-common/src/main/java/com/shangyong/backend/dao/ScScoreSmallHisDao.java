package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.ScScoreSmall;

/**
 * ScScoreSmallHisDao数据库操作接口类bean
 * 
 * @author xk
 * @date Fri Jun 16 20:30:13 CST 2017
 **/
@Mapper
public interface ScScoreSmallHisDao {
	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(ScScoreSmall scScoreSmall);

	public int listAllCount(ScScoreSmall scScoreSmall);

	List<ScScoreSmall> getAll(ScScoreSmall scScoreSmall);

	/**
	 * 
	 * 查询（根据主键查询）
	 * 
	 **/
	public ScScoreSmall selectByPrimaryKey(Integer smallHisId);
}