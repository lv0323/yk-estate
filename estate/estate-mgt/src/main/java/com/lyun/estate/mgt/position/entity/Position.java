package com.lyun.estate.mgt.position.entity;

public class Position {

    private Long id;
    private Long companyId;
    private String name;
    private String note;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;

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

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public Position setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }


    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    public Position setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
