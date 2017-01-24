package com.lyun.estate.biz.mq.producer;

import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.mq.consumer.MessageConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by jesse on 2017/1/20.
 */
@Service
public class MessageProducer {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    private final static String QUEUE_NAME = "estate.queue.message";
    private final static String EXCHANGE_NAME = "estate.exchange.message";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    @Qualifier("smsRabbitTemplate")
    private AmqpTemplate template;

    @PostConstruct
    public void init() {
        Queue queue = new Queue(QUEUE_NAME);
        TopicExchange exchange = new TopicExchange(EXCHANGE_NAME);
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("*");
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareBinding(binding);
    }


    @Transactional
    public boolean send(Message message) {
        template.convertAndSend(EXCHANGE_NAME, "message", message);
        logger.info("发送消息[{}]成功", message.toString());
        return true;
    }
}
