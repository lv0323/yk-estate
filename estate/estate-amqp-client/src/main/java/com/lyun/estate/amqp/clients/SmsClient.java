package com.lyun.estate.amqp.clients;

import com.lyun.estate.core.pojos.Sms;
import com.lyun.estate.core.supports.ExecutionContext;
import com.lyun.estate.core.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SmsClient {
    private Logger logger = LoggerFactory.getLogger(SmsClient.class);
    @Autowired
    ExecutionContext executionContext;

    @Autowired
    @Qualifier("smsRabbitTemplate")
    public RabbitTemplate smsRabbitTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger();

//    @Scheduled(fixedRate = 2000)
    public void sendSms() {
        if (StringUtils.isEmpty(executionContext.getCorrelationId())) {
            executionContext.setCorrelationId(CommonUtil.getUuid());
        }
        Sms sms = new Sms();
        sms.setMobile("15021916760");
        sms.setTemplateId(String.format("%06d", atomicInteger.incrementAndGet()));
        sms.setArguments(new String[]{"100000", "01"});
        smsRabbitTemplate.correlationConvertAndSend(sms, new CorrelationData(executionContext.getCorrelationId()));
        logger.info("smsSend: {}", sms);
        executionContext.clear();
    }
}
