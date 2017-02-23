package com.lyun.estate.biz.audit.entity;

import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.support.def.DomainType;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-15.
 */
public class Audit {
    private Long id;
    private Long companyId;
    private Long departmentId;
    private Long operatorId;
    private AuditSubject subject;
    private Long targetId;
    private DomainType domainType;
    private String content;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public Audit setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Audit setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Audit setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public Audit setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public AuditSubject getSubject() {
        return subject;
    }

    public Audit setSubject(AuditSubject subject) {
        this.subject = subject;
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Audit setTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public Audit setDomainType(DomainType domainType) {
        this.domainType = domainType;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Audit setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Audit setContent(String content) {
        this.content = content;
        return this;
    }
}
