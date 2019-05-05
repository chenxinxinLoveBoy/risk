package com.shangyong.shangyong.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.ScScheduleJob;
import com.shangyong.backend.service.ScheduleJobService;
import com.shangyong.backend.service.impl.ScheduleJobServiceImpl;
import com.shangyong.backend.utils.SpringIocUtils;

@Component
public class TaskListener implements ApplicationListener {
//public class TaskListener implements ApplicationListener<ApplicationReadyEvent> {

    private Logger logger = LoggerFactory.getLogger(TaskListener.class);

//    @Override
//    public void onApplicationEvent(final ApplicationReadyEvent event) {
//    	logger.info("=======TaskListener===initJob======start==");
//    	ScheduleJobService ScheduleJobService = SpringIocUtils.getBean(ScheduleJobServiceImpl.class);
//    	ScScheduleJob scScheduleJob = new ScScheduleJob();
//    	String backendTaskPrefix = Constants.JOB_PREFIX + Constants.BACKEND_TASK;
//    	logger.info("-->>初始化此前缀定时任务， backendTaskPrefix:" + backendTaskPrefix);
//    	scScheduleJob.setJobGroup(backendTaskPrefix);
//    	ScheduleJobService.initJob(scScheduleJob);
//    	logger.info("=======TaskListener===initJob======end==");
//    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        logger.info("=======TaskListener===initJob======start==");
        ScheduleJobService ScheduleJobService = SpringIocUtils.getBean(ScheduleJobServiceImpl.class);
        ScScheduleJob scScheduleJob = new ScScheduleJob();
        String backendTaskPrefix = Constants.JOB_PREFIX + Constants.BACKEND_TASK;
        logger.info("-->>初始化此前缀定时任务， backendTaskPrefix:" + backendTaskPrefix);
        scScheduleJob.setJobGroup(backendTaskPrefix);
        ScheduleJobService.initJob(scScheduleJob);
        logger.info("=======TaskListener===initJob======end==");
    }
}