package com.lyun.estate.biz.permission.service;

import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-04-10.
 */
@Service
public class GrantService {

    Grant getGrant(long employeeId, Permission permission) {
        ExceptionUtil.checkNotNull("权限", permission);
        return null;
    }

}
