package com.lyun.estate.amqp.spec.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.lyun.estate.amqp.spec")
@PropertySource("classpath:/estate/amqp/spec.properties")
@PropertySource(value = "file:${LVJINSUO_HOME}/conf/estate/amqp/spec.properties", ignoreResourceNotFound = true)
public class AmqpSpecConfig {
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
        return getCachingConnectionFactory();
    }

    @Bean("syncConnectionFactory")
    public ConnectionFactory syncConnectionFactory() {
        CachingConnectionFactory connectionFactory = getCachingConnectionFactory();
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }


    private CachingConnectionFactory getCachingConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(hosts);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vhost);
        return connectionFactory;
    }

}
