package com.lyun.estate.amqp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyun.estate.amqp.config.AmqpAdminConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class AttentionService {
    private Logger logger = LoggerFactory.getLogger(AttentionService.class);

    @RabbitListener(queues = AmqpAdminConfig.attentionQueueName)
    public void onAttention(Message message) {
        logger.info("Attention Received:{}", new ObjectMapper().copy());
    }
}
