package com.lyun.estate.biz.mq.producer;

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

    private final static String QUEUE_NAME = "estate.queue.ms";
    private final static String EXCHANGE_NAME = "estate.exchange.ms";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    @Qualifier("smsRabbitTemplate")
    private AmqpTemplate template;

    @Autowired
    private MessageService messageService;

    @PostConstruct
    public void init() {
        Queue queue = new Queue(QUEUE_NAME);
        TopicExchange exchange = new TopicExchange(EXCHANGE_NAME);
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("ms");
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareBinding(binding);
    }


    @Transactional
    public boolean send(Message message) {
        try {
            template.convertAndSend(EXCHANGE_NAME, "ms", message);
            logger.info("发送消息[{}]成功!", message.toString());
            return true;
        } catch (Exception ex) {
            /* 发送到MQ失败处理流程 **/
            logger.info("发送消息[{}]失败,原因[{}],进入发送失败处理流程!", message.toString(), ex.getMessage());
            return messageService.produceMessage(message);
        }

    }
}
