package com.lyun.estate.biz.audit.entity;

import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.support.def.DomainType;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-15.
 */
public class AuditDTO {
    private Long id;
    private Long companyId;
    private Long departmentId;
    private Long operatorId;
    private AuditSubject subject;
    private Long targetId;
    private DomainType domainType;
    private String content;
    private Date createTime;
    private String departmentName;
    private String operatorName;

    public Long getId() {
        return id;
    }

    public AuditDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public AuditDTO setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public AuditDTO setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public AuditDTO setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public AuditSubject getSubject() {
        return subject;
    }

    public AuditDTO setSubject(AuditSubject subject) {
        this.subject = subject;
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public AuditDTO setTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public AuditDTO setDomainType(DomainType domainType) {
        this.domainType = domainType;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public AuditDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AuditDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public AuditDTO setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public AuditDTO setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }
}
