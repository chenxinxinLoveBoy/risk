package com.shangyong.backend.common.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangyong.backend.entity.ScScheduleJob;
import com.shangyong.backend.utils.TaskUtils;

/**
 * 计划任务执行工厂类(无状态)
 */
public class QuartzJobFactory implements Job {

	private Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScScheduleJob scheduleJob = (ScScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokeMethod(scheduleJob);
		logger.info("++++++++++++++++++++++QuartzJobFactory++execute被调用++++++++++++++++");
	}
}