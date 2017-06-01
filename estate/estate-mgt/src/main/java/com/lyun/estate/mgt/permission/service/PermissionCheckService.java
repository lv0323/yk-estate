package com.lyun.estate.mgt.permission.service;

import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.service.CompanyService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.fang.domian.FangInfoOwnerDTO;
import com.lyun.estate.biz.permission.def.GrantScope;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.permission.service.GrantService;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.context.Operator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-04-06.
 */
@Service
public class PermissionCheckService {
    @Autowired
    private MgtFangService mgtFangService;

    @Autowired
    private MgtContext mgtContext;

    @Autowired
    private GrantService grantService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyPermissionService companyPermissionService;

    private Logger logger = LoggerFactory.getLogger(PermissionCheckService.class);


    public boolean checkScope(long fangId, Permission permission) {
        Operator operator = mgtContext.getOperator();
        if (operator.getSysAdmin()) {
            return true;
        }
        if (permission.getCategory() == PermissionDefine.Category.FANG) {
            Grant grant = grantService.getEmployeeGrantsMap(operator.getId()).get(permission);
            if (grant != null && grant.getScope() != null && grant.getScope() != GrantScope.NONE) {
                GrantScope scope = grant.getScope();
                FangInfoOwnerDTO lastFangInfoOwner = mgtFangService.findLastFangInfoOwner(fangId);
                if (scope == GrantScope.SELF && Objects.equals(lastFangInfoOwner.getEmployeeId(), operator.getId())) {
                    return true;
                } else if (scope == GrantScope.DEPT && Objects.equals(lastFangInfoOwner.getDepartmentId(),
                        operator.getDepartmentId())) {
                    return true;
                } else if (scope == GrantScope.COMPANY && Objects.equals(lastFangInfoOwner.getCompanyId(),
                        operator.getCompanyId())) {
                    return true;
                }
            }
            throw new EstateException(ExCode.PERMISSION_ERROR);
        } else {
            logger.error("未实现的权限校验，{},{},{}", operator.getId(), fangId, permission);
            throw new EstateException(ExCode.DEFAULT_EXCEPTION);
        }
    }


    public boolean checkExist(Permission permission) {
        Operator operator = mgtContext.getOperator();
        if (operator.getSysAdmin()) {
            return true;
        }
        if ((permission == Permission.PERMISSION_MANAGEMENT
                || permission == Permission.VIEW_AUDIT_LOG)
                && operator.getBoss()) {
            return true;
        }

        Grant grant = grantService.getEmployeeGrantsMap(operator.getId()).get(permission);
        if (grant == null
                //历史原因，fix
                || grant.getScope() == GrantScope.NONE) {
            throw new EstateException(ExCode.PERMISSION_NOT_FOUND, permission.getLabel());
        }
        return true;
    }


    public boolean checkLimit(Permission permission) {
        Operator operator = mgtContext.getOperator();
        if (operator.getSysAdmin()) {
            return true;
        }
        Grant grant = grantService.getEmployeeGrantsMap(operator.getId()).get(permission);
        //grant == null , no limit
        if (grant == null) {
            return true;
        }

        Employee employee = employeeService.findById(operator.getId());

        if (permission == Permission.VIEW_SELL_CONTACT_LIMIT) {
            if (employee.getLastSellCountTime() != null
                    && employee.getLastSellCountTime().toInstant().isAfter(CommonUtil.startOfToday())
                    && (Optional.ofNullable(employee.getSellContactCount()).orElse(0) >= grant.getLimits())) {
                throw new EstateException(ExCode.PERMISSION_OUT_LIMIT, permission.getLabel());
            }
        } else if (permission == Permission.VIEW_RENT_CONTACT_LIMIT) {
            if (employee.getLastRentCountTime() != null
                    && employee.getLastRentCountTime().toInstant().isAfter(CommonUtil.startOfToday())
                    && (Optional.ofNullable(employee.getRentContactCount()).orElse(0) >= grant.getLimits())) {
                throw new EstateException(ExCode.PERMISSION_OUT_LIMIT, permission.getLabel());
            }
        }
        return true;
    }

    public boolean verifyPage(Permission permission) {
        Operator operator = mgtContext.getOperator();
        if (operator.getSysAdmin()) {
            return true;
        }
        if ((permission == Permission.P_CONFIG
                || permission == Permission.P_CONFIG_PAGE
                || permission == Permission.P_CONFIG_PERMISSION)
                && operator.getBoss()) {
            return true;
        }

        List<Grant> grants = grantService.getGrantsByCategory(operator.getPositionId(),
                DomainType.POSITION, PermissionDefine.Category.PAGE);
        return grants != null && grants.stream().anyMatch(t -> t.getPermission() == permission);
    }


    public boolean verifyCompanyType(Permission permission) {
        Operator operator = mgtContext.getOperator();
        if (operator.getSysAdmin()) {
            return true;
        }
        if (permission.getCategory() == PermissionDefine.Category.COMPANY_TYPE) {
            return Objects.equals(permission, companyPermissionService.getByType(operator.getCompanyType()));
        } else {
            return false;
        }
    }

    public boolean checkCompany(long companyId) {
        //1. operator in company
        if (Objects.equals(companyId, mgtContext.getOperator().getCompanyId())) {
            return true;
        }

        //2. operator in yk company
        if (mgtContext.getOperator().getCompanyType() == CompanyDefine.Type.YK) {
            return true;
        }

        //3. operator in parent company
        Company one = companyService.findOne(companyId);
        if (one != null && Objects.equals(one.getParentId(), mgtContext.getOperator().getCompanyId())) {
            return true;
        }

        throw new EstateException(ExCode.PERMISSION_COMPANY_ERROR);
    }
}
