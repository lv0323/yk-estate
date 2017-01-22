package com.lyun.estate.biz.information.entity;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class InformationCounter {
    private Long id;
    private Long ownerId;
    private Long CInfoIndex;
    private Long CMonthlyReportIndex;
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

    public Long getCInfoIndex() {
        return CInfoIndex;
    }

    public void setCInfoIndex(Long CInfoIndex) {
        this.CInfoIndex = CInfoIndex;
    }

    public Long getCMonthlyReportIndex() {
        return CMonthlyReportIndex;
    }

    public void setCMonthlyReportIndex(Long CMonthlyReportIndex) {
        this.CMonthlyReportIndex = CMonthlyReportIndex;
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
        return "InformationCounter{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", CInfoIndex=" + CInfoIndex +
                ", CMonthlyReportIndex=" + CMonthlyReportIndex +
                ", noticeIndex=" + noticeIndex +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
