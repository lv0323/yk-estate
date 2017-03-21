package com.lyun.estate.biz.agent.entity;

import com.lyun.estate.biz.support.def.Gender;

/**
 * Created by Jeffrey on 2017-03-21.
 */
public class Agent {
    private Long employeeId;
    private String name;
    private Gender gender;
    private String mobile;
    private String openContact;
    private Long avatarId;
    private String avatarURI;
    private Integer showingCount;

    public Long getEmployeeId() {
        return employeeId;
    }

    public Agent setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Agent setName(String name) {
        this.name = name;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Agent setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Agent setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getOpenContact() {
        return openContact;
    }

    public Agent setOpenContact(String openContact) {
        this.openContact = openContact;
        return this;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public Agent setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
        return this;
    }

    public String getAvatarURI() {
        return avatarURI;
    }

    public Agent setAvatarURI(String avatarURI) {
        this.avatarURI = avatarURI;
        return this;
    }

    public Integer getShowingCount() {
        return showingCount;
    }

    public Agent setShowingCount(Integer showingCount) {
        this.showingCount = showingCount;
        return this;
    }
}
