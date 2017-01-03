package com.lyun.estate.amqp.clients;

import com.lyun.estate.core.pojos.Sms;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SmsClient {
    @Autowired
    @Qualifier("smsRabbitTemplate")
    public RabbitTemplate smsRabbitTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendSms() {
        Sms sms = new Sms();
        sms.setMobile("15021916760");
        sms.setTemplateId("test00001");
        sms.setArguments(new String[]{"100000", "01"});
        smsRabbitTemplate.convertAndSend(sms);
    }
}
