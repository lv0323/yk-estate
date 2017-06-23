package com.lyun.estate.mgt.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.application.CommonApplicationService;
import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
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

import java.util.Date;
import java.util.List;

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

    @Autowired
    private CommonApplicationService commonApplicationService;

    @Transactional
    public Fang requestPublish(long fangId, String applyReason) {
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

        commonApplicationService.create(CommonApplicationEntity.Type.PUBLISH_HOUSE, infoOwner.getId(), applyReason, fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请上架授权编号为【" + fangBase.getLicenceId() + "】的房源")
        );
        return fangBase;
    }

    @Transactional
    public Fang requestUnPublish(long fangId, String applyReason) {
        permissionCheckService.checkScope(fangId, Permission.FANG_UN_PUBLISH);

        commonApplicationService.create(CommonApplicationEntity.Type.UN_PUBLISH_HOUSE, mgtContext.getOperator().getId(), applyReason, fangId);
        Fang fang = fangMgtService.getFangBase(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请下架授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public Fang requestPause(long fangId, String applyReason) {
        permissionCheckService.checkScope(fangId, Permission.FANG_PAUSE);

        commonApplicationService.create(CommonApplicationEntity.Type.PAUSE_HOUSE, mgtContext.getOperator().getId(), applyReason, fangId);
        Fang fang = fangMgtService.getFangBase(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请暂缓授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public Fang requestPublic(long fangId, String applyReason) {
        permissionCheckService.checkScope(fangId, Permission.FANG_APPLY_PUBLIC);

        processService.publicPreCheck(fangId);

        commonApplicationService.create(CommonApplicationEntity.Type.PUBLIC_HOUSE, mgtContext.getOperator().getId(), applyReason, fangId);
        Fang fang = fangMgtService.getFangBase(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请发布授权编号为【" + fang.getLicenceId() + "】的房源")
        );
        return fang;
    }

    @Transactional
    public Fang requestUndoPublic(long fangId, String applyReason) {
        permissionCheckService.checkScope(fangId, Permission.FANG_UNDO_PUBLIC);

        commonApplicationService.create(CommonApplicationEntity.Type.UN_PUBLIC_HOUSE, mgtContext.getOperator().getId(), applyReason, fangId);
        Fang fang = fangMgtService.getFangBase(fangId);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "申请撤销授权编号为【" + fang.getLicenceId() + "】房源的外网发布")
        );
        return fang;
    }

    @Transactional
    public void approve(long applicationId, String reviewerComments) {
        // todo check privilege??

        commonApplicationService.approve(applicationId, mgtContext.getOperator().getId(), reviewerComments);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_APPLICATION, applicationId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "批准了【" + applicationId + "】")
        );
    }

    @Transactional
    public void reject(long applicationId, String reviewerComments) {
        // todo check privilege??

        commonApplicationService.reject(applicationId, mgtContext.getOperator().getId(), reviewerComments);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_APPLICATION, applicationId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "拒绝了【" + applicationId + "】")
        );
    }

    @Transactional
    public void close(long applicationId, String reviewerComments) {
        // todo check privilege??

        commonApplicationService.close(applicationId, mgtContext.getOperator().getId(), reviewerComments);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_APPLICATION, applicationId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "关闭了【" + applicationId + "】")
        );
    }

    public List<CommonApplicationEntity> findApplications(CommonApplicationEntity.Type type, long id, long applicantId, CommonApplicationEntity.Status status, Date startTime, Date endTime, PageBounds pageBounds) {
        return commonApplicationService.findApplications(type, id, applicantId, status, startTime, endTime, pageBounds);
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
