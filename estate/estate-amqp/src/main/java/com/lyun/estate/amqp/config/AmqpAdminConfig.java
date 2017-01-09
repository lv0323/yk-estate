package com.lyun.estate.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AmqpConfig.class)
public class AmqpAdminConfig {
    public static final String smsQueueName = "ykestate.queue.sms1";
    public static final String attentionQueueName = "ykestate.queue.attention1";

    @Autowired
    private ConnectionFactory connectionFactory;

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
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareQueue(smsQueue());
        rabbitAdmin.declareQueue(annotationQueue());
        return rabbitAdmin;
    }
}
