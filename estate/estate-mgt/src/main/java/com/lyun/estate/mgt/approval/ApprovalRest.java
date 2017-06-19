package com.lyun.estate.mgt.approval;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.domain.ApprovalDTO;
import com.lyun.estate.biz.approval.entity.Approval;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import com.lyun.estate.mgt.approval.service.ApprovalMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jeffrey on 2017-06-14.
 */
@RestController
@RequestMapping("api/approval")
public class ApprovalRest {

    @Autowired
    private ApprovalMgtService approvalMgtService;

    @PostMapping("create")
    public Approval create(@RequestParam ApprovalDefine.Type type,
                           @RequestParam String data) {
        return approvalMgtService.create(type, data);
    }

    @PostMapping("approve")
    public Approval approve(@RequestParam Long id,
                            @RequestParam ApprovalDefine.Status status) {
        return approvalMgtService.approve(id, status);
    }

    @GetMapping("list")
    public PageList<ApprovalDTO> list(@RequestParam(required = false) ApprovalDefine.Type type,
                                      @RequestParam(required = false) ApprovalDefine.Status status,
                                      @RequestParam(required = false) Long applyCompanyId,
                                      @RequestParam(required = false) Long applyDeptId,
                                      @RequestParam(required = false) Long applyId,
                                      @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return approvalMgtService.list(type, status, applyCompanyId, applyDeptId, applyId, pageBounds);
    }

}
