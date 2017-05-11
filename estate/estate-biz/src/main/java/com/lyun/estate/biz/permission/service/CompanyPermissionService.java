package com.lyun.estate.biz.permission.service;

import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.permission.def.Permission;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-05-10.
 */
@Service
public class CompanyPermissionService {

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
}
