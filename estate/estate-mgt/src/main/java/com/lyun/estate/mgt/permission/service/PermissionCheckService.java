package com.lyun.estate.mgt.permission.service;

import com.lyun.estate.biz.fang.domian.FangInfoOwnerDTO;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.def.PermissionTarget;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.context.Operator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by Jeffrey on 2017-04-06.
 */
@Service
public class PermissionCheckService {
    @Autowired
    private MgtFangService mgtFangService;

    @Autowired
    private MgtContext mgtContext;

    private Logger logger = LoggerFactory.getLogger(PermissionCheckService.class);


    public void check(long targetId, Permission permission) {
        Operator operator = mgtContext.getOperator();
        if (permission.getTarget() == PermissionTarget.FANG) {
            List<FangInfoOwnerDTO> infoOwners = mgtFangService.getInfoOwners(targetId);
            if (infoOwners.stream().anyMatch(t -> Objects.equals(t.getEmployeeId(), operator.getId()))) {

            } else {
                throw new EstateException(ExCode.PERMISSION_ERROR);
            }
        } else {
            logger.error("未实现的权限校验，{},{},{}", operator.getId(), targetId, permission);
            throw new EstateException(ExCode.DEFAULT_EXCEPTION);
        }
    }

}
