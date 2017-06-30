package com.lyun.estate.mgt.common.application;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.application.CommonApplicationService;
import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummary;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import com.lyun.estate.mgt.fang.service.FangMgtService;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommonApplicationMgtService {

    @Autowired
    private CommonApplicationService commonApplicationService;

    @Autowired
    private FangMgtService fangMgtService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private PermissionCheckService permissionCheckService;

    public CommonApplicationEntity request(CommonApplicationEntity.Type type, long applicantId, String applyReason, long domainId) {
        return commonApplicationService.create(type, applicantId, applyReason, domainId);
    }

    @Transactional
    public int approve(long applicationId, String reviewerComments, Boolean isForceApprove, final MgtContext mgtContext) {
        permissionCheckService.checkExist(Permission.FANG_APPLICATION);

        int result = commonApplicationService.approve(applicationId, mgtContext.getOperator().getId(), reviewerComments, isForceApprove);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_APPLICATION, applicationId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "批准了【" + applicationId + "】")
        );

        return result;
    }

    @Transactional
    public int reject(long applicationId, String reviewerComments, final MgtContext mgtContext) {
        permissionCheckService.checkExist(Permission.FANG_APPLICATION);

        int result = commonApplicationService.reject(applicationId, mgtContext.getOperator().getId(), reviewerComments);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_APPLICATION, applicationId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "拒绝了【" + applicationId + "】")
        );
        return result;
    }

    @Transactional
    public int close(long applicationId, String reviewerComments, final MgtContext mgtContext) {

        int result = commonApplicationService.close(applicationId, mgtContext.getOperator().getId(), reviewerComments);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_APPLICATION, applicationId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "关闭了【" + applicationId + "】")
        );

        return result;
    }

    public PageList<CommonApplicationDTO> findApplications(List<CommonApplicationEntity.Type> types, Long id, Long applicantId, List<CommonApplicationEntity.Status> status, Date startTime, Date endTime, PageBounds pageBounds) {

        PageList<CommonApplicationEntity> originPageList = commonApplicationService
                .findApplications(types, id, applicantId, status, startTime, endTime, pageBounds);

        PageList<CommonApplicationDTO> dtoPageList = new PageList(originPageList.getPaginator());
        dtoPageList.addAll(
                originPageList
                        .stream()
                        .map(this::generateDTO)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList())
        );

        return dtoPageList;
    }

    public Optional<CommonApplicationDTO> generateDTO(CommonApplicationEntity commonApplicationEntity) {

        switch (commonApplicationEntity.getType()) {
            case UN_PUBLIC_HOUSE:
            case PUBLIC_HOUSE:
            case UN_PUBLISH_HOUSE:
            case PUBLISH_HOUSE:
            case PAUSE_HOUSE:
            case SUCCESS_HOUSE:
                CommonApplicationDTO<MgtFangSummary> dto =
                        new CommonApplicationDTO<MgtFangSummary>(){{
                            setDomain(fangMgtService.getFangSummary(commonApplicationEntity.getDomainId()));
                            setApplication(commonApplicationEntity);
                            setApplicant(employeeService.selectDTOById(commonApplicationEntity.getApplicantId()));
                        }};
                if (commonApplicationEntity.getReviewerId() > 0) {
                    dto.setReviewer(employeeService.selectDTOById(commonApplicationEntity.getReviewerId()));
                }
                return Optional.of(dto);
            default:
                return Optional.empty();

        }

    }

}
