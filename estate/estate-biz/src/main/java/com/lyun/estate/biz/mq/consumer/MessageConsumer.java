package com.lyun.estate.biz.mq.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jesse on 2017/1/20.
 */
@Service
public class MessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    private final static String QUEUE_NAME = "estate.queue.message";

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MessageService messageService;

    @Autowired
    @Qualifier("smsRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    public boolean receive() {
        Object o = rabbitTemplate.receiveAndConvert(QUEUE_NAME);
        try {
            Message message = (Message) o;
            if (message == null) {
                logger.info("队列里没有消息!");
                return true;
            }
            // 重复消息处理
            if (messageService.getMessageByUUID(message.getUuid()) != null) {
                logger.warn("消息[{}]已经存在，忽略！消息内容为：{}", message.getUuid(), message.toString());
                return true;
            }
            boolean result = messageService.createMessage(message.getTitle(), message.getSummary(), message.getContent(), message.getContentType(), message.getBusinessType(), message.getSenderId(), message.getReceiverId());
            logger.info("接收消息[{}][{}]", message.toString(), result);
            return result;
        } catch (Exception ex) {
            try {
                String errorObject = mapper.writeValueAsString(o);
                FileWriter fw = new FileWriter("message_error.json");
                PrintWriter out = new PrintWriter(fw);
                out.write(errorObject);
                out.println();
                fw.close();
                out.close();
                return false;
            } catch (JsonProcessingException e) {
                logger.warn("消息解析成JSON错误:[{}]", e.getMessage());
                return false;
            } catch (IOException e) {
                logger.warn("文件操作异常:[{}]", e.getMessage());
                return false;
            }
        }

    }
}
