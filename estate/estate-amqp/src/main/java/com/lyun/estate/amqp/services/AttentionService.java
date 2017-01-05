package com.lyun.estate.amqp.services;

import com.lyun.estate.amqp.config.AmqpConfig;
import com.lyun.estate.core.pojos.Attention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AttentionService {
    private Logger logger = LoggerFactory.getLogger(AttentionService.class);

    @RabbitListener(queues = AmqpConfig.attentionQueueName)
    public void onAttention(Attention attention) {
        logger.info("Received:{}", attention);
    }
}
