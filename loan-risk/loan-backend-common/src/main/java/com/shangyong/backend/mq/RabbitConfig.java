package com.shangyong.backend.mq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

/**
 * rabbitmq配置
 * @author zhouhl
 */

@PropertySource(value="classpath:application.properties")
@Configuration
public class RabbitConfig {
	
	private Logger logger = LoggerFactory.getLogger(RabbitConfig.class);
	
    public static final String EXCHANGE   = "backend_exchange";
    public static final String BACKEND_DINGDING_ROUTINGKEY = "backend_dingding_routingKey";
  
/*//    @Value("${spring.rabbitmq.host}")
    private String host = "locahost";
    
//    @Value("${spring.rabbitmq.port}")
    private Integer port = 5672;
    
//    @Value("${spring.rabbitmq.username}")
    private String username = "backend";
    
//    @Value("${spring.rabbitmq.password}")
    private String password = "backend";
    
//    @Value("${spring.rabbitmq.virtualHost}")
    private String virtualHost = "/backend-host";
    
    */
  /*  @Bean  
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();  
        connectionFactory.setAddresses(host + ":" +  port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }*/
  
    /*@Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				if (!ack) {
					logger.error("-----------rabbitTemplate-----correlationData:" + correlationData + ", ack:" + ack + ", cause:" + cause);
				}
			}
        });
        
        return template;
    }
  
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE);
    }
  
    @Bean(value="dingding_binding")
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(this.defaultExchange()).with(RabbitConfig.BACKEND_DINGDING_ROUTINGKEY);
    }
    
    @Bean(value="backend_dingding_queue")
    public Queue queue() {
        return new Queue("backend_dingding_queue", true);
    }
*/
}
