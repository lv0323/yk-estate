package com.lyun.estate.mgt.permission.service;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.domian.FangInfoOwnerDTO;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.def.PermissionTarget;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.biz.support.settings.SettingProvider;
import com.lyun.estate.biz.support.settings.def.NameSpace;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.context.Operator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
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
    private SettingProvider settingProvider;

    @Autowired
    private MgtContext mgtContext;

    private List<Long> bizAdminIdList = new ArrayList<>();

    private List<Permission> selfOperateAllowed = Lists.newArrayList(
            Permission.VIEW_CONTACT,
            Permission.MODIFY_CONTACT,
            Permission.PUBLISH,
            Permission.PAUSE,
            Permission.UN_PUBLISH,
            Permission.RE_PUBLISH,
            Permission.APPLY_PUBLIC,
            Permission.UNDO_PUBLIC);

    private Logger logger = LoggerFactory.getLogger(PermissionCheckService.class);

    @PostConstruct
    private void init() {
        String bizAdminIdsStr = settingProvider.find(NameSpace.CONFIG, "BIZ_ADMIN_IDS").getValue();
        Arrays.stream(bizAdminIdsStr.split(",")).forEach(t -> bizAdminIdList.add(Long.valueOf(t)));
    }

    public void check(long targetId, Permission permission) {
        Operator operator = mgtContext.getOperator();
        if (permission.getTarget() == PermissionTarget.FANG) {
            //管理员都全部允许
            if (bizAdminIdList.contains(operator.getId())) {
                return;
            }

            if (selfOperateAllowed.contains(permission)) {
                List<FangInfoOwnerDTO> infoOwners = mgtFangService.getInfoOwners(targetId);
                if (infoOwners.stream().anyMatch(t -> Objects.equals(t.getEmployeeId(), operator.getId()))) {
                    return;
                }
            }
            throw new EstateException(ExCode.PERMISSION_ERROR);
        } else {
            logger.error("未实现的权限校验，{},{},{}", operator.getId(), targetId, permission);
            throw new EstateException(ExCode.DEFAULT_EXCEPTION);
        }
    }

}
