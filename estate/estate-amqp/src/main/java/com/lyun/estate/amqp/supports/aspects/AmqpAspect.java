package com.lyun.estate.amqp.supports.aspects;

import com.lyun.estate.amqp.supports.YN;
import com.lyun.estate.amqp.supports.context.AmqpExecutionContext;
import com.lyun.estate.amqp.supports.utils.CommonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

import static com.lyun.estate.amqp.supports.YN.Y;

@Aspect
@Component
public class AmqpAspect {
    private Logger logger = LoggerFactory.getLogger(AmqpAspect.class);

    @Value("${amqp.access.trace.enable}")
    YN traceEnable;

    @Autowired
    AmqpExecutionContext executionContext;

    private void init(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        executionContext.setUserId((String) messageProperties.getHeaders().get("x-user-id"));
        executionContext.setClient((String) messageProperties.getHeaders().get("x-client"));
        executionContext.setClusterId(messageProperties.getClusterId());
        executionContext.setConsumerQueue(messageProperties.getConsumerQueue());
        String correlationId = (String) messageProperties.getHeaders().get("x-correlation-id");
        if (StringUtils.isEmpty(correlationId)) {
            correlationId = CommonUtil.getUuid();
        }
        executionContext.setCorrelationId(correlationId);
        executionContext.setReceivedRoutingKey(messageProperties.getReceivedRoutingKey());
        if (traceEnable == Y) {
            logger.info("amqp received message: {}", message);
        }
    }

    private void clear() {
        executionContext.clear();
    }

    @Before(value = "@annotation(rabbitListener)", argNames = "joinPoint,rabbitListener")
    public void before(JoinPoint joinPoint, RabbitListener rabbitListener) {
        Arrays.stream(joinPoint.getArgs()).filter(arg -> arg instanceof Message).findAny().ifPresent(message -> init((Message) message));
    }

    @After(value = "@annotation(rabbitListener)", argNames = "rabbitListener")
    public void after(RabbitListener rabbitListener) {
        clear();
    }

}
