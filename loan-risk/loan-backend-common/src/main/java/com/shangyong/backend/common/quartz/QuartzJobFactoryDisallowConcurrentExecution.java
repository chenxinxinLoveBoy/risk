package com.shangyong.backend.common.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.shangyong.backend.entity.ScScheduleJob;
import com.shangyong.backend.utils.TaskUtils;

/**
 * 
 * 计划任务执行工厂类(有状态)
 * 
 * @Description: 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScScheduleJob scheduleJob = (ScScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokeMethod(scheduleJob);
	}
}