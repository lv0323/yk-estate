package com.lyun.estate.mgt.position.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.biz.position.service.PositionService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by Jeffrey on 2017-02-16.
 */
@Service
public class PositionMgtService {
    @Autowired
    private PositionService positionService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private MgtContext mgtContext;
    @Autowired
    private PermissionCheckService permissionCheckService;


    @Transactional
    public Position create(Position position) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);

        Objects.requireNonNull(position).setCompanyId(mgtContext.getOperator().getCompanyId());
        Position result = positionService.create(position);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, result.getId(), DomainType.POSITION,
                        AuditHelper.operatorName(mgtContext) +
                                "新增了一个名为【" + result.getName() + "】的岗位")
        );

        return result;
    }

    @Transactional
    public Boolean deleteById(Long id) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);
        Position needDelete = positionService.selectById(id);
        if (needDelete == null) {
            return false;
        }
        permissionCheckService.checkCompany(needDelete.getCompanyId());

        Boolean result = positionService.deleteById(id);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, needDelete.getId(), DomainType.POSITION,
                        AuditHelper.operatorName(mgtContext) +
                                "删除了名为【" + needDelete.getName() + "】的岗位")
        );
        return result;
    }

    @Transactional
    public Object update(Position position) {
        permissionCheckService.checkExist(Permission.ORG_MANAGEMENT);
        Position result = positionService.update(position);
        permissionCheckService.checkCompany(result.getCompanyId());
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.ORGANIZATION, result.getId(), DomainType.POSITION,
                        AuditHelper.operatorName(mgtContext) +
                                "修改了岗位【" + result.getName() + "】的信息")
        );
        return result;
    }

    public PageList<Position> listByPageBounds(PageBounds pageBounds) {
        return positionService.selectByCompanyId(mgtContext.getOperator().getCompanyId(), pageBounds);
    }

    public List<Position> listAllByCompanyId() {
        return positionService.listAllByCompanyId(mgtContext.getOperator().getCompanyId());
    }
}
