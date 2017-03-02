package com.lyun.estate.biz.favorite.entity;

import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.favorite.def.FavoriteType;
import com.lyun.estate.biz.support.def.DomainType;

import java.util.Date;

/**
 * Created by jesse on 2017/2/8.
 */
public class Favorite {
    private Long id;
    private Long followerId;
    private DomainType domainType;
    private FavoriteType type;
    private Long targetId;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public Favorite setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public Favorite setFollowerId(Long followerId) {
        this.followerId = followerId;
        return this;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public Favorite setDomainType(DomainType domainType) {
        this.domainType = domainType;
        return this;
    }

    public FavoriteType getType() {
        return type;
    }

    public Favorite setType(FavoriteType type) {
        this.type = type;
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Favorite setTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Favorite setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("followerId", followerId)
                .add("domainType", domainType)
                .add("type", type)
                .add("targetId", targetId)
                .add("createTime", createTime)
                .toString();
    }
}
