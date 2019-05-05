package com.shangyong.backend.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

@Configuration
@EnableScheduling //启动定时任务
public class HeartbeatLog {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger("DubboCustomerHeartbeatLog");

	@Scheduled(cron = "0/30 * * * * ?")
	public void outLogMsg(){
		
		logger.info("loan-backend-dubbo-customer 运行正常");
	}
}