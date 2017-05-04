package com.lyun.estate.biz.permission.service;

import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.permission.repo.GrantRepo;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.config.EstateCacheConfig;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
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

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    @Qualifier(EstateCacheConfig.MANAGER_10_5K)
    private CacheManager cacheManager;

    private Cache positionPages;

    private Cache employeeActions;
    private Logger logger = LoggerFactory.getLogger(GrantService.class);

    @PostConstruct
    private void init() {
        positionPages = cacheManager.getCache(EstateCacheConfig.POSITION_PAGES);
        employeeActions = cacheManager.getCache(EstateCacheConfig.EMPLOYEE_ACTIONS);
    }

    @SuppressWarnings("unchecked")
    public List<Grant> getGrantsByCategory(long targetId, DomainType targetType, PermissionDefine.Category category) {
        ExceptionUtil.checkNotNull("主体类型", targetType);
        ExceptionUtil.checkNotNull("权限分类", category);
        if (targetType == DomainType.POSITION && category == PermissionDefine.Category.PAGE) {
            List<Grant> cached = positionPages.get(targetId, List.class);
            if (cached != null) {
                return cached;
            } else {
                List<Grant> result = grantRepo.findGrantsByCategory(targetId, targetType, category);
                positionPages.putIfAbsent(targetId, result);
                return result;
            }
        } else {
            return grantRepo.findGrantsByCategory(targetId, targetType, category);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<Permission, Grant> getEmployeeGrantsMap(long employeeId) {
        Map<Permission, Grant> cached = employeeActions.get(employeeId, Map.class);
        if (cached != null) {
            return cached;
        } else {
            Map<Permission, Grant> result = new HashMap<>();
            List<Grant> grants = grantRepo.findGrants(employeeId, DomainType.EMPLOYEE);
            grants.forEach(t -> result.put(t.getPermission(), t));
            employeeActions.putIfAbsent(employeeId, result);
            return result;
        }
    }

    @Transactional
    public boolean regrantEmployeeByPosition(long employeeId, long positionId, long operatorId) {
        //evict cache
        employeeActions.evict(employeeId);

        grantRepo.delAll(employeeId, DomainType.EMPLOYEE, operatorId);
        List<Grant> grants = grantRepo.findPositionNotPageGrants(positionId);
        if (!CollectionUtils.isEmpty(grants)) {
            grants.forEach(
                    g -> {
                        Grant newGrant = new Grant();
                        newGrant.setTargetId(employeeId)
                                .setTargetType(DomainType.EMPLOYEE)
                                .setPermission(g.getPermission())
                                .setCategory(g.getPermission().getCategory())
                                .setScope(g.getScope())
                                .setLimits(g.getLimits())
                                .setGrantById(operatorId);
                        grantRepo.save(newGrant);
                    }
            );
        }
        return true;
    }


    @Transactional
    public boolean regrant(long targetId, DomainType targetType, PermissionDefine.Category category,
                           List<Grant> grants, long operatorId) {
        ExceptionUtil.checkIllegal(targetType == DomainType.EMPLOYEE || targetType == DomainType.POSITION,
                "主体类型", targetType);
        ExceptionUtil.checkIllegal(CollectionUtils.isEmpty(grants) || grants.stream().noneMatch(
                t -> t.getPermission() == null || t.getTargetId() == null || t.getTargetType() == null
        ), "授权信息", grants);

        if (targetType == DomainType.EMPLOYEE && category == PermissionDefine.Category.PAGE) {
            throw new EstateException(ExCode.PERMISSION_CATEGORY_NOT_SUPPORT, category.getLabel());
        }

        //evict cache
        if (targetType == DomainType.POSITION && category == PermissionDefine.Category.PAGE) {
            positionPages.evict(targetId);
        } else if (targetType == DomainType.EMPLOYEE) {
            employeeActions.evict(targetId);
        }

        grantRepo.delAllOfCategory(targetId, targetType, category, operatorId);

        if (!CollectionUtils.isEmpty(grants)) {
            grants.forEach(
                    g -> {
                        if (g.getPermission().getCategory() != category) {
                            logger.error("权限{}不属于类别{}", g.getPermission(), category);
                            throw new EstateException(ExCode.PERMISSION_CATEGORY_ERROR);
                        }
                        Grant newGrant = new Grant();
                        newGrant.setTargetId(targetId)
                                .setTargetType(targetType)
                                .setPermission(g.getPermission())
                                .setCategory(category)
                                .setScope(g.getScope())
                                .setLimits(g.getLimits())
                                .setGrantById(operatorId);
                        grantRepo.save(newGrant);
                    }
            );
        }
        return true;
    }

    @Transactional
    public boolean regrantByPosition(Long companyId, Long positionId, Long operatorId) {
        List<Employee> employees = employeeService.listByCompanyIdAndPositionId(companyId, positionId);
        List<Grant> grants = grantRepo.findPositionNotPageGrants(positionId);

        employees.forEach(
                t -> {
                    //evict cache
                    employeeActions.evict(t.getId());

                    grantRepo.delAll(t.getId(), DomainType.EMPLOYEE, operatorId);
                    if (!CollectionUtils.isEmpty(grants)) {
                        grants.forEach(
                                g -> {
                                    Grant newGrant = new Grant();
                                    newGrant.setTargetId(t.getId())
                                            .setTargetType(DomainType.EMPLOYEE)
                                            .setPermission(g.getPermission())
                                            .setCategory(g.getPermission().getCategory())
                                            .setScope(g.getScope())
                                            .setLimits(g.getLimits())
                                            .setGrantById(operatorId);
                                    grantRepo.save(newGrant);
                                }
                        );
                    }
                });
        return true;
    }
}
