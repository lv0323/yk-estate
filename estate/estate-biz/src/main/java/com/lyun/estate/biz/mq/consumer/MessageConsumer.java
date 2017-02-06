package com.lyun.estate.biz.mq.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    /**
     * pull模式
     * @return
     */
    public boolean receive() {
        Object o = rabbitTemplate.receiveAndConvert(QUEUE_NAME);
        try {
            Message message = (Message) o;
            if (message == null) {
                logger.info("队列里没有消息!");
                return true;
            }
            boolean result = messageService.consumeMessage(message);
            logger.info("接收消息[{}][{}]", message.toString(), result);
            return result;
        } catch (Exception ex) {
            /* MQ收到消息后保存失败理流程 **/
            logger.warn("处理消息[{}]错误:[{}]", o.toString(), ex.getMessage());
            try {
                String errorObject = mapper.writeValueAsString(o);
                //TODO 指定文件路径地址
                FileWriter fw = new FileWriter("message_error.json", true);
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

    /**
     * 发布-订阅模式
     * @param message
     */
    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(final Message message) {
//        try {
//            if (message == null) {
//                logger.info("队列里没有消息!");
//            } else {
//                boolean result = messageService.consumeMessage(message);
//                logger.info("接收消息[{}][{}]", message.toString(), result);
//            }
//        } catch (Exception ex) {
//            /* MQ收到消息后保存失败理流程 **/
//            assert message != null;
//            logger.warn("处理消息[{}]错误:[{}]", message.toString(), ex.getMessage());
//            try {
//                //TODO 指定文件路径地址
//                FileWriter fw = new FileWriter("message_error.json", true);
//                PrintWriter out = new PrintWriter(fw);
//                out.write(message.toString());
//                out.println();
//                fw.close();
//                out.close();
//            }  catch (IOException e) {
//                logger.warn("文件操作异常:[{}]", e.getMessage());
//            }
//        }
        if (message == null) {
            logger.info("队列里没有消息!");
        } else {
            boolean result = messageService.consumeMessage(message);
            logger.info("接收消息[{}][{}]", message.toString(), result);
        }
    }
}
