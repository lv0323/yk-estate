package com.lyun.estate.mgt.grant.service;

import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.permission.service.GrantService;
import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.biz.position.service.PositionService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.permission.service.CompanyPermissionService;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jeffrey on 2017-04-24.
 */
@Service
public class GrantMgtService {

    @Autowired
    private GrantService grantService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private MgtContext mgtContext;

    @Autowired
    private PermissionCheckService permissionCheckService;

    @Autowired
    private CompanyPermissionService companyPermissionService;

    public List<Grant> getGrantsByCategory(Long targetId, DomainType targetType, PermissionDefine.Category category) {
        permissionCheckService.checkExist(Permission.PERMISSION_MANAGEMENT);

        checkTargetCompany(targetId, targetType);

        return grantService.getGrantsByCategory(targetId, targetType, category);
    }

    @Transactional
    public boolean regrant(Long targetId, DomainType targetType, PermissionDefine.Category category,
                           List<Grant> grantList) {
        permissionCheckService.checkExist(Permission.PERMISSION_MANAGEMENT);

        companyPermissionService.checkPermissionGrantableForCompanyType(
                grantList.stream().map(Grant::getPermission).collect(Collectors.toList()));

        String targetName = checkTargetCompany(targetId, targetType);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.PERMISSION, targetId, targetType,
                        AuditHelper.operatorName(mgtContext) +
                                "修改了" + targetType.getLabel() + "【" + targetName + "】【" + category.getLabel() + "】的权限")
        );

        return grantService.regrant(targetId, targetType, category, grantList, mgtContext.getOperator().getId());
    }


    @Transactional
    public boolean regrantEmployeeByPosition(Long employeeId, Long positionId) {
        permissionCheckService.checkExist(Permission.PERMISSION_MANAGEMENT);

        String employeeName = checkTargetCompany(employeeId, DomainType.EMPLOYEE);
        String positionName = checkTargetCompany(positionId, DomainType.POSITION);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.PERMISSION, employeeId, DomainType.EMPLOYEE,
                        AuditHelper.operatorName(mgtContext) +
                                "修改了员工【" + employeeName + "】的权限为岗位【" + positionName + "】的权限")
        );

        return grantService.regrantEmployeeByPosition(employeeId, positionId, mgtContext.getOperator().getId());
    }

    @Transactional
    public boolean regrantByPosition(Long positionId) {
        permissionCheckService.checkExist(Permission.PERMISSION_MANAGEMENT);
        String positionName = checkTargetCompany(positionId, DomainType.POSITION);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.PERMISSION, positionId, DomainType.POSITION,
                        AuditHelper.operatorName(mgtContext) +
                                "为岗位是【" + positionName + "】的员工更新了权限")
        );

        return grantService.regrantByPosition(mgtContext.getOperator().getCompanyId(),
                positionId,
                mgtContext.getOperator().getId());
    }


    private String checkTargetCompany(Long targetId, DomainType targetType) {
        if (targetType == DomainType.EMPLOYEE) {
            Employee employee = employeeService.findById(targetId);
            permissionCheckService.checkCompany(employee.getCompanyId());
            return employee.getName();
        } else if (targetType == DomainType.POSITION) {
            Position position = positionService.selectById(targetId);
            permissionCheckService.checkCompany(position.getCompanyId());
            return position.getName();
        }
        throw new EstateException("不支持该主体类型：" + targetType);
    }
}
