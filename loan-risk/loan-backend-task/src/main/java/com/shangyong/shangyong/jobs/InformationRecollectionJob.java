package com.shangyong.shangyong.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shangyong.backend.dao.ApplicationDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.entity.BaseResult;
import com.shangyong.shangyong.authcenter.dubbo.CustomerInfoNewDubboService;
import com.shangyong.shangyong.utils.SpringContextUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * @Author wqk
 * @Date 2018年7月25日
 * @Description 定时调用用户的审批订单为“信息重新采集”状态，调用app步骤号修改接口，调用成功之后加上一个是否调用状态字段，避免订单重复推送
 *
 */
@Component
public class InformationRecollectionJob implements Job {

    private static Logger logger = LoggerFactory.getLogger("OperatorInfoFailurePushStatusTask");

    @Autowired
    private ApplicationDao applicationDao;

    @Reference(version="1.0.0",retries=-1,timeout=15000)
    private CustomerInfoNewDubboService customerInfoNewDubboService;

    private static final String CODE = "200";

//    @Override
//    public SaturnJobReturn handleJavaJob(String s, Integer shardItem, String s1,
//                                         SaturnJobExecutionContext saturnJobExecutionContext) throws InterruptedException {
//        scheduler();
//        return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//    }

    public void scheduler() {
        try {
            List<Application> list = applicationDao.findOperatorInfoFailure();
            logger.info("[信息重新采集] 需要处理的数量为"+list.size());

            for(int i = 0;i < list.size(); i++){
                Application application = list.get(i);

                //调用app步骤号修改接口，返回一个状态state
                BaseResult base = customerInfoNewDubboService.updateCustomerInfoNewStepNoFromRisk(
                        application.getCustomerId(),
                        application.getApplicationId(),
                        application.getAppSerialNumber(),
                        application.getAppName(),
                        application.getIsStep(),
                        null);

                if(CODE.equals(base.getCode())){
                    logger.info("[信息重新采集] [applicationId="+application.getApplicationId()+"]调用app步骤号修改接口成功");

                    int flag = applicationDao.updateIsCallSucess(application.getApplicationId());
                    if(flag > 0){
                        logger.info("[信息重新采集] [applicationId:"+application.getApplicationId()+"]的is_call_sucess已修改为1");
                    }else{
                        logger.error("[信息重新采集] [applicationId:"+application.getApplicationId()+"]的is_call_sucess修改失败");
                    }
                }else{
                    logger.error("[信息重新采集] [applicationId:"+application.getApplicationId()+"调用app步骤号修改接口失败");
                }
            }
        } catch (Throwable e) {
            logger.error("[信息重新采集] 调用app步骤号定时任务返回错误信息：-->"+e.getMessage(),e);
        }
    }

    /**
     * 这是个静态方法，在executor初始化时会调用，并生成供saturn使用的实现类对象
     */
    public static Object getObject() {
        InformationRecollectionJob instance = (InformationRecollectionJob) SpringContextUtils.getBean(InformationRecollectionJob.class);
        return instance;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        scheduler();
    }
}
