package com.lyun.estate.biz.mq.producer;

import com.lyun.estate.biz.message.entity.EventMessage;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.service.MessageService;
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
    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    private final static String MESSAGE_QUEUE_NAME = "estate.queue.e_message";
//    private final static String EVENT_MESSAGE_QUEUE_NAME = "estate.queue.ems";
    private final static String EXCHANGE_NAME = "estate.exchange.ms";
    private final static String ROUTING_KEY = "e_message";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    @Qualifier("messageRabbitTemplate")
    private AmqpTemplate template;

    @Autowired
    private MessageService messageService;

    @PostConstruct
    public void init() {
        TopicExchange exchange = new TopicExchange(EXCHANGE_NAME, true, false);

        Queue messageQueue = new Queue(MESSAGE_QUEUE_NAME, true);
        Binding messageBinding = BindingBuilder.bind(messageQueue).to(exchange).with(ROUTING_KEY);
//
//        Queue eventMessageQueue = new Queue(EVENT_MESSAGE_QUEUE_NAME, true);
//        Binding eventMessageBinding = BindingBuilder.bind(eventMessageQueue).to(exchange).with("ems");

        amqpAdmin.declareExchange(exchange);

        amqpAdmin.declareQueue(messageQueue);
        amqpAdmin.declareBinding(messageBinding);
//
//        amqpAdmin.declareQueue(eventMessageQueue);
//        amqpAdmin.declareBinding(eventMessageBinding);
    }


    @Transactional
    public boolean send(EventMessage eventMessage) {
        try {
            template.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, eventMessage);
            logger.info("发送消息[{}]成功!", eventMessage.toString());
            return true;
        } catch (Exception ex) {
            /* 发送到MQ失败处理流程 **/
            logger.info("发送消息[{}]失败,原因[{}],进入发送失败处理流程!", eventMessage.toString(), ex.getMessage());
            return messageService.produceMessage(eventMessage);
        }

    }

    @Transactional
    public boolean send(Message message) {
        return messageService.produceMessage(message);
    }
}
