package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScScoreCredit;


/**
 * ScScoreCreditDao数据库操作接口类bean
 * @author xk
 * @date Fri Jun 16 21:20:14 CST 2017
 **/
@Mapper
public interface ScScoreCreditDao{


	/**
	 * 
	 * 添加
	 * 
	 **/
	boolean insert(ScScoreCredit record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	boolean updateByPrimaryKey(ScScoreCredit record);

	/**
	 * 查询所有
	 * @param scScoreCredit
	 * @return
	 */
	public List<ScScoreCredit> findAll(ScScoreCredit scScoreCredit);

	/**
	 * 根据ID查询
	 * @param scoreCreditId
	 * @return
	 */
	public ScScoreCredit getEntityById(String scoreCreditId);

	/**
	 * 统计
	 * @param scScoreCredit
	 * @return
	 */
	public int listAllCount(ScScoreCredit scScoreCredit);

	/**
	 * 根据分数查询
	 * @param scoreCredit
	 * @return
	 */
	public List<ScScoreCredit> queryByScore(ScScoreCredit scoreCredit);
	
	/**
	 * 根据分数查询
	 * @param score
	 * @return
	 */
	public ScScoreCredit getByScore(@Param("score") String score, @Param("scoreTplId") String scoreTplId);

}