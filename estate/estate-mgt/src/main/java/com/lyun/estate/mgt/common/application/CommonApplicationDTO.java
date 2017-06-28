package com.lyun.estate.mgt.common.application;

import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.biz.employee.domain.EmployeeDTO;

import java.util.Date;

public class CommonApplicationDTO<T> {
    CommonApplicationEntity application;
    private EmployeeDTO applicant;
    private EmployeeDTO reviewer;
    private T domain;

    public CommonApplicationEntity getApplication() {
        return application;
    }

    public CommonApplicationDTO setApplication(CommonApplicationEntity application) {
        this.application = application;
        return this;
    }

    public T getDomain() {
        return domain;
    }

    public CommonApplicationDTO setDomain(T domain) {
        this.domain = domain;
        return this;
    }

    public EmployeeDTO getApplicant() {
        return applicant;
    }

    public CommonApplicationDTO setApplicant(EmployeeDTO applicant) {
        this.applicant = applicant;
        return this;
    }

    public EmployeeDTO getReviewer() {
        return reviewer;
    }

    public CommonApplicationDTO setReviewer(EmployeeDTO reviewer) {
        this.reviewer = reviewer;
        return this;
    }
}
