package com.lyun.estate.biz.permission.entity;

import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.permission.def.GrantScope;
import com.lyun.estate.biz.permission.def.Permission;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-04-10.
 */
public class Grant {
    private Long id;
    private Long employeeId;
    private Permission permission;
    private GrantScope grantScope;
    private Long grantById;
    private Date createTime;
    private Date updateTime;
    private boolean isDeleted;

    public Long getId() {
        return id;
    }

    public Grant setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Grant setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Permission getPermission() {
        return permission;
    }

    public Grant setPermission(Permission permission) {
        this.permission = permission;
        return this;
    }

    public GrantScope getGrantScope() {
        return grantScope;
    }

    public Grant setGrantScope(GrantScope grantScope) {
        this.grantScope = grantScope;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employeeId", employeeId)
                .add("permission", permission)
                .add("grantScope", grantScope)
                .add("grantById", grantById)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .add("isDeleted", isDeleted)
                .toString();
    }
}
