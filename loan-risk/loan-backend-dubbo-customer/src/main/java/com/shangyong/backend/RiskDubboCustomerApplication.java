package com.shangyong.backend;

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
@ComponentScan
@SpringBootApplication
@MapperScan("com.shangyong.backend.dao")
public class RiskDubboCustomerApplication extends SpringBootServletInitializer {

	// tomcat启动方式
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RiskDubboCustomerApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RiskDubboCustomerApplication.class, args);
	}
}