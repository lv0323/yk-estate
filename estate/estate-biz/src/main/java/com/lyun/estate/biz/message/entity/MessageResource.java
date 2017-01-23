package com.lyun.estate.biz.message.entity;

import com.lyun.estate.biz.message.def.MessageBusinessType;
import com.lyun.estate.biz.message.def.MessageContentType;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class MessageResource {
    private Long id;
    private Long receiver;
    private String title;
    private String summary;
    private String content;
    private MessageContentType contentType;
    private MessageBusinessType businessType;
    private String data;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
