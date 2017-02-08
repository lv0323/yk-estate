package com.lyun.estate.biz.message.entity;

import com.lyun.estate.biz.spec.common.DomainType;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class EventMessage {
    private Long id;
    private String title;
    private Long domainId;
    private DomainType domainType;
    private Date createTime;
    private Date updateTime;
    private String uuid;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", domainId=" + domainId +
                ", domainType=" + domainType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
