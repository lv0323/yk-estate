package com.lyun.estate.amqp.endpoints;

import com.lyun.estate.amqp.config.AmqpAdminConfig;
import com.lyun.estate.amqp.pojos.Attention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AttentionEndpoint {
    private Logger logger = LoggerFactory.getLogger(AttentionEndpoint.class);

    @RabbitListener(queues = AmqpAdminConfig.attentionQueueName, containerFactory = "rabbitListenerContainerFactory")
    public void onAttention(Message message, Attention attention) {
        logger.info("Attention Received:{}", attention);
    }
}
