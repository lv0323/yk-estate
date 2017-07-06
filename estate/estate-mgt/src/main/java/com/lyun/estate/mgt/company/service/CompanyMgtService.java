package com.lyun.estate.mgt.company.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.domain.CompanyDTO;
import com.lyun.estate.biz.company.domain.CompanySigningDTO;
import com.lyun.estate.biz.company.domain.CreateCompanyInfo;
import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.entity.CompanySigning;
import com.lyun.estate.biz.company.service.CompanyService;
import com.lyun.estate.biz.company.service.CompanySigningService;
import com.lyun.estate.biz.department.entity.DepartmentDTO;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.domain.EmployeeDTO;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jeffrey on 2017-05-15.
 */
@Service
public class CompanyMgtService {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanySigningService companySigningService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PermissionCheckService permissionCheckService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private MgtContext mgtContext;

    @Transactional
    public Company createCompany(CreateCompanyInfo info) {
        permissionCheckService.checkExist(Permission.CREATE_FRANCHISEE);

        Company company = companyService.createCompany(info, mgtContext.getOperator().getId());

        auditService.save(
                AuditHelper.build(
                        mgtContext, AuditSubject.FRANCHISEE, company.getId(), DomainType.FRANCHISEE,
                        AuditHelper.operatorName(mgtContext) + "创建了加盟商" + info.getAbbr()
                )
        );

        return company;
    }

    public List<Company> getParents() {
        if (mgtContext.getOperator().getCompanyType() == CompanyDefine.Type.YK) {
            return companyService.findYkRaCompany();
        } else if (mgtContext.getOperator().getCompanyType() == CompanyDefine.Type.REGIONAL_AGENT) {
            return Lists.newArrayList(companyService.findOne(mgtContext.getOperator().getCompanyId()));
        } else {
            return Lists.newArrayList();
        }
    }

    public List<DepartmentDTO> findDepartment(Long companyId) {
        permissionCheckService.checkCompany(companyId);

        return departmentService.listSortedByCompanyId(companyId);
    }

    public List<EmployeeDTO> findPartAIds(Long companyId, Long departmentId) {
        permissionCheckService.checkCompany(companyId);

        return employeeService.listByCompanyIdDepartmentId(companyId, departmentId, new PageBounds(1, 100));
    }

    public PageList<CompanyDTO> list(Long cityId, Long parentId, CompanyDefine.Type companyType,
                                     PageBounds pageBounds) {
        permissionCheckService.checkExist(Permission.LIST_FRANCHISEE);

        if (parentId == null) {
            if (mgtContext.getOperator().getCompanyType() != CompanyDefine.Type.YK) {
                parentId = mgtContext.getOperator().getCompanyId();
            }
        } else {
            permissionCheckService.checkCompany(parentId);
        }

        return companyService.list(cityId, parentId, companyType, pageBounds);
    }

    public PageList<CompanySigningDTO> lisSigningByCompanyId(Long companyId, PageBounds pageBounds) {
        if (companyId == null) {
            companyId = mgtContext.getOperator().getCompanyId();
        } else {
            permissionCheckService.checkCompany(companyId);
        }
        return companySigningService.listByCompanyId(companyId, pageBounds);
    }

    @Transactional
    public Company updateInfo(Company company) {
        permissionCheckService.checkExist(Permission.MODIFY_FRANCHISEE);
        permissionCheckService.checkCompany(company.getId());

        auditService.save(
                AuditHelper.build(
                        mgtContext, AuditSubject.FRANCHISEE, company.getId(), DomainType.FRANCHISEE,
                        AuditHelper.operatorName(mgtContext) + "修改了加盟商信息" + company.toString()
                )
        );
        return companyService.updateInfo(company);
    }

    @Transactional
    public Company updateBoss(Long companyId, Long bossId) {
        permissionCheckService.checkExist(Permission.MODIFY_FRANCHISEE);
        permissionCheckService.checkCompany(companyId);
        auditService.save(
                AuditHelper.build(
                        mgtContext, AuditSubject.FRANCHISEE, companyId, DomainType.FRANCHISEE,
                        AuditHelper.operatorName(mgtContext) + "修改了加盟商负责人为：" + bossId
                )
        );

        return companyService.updateBoss(companyId, bossId);
    }

    @Transactional
    public CompanySigning renewSigning(CompanySigning signing) {
        permissionCheckService.checkExist(Permission.MODIFY_FRANCHISEE);
        permissionCheckService.checkCompany(signing.getCompanyId());
        auditService.save(
                AuditHelper.build(
                        mgtContext, AuditSubject.FRANCHISEE, signing.getCompanyId(), DomainType.FRANCHISEE,
                        AuditHelper.operatorName(mgtContext) + "与进行了续签：" + signing.toString()
                )
        );
        return companyService.renewSigning(signing);
    }

    @Transactional
    public CompanySigning updateSigningInfo(CompanySigning signing) {
        permissionCheckService.checkExist(Permission.MODIFY_FRANCHISEE);
        CompanySigning oldSigning = companySigningService.findOne(signing.getId());
        permissionCheckService.checkCompany(oldSigning.getCompanyId());

        auditService.save(
                AuditHelper.build(
                        mgtContext, AuditSubject.FRANCHISEE, oldSigning.getCompanyId(), DomainType.FRANCHISEE,
                        AuditHelper.operatorName(mgtContext) + "修改了签约信息：" + signing.toString()
                )
        );

        return companySigningService.updateSigningInfo(signing);
    }

    @Transactional
    public Boolean deleteSigning(Long signingId) {
        permissionCheckService.checkExist(Permission.MODIFY_FRANCHISEE);
        CompanySigning signing = companySigningService.findOne(signingId);
        permissionCheckService.checkCompany(signing.getCompanyId());

        auditService.save(
                AuditHelper.build(
                        mgtContext, AuditSubject.FRANCHISEE, signing.getCompanyId(), DomainType.FRANCHISEE,
                        AuditHelper.operatorName(mgtContext) + "删除了签约信息：" + signingId
                )
        );

        return companySigningService.deleteSigning(signingId);
    }

    public CompanyDTO findDTO(Long id) {
        permissionCheckService.checkExist(Permission.LIST_FRANCHISEE);
        permissionCheckService.checkCompany(id);
        return companyService.findDTO(id);
    }

    public CompanyDTO findSelfDTO() {
        long id = mgtContext.getOperator().getCompanyId();
        return companyService.findDTO(id);
    }
}
