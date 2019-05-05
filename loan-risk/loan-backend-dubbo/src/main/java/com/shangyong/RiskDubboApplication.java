package com.shangyong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot 应用启动类
 * 
 * @author zhangze
 */
// Spring Boot 应用的标识
@EnableAutoConfiguration
@ComponentScan(basePackages = "com")
@SpringBootApplication
@MapperScan("com.shangyong.backend.dao")
public class RiskDubboApplication extends SpringBootServletInitializer {

	// main方法启动
	// public class AccountApplication{
	// public static void main(String[] args) {
	// try{
	// // 程序启动入口
	// // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
	// // ConfigurableApplicationContext run =
	// SpringApplication.run(AccountApplication.class, args);
	//// SpringApplication.run(AccountApplication.class, args);
	// System.out.println("springboot启动完成！");
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// }

	// tomcat启动方式
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RiskDubboApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RiskDubboApplication.class, args);
	}

}