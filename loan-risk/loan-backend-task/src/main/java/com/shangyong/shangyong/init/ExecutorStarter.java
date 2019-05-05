/**
 * Project Name:loan-backend-task
 * File Name:ExecutorStarter.java
 * Package Name:com.shangyong.init
 * Date:2018年7月5日下午8:45:21
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shangyong.shangyong.init;

//import com.alibaba.dubbo.common.logger.Logger;
//import com.alibaba.dubbo.common.logger.LoggerFactory;
//import com.vip.saturn.embed.SaturnEmbed;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;

/**
 * ClassName:ExecutorStarter
 * Function: executor启动类
 * Date:     2018年7月5日 下午8:45:21 
 * @author   lz
 * @since    JDK 1.8
 */

//@Component
//@PropertySource(value = "classpath:config.properties")
//public class ExecutorStarter implements ApplicationRunner {
//
//	private static Logger logger = LoggerFactory.getLogger("executorStarter");
//
//	@Value("${saturn.home}")
//	private String saturnHome;
//
//	@Value("${saturn.namespace}")
//	private String saturnNamespace;
//
//	@Value("${saturn.executorname}")
//	private String saturnExecutorName;
//
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//		try {
//			logger.info("#####saturn执行器启动   环境变量 home：" + saturnHome
//				+ ",域nemespace:" + saturnNamespace
//				+ ",别名executorName:"+ saturnExecutorName +"#####");
//			System.setProperty("saturn.home", saturnHome);
//			System.setProperty("saturn.app.namespace", saturnNamespace);
//			System.setProperty("saturn.app.executorName", saturnExecutorName);
//			SaturnEmbed.start();
//		} catch (Exception e) {
//				logger.error("", e);
//		} finally {
//			Thread.currentThread().setContextClassLoader(contextClassLoader);
//		}
//	}
//}
