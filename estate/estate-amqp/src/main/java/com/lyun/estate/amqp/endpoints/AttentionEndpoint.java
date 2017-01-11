package com.lyun.estate.amqp.endpoints;

import com.lyun.estate.amqp.spec.pojos.Attention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AttentionEndpoint {
    private Logger logger = LoggerFactory.getLogger(AttentionEndpoint.class);

    @RabbitListener(queues = "${estate.amqp.queue.attention.name}", containerFactory = "rabbitListenerContainerFactory")
    public void onAttention(Message message, Attention attention) {
        logger.info("Attention Received:{}", attention);
    }
}

