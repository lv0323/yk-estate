package com.lyun.estate.biz.dianping.entity;

import com.lyun.estate.biz.dianping.domain.CorpStatus;

/**
 * Created by localuser on 2017/5/15.
 */
public class Corp {

    private long id;

    private String name;

    private CorpStatus status;

    private long commentCount;

    private long positiveCount;

    private long negativeCount;

    private long visitCount;

    private boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CorpStatus getStatus() {
        return status;
    }

    public void setStatus(CorpStatus status) {
        this.status = status;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public long getPositiveCount() {
        return positiveCount;
    }

    public void setPositiveCount(long positiveCount) {
        this.positiveCount = positiveCount;
    }

    public long getNegativeCount() {
        return negativeCount;
    }

    public void setNegativeCount(long negativeCount) {
        this.negativeCount = negativeCount;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(long visitCount) {
        this.visitCount = visitCount;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
