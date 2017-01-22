package com.lyun.estate.biz.information.entity;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class InformationCounterResource {
    private Long id;
    private Long ownerId;
    private Integer unreadCInfoCount;
    private Integer unreadCMReportCount;
    private Integer unreadNoticeCount;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUnreadCInfoCount() {
        return unreadCInfoCount;
    }

    public void setUnreadCInfoCount(Integer unreadCInfoCount) {
        this.unreadCInfoCount = unreadCInfoCount;
    }

    public Integer getUnreadCMReportCount() {
        return unreadCMReportCount;
    }

    public void setUnreadCMReportCount(Integer unreadCMReportCount) {
        this.unreadCMReportCount = unreadCMReportCount;
    }

    public Integer getUnreadNoticeCount() {
        return unreadNoticeCount;
    }

    public void setUnreadNoticeCount(Integer unreadNoticeCount) {
        this.unreadNoticeCount = unreadNoticeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "InformationCounterResource{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", unreadCInfoCount=" + unreadCInfoCount +
                ", unreadCMReportCount=" + unreadCMReportCount +
                ", unreadNoticeCount=" + unreadNoticeCount +
                ", createTime=" + createTime +
                '}';
    }
}
