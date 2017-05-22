package com.lyun.estate.mgt.company.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.domain.Company;
import com.lyun.estate.biz.company.entity.CreateCompanyInfo;
import com.lyun.estate.biz.company.service.CompanyService;
import com.lyun.estate.biz.department.entity.DepartmentDTO;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.entity.Employee;
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
        permissionCheckService.checkCompany(info.getParentId());

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
        permissionCheckService.checkExist(Permission.CREATE_FRANCHISEE);

        if (mgtContext.getOperator().getCompanyType() == CompanyDefine.Type.YK) {
            return companyService.findYkRaCompany();
        } else if (mgtContext.getOperator().getCompanyType() == CompanyDefine.Type.REGIONAL_AGENT) {
            return Lists.newArrayList(companyService.findOne(mgtContext.getOperator().getCompanyId()));
        } else {
            return Lists.newArrayList();
        }
    }

    public List<DepartmentDTO> findDepartment(Long companyId) {
        permissionCheckService.checkExist(Permission.CREATE_FRANCHISEE);
        permissionCheckService.checkCompany(companyId);

        return departmentService.listSortedByCompanyId(companyId);
    }

    public List<Employee> findPartAIds(Long companyId, Long departmentId) {
        permissionCheckService.checkExist(Permission.CREATE_FRANCHISEE);
        permissionCheckService.checkCompany(companyId);

        return employeeService.listByCompanyIdDepartmentId(companyId, departmentId, new PageBounds(1, 100));
    }
}
