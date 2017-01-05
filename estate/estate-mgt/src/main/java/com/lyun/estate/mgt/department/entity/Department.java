package com.lyun.estate.mgt.department.entity;

public class Department {

    private Long id;
    private Long parentId;
    private Long companyId;
    private String name;
    private String shortName;
    private String telephone;
    private String address;

    public Long getId() {
        return id;
    }

    public Department setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Department setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Department setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public Department setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public Department setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Department setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Department setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }
}
