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
@ComponentScan(basePackages = "com.shangyong")
@MapperScan("com.shangyong.backend.dao")
@SpringBootApplication
public class RiskBlacklistApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RiskBlacklistApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RiskBlacklistApplication.class, args);
	}

}