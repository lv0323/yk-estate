package com.lyun.estate.mgt.approval.service;

import com.lyun.estate.biz.approval.def.ApprovalDefine;
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

        Approval approval = new Approval()
                .setType(type)
                .setData(data)
                .setApplyId(mgtContext.getOperator().getId());

        return approvalService.create(approval);
    }
}
