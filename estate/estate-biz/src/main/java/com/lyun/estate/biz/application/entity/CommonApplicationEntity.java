package com.lyun.estate.biz.application.entity;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

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
    private Date updateTime;

    public enum Type implements LabelEnum {
        PUBLISH_HOUSE("上架"),
        UN_PUBLISH_HOUSE("下架"),
        PAUSE_HOUSE("暂缓"),
        SUCCESS_HOUSE("成交"),

        UN_PUBLIC_HOUSE("撤销发布"),
        PUBLIC_HOUSE("发布外网"),

        FANG_TAG_APPROVAL("更改标签");
        private final String label;

        Type(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Status implements LabelEnum {
        NEW("待审批"), APPROVED("已通过"), REJECTED("已拒绝"), CLOSED("已关闭");

        private final String label;

        Status(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public CommonApplicationEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}