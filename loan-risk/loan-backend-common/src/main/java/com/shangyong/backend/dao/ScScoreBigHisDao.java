package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.ScScoreBig;

/**
 * ScScoreBigHisDao数据库操作接口类bean
 * 
 * @author xk
 * @date Fri Jun 16 20:30:13 CST 2017
 **/
@Mapper
public interface ScScoreBigHisDao {
	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(ScScoreBig scScoreBig);

	public int listAllCount(ScScoreBig scScoreBig);

	List<ScScoreBig> getAll(ScScoreBig scScoreBig);

	/**
	 * 
	 * 查询（根据主键查询）
	 * 
	 **/
	public ScScoreBig selectByPrimaryKey(Integer bigHisId);
}