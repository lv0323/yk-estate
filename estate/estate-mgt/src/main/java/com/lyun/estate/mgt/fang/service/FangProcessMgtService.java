package com.lyun.estate.mgt.fang.service;

import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.fang.def.HouseProcess;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.biz.fang.service.FangProcessService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.context.Operator;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jeffrey on 2017-03-10.
 */
@Service
class FangProcessMgtService {

    @Autowired
    private FangMgtService fangMgtService;

    @Autowired
    private FangProcessService processService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private PermissionCheckService permissionCheckService;

    @Autowired
    private MgtContext mgtContext;

    @Transactional
    public Fang publish(long fangId) {
        Fang fangBase = fangMgtService.getFangBase(fangId);
        if (fangBase.getProcess() == HouseProcess.UN_PUBLISH) {
            permissionCheckService.checkScope(fangId, Permission.FANG_RE_PUBLISH);
        } else {
            permissionCheckService.checkScope(fangId, Permission.FANG_PUBLISH);
        }

        Operator operator = mgtContext.getOperator();
        FangInfoOwner infoOwner = new FangInfoOwner()
                .setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId())
                .setEmployeeId(operator.getId());

        Fang fang = processService.publish(fangId, infoOwner);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "上架了授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public Fang unPublish(long fangId) {
        permissionCheckService.checkScope(fangId, Permission.FANG_UN_PUBLISH);

        Fang fang = processService.unPublish(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "下架了授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public Fang pause(long fangId) {
        permissionCheckService.checkScope(fangId, Permission.FANG_PAUSE);

        Fang fang = processService.pause(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "暂缓了授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public Fang deal(long fangId) {
        Fang fang = processService.deal(fangId);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "成交了授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public boolean delete(long fangId) {
        Fang fang = processService.delete(fangId);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "删除了授权房源编号为【" + fang.getLicenceId() + "】信息")
        );
        return true;
    }

    @Transactional
    public Fang applyPublic(long fangId) {
        permissionCheckService.checkScope(fangId, Permission.FANG_APPLY_PUBLIC);

        Fang fang = processService.applyPublic(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请发布了授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public Fang confirmPublic(long fangId) {
        permissionCheckService.checkScope(fangId, Permission.FANG_CONFIRM_PUBLIC);

        Fang fang = processService.confirmPublic(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "通过了发布授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public Fang rejectPublic(long fangId) {
        permissionCheckService.checkScope(fangId, Permission.FANG_REJECT_PUBLIC);

        Fang fang = processService.rejectPublic(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "拒绝了发布授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public Fang undoPublic(long fangId) {
        permissionCheckService.checkScope(fangId, Permission.FANG_UNDO_PUBLIC);

        Fang fang = processService.undoPublic(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "撤销了授权编号为【" + fang.getLicenceId() + "】房源的外网发布")
        );
        return fang;
    }
}
