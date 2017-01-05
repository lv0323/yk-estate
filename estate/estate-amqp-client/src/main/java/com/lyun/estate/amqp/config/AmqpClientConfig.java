package com.lyun.estate.amqp.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.lyun.estate.amqp")
@PropertySource("classpath:/estate/amqp/client.properties")
@PropertySource(value = "file:${LVJINSUO_HOME}/conf/estate/amqp/client.properties", ignoreResourceNotFound = true)
@EnableScheduling
public class AmqpClientConfig {
    @Value("${estate.amqp.hosts}")
    private String hosts;
    @Value("${estate.amqp.userName}")
    private String userName;
    @Value("${estate.amqp.password}")
    private String password;
    @Value("${estate.amqp.vhost}")
    private String vhost;

    @Value("${estate.amqp.queue.sms.name}")
    private String smsQueueName;
    @Value("${estate.amqp.queue.attention.name}")
    private String attentionQueueName;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(hosts);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vhost);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate smsRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setQueue(smsQueueName);
        rabbitTemplate.setRoutingKey(smsQueueName);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitTemplate annotationRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setQueue(attentionQueueName);
        rabbitTemplate.setRoutingKey(attentionQueueName);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
