package com.lyun.estate.biz.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.message.def.MessageBusinessType;
import com.lyun.estate.biz.message.def.MessageContentType;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageCounter;
import com.lyun.estate.biz.message.entity.MessageCounterResource;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.repository.MessageRepository;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.service.FangService;
import com.lyun.estate.core.supports.ExecutionContext;
import com.lyun.estate.core.supports.exceptions.EstateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.lyun.estate.core.supports.exceptions.ExCode.*;

/**
 * Created by jesse on 2017/1/20.
 */
@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private FangService fangService;

    @Autowired
    private ExecutionContext executionContext;


    public MessageCounterResource getMessageCounter(Long receiverId) {
        return messageRepository.getMessageCounterResource(receiverId);
    }

    public MessageCounterResource getMessageCounter() {
        return getMessageCounter(Long.valueOf(executionContext.getUserId()));
    }

    @Transactional
    public List<MessageResource> getMessage(Long receiverId, MessageBusinessType businessType, PageBounds pageBounds) {
         /*  获取消息 */
        List<MessageResource> messageResourceList = messageRepository.getMessage(receiverId, businessType, pageBounds);
        if (CollectionUtils.isEmpty(messageResourceList)) {
            return messageResourceList;
        }
        /*  JSON转换 */
        List<MessageResource> resultList = messageResourceList.stream().map(t -> {
            switch (t.getContentType()) {
                case FANG:
                    FangSummary fang = fangService.getSummary(Long.valueOf(t.getContent()));
                    if (fang == null) {
                        logger.warn("根据信息中存放的content[{}]无法获取有效房源", t.getContent());
                        break;
                    }
                    try {
                        t.setData(mapper.writeValueAsString(fang));
                    } catch (JsonProcessingException e) {
                        throw new EstateException(JSON_ERROR, fang.toString(), e.getMessage());
                    }
                    break;
                case REPORT:
                    //TODO 月报实现
                    t.setData(t.getContent());
                    break;
                case PHOTO_ARTICLE:
                    //TODO 图文实现
                    t.setData(t.getContent());
                    break;
                default:
                    throw new EstateException(PARAM_ILLEGAL, "MessageContentType", t.getContentType());
            }
            return t;
        }).collect(Collectors.toList());
         /* 更新消息计数器 */
        MessageResource lastMessageResource = messageResourceList.stream().reduce((x, y) -> {
            if (x.getId() >= y.getId()) {
                return x;
            }
            return y;
        }).get();
        MessageCounter messageCounter = messageRepository.getMessageCounter(receiverId);
        if (messageCounter == null) {
            throw new EstateException(NOT_FOUND, receiverId, "MessageCounter");
        }
        switch (businessType) {
            case C_MS:
                messageCounter.setCMsIndex(lastMessageResource.getId());
                break;
            case C_M_REPORT:
                messageCounter.setCMReportIndex(lastMessageResource.getId());
                break;
            case NOTICE:
                messageCounter.setNoticeIndex(lastMessageResource.getId());
                break;
            default:
                throw new EstateException(PARAM_ILLEGAL, "MessageBusinessType", businessType);
        }
        if (messageRepository.updateMessageCounter(messageCounter) != 1) {
            throw new EstateException(UPDATE_FAIL, "MessageCounter", messageCounter.toString());
        }
        return resultList;
    }

    @Transactional
    public List<MessageResource> getMessage(MessageBusinessType businessType, PageBounds pageBounds) {
        return getMessage(Long.valueOf(executionContext.getUserId()), businessType, pageBounds);
    }

    @Transactional
    public boolean createMessage(String title, String summary, String content, MessageContentType contentType, MessageBusinessType businessType, Long senderId, Long receiverId) {
        /* 创建message */
        Message message = new Message();
        message.setTitle(title);
        message.setSummary(summary);
        message.setContent(content);
        message.setContentType(contentType);
        message.setBusinessType(businessType);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        if (messageRepository.createMessage(message) != 1) {
            throw new EstateException(CREATE_FAIL, "Message", message.toString());
        }
        /* 首次创建message counter */
        MessageCounter messageCounter = messageRepository.getMessageCounter(receiverId);
        if (messageCounter == null) {
            messageCounter = new MessageCounter();
            messageCounter.setOwnerId(receiverId);
            if (messageRepository.createMessageCounter(messageCounter) != 1) {
                throw new EstateException(CREATE_FAIL, "MessageCounter", messageCounter.toString());
            }
        }
        return true;
    }

    @Transactional
    public boolean createMessage(String title, String summary, String content, MessageContentType contentType, MessageBusinessType businessType) {
        //TODO 默认sender
        return createMessage(title, summary, content, contentType, businessType, 1L, Long.valueOf(executionContext.getUserId()));
    }

}
