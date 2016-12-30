package com.lyun.estate.biz.user.domain;

import com.lyun.estate.core.domain.BaseEntity;

public class User extends BaseEntity {
    private String userName;
    private String realName;
    private String cardId;
    private String salt;
    private String hash;
    private String email;
    private String mobile;
    private int clientId;
    private String description;


    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public User setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getCardId() {
        return cardId;
    }

    public User setCardId(String cardId) {
        this.cardId = cardId;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public User setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public User setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public int getClientId() {
        return clientId;
    }

    public User setClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }


}
