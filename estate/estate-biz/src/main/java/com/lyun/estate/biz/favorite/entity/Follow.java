package com.lyun.estate.biz.favorite.entity;

import com.lyun.estate.biz.support.def.DomainType;

import java.util.Date;

/**
 * Created by jesse on 2017/2/8.
 */
public class Follow {
    private Long id;
    private Long followerId;
    private DomainType domainType;
    private Long targetId;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public void setDomainType(DomainType domainType) {
        this.domainType = domainType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", followerId=" + followerId +
                ", domainType=" + domainType +
                ", targetId=" + targetId +
                ", createTime=" + createTime +
                '}';
    }
}
