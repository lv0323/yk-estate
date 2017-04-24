package com.lyun.estate.mgt.grant.service;

import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.permission.service.GrantService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jeffrey on 2017-04-24.
 */
@Service
public class GrantMgtService {

    @Autowired
    private GrantService grantService;

    @Autowired
    private MgtContext mgtContext;

    public List<Grant> getGrantsByCategory(Long targetId, DomainType targetType, PermissionDefine.Category category) {
        return grantService.getGrantsByCategory(targetId, targetType, category);
    }

    public boolean regrant(Long targetId, DomainType targetType, PermissionDefine.Category category,
                           List<Grant> grantList) {
        return grantService.regrant(targetId, targetType, category, grantList, mgtContext.getOperator().getId());
    }

    public boolean regrantEmployeeByPosition(Long employeeId, Long positionId) {
        return grantService.regrantEmployeeByPosition(employeeId, positionId, mgtContext.getOperator().getId());
    }
}
