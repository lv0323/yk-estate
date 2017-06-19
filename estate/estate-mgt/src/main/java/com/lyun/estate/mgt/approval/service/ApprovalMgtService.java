package com.lyun.estate.mgt.approval.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.domain.ApprovalDTO;
import com.lyun.estate.biz.approval.domain.Signing;
import com.lyun.estate.biz.approval.entity.Approval;
import com.lyun.estate.biz.approval.service.ApprovalService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Approval approve(Long id, ApprovalDefine.Status status) {
        permissionCheckService.checkExist(Permission.APPROVAL_APPROVE);
        return approvalService.approve(id, mgtContext.getOperator().getId(), status);
    }

    public PageList<ApprovalDTO> list(ApprovalDefine.Type type, ApprovalDefine.Status status, Long applyCompanyId,
                                      Long applyDeptId,
                                      Long applyId, PageBounds pageBounds) {
        permissionCheckService.checkExist(Permission.APPROVAL_LIST_EXPORT);
        return approvalService.list(type, status, applyCompanyId, applyDeptId, applyId, pageBounds);
    }
}
