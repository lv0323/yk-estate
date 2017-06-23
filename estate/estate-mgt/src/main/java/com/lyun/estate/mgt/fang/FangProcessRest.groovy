package com.lyun.estate.mgt.fang

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.lyun.estate.biz.application.entity.CommonApplicationEntity
import com.lyun.estate.biz.fang.entity.Fang
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver
import com.lyun.estate.mgt.fang.service.FangProcessMgtService
import com.lyun.estate.mgt.supports.CommonResp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Jeffrey on 2017-03-10.
 */
@RestController
@RequestMapping('api/fang-process')
class FangProcessRest {

    @Autowired
    FangProcessMgtService processMgtService

    @PostMapping('publish')
    CommonApplicationEntity requestPublish(@RequestParam Long fangId,
                 @RequestParam String applyReason) {
        return processMgtService.requestPublish(fangId, applyReason)
    }

    @PostMapping('un-publish')
    CommonApplicationEntity requestUnPublish(@RequestParam Long fangId,
                          @RequestParam String applyReason) {
        return processMgtService.requestUnPublish(fangId, applyReason)
    }

    @PostMapping('pause')
    CommonApplicationEntity requestPause(@RequestParam Long fangId,
               @RequestParam String applyReason) {
        return processMgtService.requestPause(fangId, applyReason)
    }

    @PostMapping('apply-public')
    CommonApplicationEntity requestApplyPublic(@RequestParam Long fangId,
                     @RequestParam String applyReason) {
        return processMgtService.requestPublic(fangId, applyReason)
    }

    @PostMapping('undo-public')
    CommonApplicationEntity requestUndoPublic(@RequestParam Long fangId,
                           @RequestParam String applyReason) {
        return processMgtService.requestUndoPublic(fangId, applyReason)
    }

    @PostMapping('approve')
    int approveApplication(@RequestParam Long applicationId,
                                               @RequestParam String reviewerComments) {
        processMgtService.approve(applicationId, reviewerComments)
    }

    @PostMapping('reject')
    int rejectApplication(@RequestParam Long applicationId,
                            @RequestParam String reviewerComments) {
        processMgtService.reject(applicationId, reviewerComments)
    }

    @PostMapping('close')
    int closeApplication(@RequestParam Long applicationId,
                            @RequestParam String reviewerComments) {
        processMgtService.close(applicationId, reviewerComments)
    }

    @PostMapping('deal')
    Fang deal(@RequestParam Long fangId) {
        processMgtService.deal(fangId)
    }

    @PostMapping('delete')
    CommonResp delete(@RequestParam Long fangId) {
        processMgtService.delete(fangId) ? CommonResp.succeed() : CommonResp.failed()
    }

    @GetMapping("/all")
    List<CommonApplicationEntity> getApplications(@RequestParam CommonApplicationEntity.Type type,
                                                         @RequestParam long id,
                                                         @RequestParam long applicantId,
                                                         @RequestParam CommonApplicationEntity.Status status,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startTime,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endTime,
                                                         @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return processMgtService.findApplications(type, id, applicantId, status, startTime, endTime, pageBounds);
    }
}
