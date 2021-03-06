package com.lyun.estate.op.dianping.comment.domain;

import com.lyun.estate.op.dianping.comment.entity.Comment;

import java.util.List;

/**
 * Created by localuser on 2017/5/15.
 */
public class CommentDTO {

    private long id;

    private String content;

    private long positiveCount;

    private String createTime;

    private long userId;

    private String nicky;

    private String avatar;

    private long corpId;

    private String corpName;

    private boolean liked;

    private List<String> tags;

    private CommentDTO(){}

    public CommentDTO(Comment raw){
        this.id = raw.getId();
        this.content = raw.getContent();
        this.positiveCount = raw.getPositiveCount();
        this.createTime = raw.getCreateTime();
        this.userId = raw.getUserId();
        this.nicky = raw.getNicky();
        this.avatar = raw.getAvatar();
        this.corpId = raw.getCorpId();
        this.corpName = raw.getCorpName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPositiveCount() {
        return positiveCount;
    }

    public void setPositiveCount(long positiveCount) {
        this.positiveCount = positiveCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNicky() {
        return nicky;
    }

    public void setNicky(String nicky) {
        this.nicky = nicky;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getCorpId() {
        return corpId;
    }

    public void setCorpId(long corpId) {
        this.corpId = corpId;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
