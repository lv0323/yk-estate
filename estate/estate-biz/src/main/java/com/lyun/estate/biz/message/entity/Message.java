package com.lyun.estate.biz.message.entity;

import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.message.def.ContentType;
import com.lyun.estate.biz.message.def.MessageStatus;
import com.lyun.estate.biz.support.def.DomainType;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class Message {
    private Long id;
    private String title;
    private String content;
    private ContentType contentType;
    private Long domainId;
    private DomainType domainType;
    private Long senderId;
    private Long receiverId;
    private MessageStatus status;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Message setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getDomainId() {
        return domainId;
    }

    public Message setDomainId(Long domainId) {
        this.domainId = domainId;
        return this;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public Message setDomainType(DomainType domainType) {
        this.domainType = domainType;
        return this;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Message setSenderId(Long senderId) {
        this.senderId = senderId;
        return this;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public Message setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public Message setStatus(MessageStatus status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Message setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Message setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public Message setContentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("content", content)
                .add("contentType", contentType)
                .add("domainId", domainId)
                .add("domainType", domainType)
                .add("senderId", senderId)
                .add("receiverId", receiverId)
                .add("status", status)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}
