package com.lyun.estate.biz.fang.entity;

import com.google.common.base.MoreObjects;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class FangContact {
    private Long id;
    private Long fangId;
    private String name;
    private String mobile;
    private String aMobile;
    private String bMobile;
    private String qq;
    private String weChat;
    private String email;
    private Boolean isDeleted;
    private Date createTime;
    private Date updateTime;

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

    public String getName() {
        return name;
    }

    public FangContact setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public FangContact setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getaMobile() {
        return aMobile;
    }

    public FangContact setaMobile(String aMobile) {
        this.aMobile = aMobile;
        return this;
    }

    public String getbMobile() {
        return bMobile;
    }

    public FangContact setbMobile(String bMobile) {
        this.bMobile = bMobile;
        return this;
    }

    public String getQq() {
        return qq;
    }

    public FangContact setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public String getWeChat() {
        return weChat;
    }

    public FangContact setWeChat(String weChat) {
        this.weChat = weChat;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public FangContact setEmail(String email) {
        this.email = email;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public FangContact setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FangContact setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FangContact setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("id", id)
                .add("fangId", fangId)
                .add("name", name)
                .add("mobile", mobile)
                .add("aMobile", aMobile)
                .add("bMobile", bMobile)
                .add("qq", qq)
                .add("weChat", weChat)
                .add("email", email)
                .add("isDeleted", isDeleted)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}
