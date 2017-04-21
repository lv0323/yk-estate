package com.lyun.estate.biz.permission.service;

import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.permission.repo.GrantRepo;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeffrey on 2017-04-10.
 */
@Service
public class GrantService {

    @Autowired
    private GrantRepo grantRepo;

    public Grant getGrant(long targetId, DomainType targetType, Permission permission) {
        ExceptionUtil.checkNotNull("主体类型", targetType);
        ExceptionUtil.checkNotNull("权限", permission);
        return grantRepo.findGrant(targetId, targetType, permission);
    }

    public Map<Permission, Grant> getEmployeeGrants(long employeeId) {
        Map<Permission, Grant> result = new HashMap<>();
        List<Grant> grants = grantRepo.findGrants(employeeId, DomainType.EMPLOYEE);
        grants.forEach(t -> result.put(t.getPermission(), t));
        return result;
    }

    public List<Grant> getPositionGrants(long positionId) {
        return grantRepo.findGrants(positionId, DomainType.POSITION);
    }

    @Transactional
    public boolean regrantEmployeeByPositionId(long employeeId, long positionId, long operatorId) {
        grantRepo.delAll(employeeId, DomainType.EMPLOYEE, operatorId);
        List<Grant> grants = getPositionGrants(positionId);
        regrant(employeeId, DomainType.EMPLOYEE, grants, operatorId);
        return true;
    }


    @Transactional
    public boolean regrant(long targetId, DomainType targetType, List<Grant> grants, long operatorId) {
        ExceptionUtil.checkNotNull("主体类型", targetType);
        ExceptionUtil.checkIllegal(CollectionUtils.isEmpty(grants) || grants.stream().noneMatch(
                t -> t.getGrantById() == null || t.getPermission() == null || t.getTargetId() == null || t.getTargetType() == null
        ), "授权信息", grants);

        grantRepo.delAll(targetId, targetType, operatorId);

        if (!CollectionUtils.isEmpty(grants)) {
            grants.forEach(
                    g -> {
                        Grant newGrant = new Grant();
                        newGrant.setTargetId(targetId)
                                .setTargetType(targetType)
                                .setPermission(g.getPermission())
                                .setScope(g.getScope())
                                .setLimits(g.getLimits())
                                .setGrantById(operatorId);
                        grantRepo.save(newGrant);
                    }
            );
        }
        return true;
    }
}
