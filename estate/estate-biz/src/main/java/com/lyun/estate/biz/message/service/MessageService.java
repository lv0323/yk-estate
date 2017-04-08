package com.lyun.estate.biz.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.message.def.MessageStatus;
import com.lyun.estate.biz.message.entity.FangChangePriceBean;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.entity.MessageSummaryResource;
import com.lyun.estate.biz.message.repository.MessageRepository;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.rest.service.FangService;
import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.service.UserService;
import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

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
    private RestContext restContext;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;


    public List<MessageSummaryResource> getMessageSummary(Long receiverId) {
        if (receiverId == null) {
            throw new EstateException(PARAM_NULL, "receiverId");
        }
        List<MessageSummaryResource> summaryResourceList = messageRepository.getDistinctSender(receiverId);

        summaryResourceList.forEach(
                t -> {
                    Message lastMessage = messageRepository.getLastMessage(receiverId, t.getSenderId());
                    t.setLastMessageId(lastMessage.getId());
                    t.setLastMessageTitle(lastMessage.getTitle());
                    int unreadCount = messageRepository.getUnReadCount(receiverId, t.getSenderId());
                    t.setUnreadCount(unreadCount);
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
            switch (t.getContentType()) {
                case FANG_SUMMARY:
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
                case FANG_PROCESS:
                    FangSummary fangSummary = fangService.getSummary(t.getDomainId());
                    if (fangSummary == null) {
                        logger.warn("根据信息中存放的content[{}]无法获取有效房源", t.getDomainId());
                        break;
                    }
                    FangChangePriceBean changePriceBean = new FangChangePriceBean();
                    try {
                        BeanUtils.copyProperties(fangSummary, changePriceBean);
                        changePriceBean.setMessageContent(t.getContent());
                        t.setData(mapper.writeValueAsString(changePriceBean));
                    } catch (JsonProcessingException e) {
                        throw new EstateException(JSON_ERROR, changePriceBean.toString(), e.getMessage());
                    }
                    break;
                default:
                    t.setData(t.getDomainId().toString());
                    break;
            }
        });

        if (messageRepository.updateToRead(receiverId, senderId, lastMessageId) < 0) {
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
    public Message createMessage(Message message) {
        /* 创建message */
        ExceptionUtil.checkNotNull("消息", message);
        ExceptionUtil.checkNotNull("标题", message.getTitle());
        ExceptionUtil.checkNotNull("消息类型", message.getContentType());
        ExceptionUtil.checkNotNull("domainId", message.getDomainId());
        ExceptionUtil.checkNotNull("domainType", message.getDomainType());
        ExceptionUtil.checkNotNull("senderId", message.getSenderId());
        ExceptionUtil.checkNotNull("receiverId", message.getReceiverId());

        message.setStatus(MessageStatus.UNREAD);

        if (messageRepository.createMessage(message) > 0) {
            return messageRepository.findOne(message.getId());
        }
        throw new EstateException(CREATE_FAIL, "Message", message.toString());
    }


}
