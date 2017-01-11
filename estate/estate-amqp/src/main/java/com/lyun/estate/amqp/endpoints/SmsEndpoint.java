package com.lyun.estate.amqp.endpoints;

import com.lyun.estate.amqp.spec.resources.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsEndpoint {
    private Logger logger = LoggerFactory.getLogger(SmsEndpoint.class);

    @RabbitListener(queues = "${estate.amqp.queue.sms.name}", containerFactory = "rabbitListenerContainerFactory")
    public CommonResponse onSms(Message message, Map<String, Object> smsCode) {
        logger.info("Sms Received:{}", smsCode);
        return new CommonResponse().setSuccess(true);
    }
}