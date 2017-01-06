package com.lyun.estate.amqp.services;

import com.lyun.estate.amqp.config.AmqpAdminConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class SmsService {
    private Logger logger = LoggerFactory.getLogger(SmsService.class);

    @RabbitListener(queues = AmqpAdminConfig.smsQueueName)
    public void onSms(Message message) {
        logger.info("Message Received:{}", message);
        logger.info("Message Received:{}", new String(message.getBody(), StandardCharsets.UTF_8));
    }
}