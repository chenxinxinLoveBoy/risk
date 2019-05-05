
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

//import com.vip.saturn.job.AbstractSaturnJavaJob;
//import com.vip.saturn.job.SaturnJobExecutionContext;
//import com.vip.saturn.job.SaturnJobReturn;

/**
 * @Description 根据步骤标识去调用91征信接口
 * 定时查询待审核步骤为5的数据，然后调用91征信的第三方接口
 * @author lz
 *
 * @date 2018年7月25日  
 *
 */
@Component
public class NineOneCreditApplicationRemindJob implements Job {

	private static Logger logger = LoggerFactory.getLogger("XczxDataSynchronousTask");

	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;

	@Autowired
	private TaskServiceImpl taskServiceImpl;

	@Autowired
	private ApplicationServiceImpl applicationService;

	@Autowired
	private IScAlarm iScAlarm;

//	@Override
//	public SaturnJobReturn handleJavaJob(String jobName, Integer shardItem, String shardParam,
//                                         SaturnJobExecutionContext shardingContext) throws InterruptedException {
//		scheduler();
//		return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//	}

	public void scheduler() {
		try {
			logger.info("[91征信] 定时任务获取待处理数据，当前任务处理心跳正常");
			/**
			 * 步骤标识（0：待处理、1：同盾设备信息、2：同盾反欺诈、3：白骑士资信云报告、4：极光黑名单、5：腾讯反欺诈、6：91征信查询（多头借贷）、7：91回调、8：白骑士运营商原始数据，98：规则引擎模板分发、99：潘多拉拒绝名单、100：客户授信）
			 */
			String isStep = Constants.JG_BLACK_STEP;
			List<Map<String, Object>> list = tdLoanInterfaceService.getAppLicationList(isStep);
			if (CollectionUtils.isNotEmpty(list)) {
				xczxApplicationTaskProcess(list, isStep);
				logger.info("[91征信] 定时任务处理结束，条数：" + list.size() + "，当前任务处理心跳正常");
			}else{
				logger.info("[91征信] 定时任务获取待处理数据0条，当前定时任务处理结束");
			}
		} catch (Throwable e) {
			logger.error("[91征信] 定时任务待处理数据返回信息错误信息：" + e.getMessage(), e);
		}
	}

	/**
	 * 处理在91征信步骤查询的待审核申请单List
	 * @param list
	 * @param isStep
	 */
	public void xczxApplicationTaskProcess(List<Map<String, Object>> list, String isStep) {
		logger.info("[91征信] 定时任务获取待处理数据, 待处理数据条数： " + list.size() + ",处理开始");
		for (int i = 0; i < list.size(); i++) {
			logger.info("[91征信] 定时任务获取待处理数据，当前任务处理心跳正常");
			String applicationId = "";
			try {
				long startTime = System.currentTimeMillis();
				applicationId = (String) list.get(i).get("applicationNumber");
				logger.info("[91征信] 定时任务单笔处理开始，[applicationId=" + applicationId + "]");

				Map<String, Object> dataAllMap = new HashMap<String, Object>();
				//将借款申请信息和客户信息的结果集封装一个MAP中
				dataAllMap = tdLoanInterfaceService.getCustApplicationList(list.get(i));
				logger.info("[91征信] 单笔参数集合：" + dataAllMap);

				taskServiceImpl.xczxApplicationTask(dataAllMap, isStep);
				logger.info("[91征信] 单笔处理结束，耗时时间： " + (System.currentTimeMillis() - startTime) + "ms");

			} catch (Throwable e) {
				boolean flag = applicationService.updateErrorDescription(applicationId, "91征信：" + e.getMessage());
				iScAlarm.sendDingdingMsg("[91征信] 定时任务审核申请单出现了异常 applicationId:"+applicationId+">error:"+e.getMessage(), "1");
				logger.error("[91征信] 单笔处理异常，申请单编号：" + applicationId + "; 步骤处理异常描述更新：" + flag + "; 错误信息：-->" + e.getMessage(), e);
			}
		}
		logger.info("[91征信] 定时任务处理数据条数： " + list.size() + ",处理结束");
	}

	/**
	 * 这是个静态方法，在executor初始化时会调用，并生成供saturn使用的实现类对象
	 */
	public static Object getObject() {
		NineOneCreditApplicationRemindJob instance = (NineOneCreditApplicationRemindJob) SpringContextUtils.getBean(NineOneCreditApplicationRemindJob.class);
		return instance;
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		scheduler();
	}
}

