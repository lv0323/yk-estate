package com.lyun.estate.biz.approval.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.domain.*;
import com.lyun.estate.biz.approval.entity.Approval;
import com.lyun.estate.biz.approval.repo.ApprovalRepo;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.domain.CreateCompanyInfo;
import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.service.CompanyService;
import com.lyun.estate.biz.employee.domain.EmployeeDTO;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Jeffrey on 2017-06-14.
 */
@Service
public class ApprovalService {

    @Autowired
    private ApprovalRepo approvalRepo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private String datePattern = "yyyy-MM-dd";

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
                ExceptionUtil.checkIllegal(Arrays.stream(CompanyDefine.Type.values())
                                .anyMatch(t -> Objects.equals(t.name(), signing.getCompanyType())),
                        "签约-签约类型",
                        signing.getCompanyType());
                ExceptionUtil.checkNotNull("签约-对方负责人", signing.getBossName());
                ExceptionUtil.checkIllegal(ValidateUtil.isMobile(signing.getBossMobile()),
                        "签约-对方负责人手机",
                        signing.getBossMobile());
                ExceptionUtil.checkNotNull("签约-本公司负责人", signing.getPartAInChargeId());
                ExceptionUtil.checkNotNull("签约-本公司负责人姓名", signing.getPartAInChargeName());
                ExceptionUtil.checkNotNull("签约-备注", signing.getNote());

                SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

                ExceptionUtil.checkIllegal(parseDateStr(dateFormat, signing.getStartDate()) != null,
                        "签约-签约开始时间",
                        signing.getStartDate());
                ExceptionUtil.checkIllegal(parseDateStr(dateFormat, signing.getEndDate()) != null,
                        "签约-签约结束时间",
                        signing.getEndDate());
                ExceptionUtil.checkNotNull("签约-签约年限", signing.getYears());
                ExceptionUtil.checkNotNull("签约-签约店数", signing.getStoreCount());
                ExceptionUtil.checkNotNull("签约-签约金额", signing.getPrice());

                ExceptionUtil.checkNotNull("签约-父公司", signing.getParentId());
                break;
            default:
                return false;
        }
        return true;
    }

    private Date parseDateStr(SimpleDateFormat dateFormat, String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            ExceptionUtil.catching(e);
        }
        return null;
    }

    public <T> T readFromData(String data, Class<T> clazz) {
        if (data != null) {
            try {
                return objectMapper.readValue(data, clazz);
            } catch (IOException e) {
                logger.warn("object mapper read value error");
            }
        }
        return null;
    }

    public String writeToData(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.warn("object mapper write value error");
            throw ExceptionUtil.wrap(e);
        }
    }

    public PageList<ApprovalDTO> list(ApprovalDefine.Type type,
                                      ApprovalDefine.Status status,
                                      Long applyCompanyId,
                                      Long applyDeptId,
                                      Long applyId,
                                      PageBounds pageBounds) {
        return listApprovalDTO(type, status, applyCompanyId, applyDeptId, applyId, pageBounds);
    }

    private PageList<ApprovalDTO> listApprovalDTO(ApprovalDefine.Type type, ApprovalDefine.Status status,
                                                  Long applyCompanyId,
                                                  Long applyDeptId, Long applyId, PageBounds pageBounds) {
        PageList<ApprovalDTO> result = approvalRepo.list(type,
                status,
                applyCompanyId,
                applyDeptId,
                applyId,
                pageBounds);

        result.forEach(t -> {
            fillDomain(t);
            fillApprover(t);
        });

        return result;
    }

    private void fillApprover(ApprovalDTO approvalDTO) {
        if (approvalDTO.getApproverId() != null) {
            EmployeeDTO employee = employeeService.selectDTOById(approvalDTO.getApproverId());
            if (employee != null) {
                approvalDTO.setApproverName(employee.getName());
                approvalDTO.setApproverDeptId(employee.getDepartmentId());
                approvalDTO.setApproverDeptName(employee.getDepartmentName());
                approvalDTO.setApplyCompanyId(employee.getCompanyId());
                approvalDTO.setApplyCompanyShortName(employee.getCompanyAbbr());
            }
        }
    }

    private void fillDomain(ApprovalDTO approvalDTO) {
        switch (approvalDTO.getType()) {
            case LEAVING:
                approvalDTO.setLeaving(readFromData(approvalDTO.getData(), Leaving.class));
                break;
            case BIZ_TRIP:
                approvalDTO.setBizTrip(readFromData(approvalDTO.getData(), BizTrip.class));
                break;
            case COLD_VISIT:
                approvalDTO.setColdVisit(readFromData(approvalDTO.getData(), ColdVisit.class));
                break;
            case SIGNING:
                approvalDTO.setSigning(readFromData(approvalDTO.getData(), Signing.class));
                break;
        }
    }

    @Transactional
    public Approval approve(Long approvalId, Long operatorId, ApprovalDefine.Status status) {
        ExceptionUtil.checkNotNull("审批编号", approvalId);
        ExceptionUtil.checkIllegal(status == ApprovalDefine.Status.APPROVED || status == ApprovalDefine.Status.REJECTED,
                "审批结果", status);
        Approval oneForUpdate = approvalRepo.findOneForUpdate(approvalId);
        if (!oneForUpdate.getType().isNeedApproval()) {
            throw new EstateException(ExCode.APPROVAL_NO_NEED_APPROVE);
        }

        if (oneForUpdate.getStatus().isEnd()) {
            throw new EstateException(ExCode.APPROVAL_APPROVED, oneForUpdate.getStatus());
        }

        if (oneForUpdate.getType() == ApprovalDefine.Type.SIGNING && status == ApprovalDefine.Status.APPROVED) {
            Signing signing = readFromData(oneForUpdate.getData(), Signing.class);
            Company company = companyService.createCompany(buildInfo(signing, oneForUpdate.getApplyId()), operatorId);
            signing.setCompanyId(company.getId());
            approvalRepo.updateStatusAndData(approvalId, operatorId, writeToData(signing), status);

        } else {
            approvalRepo.updateStatus(approvalId, operatorId, status);
        }
        return approvalRepo.findOne(approvalId);
    }

    private CreateCompanyInfo buildInfo(Signing signing, Long applyId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        return new CreateCompanyInfo()
                .setParentId(signing.getParentId())
                .setCityId(signing.getCityId())
                .setType(CompanyDefine.Type.valueOf(signing.getCompanyType()))
                .setName(signing.getCompanyName())
                .setAbbr(signing.getCompanyAbbr())
                .setAddress(signing.getAddress())
                .setPartAId(applyId)
                .setYears(signing.getYears())
                .setStoreCount(signing.getStoreCount())
                .setPrice(signing.getPrice())
                .setBossName(signing.getBossName())
                .setMobile(signing.getBossMobile())
                .setStartDate(parseDateStr(dateFormat, signing.getStartDate()))
                .setEndDate(parseDateStr(dateFormat, signing.getEndDate()));
    }


}
