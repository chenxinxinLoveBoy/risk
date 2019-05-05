package com.shangyong.mongo.common;

import com.mongodb.MongoClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  * User: kenzhao
 *  * Date: 2017/8/26
 *  * Time: 15:45
 *  * PROJECT_NAME: risk-parent_2.6
 *  * PACKAGE_NAME: com.honglu.rabbitmq.mongo
 *  * DESC:
 *  * Version: v1.0.0
 *  
 */
@Configuration
public class MongoInit {
    static class OptionsConfig {
        @Bean
        public MongoClientOptions mongoOptions() {
            MongoClientOptions mongoClientOptions = MongoClientOptions.builder().socketTimeout(500000).connectTimeout(9000).serverSelectionTimeout(0).build();
            return mongoClientOptions;
        }

    }
}
