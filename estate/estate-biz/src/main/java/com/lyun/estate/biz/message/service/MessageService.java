package com.lyun.estate.biz.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.message.def.MessageContentType;
import com.lyun.estate.biz.message.def.MessageStatus;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.entity.MessageSummaryResource;
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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

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


    public List<MessageSummaryResource> getMessageSummary(Long receiverId) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        return messageRepository.getMessageSummaryResource(receiverId);
    }

    public List<MessageSummaryResource> getMessageSummary() {
        return getMessageSummary(Long.valueOf(executionContext.getUserId()));
    }

    @Transactional
    public PageList<MessageResource> getMessage(Long receiverId, Long senderId, Long lastMessageId, PageBounds pageBounds) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        if (senderId == null) {
            throw new EstateException(PARAM_NULL, "senderId");
        }
         /*  获取消息 */
        PageList<MessageResource> messageResourceList = messageRepository.getMessage(receiverId, senderId, pageBounds);
        if (CollectionUtils.isEmpty(messageResourceList)) {
            return messageResourceList;
        }
        /*  JSON转换 */
        messageResourceList.forEach(t -> {
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
        });
        //TODO 标记未读为已读
        if (messageRepository.updateToRead(receiverId, senderId, lastMessageId) <= 0) {
            throw new EstateException(UPDATE_FAIL, "message status", "receiver:" + receiverId + ";sender:" + senderId + ";lastMessageId:" + lastMessageId);
        }

        return messageResourceList;
    }

    public List<MessageResource> getMessage(Long senderId, Long lastMessageId, PageBounds pageBounds) {
        return getMessage(Long.valueOf(executionContext.getUserId()), senderId, lastMessageId, pageBounds);
    }

    @Transactional
    private boolean createMessage(String title, String summary, String content, MessageContentType contentType, Long senderId, Long receiverId, String uuid) {
        /* 创建message */
        if (StringUtils.isEmpty(title)) {
            throw new EstateException(PARAM_NULL, "title");
        }
        if (StringUtils.isEmpty(content)) {
            throw new EstateException(PARAM_NULL, "content");
        }
        if (contentType == null) {
            throw new EstateException(PARAM_NULL, "contentType");
        }
        if (senderId == null) {
            throw new EstateException(PARAM_NULL, "senderId");
        }
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        if (StringUtils.isEmpty(uuid)) {
            throw new EstateException(PARAM_NULL, "uuid");
        }
        Message message = new Message();
        message.setTitle(title);
        message.setSummary(summary);
        message.setContent(content);
        message.setContentType(contentType);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setUuid(uuid);
        message.setStatus(MessageStatus.UNREAD);
        return createMessage(message);
    }

    private boolean createMessage(Message message) {
        if (messageRepository.createMessage(message) != 1) {
            throw new EstateException(CREATE_FAIL, "Message", message.toString());
        }
        return true;
    }

    /**
     * 提供小区动态消息生成器
     * @param receiverId
     * @param fangId
     * @param title
     * @return
     */
    @Transactional
    public Message generateSimpleFangMessage(Long receiverId, Long fangId, String title, String summary) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        if (fangId == null) {
            throw new EstateException(PARAM_NULL, "fangId");
        }
        if (StringUtils.isEmpty(title)) {
            throw new EstateException(PARAM_NULL, "title");
        }
        Message message = new Message();
        message.setTitle(title);
        message.setSummary(summary);
        message.setContent(fangId.toString());
        message.setContentType(MessageContentType.FANG);
        message.setSenderId(1L);//TODO 默认的发送者
        message.setReceiverId(receiverId);
        message.setUuid(UUID.randomUUID().toString());

        return message;
    }

    @Transactional
    public Message generateSimpleReportMessage(Long receiverId, Long reportId, String title, String summary) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        if (reportId == null) {
            throw new EstateException(PARAM_NULL, "reportId");
        }
        if (StringUtils.isEmpty(title)) {
            throw new EstateException(PARAM_NULL, "title");
        }
        Message message = new Message();
        message.setTitle(title);
        message.setSummary(summary);
        message.setContent(reportId.toString());
        message.setContentType(MessageContentType.REPORT);
        message.setSenderId(2L);//TODO 默认的发送者
        message.setReceiverId(receiverId);
        message.setUuid(UUID.randomUUID().toString());

        return message;
    }

    public Message generateSimplePhotoArticleMessage(Long receiverId, String content, String title, String summary) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        if (StringUtils.isEmpty(content)) {
            throw new EstateException(PARAM_NULL, "content");
        }
        if (StringUtils.isEmpty(title)) {
            throw new EstateException(PARAM_NULL, "title");
        }

        Message message = new Message();
        message.setTitle(title);
        message.setSummary(summary);
        message.setContent(content);
        message.setContentType(MessageContentType.PHOTO_ARTICLE);
        message.setSenderId(3L);//TODO 默认的发送者
        message.setReceiverId(receiverId);
        message.setUuid(UUID.randomUUID().toString());

        return message;
    }

    @Transactional
    public boolean produceMessage(Message message) {
        if (message == null) {
            throw new EstateException(PARAM_NULL, "message");
        }
        return createMessage(message.getTitle(), message.getSummary(), message.getContent(), message.getContentType(), message.getSenderId(), message.getReceiverId(), message.getUuid());
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
        return createMessage(message.getTitle(), message.getSummary(), message.getContent(), message.getContentType(), message.getSenderId(), message.getReceiverId(), message.getUuid());
    }

    private Message getMessageByUUID(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new EstateException(PARAM_NULL, "UUID");
        }
        return messageRepository.getMessageByUUID(uuid);
    }

}
