package com.lyun.estate.amqp.client.config;

import com.lyun.estate.amqp.spec.config.AmqpSpecConfig;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.lyun.estate.amqp.client")
@Import(AmqpSpecConfig.class)
public class AmqpClientConfig {
    @Value("${estate.amqp.queue.sms.name}")
    private String smsQueueName;
    @Value("${estate.amqp.queue.attention.name}")
    private String attentionQueueName;

    @Bean
    public RabbitTemplate smsRabbitTemplate(ConnectionFactory syncConnectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(syncConnectionFactory);
        rabbitTemplate.setQueue(smsQueueName);
        rabbitTemplate.setRoutingKey(smsQueueName);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Bean
    public RabbitTemplate attentionRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setQueue(attentionQueueName);
        rabbitTemplate.setRoutingKey(attentionQueueName);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
