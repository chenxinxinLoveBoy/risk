package com.shangyong.shangyong.jobs;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.shangyong.utils.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description 获取最后一步标识，审批数据为最后一步修改审批通过
 * @author ChenGeng
 *
 */
@Component
public class UpdateSpStatusRemindJob implements Job {

	private static Logger logger = (Logger) LoggerFactory.getLogger(UpdateSpStatusRemindJob.class);
	private static Logger approvalStatusLogger = (Logger) LoggerFactory.getLogger("approvalStatusLogger");
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类

//	@Override
//	public SaturnJobReturn handleJavaJob(String s, Integer shardItem, String s1, SaturnJobExecutionContext saturnJobExecutionContext) throws InterruptedException {
//		scheduler();
//		return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//	}

	public void scheduler() {
			try {
				approvalStatusLogger.error("跟新审批额度定时任务获取待处理数据，当前任务处理心跳正常");
				// 获取参数值
				SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.UPDATE_STEP_STATUS);
				String stepStatus = sysParam.getParamValueOne();
				approvalStatusLogger.info("获取参数值中最新步骤号:" + stepStatus);
				List<Map<String, Object>> dataMap = null;
				if (CollectionUtils.isNotEmpty(dataMap)) {
					updateSpStatusProcess(dataMap, stepStatus);
				}else{
					approvalStatusLogger.info("获取需更新审批通过状态数据0条，当前定时任务处理结束");
				}
			} catch (Throwable e) {
				logger.error(e.getMessage() + "更新审批通过状态定时任务出错", e);
				approvalStatusLogger.error(e.getMessage()+"更新审批通过状态定时任务出错", e);
			}
		}
	
	/**
	 * 处理最后一步标识，审批数据为最后一步修改审批通过申请单List
	 * @param dataMap
	 * @throws Throwable 
	 */
	@Transactional
	public void updateSpStatusProcess(List<Map<String, Object>> dataMap, String stepStatus) {
		approvalStatusLogger.info("定时任务获取待处理更新审批通过状态, 待处理数据条数：  " + dataMap.size());
		for (int i = 0; i < dataMap.size(); i++) {
			approvalStatusLogger.error("跟新审批额度定时任务获取待处理数据，当前任务处理心跳正常");
			String applicationNumber = "";
			try {
				long startTime = System.currentTimeMillis();
				applicationNumber = (String) dataMap.get(i).get("applicationNumber");
				approvalStatusLogger.info("定时任务跟新审批额度处理开始，申请单编号：" + applicationNumber);
//				taskServiceImpl.updateSpStatus(dataMap.get(i),stepStatus);
				long endTime = System.currentTimeMillis();
				approvalStatusLogger.info("跟新审批额度处理结束，耗时时间： " + (endTime - startTime) + "ms");
			} catch (Throwable e) {
				// 获取DD通知URL
//				scAlarmImpl.contains(AlarmCodeEnum.APPROVAL_STATUS_CHANGE,"单笔芝麻信用评分处理异常，申请单编号：" + applicationNumber + "; 错误信息：-->" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.APPROVAL_STATUS_CHANGE);
//				DingdingUtil.setMessage(Constants.TASK_ONE_DD_SP_URL_CODE, "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";单笔芝麻信用评分处理异常，申请单编号：" + applicationNumber + "; 错误信息：-->" + e.getMessage());
				logger.error("单笔更新审批通过状态定时任务出错：" + e.getMessage(), e);
				approvalStatusLogger.error("单笔更新审批通过状态定时任务出错" + e.getMessage(), e);
			}
		}
	}

	/**
	 * 这是个静态方法，在executor初始化时会调用，并生成供saturn使用的实现类对象
	 */
	public static Object getObject() {
		UpdateSpStatusRemindJob instance = (UpdateSpStatusRemindJob) SpringContextUtils.getBean(UpdateSpStatusRemindJob.class);
		return instance;
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		scheduler();
	}
}
