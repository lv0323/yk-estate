package com.lyun.estate.amqp.services;

import com.lyun.estate.amqp.config.AmqpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SmsService {
    private Logger logger = LoggerFactory.getLogger(SmsService.class);

    @RabbitListener(queues = AmqpConfig.smsQueueName)
    public void onSms(Message message) {
        logger.info("Received:{}", message);
    }
}