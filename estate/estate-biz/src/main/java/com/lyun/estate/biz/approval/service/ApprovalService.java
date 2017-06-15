package com.lyun.estate.biz.approval.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.domain.*;
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
                Leaving leaving = readFromData(data, Leaving.class);
                ExceptionUtil.checkNotNull("外出", leaving);
                ExceptionUtil.checkNotNull("外出-外出时间", leaving.getStartTime());
                ExceptionUtil.checkNotNull("外出-返回时间", leaving.getEndTime());
                ExceptionUtil.checkNotNull("外出-外出地点", leaving.getLocation());
                ExceptionUtil.checkNotNull("外出-外出事由", leaving.getReason());
                ExceptionUtil.checkNotNull("外出-未打卡事由", leaving.getNoClockReason());
                break;
            case BIZ_TRIP:
                BizTrip bizTrip = readFromData(data, BizTrip.class);
                ExceptionUtil.checkNotNull("出差", bizTrip);
                ExceptionUtil.checkNotNull("出差-出差时间", bizTrip.getStartTime());
                ExceptionUtil.checkNotNull("出差-返回时间", bizTrip.getEndTime());
                ExceptionUtil.checkNotNull("出差-出差天数", bizTrip.getDays());
                ExceptionUtil.checkNotNull("出差-出差事由", bizTrip.getReason());
                ExceptionUtil.checkNotNull("出差-出差成果", bizTrip.getOutcome());
                ExceptionUtil.checkNotNull("出差-待解决问题", bizTrip.getProblem());
                ExceptionUtil.checkNotNull("出差-对接资源信息", bizTrip.getResource());
                ExceptionUtil.checkNotNull("出差-出差费用", bizTrip.getCosts());
                break;
            case COLD_VISIT:
                ColdVisit coldVisit = readFromData(data, ColdVisit.class);
                ExceptionUtil.checkNotNull("陌拜", coldVisit);
                ExceptionUtil.checkNotNull("陌拜-客户公司名", coldVisit.getCompanyName());
                ExceptionUtil.checkNotNull("陌拜-对方负责人", coldVisit.getBossName());
                ExceptionUtil.checkNotNull("陌拜-负责人身份", coldVisit.getBossType());
                ExceptionUtil.checkNotNull("陌拜-联系方式", coldVisit.getContactInfo());
                ExceptionUtil.checkNotNull("陌拜-门店地址", coldVisit.getAddress());
                ExceptionUtil.checkNotNull("陌拜-去访人员姓名", coldVisit.getFollowers());
                break;
            case SIGNING:
                Signing signing = readFromData(data, Signing.class);
                ExceptionUtil.checkNotNull("签约", signing);
                ExceptionUtil.checkNotNull("签约-城市", signing.getCityId());
                ExceptionUtil.checkNotNull("签约-城市名", signing.getCityName());
                ExceptionUtil.checkNotNull("签约-公司名", signing.getCompanyName());
                ExceptionUtil.checkNotNull("签约-公司简称", signing.getCompanyAbbr());
                ExceptionUtil.checkNotNull("签约-签约类型", signing.getCompanyType());
                ExceptionUtil.checkNotNull("签约-对方负责人", signing.getBossName());
                ExceptionUtil.checkNotNull("签约-对方负责人手机", signing.getBossMobile());
                ExceptionUtil.checkNotNull("签约-本公司负责人", signing.getPartAInChargeId());
                ExceptionUtil.checkNotNull("签约-本公司负责人姓名", signing.getPartAInChargeName());
                ExceptionUtil.checkNotNull("签约-备注", signing.getNote());

                ExceptionUtil.checkNotNull("签约-签约开始时间", signing.getStartDate());
                ExceptionUtil.checkNotNull("签约-签约结束时间", signing.getEndDate());
                ExceptionUtil.checkNotNull("签约-签约年限", signing.getYears());
                ExceptionUtil.checkNotNull("签约-签约店数", signing.getStoreCount());
                ExceptionUtil.checkNotNull("签约-签约金额", signing.getPrice());
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

    public PageList<ApprovalDTO> list(ApprovalDefine.Type type,
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
