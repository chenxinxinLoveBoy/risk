package com.shangyong.backend.service;


import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.shangyong.backend.bo.ScScheduleJobBo;
import com.shangyong.backend.entity.ScScheduleJob;
import com.shangyong.exception.CalfException;

public interface ScheduleJobService {
	
	/**
	 * 项目启动初始化job
	 */
	public void initJob(ScScheduleJob paramJob);
	
	/**
	 * 从数据库中取 根据条件匹配job
	 * @return
	 */
	public List<ScScheduleJob> listByJob4DB(ScScheduleJob job);
	
	/**
	 * 分页获取条件匹配job
	 * @return
	 */
	public Map listByPage(ScScheduleJobBo job);
	
	/**
	 * 从数据库中查询job
	 */
	public ScScheduleJob getByJobId4DB(String jobId);
	
	/**
	 * 新增job到数据库中
	 */
	public void saveJob2DB(ScScheduleJob job);

	/**
	 * 更改任务状态(同步更新数据库和调度器)
	 * @throws SchedulerException
	 */
	public void changeStatus(String jobId, String cmd) throws CalfException;

	/**
	 * 更改任务 cron表达式
	 * 
	 * @throws SchedulerException
	 */
	public void updateCronExp(String jobId, String cron) throws CalfException;
	
	/**
	 * 删除定时任务quartz中及db记录
	 * @param job
	 * @return
	 */
	public int deleteJobByJobId(ScScheduleJobBo job);

}
