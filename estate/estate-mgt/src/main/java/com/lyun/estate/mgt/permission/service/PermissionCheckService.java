package com.lyun.estate.mgt.permission.service;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.fang.domian.FangInfoOwnerDTO;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.permission.service.GrantService;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.biz.support.def.DomainType;
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
    private MgtContext mgtContext;

    @Autowired
    private GrantService grantService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SettingProvider settingProvider;

    private List<Long> bizAdminIdList = new ArrayList<>();

    private List<Permission> selfOperateAllowed = Lists.newArrayList(
            Permission.VIEW_FANG_CONTACT,
            Permission.MODIFY_FANG_CONTACT,
            Permission.FANG_PUBLISH,
            Permission.FANG_PAUSE,
            Permission.FANG_UN_PUBLISH,
            Permission.FANG_RE_PUBLISH,
            Permission.FANG_APPLY_PUBLIC,
            Permission.FANG_UNDO_PUBLIC);

    private Logger logger = LoggerFactory.getLogger(PermissionCheckService.class);

    @PostConstruct
    private void init() {
        String bizAdminIdsStr = settingProvider.find(NameSpace.CONFIG, "BIZ_ADMIN_IDS").getValue();
        Arrays.stream(bizAdminIdsStr.split(",")).forEach(t -> bizAdminIdList.add(Long.valueOf(t)));
    }

    public void check(long targetId, DomainType domainType, Permission permission) {
        Operator operator = mgtContext.getOperator();
        if (permission.getCategory() == PermissionDefine.Category.FANG) {
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

    public void checkExist(Permission permission) {
//        Operator operator = mgtContext.getOperator();
//        if (operator.getSysAdmin()) {
//            return;
//        }
//        Grant grant = grantService.getEmployeeGrantsMap(operator.getId()).get(permission);
//        if (grant == null) {
//            throw new EstateException(ExCode.PERMISSION_NULL, permission.getLabel());
//        }
        return;
    }

    public void checkLimit(Permission permission) {
//        Operator operator = mgtContext.getOperator();
//        if (operator.getSysAdmin()) {
//            return;
//        }
//        Grant grant = grantService.getEmployeeGrantsMap(operator.getId()).get(permission);
//        if (grant == null) {
//            throw new EstateException(ExCode.PERMISSION_NULL, permission.getLabel());
//        }
//
//        Employee employee = employeeService.findById(operator.getId());
//
//        if (permission == Permission.VIEW_SELL_CONTACT_LIMIT) {
//            if (employee.getLastSellCountTime() != null && employee.getLastSellCountTime().toInstant()
//                    .isAfter(LocalDate.now().atStartOfDay().atZone(CommonUtil.defaultZone()).toInstant())
//                    && (Optional.ofNullable(employee.getSellContactCount()).orElse(0) >= grant.getLimits())) {
//                throw new EstateException(ExCode.PERMISSION_OUT_LIMIT, permission.getLabel());
//            }
//        } else if (permission == Permission.VIEW_RENT_CONTACT_LIMIT) {
//            if (employee.getLastRentCountTime() != null && employee.getLastRentCountTime().toInstant()
//                    .isAfter(LocalDate.now().atStartOfDay().atZone(CommonUtil.defaultZone()).toInstant())
//                    && (Optional.ofNullable(employee.getRentContactCount()).orElse(0) >= grant.getLimits())) {
//                throw new EstateException(ExCode.PERMISSION_OUT_LIMIT, permission.getLabel());
//            }
//        }
        return;
    }
}
