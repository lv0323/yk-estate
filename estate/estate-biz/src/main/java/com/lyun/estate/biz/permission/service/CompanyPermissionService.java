package com.lyun.estate.biz.permission.service;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.permission.def.Permission;

import java.util.List;

/**
 * Created by Jeffrey on 2017-05-10.
 */
public class CompanyPermissionService {

    List<Permission> getByCompayType(CompanyDefine.Type type) {
        switch (type) {
            case YK:
                return Lists.newArrayList(Permission.CT_YK);
            case CHANNEL:
                return Lists.newArrayList(Permission.CT_CHANNEL);
            case SINGLE_STORE:
                return Lists.newArrayList(Permission.CT_SS);
            case REGIONAL_AGENT:
                return Lists.newArrayList(Permission.CT_RA);
            default:
                return Lists.newArrayList();
        }
    }
}
