package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScScoreDetail;
import com.shangyong.backend.entity.ScoreDetailStatistics;

/**
 * 借款申请客户评分明细 操作接口类bean
 * 
 * @author xxj
 * @date Sat Jun 17 17:43:29 CST 2017
 **/
@Mapper
public interface ScScoreDetailDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScScoreDetail getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScScoreDetail getEntity(ScScoreDetail detail);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScScoreDetail> findAll(@Param("applicationId") String applicationId,
                                @Param("applicationBuId") String applicationBuId);

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
	int saveEntity(ScScoreDetail record);

	/**
	 * 
	 * 修改 （根据申请单号和大类编号更新）
	 * 
	 **/
	int updateByAppIdAndBigCode(ScScoreDetail record);

	/**
	 * 
	 * 删除（根据申请单号和大类编号更新）
	 * 
	 **/
	int deleteByAppIdAndBigCode(ScScoreDetail record);

	/**
	 * 
	 * 查询所有分数 （匹配申请单编号）
	 * 
	 **/
	String findAllScore(@Param("id") String id);

	/**
	 * 统计评分大表中规则名称及出现次数
	 */
	List<ScoreDetailStatistics> getBigStatistics(ScoreDetailStatistics scoreDetailStatistics);

	/**
	 * 统计评分大表中规则名称及出现次数的总条数
	 */
	int getBigStatisticsCount(ScoreDetailStatistics scoreDetailStatistics);

	/**
	 * 统计评分小表中规则名称及出现次数
	 */
	List<ScoreDetailStatistics> getSmallStatistics(ScoreDetailStatistics scoreDetailStatistics);

	/**
	 * 统计评分小表中规则名称及出现次数的总条数
	 */
	int getSmallStatisticsCount(ScoreDetailStatistics scoreDetailStatistics);

	/**
	 * 统计评分大表中规则名称及出现次数(柱形图)
	 */
	List<ScoreDetailStatistics> getBigStatisticsHistogram(ScoreDetailStatistics scoreDetailStatistics);

	/**
	 * 统计评分小表中规则名称及出现次数(饼形图)
	 */
	List<ScoreDetailStatistics> getSmallStatisticsPie(ScoreDetailStatistics scoreDetailStatistics);
}