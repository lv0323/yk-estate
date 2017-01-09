package com.lyun.estate.job.user;

import com.lyun.estate.core.pojos.Attention;
import com.lyun.estate.job.BaseJob;
import com.lyun.estate.job.supports.context.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AttentionJob extends BaseJob {
    private Logger logger = LoggerFactory.getLogger(Attention.class);

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Autowired
    @Qualifier("attentionRabbitTemplate")
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private JobExecutionContext jobExecutionContext;

    @Override
    public String jobName() {
        return "关注消息推送";
    }

    @Override
    public void runJob() {
        Attention attention = new Attention();
        int index = atomicInteger.incrementAndGet();
        attention.setDest(String.format("%06d", index));
        attention.setMessage(String.format("%06d关注消息", index));
        rabbitTemplate.convertAndSend(attention, message -> {
            message.getMessageProperties().setHeader("x-correlation-id", jobExecutionContext.getCorrelationId());
            logger.debug("attention send: {}", message);
            return message;
        });
    }
}
