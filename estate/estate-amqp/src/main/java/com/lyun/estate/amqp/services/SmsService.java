package com.lyun.estate.amqp.services;

import com.lyun.estate.amqp.config.AmqpConfig;
import com.lyun.estate.core.pojos.Sms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SmsService {
    private Logger logger = LoggerFactory.getLogger(SmsService.class);

    @RabbitListener(queues = AmqpConfig.smsQueueName)
    public void onSms(Sms sms) {
        logger.info("Received:{}", sms);
    }
}
