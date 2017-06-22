package com.lyun.estate.biz.application;

import java.util.Date;

public class CommonApplicationEntity {
    private long id;
    private Type type;
    private Status status;

    private long applicantId;
    private String applyReason;

    private long reviewerId;
    private String reviewerComments;

    private long domainId;
    private String domainFrom;
    private String domainTo;

    private Date createTime;
    private Date update_time;

    public enum Type {
        PUBLISH_HOUSE,
        UN_PUBLISH_HOUSE,
        PAUSE_HOUSE,
        SUCCESS_HOUSE,

        UN_PUBLIC_HOUSE,
        PUBLIC_HOUSE,

        FANG_TAG_APPROVAL
    }

    public enum Status {
        NEW, APPROVED, REJECTED, CLOSED_BY_REVIEWER, CLOSED_BY_APPLICANT
    }

    public long getId() {
        return id;
    }

    public CommonApplicationEntity setId(long id) {
        this.id = id;
        return this;
    }

    public Type getType() {
        return type;
    }

    public CommonApplicationEntity setType(Type type) {
        this.type = type;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public CommonApplicationEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public CommonApplicationEntity setApplicantId(long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public CommonApplicationEntity setApplyReason(String applyReason) {
        this.applyReason = applyReason;
        return this;
    }

    public long getReviewerId() {
        return reviewerId;
    }

    public CommonApplicationEntity setReviewerId(long reviewerId) {
        this.reviewerId = reviewerId;
        return this;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public CommonApplicationEntity setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;
        return this;
    }

    public long getDomainId() {
        return domainId;
    }

    public CommonApplicationEntity setDomainId(long domainId) {
        this.domainId = domainId;
        return this;
    }

    public String getDomainFrom() {
        return domainFrom;
    }

    public CommonApplicationEntity setDomainFrom(String domainFrom) {
        this.domainFrom = domainFrom;
        return this;
    }

    public String getDomainTo() {
        return domainTo;
    }

    public CommonApplicationEntity setDomainTo(String domainTo) {
        this.domainTo = domainTo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CommonApplicationEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public CommonApplicationEntity setUpdate_time(Date update_time) {
        this.update_time = update_time;
        return this;
    }
}