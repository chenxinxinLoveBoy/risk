package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.ScScoreCredit;

/**
 * ScScoreCreditHisDao数据库操作接口类bean
 * @author xk
 * @date Sat Jun 17 21:41:45 CST 2017
 **/
@Mapper
public interface ScScoreCreditHisDao{

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(ScScoreCredit record);
	
	/**
	 * 查询所有
	 * @param scScoreCredit
	 * @return
	 */
	public List<ScScoreCredit> listAll(ScScoreCredit scScoreCredit);

	/**
	 * 统计
	 * @param scScoreCredit
	 * @return
	 */
	public int listAllCount(ScScoreCredit scScoreCredit);

}