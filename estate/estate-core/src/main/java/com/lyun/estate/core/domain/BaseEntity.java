package com.lyun.estate.core.domain;

import com.lyun.estate.core.supports.types.YN;

import java.util.Date;

public class BaseEntity {
    private long id;
    private long createById;
    private Date createTime;
    private long updateById;
    private Date updateTime;
    private int version;
    private long ownerId;
    private YN isDeleted;

    public long getId() {
        return id;
    }

    public BaseEntity setId(long id) {
        this.id = id;
        return this;
    }

    public long getCreateById() {
        return createById;
    }

    public BaseEntity setCreateById(long createById) {
        this.createById = createById;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BaseEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public long getUpdateById() {
        return updateById;
    }

    public BaseEntity setUpdateById(long updateById) {
        this.updateById = updateById;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BaseEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public BaseEntity setVersion(int version) {
        this.version = version;
        return this;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public BaseEntity setOwnerId(long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public YN getIsDeleted() {
        return isDeleted;
    }

    public BaseEntity setIsDeleted(YN isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
}
