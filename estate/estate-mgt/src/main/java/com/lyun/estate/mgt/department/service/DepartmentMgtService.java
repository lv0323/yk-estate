package com.lyun.estate.mgt.department.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.entity.Audit;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.entity.DepartmentDTO;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
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
        auditService.save(new Audit()
                .setCompanyId(mgtContext.getOperator().getCompanyId())
                .setDepartmentId(mgtContext.getOperator().getDepartmentId())
                .setOperatorId(mgtContext.getOperator().getId())
                .setSubject(AuditSubject.ORGANIZATION)
                .setTargetId(result.getId())
                .setDomainType(DomainType.DEPARTMENT)
                .setContent(
                        "【" + mgtContext.getOperator().getDepartmentName() + "--" + mgtContext.getOperator()
                                .getName() + "】新增了一个名为【" + result.getName() + "】的部门")
        );
        return result;
    }

    @Transactional
    public Object updateInfo(Department department) {
        Department result = departmentService.updateInfo(department);
        auditService.save(new Audit()
                .setCompanyId(mgtContext.getOperator().getCompanyId())
                .setDepartmentId(mgtContext.getOperator().getDepartmentId())
                .setOperatorId(mgtContext.getOperator().getId())
                .setSubject(AuditSubject.ORGANIZATION)
                .setTargetId(result.getId())
                .setDomainType(DomainType.DEPARTMENT)
                .setContent("【" + mgtContext.getOperator().getDepartmentName() + "--" + mgtContext.getOperator()
                        .getName() + "】修改了部门【" + result.getName() + "】的信息")
        );
        return result;
    }

    public PageList<DepartmentDTO> listByPageBounds(PageBounds pageBounds) {
        return departmentService.selectByCompanyId(mgtContext.getOperator().getCompanyId(), pageBounds);
    }

    public List<DepartmentDTO> listAll() {
        return departmentService.listAllByCompanyId(mgtContext.getOperator().getCompanyId());
    }

    @Transactional
    public Department changeParent(Long departmentId, Long parentId) {
        Department result = departmentService.changeParent(departmentId, parentId);
        auditService.save(new Audit()
                .setCompanyId(mgtContext.getOperator().getCompanyId())
                .setDepartmentId(mgtContext.getOperator().getDepartmentId())
                .setOperatorId(mgtContext.getOperator().getId())
                .setSubject(AuditSubject.ORGANIZATION)
                .setTargetId(result.getId())
                .setDomainType(DomainType.DEPARTMENT)
                .setContent("【" + mgtContext.getOperator().getDepartmentName() + "--" + mgtContext.getOperator()
                        .getName() + "】对【" + result.getName() + "】进行了部门调动")
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
        auditService.save(new Audit()
                .setCompanyId(mgtContext.getOperator().getCompanyId())
                .setDepartmentId(mgtContext.getOperator().getDepartmentId())
                .setOperatorId(mgtContext.getOperator().getId())
                .setSubject(AuditSubject.ORGANIZATION)
                .setTargetId(needDelete.getId())
                .setDomainType(DomainType.DEPARTMENT)
                .setContent("【" + mgtContext.getOperator().getDepartmentName() + "--" + mgtContext.getOperator()
                        .getName() + "】删除了名为【" + needDelete.getName() + "】的部门")
        );
        return result;
    }
}
