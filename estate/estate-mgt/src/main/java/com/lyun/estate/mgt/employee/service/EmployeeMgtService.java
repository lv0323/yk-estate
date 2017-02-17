package com.lyun.estate.mgt.employee.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.entity.Audit;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * Created by Jeffrey on 2017-02-16.
 */
@Service
public class EmployeeMgtService {
    private final EmployeeService employeeService;
    private final AuditService auditService;
    private final MgtContext mgtContext;

    public EmployeeMgtService(EmployeeService employeeService, AuditService auditService,
                              MgtContext mgtContext) {
        this.employeeService = employeeService;
        this.auditService = auditService;
        this.mgtContext = mgtContext;
    }

    @Transactional
    public Employee create(Employee employee) {
        employee.setCompanyId(mgtContext.getOperator().getCompanyId());
        Employee result = employeeService.create(employee);
        auditService.save(new Audit()
                .setCompanyId(mgtContext.getOperator().getCompanyId())
                .setDepartmentId(mgtContext.getOperator().getDepartmentId())
                .setOperatorId(mgtContext.getOperator().getId())
                .setSubject(AuditSubject.ORGANIZATION)
                .setTargetId(result.getId())
                .setDomainType(DomainType.EMPLOYEE)
                .setContent("【" + mgtContext.getOperator() + "】新增了一个名为【" + result.getName() + "】的员工")
        );
        return result;
    }

    public PageList<Employee> listByCompanyIdDepartmentId(Long departmentId, PageBounds pageBounds) {
        return employeeService.listByCompanyIdDepartmentId(mgtContext.getOperator().getCompanyId(),
                departmentId,
                pageBounds);
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee result = employeeService.update(employee);
        auditService.save(new Audit()
                .setCompanyId(mgtContext.getOperator().getCompanyId())
                .setDepartmentId(mgtContext.getOperator().getDepartmentId())
                .setOperatorId(mgtContext.getOperator().getId())
                .setSubject(AuditSubject.ORGANIZATION)
                .setTargetId(result.getId())
                .setDomainType(DomainType.EMPLOYEE)
                .setContent("【" + mgtContext.getOperator() + "】修改了员工【" + result.getName() + "】的信息")
        );
        return result;
    }


    @Transactional
    public Boolean quit(Long id) {
        Employee needQuit = employeeService.selectById(id);
        if (needQuit == null) {
            return null;
        }
        Boolean result = employeeService.quit(id);

        auditService.save(new Audit()
                .setCompanyId(mgtContext.getOperator().getCompanyId())
                .setDepartmentId(mgtContext.getOperator().getDepartmentId())
                .setOperatorId(mgtContext.getOperator().getId())
                .setSubject(AuditSubject.ORGANIZATION)
                .setTargetId(needQuit.getId())
                .setDomainType(DomainType.POSITION)
                .setContent("【" + mgtContext.getOperator() + "】将名为【" + needQuit.getName() + "】的员工离职")
        );
        return result;
    }

    public String getAvatar() {
        return employeeService.getAvatar(mgtContext.getOperator().getId());
    }

    public FileDescription createAvatar(InputStream inputStream, String suffix) {
        return employeeService.createAvatar(mgtContext.getOperator().getId(), inputStream, suffix);
    }
}
