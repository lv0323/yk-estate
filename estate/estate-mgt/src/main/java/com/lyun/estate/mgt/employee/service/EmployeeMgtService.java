package com.lyun.estate.mgt.employee.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.auth.def.SaltSugar;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * Created by Jeffrey on 2017-02-16.
 */
@Service
public class EmployeeMgtService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private MgtContext mgtContext;
    @Autowired
    private PermissionCheckService permissionCheckService;


    @Transactional
    public Employee create(Employee employee) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);

        employee.setCompanyId(mgtContext.getOperator().getCompanyId());
        Employee result = employeeService.create(employee);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, result.getId(), DomainType.EMPLOYEE,
                        AuditHelper.operatorName(mgtContext) + "新增了一个【" + result.getDepartmentName() + "--" + result.getName() + "】员工")
        );
        return result;
    }

    public PageList<Employee> listByCompanyIdDepartmentId(Long companyId, Long departmentId, PageBounds pageBounds) {
        if (companyId == null) {
            companyId = mgtContext.getOperator().getCompanyId();
        } else {
            permissionCheckService.checkCompany(companyId);
        }

        return employeeService.listByCompanyIdDepartmentId(companyId, departmentId, pageBounds);
    }

    @Transactional
    public Employee update(Employee employee) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);
        Employee result = employeeService.update(employee);
        permissionCheckService.checkCompany(result.getCompanyId());
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, result.getId(), DomainType.EMPLOYEE,
                        AuditHelper.operatorName(mgtContext) + "修改了员工【" + result.getDepartmentName() + "--" + result.getName() + "】的信息")
        );
        return result;
    }


    @Transactional
    public Boolean quit(Long id) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);
        Employee needQuit = employeeService.selectById(id);
        if (needQuit == null) {
            return null;
        }
        permissionCheckService.checkCompany(needQuit.getCompanyId());
        Boolean result = employeeService.quit(id);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, needQuit.getId(), DomainType.EMPLOYEE,
                        AuditHelper.operatorName(mgtContext) + "离职了【" + needQuit.getDepartmentName() + "--" + needQuit.getName() + "】员工")
        );
        return result;
    }

    public String getAvatar() {
        return employeeService.getAvatarURI(mgtContext.getOperator().getId());
    }

    public String getUsername() {
        return mgtContext.getOperator().getName();
    }

    public FileDescription createAvatar(InputStream inputStream, String suffix) {
        return employeeService.createAvatar(mgtContext.getOperator().getId(), inputStream, suffix);
    }

    public Boolean changePassword(String sugaredPassword, String saltedNewPassword) {
        return employeeService.changePassword(mgtContext.getOperator().getId(), sugaredPassword, saltedNewPassword);
    }

    @Transactional
    @Deprecated
    public Boolean resetPassword(Long employeeId, String newPassword) {

        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);
        Employee employee = employeeService.selectById(employeeId);
        if (employee == null) {
            return false;
        }
        permissionCheckService.checkCompany(employee.getCompanyId());

        Boolean result = employeeService.resetPassword(employeeId, newPassword);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, employee.getId(), DomainType.EMPLOYEE,
                        AuditHelper.operatorName(mgtContext) + "重设了【" + employee.getDepartmentName() + "--" + employee.getName() + "】的登录密码")
        );
        return result;
    }

    public SaltSugar changePasswordSaltSugar() {
        return new SaltSugar().setSalt(employeeService.selectById(mgtContext.getOperator().getId()).getSalt())
                .setSugar(employeeService.sugarById(mgtContext.getOperator().getId()));
    }

    public Employee self() {
        return employeeService.selectById(mgtContext.getOperator().getId());
    }

    public Employee selfEdit(String openContact, String weChat) {
        return employeeService.updateContact(mgtContext.getOperator().getId(), openContact, weChat);
    }

    public Employee getById(Long id) {
        return employeeService.selectById(id);
    }

    @Transactional
    public boolean unbindDevice(Long id) {
        permissionCheckService.checkExist(Permission.UNBIND_DEVICE);

        Employee employee = employeeService.selectById(id);

        permissionCheckService.checkCompany(employee.getCompanyId());

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, employee.getId(), DomainType.EMPLOYEE,
                        AuditHelper.operatorName(mgtContext) + "解绑了【" + employee.getDepartmentName() + "--" + employee.getName() + "】的设备号")
        );
        return employeeService.updateDeviceId(id, "");
    }
}
