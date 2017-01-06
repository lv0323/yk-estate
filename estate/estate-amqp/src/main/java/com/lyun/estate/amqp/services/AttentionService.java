package com.lyun.estate.amqp.services;

import com.lyun.estate.amqp.config.AmqpAdminConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AttentionService {
    private Logger logger = LoggerFactory.getLogger(AttentionService.class);

    @RabbitListener(queues = AmqpAdminConfig.attentionQueueName)
    public void onAttention(Message message) {
        logger.info("Attention Received:{}", message);
    }
}
