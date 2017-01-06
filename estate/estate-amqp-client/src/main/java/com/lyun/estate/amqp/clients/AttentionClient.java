package com.lyun.estate.amqp.clients;

import com.lyun.estate.core.pojos.Attention;
import com.lyun.estate.core.supports.ExecutionContext;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AttentionClient {
    @Autowired
    @Qualifier("attentionRabbitTemplate")
    public RabbitTemplate attentionRabbitTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger();

//    @Scheduled(fixedRate = 2000)
    public void sendSms() {
        Attention attention = new Attention().setDest("attention:" + atomicInteger.incrementAndGet()).setMessage("关注消息");
        attentionRabbitTemplate.convertAndSend(attention);
    }
}
