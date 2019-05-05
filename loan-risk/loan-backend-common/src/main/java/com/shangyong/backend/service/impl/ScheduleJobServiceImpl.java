package com.shangyong.backend.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.bo.ScScheduleJobBo;
import com.shangyong.backend.common.cons.QuartzCons;
import com.shangyong.backend.common.enums.CalfResultEnum;
import com.shangyong.backend.common.quartz.QuartzJobFactory;
import com.shangyong.backend.common.quartz.QuartzJobFactoryDisallowConcurrentExecution;
import com.shangyong.backend.dao.ScScheduleJobDao;
import com.shangyong.backend.entity.ScScheduleJob;
import com.shangyong.backend.service.ScheduleJobService;
import com.shangyong.backend.utils.ExceptionUtils;
import com.shangyong.exception.CalfException;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;


/**
 * 计划任务管理: 
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired  
	private Scheduler scheduler;  

	@Autowired
	private ScScheduleJobDao scheduleJobDao;

	/**
	 * 项目启动初始化job到调度器中
	 * @param paramJob
	 * @throws Exception
	 */
	public void initJob(ScScheduleJob paramJob) {
		List<ScScheduleJob> jobList = null;
		if (paramJob == null) {
			jobList = scheduleJobDao.listAll();
		} else {
			jobList = scheduleJobDao.listByJob(paramJob);
		}
		// 将获取到的任务加入调度器
		if (jobList != null && jobList.size() > 0) {
			for (ScScheduleJob job : jobList) {
				try {
					addJob(job);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 添加任务
	 * 
	 * @param job
	 * @throws SchedulerException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addJob(ScScheduleJob job) throws SchedulerException {
		if (job == null || !QuartzCons.STATUS_RUNNING.equals(job.getJobStatus())) {
			return;
		}

		logger.debug(scheduler + ".......................................................................................add");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = QuartzCons.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;

			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

			jobDetail.getJobDataMap().put("scheduleJob", job);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<ScScheduleJob> getAllJob() throws SchedulerException {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScScheduleJob> jobList = new ArrayList<ScScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScScheduleJob job = new ScScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<ScScheduleJob> getRunningJob() throws SchedulerException {
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<ScScheduleJob> jobList = new ArrayList<ScScheduleJob>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			ScScheduleJob job = new ScScheduleJob();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseJob(ScScheduleJob scheduleJob) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeJob(ScScheduleJob scheduleJob) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(ScScheduleJob scheduleJob) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);
	}

	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void runAJobNow(ScScheduleJob scheduleJob) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(ScScheduleJob scheduleJob) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}
	
	/**
	 * 删除定时任务quartz中及db记录
	 * @param job
	 * @return
	 */
	public int deleteJobByJobId(ScScheduleJobBo job) {
		try {
			ScScheduleJob sjob = this.getByJobId4DB(job.getJobId());
			if("1".equals(sjob.getJobStatus())) {
				logger.info("-->>定时任务正在执行 不能删除，jobId:" + sjob.getJobId());
				throw new RuntimeException("定时任务正在执行 不能删除，jobId:" + sjob.getJobId());
			} 
			deleteJob(sjob);
			this.deleteByJobId(job.getJobId());
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
		
		return 1;
	}
	
	
	// ################################################
	// ###########schedule job 数据库操作
	// ################################################
	

	/**
	 * 从数据库中取 根据条件匹配job
	 * @return
	 */
	public List<ScScheduleJob> listByJob4DB(ScScheduleJob job) {
		return scheduleJobDao.listByJob(job);
	}
	
	/**
	 * 分页获取条件匹配job
	 * @return
	 */
	public Map<String, Object> listByPage(ScScheduleJobBo job) {
		Map<String, Object> res = new HashMap<String,Object>();
		List<ScScheduleJobBo> sJobBoList = new ArrayList<ScScheduleJobBo>();
		Integer count = scheduleJobDao.listByPageCount(job);
		if (count > 0) {
			List<ScScheduleJob> jobList = scheduleJobDao.listByPage(job);
			for (ScScheduleJob sjob : jobList) {
				ScScheduleJobBo scScheduleJobBo = new ScScheduleJobBo();
				BeanUtils.copyProperties(sjob, scScheduleJobBo);
				sJobBoList.add(scScheduleJobBo);
			}
		}
		res.put("list", sJobBoList);
		res.put("count", count);
		return res;
	}

	/**
	 * 从数据库中取出所有的job
	 * @return
	 */
	public List<ScScheduleJob> listAll4DB() {
		return scheduleJobDao.listAll();
	}
	
	/**
	 * 删除db定时器记录
	 * @return
	 */
	public int deleteByJobId(String jobId) {
		return scheduleJobDao.deleteByJobId(jobId);
	}
	
	/**
	 * 从数据库中查询job
	 */
	public ScScheduleJob getByJobId4DB(String jobId) {
		return scheduleJobDao.getByJobId(jobId);
	}
	
	/**
	 * 新增job到数据库中
	 */
	public void saveJob2DB(ScScheduleJob job) {
		job.setJobId(UUIDUtils.getUUID());
		job.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
		scheduleJobDao.saveJob(job);
	}

	/**
	 * 更改任务状态(同步更新数据库和调度器)
	 * @throws SchedulerException
	 */
	public void changeStatus(String jobId, String cmd) throws CalfException {
		ScScheduleJob job = getByJobId4DB(jobId);
		if (job == null) {
			return;
		}
		try {
			if (QuartzCons.STATUS_NOT_RUNNING_CMD.equals(cmd)) {
				deleteJob(job);
				job.setJobStatus(QuartzCons.STATUS_NOT_RUNNING);
			} else if (QuartzCons.STATUS_RUNNING_CMD.equals(cmd)) {
				job.setJobStatus(QuartzCons.STATUS_RUNNING);
				addJob(job);
			}
		} catch(SchedulerException e) {
			e.printStackTrace();
			ExceptionUtils.initCalfException(CalfResultEnum.FAIL_SCHEDULE_ERROR.getCode(), e.getMessage());
		}
		job.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
		scheduleJobDao.updateByJob(job);
	}

	/**
	 * 更改任务 cron表达式
	 * 
	 * @throws SchedulerException
	 */
	public void updateCronExp(String jobId, String cron) throws CalfException {
		ScScheduleJob job = getByJobId4DB(jobId);
		if (job == null) {
			return;
		}
		job.setCronExpression(cron);
		try {
			if (QuartzCons.STATUS_RUNNING.equals(job.getJobStatus())) {
				updateJobCron(job);
			}
		} catch(SchedulerException e) {
			e.printStackTrace();
			ExceptionUtils.initCalfException(CalfResultEnum.FAIL_SCHEDULE_ERROR.getCode(), e.getMessage());
		}
		scheduleJobDao.updateByJob(job);

	}

	public static void main(String[] args) {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("xxxxx");
	}
}
