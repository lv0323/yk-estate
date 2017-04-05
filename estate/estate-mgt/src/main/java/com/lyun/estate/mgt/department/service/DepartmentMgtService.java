package com.lyun.estate.mgt.department.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.entity.DepartmentDTO;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-16.
 */
@Service
public class DepartmentMgtService {

    private final DepartmentService departmentService;

    private final AuditService auditService;

    private final MgtContext mgtContext;

    public DepartmentMgtService(DepartmentService departmentService,
                                AuditService auditService,
                                MgtContext mgtContext) {
        this.departmentService = departmentService;
        this.auditService = auditService;
        this.mgtContext = mgtContext;
    }

    @Transactional
    public Department create(Department department) {
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
        Department result = departmentService.updateInfo(department);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, result.getId(), DomainType.DEPARTMENT,
                        AuditHelper.operatorName(mgtContext) + "修改了部门【" + result.getName() + "】的信息")
        );
        return result;
    }

    public PageList<DepartmentDTO> listByPageBounds(PageBounds pageBounds) {
        return departmentService.selectByCompanyId(mgtContext.getOperator().getCompanyId(), pageBounds);
    }

    public List<DepartmentDTO> listSorted() {
        return departmentService.listSortedByCompanyId(mgtContext.getOperator().getCompanyId());
    }

    @Transactional
    public Department changeParent(Long departmentId, Long parentId) {
        Department result = departmentService.changeParent(departmentId, parentId);
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
        Department needDelete = departmentService.selectById(id);
        if (needDelete == null) {
            return false;
        }
        Boolean result = departmentService.deleteById(id);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, needDelete.getId(), DomainType.DEPARTMENT,
                        AuditHelper.operatorName(mgtContext) + "删除了名为【" + needDelete.getName() + "】的部门")
        );
        return result;
    }
}
