package com.lyun.estate.amqp.client.biz;

import com.lyun.estate.amqp.spec.pojos.SmsCode;
import com.lyun.estate.amqp.spec.resources.CommonResponse;
import com.lyun.estate.core.supports.exceptions.EstateBizException;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SmsAmqpClient {

    @Autowired
    @Qualifier("smsRabbitTemplate")
    private RabbitTemplate smsRabbitTemplate;
    private Logger logger = LoggerFactory.getLogger(SmsAmqpClient.class);

    @SuppressWarnings("unchecked")
    public void sendMessage(SmsCode smsCode, String correlationId) {
        CommonResponse replyMessage = (CommonResponse) smsRabbitTemplate.convertSendAndReceive(smsCode, message -> {
            message.getMessageProperties().getHeaders().put("x-correlation-id", correlationId);
            return message;
        });
        if (replyMessage == null) {
            logger.error("短信通道处理失败，没有返回数据");
            throw new EstateBizException("send.message.error", "短信发送失败");
        } else if (!replyMessage.isSuccess()) {
            if (replyMessage.isError()) {
                logger.error("消息通道返回错误:{}", replyMessage);
                throw new EstateBizException(replyMessage.getCode(), replyMessage.getMessage());
            } else {
                logger.warn("消息通道返回警告:{}", replyMessage);
                throw new ValidateException(replyMessage.getCode(), replyMessage.getMessage());
            }
        }
    }
}
