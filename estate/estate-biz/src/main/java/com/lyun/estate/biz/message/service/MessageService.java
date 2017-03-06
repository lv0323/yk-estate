package com.lyun.estate.biz.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.favorite.entity.Favorite;
import com.lyun.estate.biz.favorite.service.FavoriteService;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.message.def.MessageStatus;
import com.lyun.estate.biz.message.entity.EventMessage;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.entity.MessageSummaryResource;
import com.lyun.estate.biz.message.repository.MessageRepository;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.rest.service.FangService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.service.UserService;
import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.EstateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.lyun.estate.biz.support.def.DomainType.*;
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
    private FavoriteService favoriteService;

    @Autowired
    private RestContext restContext;

    @Autowired
    private AdminUserUtils adminUserUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;


    public List<MessageSummaryResource> getMessageSummary(Long receiverId) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        List<MessageSummaryResource> summaryResourceList = messageRepository.getMessageSummaryResource(receiverId);
        summaryResourceList.forEach(
                t -> {
                    User sender = userService.findUserById(t.getSenderId());
                    if (Objects.nonNull(sender.getAvatarId())) {
                        FileDescription file = fileService.findOne(sender.getAvatarId());
                        if (Objects.nonNull(file)) {
                            t.setSenderImgUrl(file.getFileURI());
                        }
                    }
                }
        );
        return summaryResourceList;
    }

    public List<MessageSummaryResource> getMessageSummary() {
        return getMessageSummary(restContext.getUserId());
    }

    @Transactional
    public PageList<MessageResource> getMessage(Long receiverId, Long senderId, Long lastMessageId,
                                                PageBounds pageBounds) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        if (senderId == null) {
            throw new EstateException(PARAM_NULL, "senderId");
        }
         /*  获取消息 */
        PageList<MessageResource> messageResourceList = messageRepository.getMessage(receiverId,
                senderId,
                lastMessageId,
                pageBounds);
        if (CollectionUtils.isEmpty(messageResourceList)) {
            return messageResourceList;
        }
        /*  JSON转换 */
        messageResourceList.forEach(t -> {
            switch (t.getDomainType()) {
                case FANG:
                    FangSummary fang = fangService.getSummary(t.getDomainId());
                    if (fang == null) {
                        logger.warn("根据信息中存放的content[{}]无法获取有效房源", t.getDomainId());
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
                    t.setData(t.getDomainId().toString());
                    break;
                case NOTICE:
                    //TODO 公告
                    t.setData(t.getDomainId().toString());
                    break;
                default:
                    //TODO 默认处理
                    t.setData(t.getDomainId().toString());
                    break;
            }
        });
        //TODO 标记未读为已读
        if (messageRepository.updateToRead(receiverId, senderId, lastMessageId) <= 0) {
            throw new EstateException(UPDATE_FAIL,
                    "message status",
                    "receiver:" + receiverId + ";sender:" + senderId + ";lastMessageId:" + lastMessageId);
        }

        return messageResourceList;
    }

    public PageList<MessageResource> getMessage(Long senderId, Long lastMessageId, PageBounds pageBounds) {
        return getMessage(restContext.getUserId(), senderId, lastMessageId, pageBounds);
    }

    @Transactional
    private boolean createMessage(String title, String summary, Long domainId, DomainType domainType, Long senderId,
                                  Long receiverId) {
        /* 创建message */
        if (StringUtils.isEmpty(title)) {
            throw new EstateException(PARAM_NULL, "title");
        }
        if (domainId == null) {
            throw new EstateException(PARAM_NULL, "domainId");
        }
        if (domainType == null) {
            throw new EstateException(PARAM_NULL, "domainType");
        }
        if (senderId == null) {
            throw new EstateException(PARAM_NULL, "senderId");
        }
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        Message message = new Message();
        message.setTitle(title);
        message.setSummary(summary);
        message.setDomainId(domainId);
        message.setDomainType(domainType);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
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
     * 提供房源事件消息模板
     *
     * @param fangId
     * @param title
     * @return
     */
    public EventMessage generateSimpleFangEventMessage(Long fangId, String title) {
        if (fangId == null) {
            throw new EstateException(PARAM_NULL, "fangId");
        }
        if (StringUtils.isEmpty(title)) {
            throw new EstateException(PARAM_NULL, "title");
        }
        EventMessage eventMessage = new EventMessage();
        eventMessage.setUuid(UUID.randomUUID().toString());
        eventMessage.setTitle(title);
        eventMessage.setDomainType(DomainType.FANG);
        eventMessage.setDomainId(fangId);
        return eventMessage;
    }

    /**
     * 提供小区事件消息模板
     *
     * @param xiaoQuId
     * @param title
     * @return
     */
    public EventMessage generateSimpleXiaoQuEventMessage(Long xiaoQuId, String title) {
        if (xiaoQuId == null) {
            throw new EstateException(PARAM_NULL, "xiaoQuId");
        }
        if (StringUtils.isEmpty(title)) {
            throw new EstateException(PARAM_NULL, "title");
        }
        EventMessage eventMessage = new EventMessage();
        eventMessage.setUuid(UUID.randomUUID().toString());
        eventMessage.setTitle(title);
        eventMessage.setDomainType(DomainType.XIAO_QU);
        eventMessage.setDomainId(xiaoQuId);
        return eventMessage;
    }

    /**
     * 提供月报事件消息模板
     *
     * @param reportId
     * @param title
     * @return
     */
    public EventMessage generateSimpleReportEventMessage(Long reportId, String title) {
        if (reportId == null) {
            throw new EstateException(PARAM_NULL, "reportId");
        }
        if (StringUtils.isEmpty(title)) {
            throw new EstateException(PARAM_NULL, "title");
        }
        EventMessage eventMessage = new EventMessage();
        eventMessage.setUuid(UUID.randomUUID().toString());
        eventMessage.setTitle(title);
        eventMessage.setDomainType(REPORT);
        eventMessage.setDomainId(reportId);
        return eventMessage;
    }

    /**
     * 提供公告事件消息模板
     *
     * @param noticeId
     * @param title
     * @return
     */
    public EventMessage generateSimpleNoticeEventMessage(Long noticeId, String title) {
        if (noticeId == null) {
            throw new EstateException(PARAM_NULL, "noticeId");
        }
        if (StringUtils.isEmpty(title)) {
            throw new EstateException(PARAM_NULL, "title");
        }
        EventMessage eventMessage = new EventMessage();
        eventMessage.setUuid(UUID.randomUUID().toString());
        eventMessage.setTitle(title);
        eventMessage.setDomainType(DomainType.NOTICE);
        eventMessage.setDomainId(noticeId);
        return eventMessage;
    }

    @Transactional
    public boolean produceMessage(EventMessage eventMessage) {
        if (eventMessage == null) {
            throw new EstateException(PARAM_NULL, "eventMessage");
        }
        return eventMessageHandler(eventMessage);
    }

    @Transactional
    public boolean produceMessage(Message message) {
        if (message == null) {
            throw new EstateException(PARAM_NULL, "message");
        }
        return createMessage(message.getTitle(),
                message.getSummary(),
                message.getDomainId(),
                message.getDomainType(),
                message.getSenderId(),
                message.getReceiverId());
    }

    @Transactional
    public boolean consumeMessage(EventMessage eventMessage) {
        if (eventMessage == null) {
            throw new EstateException(PARAM_NULL, "eventMessage");
        }
        // 重复消息处理
        if (getEventMessageByUUID(eventMessage.getUuid()) != null) {
            logger.warn("消息[{}]已经存在，忽略！消息内容为：{}", eventMessage.getUuid(), eventMessage.toString());
            return true;
        }
        return eventMessageHandler(eventMessage);
    }

    private Message getEventMessageByUUID(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new EstateException(PARAM_NULL, "UUID");
        }
        return messageRepository.getEventMessageByUUID(uuid);
    }

    /**
     * 事件消息处理
     *
     * @param eventMessage
     * @return
     */
    private boolean eventMessageHandler(EventMessage eventMessage) {
        if (eventMessage.getDomainType() == null) {
            throw new EstateException(PARAM_NULL, "domainType");
        }
        switch (eventMessage.getDomainType()) {
            case FANG:
                List<Favorite> fangFollowers = favoriteService.getFavorite(eventMessage.getDomainType(),
                        eventMessage.getDomainId());
                if (CollectionUtils.isEmpty(fangFollowers)) {
                    logger.info("房源[{}]没有关注者，不需要发送消息", eventMessage.getDomainId());
                    return true;
                }
                fangFollowers.forEach(t -> {
                    createMessage(eventMessage.getTitle(),
                            null,
                            eventMessage.getDomainId(),
                            eventMessage.getDomainType(),
                            adminUserUtils.getSenderIdByDomainType(FANG),
                            t.getFollowerId());
                });
                return true;
            case XIAO_QU:
                List<Favorite> xiaoQuFollowers = favoriteService.getFavorite(eventMessage.getDomainType(),
                        eventMessage.getDomainId());
                if (CollectionUtils.isEmpty(xiaoQuFollowers)) {
                    logger.info("房源[{}]没有关注者，不需要发送消息", eventMessage.getDomainId());
                    return true;
                }
                xiaoQuFollowers.forEach(t -> {
                    createMessage(eventMessage.getTitle(),
                            null,
                            eventMessage.getDomainId(),
                            eventMessage.getDomainType(),
                            adminUserUtils.getSenderIdByDomainType(XIAO_QU),
                            t.getFollowerId());
                });
                return true;
            case REPORT:
                //TODO 指定的目标客户
                return true;
            case NOTICE:
                //TODO 指定的目标客户
                return true;
            default:
                //TODO 指定的目标客户
                return true;
        }
    }
}
