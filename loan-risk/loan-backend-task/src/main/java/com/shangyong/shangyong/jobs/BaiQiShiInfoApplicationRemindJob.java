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
 * @author caisheng
 * @Description
 * @date 2018年7月25日
 */
@Component
public class BaiQiShiInfoApplicationRemindJob implements Job {

    private static Logger bqsInfoLogger = LoggerFactory.getLogger("bqsYys");

    @Autowired
    private TdLoanInterfaceService tdLoanInterfaceService;

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @Autowired
    private IScAlarm iScAlarm;

//    @Override
//    public SaturnJobReturn handleJavaJob(String s, Integer shardItem, String s1, SaturnJobExecutionContext saturnJobExecutionContext) throws InterruptedException {
//        scheduler();
//        return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//    }

    public void scheduler() {
        try {
            bqsInfoLogger.info("[白骑士运营商] 任务获取待处理数据，当前任务处理心跳正常");
            String isStep = Constants.XC_91ZX_CALL_STEP;
            List<Map<String, Object>> list = tdLoanInterfaceService.getAppLicationList(isStep);
            if (CollectionUtils.isNotEmpty(list)) {
                bqsInfoApplicationTaskProcess(list, isStep);
                bqsInfoLogger.info("[白骑士运营商] 原始数据定时任务处理结束，条数：" + list.size() + "，当前任务处理心跳正常");
            } else {
                bqsInfoLogger.info("[白骑士运营商] 原始数据定时任务获取待处理数据0条，当前定时任务处理结束");
            }
        } catch (Throwable e) {
            bqsInfoLogger.error("[白骑士运营商] 原始数据待处理数据返回信息错误信息：-->" + e.getMessage(), e);
        }
    }

    /**
     * 处理在白骑士运营商原始数据步骤查询的待审核申请单List
     *
     * @param list
     * @param isStep
     */
    public void bqsInfoApplicationTaskProcess(List<Map<String, Object>> list, String isStep) {
        bqsInfoLogger.info("[白骑士运营商] 原始数据定时任务获取待处理数据, 待处理数据条数： " + list.size() + ",处理开始");
        for (int i = 0; i < list.size(); i++) {
            bqsInfoLogger.error("[白骑士运营商] 原始数据定时任务获取待处理数据，当前任务处理心跳正常");
            String applicationId = "";
            try {
                long startTime = System.currentTimeMillis();
                applicationId = (String) list.get(i).get("applicationNumber");
                bqsInfoLogger.info("[白骑士运营商] [applicationId=" + applicationId + "] 处理开始");

                Map<String, Object> dataAllMap = new HashMap<String, Object>();
                //将借款申请信息和客户信息的结果集封装一个MAP中
                dataAllMap = tdLoanInterfaceService.getCustApplicationList(list.get(i));
                bqsInfoLogger.info("[白骑士运营商] 单笔原始数据参数集合 [param=" + dataAllMap + "]");

                taskServiceImpl.bqsInfoApplicationTask(dataAllMap, isStep);
                bqsInfoLogger.info("[白骑士运营商] [applicationId:" + applicationId + "] 处理结束,耗时时间： " + (System.currentTimeMillis() - startTime) + " ms");
            } catch (Throwable e) {
                boolean flag = applicationService.updateErrorDescription(applicationId, "[白骑士运营商] 处理异常：" + e.getMessage());
                iScAlarm.sendDingdingMsg("[白骑士运营商] [applicationId:" + applicationId + "] 处理异常,error:" + e.getMessage(), "1");
                bqsInfoLogger.error("[白骑士运营商] [applicationId:" + applicationId + "] 单笔数据处理异常,步骤处理异常描述更新：" + flag + "; 错误信息：-->" + e.getMessage(), e);
            }
        }
        bqsInfoLogger.info("[白骑士运营商] 定时任务处理数据条数： " + list.size() + ",处理结束");
    }

    /**
     * 这是个静态方法，在executor初始化时会调用，并生成供saturn使用的实现类对象
     */
    public static Object getObject() {
        BaiQiShiInfoApplicationRemindJob instance = (BaiQiShiInfoApplicationRemindJob) SpringContextUtils.getBean(BaiQiShiInfoApplicationRemindJob.class);
        return instance;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        scheduler();
    }
}
