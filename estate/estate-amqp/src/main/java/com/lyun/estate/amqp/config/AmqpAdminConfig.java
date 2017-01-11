package com.lyun.estate.amqp.config;

import com.lyun.estate.amqp.spec.config.AmqpSpecConfig;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AmqpSpecConfig.class)
public class AmqpAdminConfig {
    @Value("${estate.amqp.queue.sms.name}")
    private String smsQueueName;
    @Value("${estate.amqp.queue.attention.name}")
    private String attentionQueueName;

    @Bean
    public Queue smsQueue() {
        return new Queue(smsQueueName, true);
    }

    @Bean
    public Queue annotationQueue() {
        return new Queue(attentionQueueName, true);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareQueue(smsQueue());
        rabbitAdmin.declareQueue(annotationQueue());
        return rabbitAdmin;
    }
}
