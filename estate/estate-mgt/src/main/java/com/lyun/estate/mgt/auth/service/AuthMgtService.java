package com.lyun.estate.mgt.auth.service;

import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.entity.Audit;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.auth.def.SaltSugar;
import com.lyun.estate.mgt.context.MgtContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jeffrey on 2017-02-16.
 */
@Service
public class AuthMgtService {

    private final EmployeeService employeeService;

    private final AuditService auditService;

    private final MgtContext mgtContext;

    public AuthMgtService(EmployeeService employeeService, AuditService auditService,
                          MgtContext mgtContext) {
        this.employeeService = employeeService;
        this.auditService = auditService;
        this.mgtContext = mgtContext;
    }

    public Boolean active(String mobile, String password, String secretKey) {
        return employeeService.active(mobile, password, secretKey);
    }

    public SaltSugar saltSugar(String mobile) {
        Employee employee = employeeService.selectByMobile(mobile);
        if (employee == null) {
            throw new EstateException(ExCode.EMPLOYEE_NOT_FOUND);
        }
        return new SaltSugar()
                .setSalt(employee.getSalt())
                .setSugar(employeeService.sugar(mobile));
    }

    @Transactional
    public Employee login(String mobile, String password) {
        Employee employee = employeeService.login(mobile, password);
        auditService.save(
                new Audit().setCompanyId(employee.getCompanyId())
                        .setDepartmentId(employee.getDepartmentId())
                        .setOperatorId(employee.getId())
                        .setSubject(AuditSubject.LOGIN_OUT)
                        .setTargetId(employee.getId())
                        .setDomainType(DomainType.EMPLOYEE)
                        .setContent("从IP:" + mgtContext.getUserAddress() + "登录成功,浏览器为：" + mgtContext.getBrowserName())
        );
        return employee;
    }

    @Transactional
    public Boolean logout() {
        auditService.save(
                new Audit().setCompanyId(mgtContext.getOperator().getCompanyId())
                        .setDepartmentId(mgtContext.getOperator().getDepartmentId())
                        .setOperatorId(mgtContext.getOperator().getId())
                        .setSubject(AuditSubject.LOGIN_OUT)
                        .setTargetId(mgtContext.getOperator().getId())
                        .setDomainType(DomainType.EMPLOYEE)
                        .setContent("从IP:" + mgtContext.getUserAddress() + "登出成功")
        );
        return true;
    }

    public SaltSugar saltSugarById(Long id) {
        Employee employee = employeeService.selectById(id);
        if (employee == null) {
            throw new EstateException(ExCode.EMPLOYEE_NOT_FOUND);
        }
        return new SaltSugar()
                .setSalt(employee.getSalt())
                .setSugar(employeeService.sugarById(id));
    }
}
