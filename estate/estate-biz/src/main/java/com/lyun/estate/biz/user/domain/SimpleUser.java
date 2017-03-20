package com.lyun.estate.biz.user.domain;

/**
 * Created by Jeffrey on 2017-03-20.
 */
public class SimpleUser {
    private long id;
    private String userName;
    private Long avatarId;
    private String avatarURI;

    public long getId() {
        return id;
    }

    public SimpleUser setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SimpleUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public SimpleUser setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
        return this;
    }

    public String getAvatarURI() {
        return avatarURI;
    }

    public SimpleUser setAvatarURI(String avatarURI) {
        this.avatarURI = avatarURI;
        return this;
    }
}
