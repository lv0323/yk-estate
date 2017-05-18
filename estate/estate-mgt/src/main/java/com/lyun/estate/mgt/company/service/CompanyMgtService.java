package com.lyun.estate.mgt.company.service;

import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.company.domain.Company;
import com.lyun.estate.biz.company.entity.CreateCompanyInfo;
import com.lyun.estate.biz.company.service.CompanyService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jeffrey on 2017-05-15.
 */
@Service
public class CompanyMgtService {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private MgtContext mgtContext;

    @Transactional
    public Company createCompany(CreateCompanyInfo info) {
        Company company = companyService.createCompany(info, mgtContext.getOperator().getId());

        auditService.save(
                AuditHelper.build(
                        mgtContext, AuditSubject.FRANCHISEE, company.getId(), DomainType.FRANCHISEE,
                        AuditHelper.operatorName(mgtContext) + "创建了加盟商" + info.getAbbr()
                )
        );

        return company;
    }
}
