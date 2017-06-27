package com.lyun.estate.mgt.common.application;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.fang.service.FangProcessMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/application")
public class CommonApplicationRest {

    @Autowired
    private CommonApplicationMgtService commonApplicationMgtService;

    @Autowired
    private MgtContext mgtContext;

    @GetMapping("/all")
    public List<CommonApplicationDTO> getApplications(@RequestParam(required = false) List<CommonApplicationEntity.Type> types,
                                                  @RequestParam(required = false) Long id,
                                                  @RequestParam(required = false) Long applicantId,
                                                  @RequestParam(required = false) List<CommonApplicationEntity.Status> status,
                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startTime,
                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endTime,
                                                  @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return commonApplicationMgtService.findApplications(types, id, applicantId, status, startTime, endTime, pageBounds);
    }

    @PutMapping("/approve")
    public int approveApplication(@RequestParam Long applicationId,
                           @RequestParam String reviewerComments,
                           @RequestParam(required = false) boolean isForceApprove) {
        return commonApplicationMgtService.approve(applicationId, reviewerComments, isForceApprove, mgtContext);
    }

    @PutMapping("/reject")
    public int rejectApplication(@RequestParam Long applicationId,
                          @RequestParam String reviewerComments) {
        return commonApplicationMgtService.reject(applicationId, reviewerComments, mgtContext);
    }

    @PutMapping("/close")
    public int closeApplication(@RequestParam Long applicationId,
                         @RequestParam String reviewerComments) {
        return commonApplicationMgtService.close(applicationId, reviewerComments, mgtContext);
    }

/**
    @PostMapping("/request")
    public CommonApplicationEntity requestPublish(@RequestParam Long domainId,
                                           @RequestParam String applyReason,
                                           @RequestParam CommonApplicationEntity.Type type) {
        return commonApplicationMgtService.request(type, mgtContext.getOperator().getId(), applyReason, domainId);
    }

**/
}
