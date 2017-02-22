package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.ContactType;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class FangContact {
    private Long id;
    private Long fangId;
    private String ownerName;
    private ContactType contactType;
    private String contactInfo;

    public Long getId() {
        return id;
    }

    public FangContact setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangContact setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public FangContact setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public FangContact setContactType(ContactType contactType) {
        this.contactType = contactType;
        return this;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public FangContact setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    @Override
    public String toString() {
        return "FangContact{" +
                "id=" + id +
                ", fangId=" + fangId +
                ", ownerName='" + ownerName + '\'' +
                ", contactType=" + contactType +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
