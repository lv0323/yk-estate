package com.lyun.estate.mgt.position.entity;

public class Position {

    private Long id;
    private Long companyId;
    private String name;
    private String note;

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
}
