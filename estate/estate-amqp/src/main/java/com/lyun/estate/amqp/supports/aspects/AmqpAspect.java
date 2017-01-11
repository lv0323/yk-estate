package com.lyun.estate.amqp.supports.aspects;

import com.lyun.estate.amqp.exceptions.AmqpBizException;
import com.lyun.estate.amqp.exceptions.AmqpFetalException;
import com.lyun.estate.amqp.spec.resources.CommonResponse;
import com.lyun.estate.amqp.supports.YN;
import com.lyun.estate.amqp.supports.context.AmqpExecutionContext;
import com.lyun.estate.amqp.supports.utils.CommonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

import static com.lyun.estate.amqp.supports.YN.Y;

@Aspect
@Component
public class AmqpAspect {
    private Logger logger = LoggerFactory.getLogger(AmqpAspect.class);

    @Value("${amqp.access.trace.enable}")
    YN traceEnable;

    @Value("${amqp.default.locale.language}")
    String defaultLocale;

    @Autowired
    private AmqpExecutionContext executionContext;

    @Autowired
    private MessageSource messageSource;

    private void init(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        executionContext.setUserId((String) messageProperties.getHeaders().get("x-user-id"));
        executionContext.setClient((String) messageProperties.getHeaders().get("x-client"));
        executionContext.setRequestLocale(replaceNullHeaderByDefault(messageProperties, "x-locale", defaultLocale));
        executionContext.setCorrelationId(replaceNullHeaderByDefault(messageProperties, "x-correlation-id", CommonUtil.getUuid()));
        executionContext.setClusterId(messageProperties.getClusterId());
        executionContext.setConsumerQueue(messageProperties.getConsumerQueue());
        executionContext.setReceivedRoutingKey(messageProperties.getReceivedRoutingKey());
        if (traceEnable == Y) {
            logger.info("amqp received message: {}", message);
        }
    }

    private String replaceNullHeaderByDefault(MessageProperties messageProperties, String headerKey, String defaultValue) {
        Object headerValue = messageProperties.getHeaders().get(headerKey);
        return headerValue == null ? defaultValue : (String) headerValue;
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

    @Around(value = "@annotation(rabbitListener)", argNames = "joinPoint,rabbitListener")
    public Object around(ProceedingJoinPoint joinPoint, RabbitListener rabbitListener) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            if (throwable instanceof AmqpFetalException) {
                AmqpFetalException amqpFetalException = (AmqpFetalException) throwable;
                CommonResponse commonResponse = new CommonResponse()
                        .setSuccess(false)
                        .setCode(amqpFetalException.getCode())
                        .setMessage(messageSource.getMessage(amqpFetalException.getCode(), null, amqpFetalException.getMessage(), new Locale(executionContext.getRequestLocale())))
                        .setError(true);
                logger.error("listener error: {}", commonResponse, throwable);
                return commonResponse;
            } else if (throwable instanceof AmqpBizException) {
                AmqpBizException amqpBizException = (AmqpBizException) throwable;
                CommonResponse commonResponse = new CommonResponse()
                        .setSuccess(false)
                        .setCode(amqpBizException.getCode())
                        .setMessage(messageSource.getMessage(amqpBizException.getCode(), null, amqpBizException.getMessage(), new Locale(executionContext.getRequestLocale())))
                        .setError(false);
                logger.warn("listener warn: {}", commonResponse, throwable);
                return commonResponse;
            } else {
                logger.error("listener unexpected exception:", throwable);
                throw throwable;
            }
        }

    }
}
