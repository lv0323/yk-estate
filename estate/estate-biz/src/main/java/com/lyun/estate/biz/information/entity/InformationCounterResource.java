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
    private Integer noticeIndex;
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

    public Integer getNoticeIndex() {
        return noticeIndex;
    }

    public void setNoticeIndex(Integer noticeIndex) {
        this.noticeIndex = noticeIndex;
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
                ", noticeIndex=" + noticeIndex +
                ", createTime=" + createTime +
                '}';
    }
}
