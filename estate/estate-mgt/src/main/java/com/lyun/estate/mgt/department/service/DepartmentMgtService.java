package com.lyun.estate.mgt.department.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.entity.DepartmentDTO;
import com.lyun.estate.biz.department.service.DepartmentService;
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
 * Created by Jeffrey on 2017-02-16.
 */
@Service
public class DepartmentMgtService {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private MgtContext mgtContext;
    @Autowired
    private PermissionCheckService permissionCheckService;

    @Transactional
    public Department create(Department department) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);

        department.setCompanyId(mgtContext.getOperator().getCompanyId());
        Department result = departmentService.create(department);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, result.getId(), DomainType.DEPARTMENT,
                        AuditHelper.operatorName(mgtContext) + "新增了一个名为【" + result.getName() + "】的部门")
        );
        return result;
    }

    @Transactional
    public Object updateInfo(Department department) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);

        Department result = departmentService.updateInfo(department);

        permissionCheckService.checkCompany(result.getCompanyId());

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, result.getId(), DomainType.DEPARTMENT,
                        AuditHelper.operatorName(mgtContext) + "修改了部门【" + result.getName() + "】的信息")
        );
        return result;
    }

    public PageList<DepartmentDTO> listByCompanyIdPageBounds(Long companyId, PageBounds pageBounds) {
        if (companyId == null) {
            companyId = mgtContext.getOperator().getCompanyId();
        } else {
            permissionCheckService.checkCompany(companyId);
        }
        return departmentService.selectByCompanyId(companyId, pageBounds);
    }

    public List<DepartmentDTO> listSorted() {
        return departmentService.listSortedByCompanyId(mgtContext.getOperator().getCompanyId());
    }

    @Transactional
    public Department changeParent(Long departmentId, Long parentId) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);
        Department result = departmentService.changeParent(departmentId, parentId);
        permissionCheckService.checkCompany(result.getCompanyId());

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, result.getId(), DomainType.DEPARTMENT,
                        AuditHelper.operatorName(mgtContext) + "对【" + result.getName() + "】进行了部门调动")
        );
        return result;
    }

    public Department selectById(Long departmentId) {
        return departmentService.selectById(departmentId);
    }

    @Transactional
    public Object deleteById(Long id) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);
        Department needDelete = departmentService.selectById(id);
        if (needDelete == null) {
            return false;
        }
        permissionCheckService.checkCompany(needDelete.getCompanyId());
        Boolean result = departmentService.deleteById(id);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, needDelete.getId(), DomainType.DEPARTMENT,
                        AuditHelper.operatorName(mgtContext) + "删除了名为【" + needDelete.getName() + "】的部门")
        );
        return result;
    }
}
