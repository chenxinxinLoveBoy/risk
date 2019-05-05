package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScFraudScoreDetail;
import com.shangyong.backend.entity.ScoreDetailStatistics;

/**
 * ScFraudScoreDetailDao数据库操作接口类bean
 * @author xiajiyun
 * @date Wed Jul 19 11:24:24 CST 2017
 **/
@Mapper
public interface ScFraudScoreDetailDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScFraudScoreDetail  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据申请单编号,借款编号 查询）
	 * 
	 **/
	List<ScFraudScoreDetail>  findAll(@Param("applicationId") String applicationId,
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
	int saveEntity(ScFraudScoreDetail record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScFraudScoreDetail record);

	/**
	 * 通过对象信息查询
	 * @param scFraudScoreDetail
	 * @return 
	 */
	List<ScFraudScoreDetail> findAll(ScFraudScoreDetail scFraudScoreDetail);

	/**
	 * 通过对象信息查询
	 * @param scFraudScoreDetail
	 */
	ScFraudScoreDetail getEntityById(ScFraudScoreDetail scFraudScoreDetail);

	/**
	 * 查询所有条数
	 * @param scFraudScoreDetail
	 * @return
	 */
	int listAllCount(ScFraudScoreDetail scFraudScoreDetail);

	/**
	 * 根据欺诈明细对象删除
	 * @param detail
	 * @return
	 */
	int deleteByAppId(ScFraudScoreDetail detail);
	
	int deleteByAppIdAndFraudRuleId(ScFraudScoreDetail detail);

	/**
	 * 
	 * 查询所有分数 （匹配申请单编号）
	 * 
	 **/
	String findAllScore(@Param("id") String id);
	

	/**
	 * 统计欺诈分小表中规则名称及出现次数
	 */
	List<ScoreDetailStatistics> getSmallStatistics(ScoreDetailStatistics scoreDetailStatistics);

	/**
	 * 统计欺诈分小表中规则名称及出现次数的总条数
	 */
	int getSmallStatisticsCount(ScoreDetailStatistics scoreDetailStatistics);
	
	/**
	 * 统计欺诈分小表中规则名称及出现次数(饼形图)
	 */
	List<ScoreDetailStatistics> getSmallStatisticsPie(ScoreDetailStatistics scoreDetailStatistics);
	
	/**
	 *获取命中的欺诈小项
	 * @param applicationId
	 * @param fraudTplId
	 * @return
	 */
	List<ScFraudScoreDetail> queryAllScore(ScFraudScoreDetail scFraudScoreDetail);

}