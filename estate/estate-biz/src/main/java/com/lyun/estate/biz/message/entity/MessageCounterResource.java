package com.lyun.estate.biz.message.entity;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class MessageCounterResource {
    private Long id;
    private Long ownerId;
    private Integer uCMsCount;
    private Integer uCMReportCount;
    private Integer uNoticeCount;
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

    public Integer getuCMsCount() {
        return uCMsCount;
    }

    public void setuCMsCount(Integer uCMsCount) {
        this.uCMsCount = uCMsCount;
    }

    public Integer getuCMReportCount() {
        return uCMReportCount;
    }

    public void setuCMReportCount(Integer uCMReportCount) {
        this.uCMReportCount = uCMReportCount;
    }

    public Integer getuNoticeCount() {
        return uNoticeCount;
    }

    public void setuNoticeCount(Integer uNoticeCount) {
        this.uNoticeCount = uNoticeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MessageCounterResource{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", uCMsCount=" + uCMsCount +
                ", uCMReportCount=" + uCMReportCount +
                ", uNoticeCount=" + uNoticeCount +
                ", createTime=" + createTime +
                '}';
    }
}
