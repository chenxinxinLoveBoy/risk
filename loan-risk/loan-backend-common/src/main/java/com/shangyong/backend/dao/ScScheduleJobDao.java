package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.bo.ScScheduleJobBo;
import com.shangyong.backend.entity.ScScheduleJob;

@Mapper
public interface ScScheduleJobDao {
	
	/**
	 * 插入任务信息
	 * @param job
	 * @return
	 */
	int saveJob(ScScheduleJob job);
	
	/**
	 * 更新任务信息
	 * @param job
	 * @return
	 */
	int updateByJob(ScScheduleJob job);
	
	/**
	 * 根据任务编号删除任务
	 * @param jobId
	 * @return
	 */
	int deleteByJobId(String jobId);

	/**
	 * 根据任务编号查询任务详情
	 * @param jobId
	 * @return
	 */
	ScScheduleJob getByJobId(String jobId);
	
	/**
	 * 根据条件查询任务信息列表
	 * @return
	 */
	List<ScScheduleJob> listByJob(ScScheduleJob job);
	
	/**
	 * 根据条件查询任务信息列表
	 * @return
	 */
	List<ScScheduleJob> listByPage(ScScheduleJobBo job);
	
	/**
	 * 分页查询列表count数
	 * @return
	 */
	Integer listByPageCount(ScScheduleJobBo job);

	/**
	 * 获取所有的任务信息列表
	 * @return
	 */
	List<ScScheduleJob> listAll();
}