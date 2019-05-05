package com.shangyong.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shangyong.backend.entity.BuPresentApplication;
import com.shangyong.backend.entity.PresentRiskCheckCount;

import java.util.List;

/**
 * BuPresentApplicationDao数据库操作接口类bean
 * @author kenzhao
 * @date Fri Sep 15 22:55:03 CST 2017
 **/
@Mapper
public interface BuPresentApplicationDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BuPresentApplication  getEntityById(@Param("presentApplicationId") String presentApplicationId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	Page<BuPresentApplication> findAll(BuPresentApplication record);

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
	int saveEntity(BuPresentApplication record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(BuPresentApplication record);

	/**
	 * 增加失败次数
	 * @param record
	 * @return
	 */
	int updateEntityFailureTimes(BuPresentApplication record);

	/**
	 * 清空失败次数
	 * @param record
	 * @return
	 */
	int updateFailureTimesZero(BuPresentApplication record);

	/**
	 * 统计报表
	 * @param presentRiskCheckCount
	 * @return
	 */
	List<PresentRiskCheckCount> queryPresentResult(PresentRiskCheckCount presentRiskCheckCount);

	/**
	 * 统计报表个数
	 * @param presentRiskCheckCount
	 * @return
	 */
	int queryPresentResultCount(PresentRiskCheckCount presentRiskCheckCount);
	/**
	 * 统计报表用于柱形图
	 * @param presentRiskCheckCount
	 * @return
	 */
	List<PresentRiskCheckCount> getPresentHistogram(PresentRiskCheckCount presentRiskCheckCount);
}