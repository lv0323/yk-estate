package com.lyun.estate.mgt.approval;

import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.entity.Approval;
import com.lyun.estate.mgt.approval.service.ApprovalMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jeffrey on 2017-06-14.
 */
@RestController
@RequestMapping("api/approval")
public class ApprovalRest {

    @Autowired
    private ApprovalMgtService approvalMgtService;

    @PostMapping("create")
    public Approval create(ApprovalDefine.Type type, String data) {
        return approvalMgtService.create(type, data);
    }

}
