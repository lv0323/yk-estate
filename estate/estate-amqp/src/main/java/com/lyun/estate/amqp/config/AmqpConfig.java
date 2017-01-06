package com.lyun.estate.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.lyun.estate")
@PropertySource("classpath:/estate/amqp/server.properties")
@PropertySource(value = "file:${LVJINSUO_HOME}/conf/estate/amqp/server.properties", ignoreResourceNotFound = true)
@EnableRabbit
public class AmqpConfig {
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
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory());
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(2);
        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(5);
        return simpleRabbitListenerContainerFactory;
    }
}
