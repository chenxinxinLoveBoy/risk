package com.shangyong.shangyong;

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
public class RiskTaskApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RiskTaskApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RiskTaskApplication.class, args);
	}

}