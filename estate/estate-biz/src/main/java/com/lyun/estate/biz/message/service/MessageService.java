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
import com.lyun.estate.biz.mq.producer.MessageProducer;
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
import org.springframework.util.StringUtils;

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

    @Autowired
    private MessageProducer messageProducer;


    public MessageCounterResource getMessageCounter(Long receiverId) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        return messageRepository.getMessageCounterResource(receiverId);
    }

    public MessageCounterResource getMessageCounter() {
        return getMessageCounter(Long.valueOf(executionContext.getUserId()));
    }

    @Transactional
    public List<MessageResource> getMessage(Long receiverId, MessageBusinessType businessType, PageBounds pageBounds) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        if (businessType == null) {
            throw new EstateException(PARAM_NULL, "businessType");
        }
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
        return createMessage(message);
    }

    private boolean createMessage(Message message) {
        if (messageRepository.createMessage(message) != 1) {
            throw new EstateException(CREATE_FAIL, "Message", message.toString());
        }
        /* 首次创建message counter */
        MessageCounter messageCounter = messageRepository.getMessageCounter(message.getReceiverId());
        if (messageCounter == null) {
            messageCounter = new MessageCounter();
            messageCounter.setOwnerId(message.getReceiverId());
            if (messageRepository.createMessageCounter(messageCounter) != 1) {
                throw new EstateException(CREATE_FAIL, "MessageCounter", messageCounter.toString());
            }
        }
        return true;
    }

    @Transactional
    public Message generateSimpleFangMessage(Long senderId, Long receiverId, Long fangId) {
        if (senderId == null) {
            //TODO 默认发送者
            senderId = 1L;
        }
        if (receiverId == null) {
            throw new EstateException(PARAM_ILLEGAL, "receiverId", receiverId);
        }
        if (fangId == null) {
            throw new EstateException(PARAM_ILLEGAL, "fangId", fangId);
        }
        Message message = new Message();
        message.setContent(fangId.toString());
        message.setContentType(MessageContentType.FANG);
        message.setBusinessType(MessageBusinessType.C_MS);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);

        return message;
    }

    @Transactional
    public Message generateSimpleReportMessage(Long senderId, Long receiverId, Long reportId) {
        if (senderId == null) {
            //TODO 默认发送者
            senderId = 1L;
        }
        if (receiverId == null) {
            throw new EstateException(PARAM_ILLEGAL, "receiverId", receiverId);
        }
        if (reportId == null) {
            throw new EstateException(PARAM_ILLEGAL, "reportId", reportId);
        }
        Message message = new Message();
        message.setContent(reportId.toString());
        message.setContentType(MessageContentType.REPORT);
        message.setBusinessType(MessageBusinessType.C_M_REPORT);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);

        return message;
    }

    public Message generateSimplePhotoArticleMessage(Long senderId, Long receiverId, String content) {
        if (senderId == null) {
            //TODO 默认发送者
            senderId = 1L;
        }
        if (receiverId == null) {
            throw new EstateException(PARAM_ILLEGAL, "receiverId", receiverId);
        }
        if (StringUtils.isEmpty(content)) {
            throw new EstateException(PARAM_ILLEGAL, "content", content);
        }

        Message message = new Message();
        message.setContent(content);
        message.setContentType(MessageContentType.PHOTO_ARTICLE);
        message.setBusinessType(MessageBusinessType.NOTICE);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);

        return message;
    }

    @Transactional
    public boolean produceMessage(Message message) {
        if (message == null) {
            throw new EstateException(PARAM_NULL, "message");
        }
        return createMessage(message.getTitle(), message.getSummary(), message.getContent(), message.getContentType(), message.getBusinessType(), message.getSenderId(), message.getReceiverId());
    }

    @Transactional
    public boolean consumeMessage(Message message) {
        if (message == null) {
            throw new EstateException(PARAM_NULL, "message");
        }
        // 重复消息处理
        if (getMessageByUUID(message.getUuid()) != null) {
            logger.warn("消息[{}]已经存在，忽略！消息内容为：{}", message.getUuid(), message.toString());
            return true;
        }
        return createMessage(message.getTitle(), message.getSummary(), message.getContent(), message.getContentType(), message.getBusinessType(), message.getSenderId(), message.getReceiverId());
    }

    private Message getMessageByUUID(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new EstateException(PARAM_NULL, "UUID");
        }
        return messageRepository.getMessageByUUID(uuid);
    }

}
