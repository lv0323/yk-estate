package com.lyun.estate.biz.approval.entity;

import com.lyun.estate.biz.approval.def.ApprovalDefine;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-06-12.
 */
public class Approval {
    private Long id;
    private Long applyId;
    private Long approverId;
    private ApprovalDefine.Type type;
    private ApprovalDefine.Status status;
    private String data;
    private String comment;
    private Date createTime;
    private Date approvalTime;
    private Date updateTime;
    private boolean isDeleted;

    public Long getId() {
        return id;
    }

    public Approval setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getApplyId() {
        return applyId;
    }

    public Approval setApplyId(Long applyId) {
        this.applyId = applyId;
        return this;
    }

    public Long getApproverId() {
        return approverId;
    }

    public Approval setApproverId(Long approverId) {
        this.approverId = approverId;
        return this;
    }

    public ApprovalDefine.Type getType() {
        return type;
    }

    public Approval setType(ApprovalDefine.Type type) {
        this.type = type;
        return this;
    }

    public ApprovalDefine.Status getStatus() {
        return status;
    }

    public Approval setStatus(ApprovalDefine.Status status) {
        this.status = status;
        return this;
    }

    public String getData() {
        return data;
    }

    public Approval setData(String data) {
        this.data = data;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Approval setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public Approval setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Approval setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Approval setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Approval setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
