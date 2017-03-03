package com.lyun.estate.biz.message.entity;

import com.lyun.estate.biz.message.def.MessageStatus;
import com.lyun.estate.biz.support.def.DomainType;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class MessageResource {
    private Long id;
    private String title;
    private String summary;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    @Override
    public String toString() {
        return "MessageResource{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", domainId=" + domainId +
                ", domainType=" + domainType +
                ", data='" + data + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
