package com.shangyong.shangyong.jobs;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.TdLoanInterfaceService;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.service.task.impl.TaskServiceImpl;
import com.shangyong.shangyong.utils.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 根据步骤标识去调用极光黑名单接口
 * 
 * 定时查询待审核步骤为8的数据，然后调用极光黑名单的第三方接口
 * @author ChenGeng
 *
 */
@Component
public class JiGuangApplicationRemindJob implements Job {

	private static Logger jgLogger = LoggerFactory.getLogger("jg");

	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;

	@Autowired
	private TaskServiceImpl taskServiceImpl;

	@Autowired
	private ApplicationServiceImpl applicationService;

	@Autowired
	private IScAlarm iScAlarm;

//	@Override
//    public SaturnJobReturn handleJavaJob(String s, Integer shardItem, String s1, SaturnJobExecutionContext saturnJobExecutionContext) throws InterruptedException{
//		scheduler();
//	    return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//	}

	public void scheduler() {
		try {
			jgLogger.info("[极光] 定时任务获取待处理数据，当前任务处理心跳正常");

			String isStep = Constants.TD_ANTI_FRAUD_STEP;
			List<Map<String, Object>> list = tdLoanInterfaceService.getAppLicationList(isStep);
			if (CollectionUtils.isNotEmpty(list)) {
				JgApplicationTaskProcess(list, isStep);
				jgLogger.info("[极光] 单定时任务处理结束，条数：" + list.size() + "，当前任务处理心跳正常");
			}else{
				jgLogger.info("[极光] 定时任务获取待处理数据0条，当前定时任务处理结束");
			}
		} catch (Throwable e) {
			jgLogger.error("[极光] 查询待处理数据返回错误信息：-->"+e.getMessage(),e);
		}
	}

	/**
	 * 处理在极光黑名单步骤查询的待审核申请单List
	 * @param list
	 * @param isStep
	 */
	public void JgApplicationTaskProcess(List<Map<String, Object>> list, String isStep) {
		jgLogger.info("[极光] 定时任务获取待处理数据, 待处理数据条数： " + list.size() + ",处理开始");
		for (int i = 0; i < list.size(); i++) {
			String applicationId = "";
			try {
				long startTime = System.currentTimeMillis();
				applicationId = (String) list.get(i).get("applicationNumber");
				jgLogger.info("[极光] [applicationId=" + applicationId + "] 开始处理");

				Map<String, Object> dataAllMap = new HashMap<String, Object>();
				//将借款申请信息和客户信息的结果集封装一个MAP中
				dataAllMap= tdLoanInterfaceService.getCustApplicationList(list.get(i));
				jgLogger.info("[极光] [applicationId=" + applicationId + "] 单笔极光黑名单参数集合：" + dataAllMap);

				taskServiceImpl.jgApplicationTask(dataAllMap, isStep);
				jgLogger.info("[极光] 单笔极光黑名单处理结束，耗时时间：" + (System.currentTimeMillis() - startTime) + "ms");
			} catch (Throwable e) {
				boolean flag = applicationService.updateErrorDescription(applicationId, "[极光] 处理异常" + e.getMessage());
				iScAlarm.sendDingdingMsg("[极光] [applicationId="+applicationId+">] 处理异常,error=" + e.getMessage(), "1");
				jgLogger.error("[极光] [applicationId=" + applicationId + "] 处理异常,步骤处理异常描述更新：" + flag + "; 错误信息：-->" + e.getMessage(), e);
			}
		}
		jgLogger.info("[极光] 极光黑名单定时任务处理数据条数： " + list.size() + ",处理结束");
	}

	/**
	 * 这是个静态方法，在executor初始化时会调用，并生成供saturn使用的实现类对象
	 */
	public static Object getObject() {
		JiGuangApplicationRemindJob instance = (JiGuangApplicationRemindJob) SpringContextUtils.getBean(JiGuangApplicationRemindJob.class);
		return instance;
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		scheduler();
	}
}
