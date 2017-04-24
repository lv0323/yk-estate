package com.lyun.estate.biz.permission.entity;

import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.permission.def.GrantScope;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.support.def.DomainType;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-04-10.
 */
public class Grant {
    private Long id;
    private Long targetId;
    private DomainType targetType;
    private Permission permission;
    private PermissionDefine.Category category;
    private Integer limits;
    private GrantScope scope;
    private Long grantById;
    private Date createTime;
    private Date updateTime;
    private Long updateById;
    private boolean isDeleted;

    public Long getId() {
        return id;
    }

    public Grant setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Grant setTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    public DomainType getTargetType() {
        return targetType;
    }

    public Grant setTargetType(DomainType targetType) {
        this.targetType = targetType;
        return this;
    }

    public Permission getPermission() {
        return permission;
    }

    public Grant setPermission(Permission permission) {
        this.permission = permission;
        return this;
    }

    public PermissionDefine.Category getCategory() {
        return category;
    }

    public Grant setCategory(PermissionDefine.Category category) {
        this.category = category;
        return this;
    }

    public GrantScope getScope() {
        return scope;
    }

    public Grant setScope(GrantScope scope) {
        this.scope = scope;
        return this;
    }

    public Long getGrantById() {
        return grantById;
    }

    public Grant setGrantById(Long grantById) {
        this.grantById = grantById;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Grant setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Grant setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Grant setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Integer getLimits() {
        return limits;
    }

    public Grant setLimits(Integer limits) {
        this.limits = limits;
        return this;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public Grant setUpdateById(Long updateById) {
        this.updateById = updateById;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("id", id)
                .add("targetId", targetId)
                .add("targetType", targetType)
                .add("permission", permission)
                .add("category", category)
                .add("limits", limits)
                .add("scope", scope)
                .add("isDeleted", isDeleted)
                .toString();
    }
}
