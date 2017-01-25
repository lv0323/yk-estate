package com.lyun.estate.rest.message;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.message.def.MessageBusinessType;
import com.lyun.estate.biz.message.def.MessageContentType;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageCounterResource;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.service.MessageService;
import com.lyun.estate.biz.mq.consumer.MessageConsumer;
import com.lyun.estate.biz.mq.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jesse on 2017/1/19.
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private MessageConsumer messageConsumer;

    @GetMapping("/counter")
    MessageCounterResource getMessageCounter() {
        return messageService.getMessageCounter();
    }

    @GetMapping("/show")
    List<MessageResource> getMessage(@RequestParam(required = true) MessageBusinessType businessType,
                                              @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return messageService.getMessage(businessType, pageBounds);
    }

    //TODO 不在rest提供API
    @GetMapping("/produce")
    boolean produceMessage(@RequestParam(required = true) String title, @RequestParam(required = true) String summary, @RequestParam(required = true) String content,
                          @RequestParam(required = true) MessageContentType contentType, @RequestParam(required = true) MessageBusinessType businessType) {
//        return messageService.createMessage(title, summary, content, contentType, businessType);
        Message message = new Message();
        message.setTitle(title);
        message.setSummary(summary);
        message.setContent(content);
        message.setContentType(contentType);
        message.setBusinessType(businessType);
        return messageProducer.send(message);
    }

    //TODO 不在rest提供API
    @GetMapping("/produce/fang")
    boolean produceFangMessage(@RequestParam(required = true) Long senderId,
                           @RequestParam(required = true) Long receiverId,
                           @RequestParam(required = true) Long fangId) {
        Message message = messageService.generateSimpleFangMessage(senderId, receiverId, fangId);
        return messageProducer.send(message);
    }

    //TODO 不在rest提供API
    @GetMapping("/produce/report")
    boolean produceReportMessage(@RequestParam(required = true) Long senderId,
                           @RequestParam(required = true) Long receiverId,
                           @RequestParam(required = true) Long reportId) {
        Message message = messageService.generateSimpleReportMessage(senderId, receiverId, reportId);
        return messageProducer.send(message);
    }

    //TODO 不在rest提供API
    @GetMapping("/produce/text")
    boolean produceTextMessage(@RequestParam(required = true) Long senderId,
                               @RequestParam(required = true) Long receiverId,
                               @RequestParam(required = true) String content) {
        Message message = messageService.generateSimplePhotoArticleMessage(senderId, receiverId, content);
        return messageProducer.send(message);
    }

    //TODO 不在rest提供API
    @GetMapping("/consume")
    boolean consumeMessage() {
        return messageConsumer.receive();
    }
}
