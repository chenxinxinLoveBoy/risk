package com.shangyong.shangyong.jobs;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description 根据步骤标识去调用同盾数据魔盒报告资信云报告接口,定时查询待审核步骤为8的数据，然后调用同盾数据魔盒报告资信云报告的第三方接口
 *
 * @author zengfq
 *
 * @date 2018年7月25日  
 *
 */

@Component
public class TongDunReportApplicationRemindJob implements Job {
	
	private static Logger tdReportLogger = LoggerFactory.getLogger("tdReportLogger");
	
	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;
	
	@Autowired
	private TaskServiceImpl taskServiceImpl;
	
	@Autowired
	private ApplicationServiceImpl applicationService;

	@Autowired
	private IScAlarm iScAlarm;
	
//	@Override
//    public SaturnJobReturn handleJavaJob(String s, Integer shardItem, String s1, SaturnJobExecutionContext saturnJobExecutionContext) throws InterruptedException {
//		scheduler();
//	    return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//	}
	
	public void scheduler() {
		try {
			tdReportLogger.info("[同盾数据魔盒] 报告定时任务获取待处理数据，当前任务处理心跳正常");
			/**
			 * 步骤标识0：待处理
			 * 1：同盾设备信息
			 * 2：同盾反欺诈
			 * 3：极光黑名单
			 * 4：91征信
			 * 5：91征信回调
			 * 6：易极付-黑名单回调
			 * 7：腾讯反欺诈
			 * 8: 上海资信
			 * 9: 白骑士运营商原始数据 + 同盾数据魔盒报告资信云报告
			 * 98：规则引擎模板分发
			 */
			String isStep = Constants.SH_CREDIT_STEP;
			String state = "1";
			List<Map<String, Object>> list = tdLoanInterfaceService.getAppLicationList(isStep,state);
			if (CollectionUtils.isNotEmpty(list)) {
				tdRepApplicationTaskProcess(list, isStep);
				tdReportLogger.error("[同盾数据魔盒] 报告资信云报告定时任务处理结束，条数：" + list.size() + "，当前任务处理心跳正常");
			}else{
				tdReportLogger.info("[同盾数据魔盒] 报告资信云报告定时任务获取待处理数据0条，当前定时任务处理结束");
			}
		} catch (Throwable e) {
			tdReportLogger.error("[同盾数据魔盒] 查询同盾数据魔盒报告资信云报告待处理数据返回信息错误信息："+e.getMessage(), e);
		}
	}
	/**
	 * 处理在同盾数据魔盒报告资信云报告步骤查询的待审核申请单List
	 * @param dataMap
	 */
	public void tdRepApplicationTaskProcess(List<Map<String, Object>> list, String isStep) {
		tdReportLogger.info("[同盾数据魔盒] 报告资信云报告定时任务获取待处理数据, 待处理数据条数： " + list.size() + ",处理开始");
		for (int i = 0; i < list.size(); i++) {
			tdReportLogger.error("[同盾数据魔盒] 报告资信云报告定时任务获取待处理数据，当前任务处理心跳正常");
			String applicationId = "";
			try {
				long startTime = System.currentTimeMillis();
				applicationId = (String) list.get(i).get("applicationNumber");
				tdReportLogger.info("[同盾数据魔盒]  定时任务单笔报告处理开始，申请单编号：" + applicationId);
				
				Map<String, Object> dataAllMap = new HashMap<String, Object>();
				//将借款申请信息和客户信息的结果集封装一个MAP中
				dataAllMap= tdLoanInterfaceService.getCustApplicationList(list.get(i));
				tdReportLogger.info("[同盾数据魔盒] 单笔参数集合：" + dataAllMap);
				
				taskServiceImpl.tdRepApplicationTask(dataAllMap, isStep);
				long endTime = System.currentTimeMillis();
				tdReportLogger.info("[同盾数据魔盒] 单笔处理结束，耗时时间： " + (endTime - startTime) + "ms");
			} catch (Throwable e) {
				boolean flag = applicationService.updateErrorDescriptionTdRepot(applicationId, "同盾数据魔盒报告资信云报告："+e.getMessage());
				iScAlarm.sendDingdingMsg("[同盾数据魔盒]  报告资信云定时任务审核申请单出现了异常 applicationId:"+applicationId+">error:"+e.getMessage(), "1");
				tdReportLogger.error("[同盾数据魔盒] 单笔同盾数据魔盒报告资信云报告处理异常，申请单编号：" + applicationId + "; 步骤处理异常描述更新：" + flag + "; 错误信息：-->" + e.getMessage(),e);
			}
		}
		tdReportLogger.info("[同盾数据魔盒] 资信云报告定时任务处理数据条数： " + list.size() + ",处理结束");
	}

	
    /**
     * 这是个静态方法，在executor初始化时会调用，并生成供saturn使用的实现类对象
     */
    public static Object getObject() {
    	TongDunReportApplicationRemindJob instance = (TongDunReportApplicationRemindJob) SpringContextUtils.getBean(TongDunReportApplicationRemindJob.class);
        return instance;
    }

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		scheduler();
	}
}
