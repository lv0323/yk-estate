package com.lyun.estate.biz.position.entity;

import com.lyun.estate.biz.position.def.PositionType;

import java.util.Date;

public class Position {

    private Long id;
    private Long companyId;
    private String name;
    private PositionType type;
    private String note;
    private Boolean isDeleted;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public Position setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Position setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Position setName(String name) {
        this.name = name;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Position setNote(String note) {
        this.note = note;
        return this;
    }

    public PositionType getType() {
        return type;
    }

    public Position setType(PositionType type) {
        this.type = type;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Position setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Position setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Position setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
