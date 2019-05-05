package com.shangyong.common;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 启动定时任务
 */
@Configuration
@EnableScheduling
public class HeartbeatLog {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger("BackendHeartbeatLog");

	@Scheduled(cron = "0/30 * * * * ?")
	public void outLogMsg(){
		logger.info("loan-backend-web 运行正常");
	}
}
