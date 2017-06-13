package com.lyun.estate.mgt.permission.service;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.context.MgtContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeffrey on 2017-05-10.
 */
@Service
public class CompanyPermissionService {

    private final Map<Permission, List<CompanyDefine.Type>> limitCompanyTypes = new HashMap<>();

    @Autowired
    private MgtContext mgtContext;

    @PostConstruct
    private void init() {
        limitCompanyTypes.put(Permission.CREATE_XIAO_QU, Lists.newArrayList(CompanyDefine.Type.YK));
        limitCompanyTypes.put(Permission.MODIFY_XIAO_QU, Lists.newArrayList(CompanyDefine.Type.YK));
        limitCompanyTypes.put(Permission.DEL_XIAO_QU, Lists.newArrayList(CompanyDefine.Type.YK));
        limitCompanyTypes.put(Permission.DEL_BUILDING, Lists.newArrayList(CompanyDefine.Type.YK));
        limitCompanyTypes.put(Permission.FANG_CONFIRM_PUBLIC, Lists.newArrayList(CompanyDefine.Type.YK));
        limitCompanyTypes.put(Permission.FANG_REJECT_PUBLIC, Lists.newArrayList(CompanyDefine.Type.YK));
        limitCompanyTypes.put(Permission.FANG_UNDO_PUBLIC, Lists.newArrayList(CompanyDefine.Type.YK));

        limitCompanyTypes.put(Permission.OP_MANAGE_XY, Lists.newArrayList(CompanyDefine.Type.YK));
        limitCompanyTypes.put(Permission.P_OPERATION, Lists.newArrayList(CompanyDefine.Type.YK));
        limitCompanyTypes.put(Permission.P_OPERATION_XY, Lists.newArrayList(CompanyDefine.Type.YK));


        limitCompanyTypes.put(Permission.P_FRANCHISEE,
                Lists.newArrayList(CompanyDefine.Type.YK, CompanyDefine.Type.REGIONAL_AGENT));
        limitCompanyTypes.put(Permission.P_FRANCHISEE_C,
                Lists.newArrayList(CompanyDefine.Type.YK, CompanyDefine.Type.REGIONAL_AGENT));
        limitCompanyTypes.put(Permission.P_FRANCHISEE_SS,
                Lists.newArrayList(CompanyDefine.Type.YK, CompanyDefine.Type.REGIONAL_AGENT));
        limitCompanyTypes.put(Permission.P_FRANCHISEE_RA,
                Lists.newArrayList(CompanyDefine.Type.YK, CompanyDefine.Type.REGIONAL_AGENT));
    }


    public Permission getByType(CompanyDefine.Type type) {
        switch (type) {
            case YK:
                return Permission.CT_YK;
            case CHANNEL:
                return Permission.CT_CHANNEL;
            case SINGLE_STORE:
                return Permission.CT_SS;
            case REGIONAL_AGENT:
                return Permission.CT_RA;
            default:
                return null;
        }
    }

    public void checkPermissionGrantableForCompanyType(List<Permission> permissions) {
        permissions.forEach(permission -> {
                    List<CompanyDefine.Type> supportsLimits = limitCompanyTypes.get(permission);
                    if (supportsLimits != null && !supportsLimits.contains(mgtContext.getOperator().getCompanyType())) {
                        throw new EstateException(ExCode.PERMISSION_COMPANY_TYPE_NOT_SUPPORT,
                                mgtContext.getOperator().getCompanyType(), permission);
                    }
                }
        );
    }
}
