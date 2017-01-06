package com.lyun.estate.amqp.supports.aspects;

import com.lyun.estate.amqp.supports.context.AmqpExecutionContext;
import com.lyun.estate.core.utils.CommonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Aspect
@Component
public class AmqpAspect {


    @Autowired
    AmqpExecutionContext executionContext;

    private void init(MessageProperties messageProperties) {
        executionContext.setUserId(messageProperties.getUserId());
        executionContext.setClusterId(messageProperties.getClusterId());
        executionContext.setConsumerQueue(messageProperties.getConsumerQueue());
        String correlationId = messageProperties.getCorrelationIdString();
        if (StringUtils.isEmpty(correlationId)) {
            correlationId = CommonUtil.getUuid();
        }
        executionContext.setCorrelationId(correlationId);
        executionContext.setReceivedRoutingKey(messageProperties.getReceivedRoutingKey());
    }

    private void clear() {
        executionContext.clear();
    }

    @Before(value = "@annotation(rabbitListener)", argNames = "joinPoint,rabbitListener")
    public void before(JoinPoint joinPoint, RabbitListener rabbitListener) {
        Arrays.stream(joinPoint.getArgs()).filter(arg -> arg instanceof Message).findAny().ifPresent(message -> init(((Message) message).getMessageProperties()));
    }

    @After(value = "@annotation(rabbitListener)", argNames = "joinPoint,rabbitListener")
    public void after(JoinPoint joinPoint, RabbitListener rabbitListener) {
        clear();
    }

}
