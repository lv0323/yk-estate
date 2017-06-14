package com.lyun.estate.biz.approval.domain;

import com.lyun.estate.biz.approval.def.ApprovalDefine;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-06-12.
 */
public class ApprovalDTO {
    private Long id;
    private Long applyId;
    private String applyName;
    private Long applyDeptId;
    private String applyDeptName;
    private Long applyCompanyId;
    private String applyCompanyShortName;
    private Long approverId;
    private String approverName;
    private Long approverDeptId;
    private String approverDeptName;
    private Long approverCompanyId;
    private String approverCompanyShortName;
    private ApprovalDefine.Type type;
    private ApprovalDefine.Status status;
    private String data;
    private String comment;
    private Date createTime;
    private Date approvalTime;
    private Date updateTime;
    private Leaving leaving;
    private BizTrip bizTrip;
    private ColdVisit coldVisit;
    private Signing signing;

    public Long getId() {
        return id;
    }

    public ApprovalDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getApplyId() {
        return applyId;
    }

    public ApprovalDTO setApplyId(Long applyId) {
        this.applyId = applyId;
        return this;
    }

    public String getApplyName() {
        return applyName;
    }

    public ApprovalDTO setApplyName(String applyName) {
        this.applyName = applyName;
        return this;
    }

    public Long getApplyDeptId() {
        return applyDeptId;
    }

    public ApprovalDTO setApplyDeptId(Long applyDeptId) {
        this.applyDeptId = applyDeptId;
        return this;
    }

    public String getApplyDeptName() {
        return applyDeptName;
    }

    public ApprovalDTO setApplyDeptName(String applyDeptName) {
        this.applyDeptName = applyDeptName;
        return this;
    }

    public Long getApplyCompanyId() {
        return applyCompanyId;
    }

    public ApprovalDTO setApplyCompanyId(Long applyCompanyId) {
        this.applyCompanyId = applyCompanyId;
        return this;
    }

    public String getApplyCompanyShortName() {
        return applyCompanyShortName;
    }

    public ApprovalDTO setApplyCompanyShortName(String applyCompanyShortName) {
        this.applyCompanyShortName = applyCompanyShortName;
        return this;
    }

    public Long getApproverId() {
        return approverId;
    }

    public ApprovalDTO setApproverId(Long approverId) {
        this.approverId = approverId;
        return this;
    }

    public String getApproverName() {
        return approverName;
    }

    public ApprovalDTO setApproverName(String approverName) {
        this.approverName = approverName;
        return this;
    }

    public Long getApproverDeptId() {
        return approverDeptId;
    }

    public ApprovalDTO setApproverDeptId(Long approverDeptId) {
        this.approverDeptId = approverDeptId;
        return this;
    }

    public String getApproverDeptName() {
        return approverDeptName;
    }

    public ApprovalDTO setApproverDeptName(String approverDeptName) {
        this.approverDeptName = approverDeptName;
        return this;
    }

    public Long getApproverCompanyId() {
        return approverCompanyId;
    }

    public ApprovalDTO setApproverCompanyId(Long approverCompanyId) {
        this.approverCompanyId = approverCompanyId;
        return this;
    }

    public String getApproverCompanyShortName() {
        return approverCompanyShortName;
    }

    public ApprovalDTO setApproverCompanyShortName(String approverCompanyShortName) {
        this.approverCompanyShortName = approverCompanyShortName;
        return this;
    }

    public ApprovalDefine.Type getType() {
        return type;
    }

    public ApprovalDTO setType(ApprovalDefine.Type type) {
        this.type = type;
        return this;
    }

    public ApprovalDefine.Status getStatus() {
        return status;
    }

    public ApprovalDTO setStatus(ApprovalDefine.Status status) {
        this.status = status;
        return this;
    }

    public String getData() {
        return data;
    }

    public ApprovalDTO setData(String data) {
        this.data = data;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ApprovalDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public ApprovalDTO setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public ApprovalDTO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Leaving getLeaving() {
        return leaving;
    }

    public ApprovalDTO setLeaving(Leaving leaving) {
        this.leaving = leaving;
        return this;
    }

    public BizTrip getBizTrip() {
        return bizTrip;
    }

    public ApprovalDTO setBizTrip(BizTrip bizTrip) {
        this.bizTrip = bizTrip;
        return this;
    }

    public ColdVisit getColdVisit() {
        return coldVisit;
    }

    public ApprovalDTO setColdVisit(ColdVisit coldVisit) {
        this.coldVisit = coldVisit;
        return this;
    }

    public Signing getSigning() {
        return signing;
    }

    public ApprovalDTO setSigning(Signing signing) {
        this.signing = signing;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public ApprovalDTO setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
