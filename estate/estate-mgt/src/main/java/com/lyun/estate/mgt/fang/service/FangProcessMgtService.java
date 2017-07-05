package com.lyun.estate.mgt.fang.service;

import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.fang.def.HouseProcess;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.biz.fang.service.FangProcessService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.common.application.CommonApplicationMgtService;
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
public class FangProcessMgtService {

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

    @Autowired
    private CommonApplicationMgtService commonApplicationMgtService;

    @Transactional
    public CommonApplicationEntity requestPublish(long fangId, String applyReason) {
        Fang fangBase = fangMgtService.getFangBase(fangId);
        if (fangBase.getProcess() == HouseProcess.UN_PUBLISH) {
            permissionCheckService.checkScope(fangId, Permission.FANG_RE_PUBLISH);
        } else {
            permissionCheckService.checkScope(fangId, Permission.FANG_PUBLISH);
        }

        if (fangBase.getProcess() == HouseProcess.PAUSE) {
            Operator operator = mgtContext.getOperator();
            FangInfoOwner infoOwner = new FangInfoOwner(){{
                setCompanyId(operator.getCompanyId());
                setDepartmentId(operator.getDepartmentId());
                setEmployeeId(operator.getId());
            }};

            processService.publish(fangBase.getId(), infoOwner);

            auditService.save(
                    AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                            AuditHelper.operatorName(mgtContext) +
                                    "有效了授权编号为【" + fangBase.getLicenceId() + "】的房源")
            );

            return new CommonApplicationEntity(){{
                setApplicantId(-1);
            }};

        } else {
            CommonApplicationEntity commonApplicationEntity = commonApplicationMgtService.request(CommonApplicationEntity.Type.PUBLISH_HOUSE, mgtContext.getOperator().getId(), applyReason, fangId);

            auditService.save(
                    AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                            AuditHelper.operatorName(mgtContext) +
                                    "申请有效授权编号为【" + fangBase.getLicenceId() + "】的房源")
            );
            return commonApplicationEntity;
        }

    }

    @Transactional
    public CommonApplicationEntity requestUnPublish(long fangId, String applyReason) {
        permissionCheckService.checkScope(fangId, Permission.FANG_UN_PUBLISH);

        CommonApplicationEntity commonApplicationEntity = commonApplicationMgtService.request(CommonApplicationEntity.Type.UN_PUBLISH_HOUSE, mgtContext.getOperator().getId(), applyReason, fangId);
        Fang fang = fangMgtService.getFangBase(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请无效授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return commonApplicationEntity;
    }

    @Transactional
    public CommonApplicationEntity requestPause(long fangId, String applyReason) {
        permissionCheckService.checkScope(fangId, Permission.FANG_PAUSE);

        CommonApplicationEntity commonApplicationEntity = commonApplicationMgtService.request(CommonApplicationEntity.Type.PAUSE_HOUSE, mgtContext.getOperator().getId(), applyReason, fangId);
        Fang fang = fangMgtService.getFangBase(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请暂缓授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return commonApplicationEntity;
    }

    @Transactional
    public CommonApplicationEntity requestPublic(long fangId, String applyReason) {
        permissionCheckService.checkScope(fangId, Permission.FANG_APPLY_PUBLIC);

        processService.publicPreCheck(fangId);

        CommonApplicationEntity commonApplicationEntity = commonApplicationMgtService.request(CommonApplicationEntity.Type.PUBLIC_HOUSE, mgtContext.getOperator().getId(), applyReason, fangId);
        Fang fang = fangMgtService.getFangBase(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请发布授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return commonApplicationEntity;
    }

    @Transactional
    public CommonApplicationEntity requestUndoPublic(long fangId, String applyReason) {
        permissionCheckService.checkScope(fangId, Permission.FANG_UNDO_PUBLIC);

        CommonApplicationEntity commonApplicationEntity = commonApplicationMgtService.request(CommonApplicationEntity.Type.UN_PUBLIC_HOUSE, mgtContext.getOperator().getId(), applyReason, fangId);
        Fang fang = fangMgtService.getFangBase(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请撤销授权编号为【" + fang.getLicenceId() + "】房源的外网发布")
        );
        return commonApplicationEntity;
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

}
