package com.shangyong.shangyong.jobs;

import com.shangyong.shangyong.service.AlarmService;
import com.shangyong.shangyong.utils.SpringContextUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author caisheng
 * @Date 2018年8月3日
 * @Description 申请单 失败次数 超过4次 报警
 */
@Component
public class AlarmFailApplicationJob implements Job {

    private static Logger logger = LoggerFactory.getLogger("alarmJob");

    @Autowired
    private AlarmService alarmService;

//    @Override
//    public SaturnJobReturn handleJavaJob(String jobName, Integer shardItem, String shardParam, SaturnJobExecutionContext shardingContext) throws InterruptedException {
//
//        logger.info("[报警] [失败次数到达5次] 开始处理");
//
//        alarmService.alarmFailApplication();
//
//        logger.info("[报警] [失败次数到达5次] 处理结束");
//        return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//    }

    /**
     * 这是个静态方法，在executor初始化时会调用，并生成供saturn使用的实现类对象
     */
    public static Object getObject() {
        AlarmFailApplicationJob instance = (AlarmFailApplicationJob) SpringContextUtils.getBean(AlarmFailApplicationJob.class);
        return instance;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        alarmService.alarmFailApplication();
    }
}
