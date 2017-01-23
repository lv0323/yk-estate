package com.lyun.estate.biz.message.entity;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class MessageCounter {
    private Long id;
    private Long ownerId;
    private Long CMsIndex;
    private Long CMReportIndex;
    private Long noticeIndex;
    private Date createTime;
    private Date updateTime;

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

    public Long getCMsIndex() {
        return CMsIndex;
    }

    public void setCMsIndex(Long CMsIndex) {
        this.CMsIndex = CMsIndex;
    }

    public Long getCMReportIndex() {
        return CMReportIndex;
    }

    public void setCMReportIndex(Long CMReportIndex) {
        this.CMReportIndex = CMReportIndex;
    }

    public Long getNoticeIndex() {
        return noticeIndex;
    }

    public void setNoticeIndex(Long noticeIndex) {
        this.noticeIndex = noticeIndex;
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

    @Override
    public String toString() {
        return "MessageCounter{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", CMsIndex=" + CMsIndex +
                ", CMReportIndex=" + CMReportIndex +
                ", noticeIndex=" + noticeIndex +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
