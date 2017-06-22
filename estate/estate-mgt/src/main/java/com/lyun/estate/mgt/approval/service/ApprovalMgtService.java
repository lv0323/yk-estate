package com.lyun.estate.mgt.approval.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.domain.ApprovalDTO;
import com.lyun.estate.biz.approval.domain.Signing;
import com.lyun.estate.biz.approval.entity.Approval;
import com.lyun.estate.biz.approval.service.ApprovalService;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.report.engine.ReportEngine;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 2017-06-14.
 */
@Service
public class ApprovalMgtService {
    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private PermissionCheckService permissionCheckService;

    @Autowired
    private MgtContext mgtContext;

    @Autowired
    private ReportEngine reportEngine;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger logger = LoggerFactory.getLogger(ApprovalMgtService.class);

    public Approval create(ApprovalDefine.Type type, String data) {
        permissionCheckService.checkExist(Permission.APPROVAL_CREATE);

        if (type == ApprovalDefine.Type.SIGNING) {
            Signing signing = approvalService.readFromData(data, Signing.class);
            signing.setParentId(mgtContext.getOperator().getCompanyId());
            data = approvalService.writeToData(signing);
        }

        Approval approval = new Approval()
                .setType(type)
                .setData(data)
                .setApplyId(mgtContext.getOperator().getId());

        return approvalService.create(approval);
    }

    public Approval approve(Long id, ApprovalDefine.Status status, String comment) {
        permissionCheckService.checkExist(Permission.APPROVAL_APPROVE);
        return approvalService.approve(id, mgtContext.getOperator().getId(), status, comment);
    }

    public PageList<ApprovalDTO> list(ApprovalDefine.Type type,
                                      ApprovalDefine.Status status,
                                      Long applyCompanyId,
                                      Long applyDeptId,
                                      Long applyId,
                                      Date startTime,
                                      Date endTime,
                                      PageBounds pageBounds) {
        permissionCheckService.checkExist(Permission.APPROVAL_LIST_EXPORT);

        //check companyId
        if (mgtContext.getOperator().getCompanyType() != CompanyDefine.Type.YK) {
            applyCompanyId = mgtContext.getOperator().getCompanyId();
        }

        return approvalService.list(type, status, applyCompanyId, applyDeptId, applyId, startTime, endTime, pageBounds);
    }


    public void reportExport(ApprovalDefine.Type type,
                             ApprovalDefine.Status status,
                             Long applyCompanyId,
                             Long applyDeptId,
                             Long applyId,
                             Date startTime,
                             Date endTime, HttpServletResponse response) {
        permissionCheckService.checkExist(Permission.APPROVAL_LIST_EXPORT);

        if (mgtContext.getOperator().getCompanyType() != CompanyDefine.Type.YK) {
            applyCompanyId = mgtContext.getOperator().getCompanyId();
        }

        logger.info("query report [" + type + "] start...");
        Map<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("applyCompanyId", applyCompanyId);
        param.put("applyDeptId", applyDeptId);
        param.put("applyId", applyId);
        param.put("startTime", startTime);
        param.put("endTime", endTime);

        try {
            reportEngine.reportExport("approval_" + type, param, response);
        } catch (Exception e) {
            logger.error("query reportQuery [{}] have error : [{}]", type, e);
            throw ExceptionUtil.wrap(e);
        } finally {
            logger.info("query report [" + type + "] end!");
        }
    }
}
