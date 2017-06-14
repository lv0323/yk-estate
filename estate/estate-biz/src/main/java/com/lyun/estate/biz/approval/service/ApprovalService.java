package com.lyun.estate.biz.approval.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.entity.Approval;
import com.lyun.estate.biz.approval.repo.ApprovalRepo;
import com.lyun.estate.biz.company.service.CompanyService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by Jeffrey on 2017-06-14.
 */
@Service
public class ApprovalService {

    @Autowired
    private ApprovalRepo approvalRepo;

    @Autowired
    private CompanyService companyService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger logger = LoggerFactory.getLogger(ApprovalService.class);


    public Approval create(Approval approval) {
        ExceptionUtil.checkNotNull("申请人编号", approval.getApplyId());
        ExceptionUtil.checkNotNull("审批类型", approval.getType());
        ExceptionUtil.checkIllegal(validate(approval.getType(), approval.getData()), "审批数据", approval.getData());

        approvalRepo.create(approval);

        return approvalRepo.findOne(approval.getId());
    }

    private boolean validate(ApprovalDefine.Type type, String data) {
        switch (type) {
            case LEAVING:
                ApprovalDefine.Leaving leaving = readFromData(data, ApprovalDefine.Leaving.class);
                ExceptionUtil.checkNotNull("外出", leaving);
                ExceptionUtil.checkNotNull("外出-", leaving);
                break;
            case BIZ_TRIP:
                ApprovalDefine.BizTrip bizTrip = readFromData(data, ApprovalDefine.BizTrip.class);
                ExceptionUtil.checkNotNull("", bizTrip);
                break;
            case COLD_VISIT:
                ApprovalDefine.ColdVisit coldVisit = readFromData(data, ApprovalDefine.ColdVisit.class);
                ExceptionUtil.checkNotNull("", coldVisit);
                break;
            case SIGNING:
                ApprovalDefine.Signing signing = readFromData(data, ApprovalDefine.Signing.class);
                ExceptionUtil.checkNotNull("", signing);
                break;
            default:
                return false;
        }
        return true;
    }

    private <T> T readFromData(String data, Class<T> clazz) {
        if (data != null) {
            try {
                return objectMapper.readValue(data, clazz);
            } catch (IOException e) {
                logger.warn("object mapper read value error");
            }
        }
        return null;
    }

    public PageList<Approval> list(ApprovalDefine.Type type,
                                   ApprovalDefine.Status status,
                                   Long applyCompanyId,
                                   Long applyDeptId,
                                   Long applyId,
                                   PageBounds pageBounds) {
        return approvalRepo.list(type, status, applyCompanyId, applyDeptId, applyId, pageBounds);
    }

    @Transactional
    public Approval approve(Long approvalId, Long operatorId, ApprovalDefine.Status status) {
        ExceptionUtil.checkNotNull("审批编号", approvalId);
        ExceptionUtil.checkIllegal(status == ApprovalDefine.Status.APPROVED || status == ApprovalDefine.Status.REJECTED,
                "审批结果", status);
        Approval oneForUpdate = approvalRepo.findOneForUpdate(approvalId);
        if (!oneForUpdate.getType().isNeedApproval()) {
            throw new EstateException(ExCode.APPROVAL_APPROVED, oneForUpdate.getStatus());
        }

        if (oneForUpdate.getStatus().isEnd()) {
            throw new EstateException(ExCode.APPROVAL_APPROVED, oneForUpdate.getStatus());
        }

        if (oneForUpdate.getType() == ApprovalDefine.Type.SIGNING && status == ApprovalDefine.Status.APPROVED) {

//            approvalRepo.updateStatusAndData(approvalId, operatorId, status);
        } else {
//            approvalRepo.updateStatus(approvalId, operatorId, status);
        }

//        todo::company 增加 inChargeId, 修改list页负责人信息

        return approvalRepo.findOne(approvalId);
    }


}
