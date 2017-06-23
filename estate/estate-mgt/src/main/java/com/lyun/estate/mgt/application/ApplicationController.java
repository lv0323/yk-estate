package com.lyun.estate.mgt.application;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import com.lyun.estate.mgt.context.MgtContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    private ApplicationMgtService applicationMgtService;

    @Autowired
    private MgtContext mgtContext;

    @PostMapping("/create")
    public CommonApplicationEntity create(@RequestParam CommonApplicationEntity.Type type,
                                          @RequestParam String applyReason,
                                          @RequestParam long domainId) {
        return applicationMgtService.create(type, mgtContext.getOperator().getId(), applyReason, domainId);
    }

    @PutMapping("/approve")
    public void approve(@RequestParam long applicationId,
                        @RequestParam String comments) {
        applicationMgtService.approve(applicationId, mgtContext.getOperator().getId(), comments);
    }

    @PutMapping("/reject")
    public void reject(@RequestParam long applicationId,
                       @RequestParam String comments) {
        applicationMgtService.reject(applicationId, mgtContext.getOperator().getId(), comments);
    }

    @PutMapping("/close")
    public void close(@RequestParam long applicationId,
                      @RequestParam String comments) {
        applicationMgtService.close(applicationId, mgtContext.getOperator().getId(), comments);
    }


    @GetMapping("/all")
    public List<CommonApplicationEntity> getApplications(@RequestParam CommonApplicationEntity.Type type,
                                                         @RequestParam long id,
                                                         @RequestParam long applicantId,
                                                         @RequestParam CommonApplicationEntity.Status status,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startTime,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endTime,
                                                         @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return applicationMgtService.findApplications(type, id, applicantId, status, startTime, endTime, pageBounds);

    }

}
