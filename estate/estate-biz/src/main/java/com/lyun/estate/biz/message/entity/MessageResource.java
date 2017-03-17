package com.lyun.estate.biz.message.entity;

import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.message.def.ContentType;
import com.lyun.estate.biz.message.def.MessageStatus;
import com.lyun.estate.biz.support.def.DomainType;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class MessageResource {
    private Long id;
    private String title;
    private String content;
    private ContentType contentType;
    private Long domainId;
    private DomainType domainType;
    private String data;
    private MessageStatus status;
    private Date createTime;

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

    public String getContent() {
        return content;
    }

    public MessageResource setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public void setDomainType(DomainType domainType) {
        this.domainType = domainType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public MessageResource setContentType(ContentType contentType) {
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
                .add("data", data)
                .add("status", status)
                .add("createTime", createTime)
                .toString();
    }
}
