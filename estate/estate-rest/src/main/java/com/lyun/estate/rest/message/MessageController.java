package com.lyun.estate.rest.message;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.auth.token.JWTTokenArgumentResolver;
import com.lyun.estate.biz.event.def.EventDefine;
import com.lyun.estate.biz.event.entity.Event;
import com.lyun.estate.biz.event.service.EventService;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.entity.MessageSummaryResource;
import com.lyun.estate.biz.message.service.MessageService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.utils.CommonUtil;
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
    EventService eventService;
    @Autowired
    private MessageService messageService;

    @GetMapping("/conversation")
    @CheckToken
    List<MessageSummaryResource> getMessageSummary(
            @RequestHeader(JWTTokenArgumentResolver.AUTH_HEADER) JWTToken jwtToken) {
        return messageService.getMessageSummary();
    }

    @GetMapping("/messages")
    @CheckToken
    PageList<MessageResource> getMessage(@RequestParam Long senderId,
                                         @RequestParam(required = false) Long lastMessageId,
                                         @RequestHeader(JWTTokenArgumentResolver.AUTH_HEADER) JWTToken jwtToken,
                                         @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return messageService.getMessage(senderId, lastMessageId, pageBounds);
    }

    @PostMapping("event")
    Event event(@RequestParam Long domainId,
                @RequestParam EventDefine.Type type,
                @RequestParam String content) {
        return eventService.produce(new Event().setUuid(CommonUtil.getUuid())
                .setDomainId(domainId).setDomainType(DomainType.FANG).setType(type)
                .setContent(content));
    }
}
