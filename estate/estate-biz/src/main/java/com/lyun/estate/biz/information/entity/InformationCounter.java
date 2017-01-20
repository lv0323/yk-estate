package com.lyun.estate.biz.information.entity;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class InformationCounter {
    private Long id;
    private Long ownerId;
    private Integer communityInfoIndex;
    private Integer communityMonthlyReportIndex;
    private Integer noticeIndex;
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

    public Integer getCommunityInfoIndex() {
        return communityInfoIndex;
    }

    public void setCommunityInfoIndex(Integer communityInfoIndex) {
        this.communityInfoIndex = communityInfoIndex;
    }

    public Integer getCommunityMonthlyReportIndex() {
        return communityMonthlyReportIndex;
    }

    public void setCommunityMonthlyReportIndex(Integer communityMonthlyReportIndex) {
        this.communityMonthlyReportIndex = communityMonthlyReportIndex;
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
                ", communityInfoIndex=" + communityInfoIndex +
                ", communityMonthlyReportIndex=" + communityMonthlyReportIndex +
                ", noticeIndex=" + noticeIndex +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
