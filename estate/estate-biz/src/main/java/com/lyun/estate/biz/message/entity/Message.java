package com.lyun.estate.biz.message.entity;

import com.lyun.estate.biz.message.def.MessageBusinessType;
import com.lyun.estate.biz.message.def.MessageContentType;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jesse on 2017/1/20.
 */
public class Message {
    private Long id;
    private String title;
    private String summary;
    private String content;
    private MessageContentType contentType;
    private MessageBusinessType businessType;
    private Long senderId;
    private Long receiverId;
    private Date createTime;
    private Date updateTime;
    private String uuid = UUID.randomUUID().toString();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageContentType getContentType() {
        return contentType;
    }

    public void setContentType(MessageContentType contentType) {
        this.contentType = contentType;
    }

    public MessageBusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(MessageBusinessType businessType) {
        this.businessType = businessType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", contentType=" + contentType +
                ", businessType=" + businessType +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
