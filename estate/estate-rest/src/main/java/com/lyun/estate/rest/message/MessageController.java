package com.lyun.estate.rest.message;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.message.def.MessageBusinessType;
import com.lyun.estate.biz.message.def.MessageContentType;
import com.lyun.estate.biz.message.entity.MessageCounterResource;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.service.MessageService;
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
    @GetMapping("/create")
    boolean createMessage(@RequestParam(required = true) String title, @RequestParam(required = true) String summary, @RequestParam(required = true) String content,
                          @RequestParam(required = true) MessageContentType contentType, @RequestParam(required = true) MessageBusinessType businessType) {
        return messageService.createMessage(title, summary, content, contentType, businessType);
    }
}
