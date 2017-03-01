package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.FollowType;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-28.
 */
public class FangFollow {
    private Long id;
    private Long fangId;
    private Long companyId;
    private Long departmentId;
    private Long employeeId;
    private FollowType followType;
    private String content;
    private Boolean isDeleted;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public FangFollow setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangFollow setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangFollow setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public FangFollow setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangFollow setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public FollowType getFollowType() {
        return followType;
    }

    public FangFollow setFollowType(FollowType followType) {
        this.followType = followType;
        return this;
    }

    public String getContent() {
        return content;
    }

    public FangFollow setContent(String content) {
        this.content = content;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public FangFollow setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FangFollow setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FangFollow setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "FangFollow{" +
                "id=" + id +
                ", fangId=" + fangId +
                ", employeeId=" + employeeId +
                ", followType=" + followType +
                ", content='" + content + '\'' +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
