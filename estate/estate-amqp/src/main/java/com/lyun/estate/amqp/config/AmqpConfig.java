package com.lyun.estate.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.lyun.estate")
@PropertySource("classpath:/estate/amqp/server.properties")
@PropertySource(value = "file:${LVJINSUO_HOME}/conf/estate/amqp/server.properties", ignoreResourceNotFound = true)
public class AmqpConfig {
    public static final String smsQueueName = "ykestate.queue.sms";
    public static final String attentionQueueName = "ykestate.queue.attention";

    @Value("${estate.amqp.hosts}")
    private String hosts;
    @Value("${estate.amqp.userName}")
    private String userName;
    @Value("${estate.amqp.password}")
    private String password;
    @Value("${estate.amqp.vhost}")
    private String vhost;

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
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(smsQueue(), annotationQueue());
        container.setMessageConverter(jackson2JsonMessageConverter());
        return container;
    }

    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue smsQueue() {
        return new Queue(smsQueueName, true);
    }

    @Bean
    public Queue annotationQueue() {
        return new Queue(attentionQueueName, true);
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.declareQueue(smsQueue());
        rabbitAdmin.declareQueue(annotationQueue());
        return rabbitAdmin;
    }
}
