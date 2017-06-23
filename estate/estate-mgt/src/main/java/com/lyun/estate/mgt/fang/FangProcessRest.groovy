package com.lyun.estate.mgt.fang

import com.lyun.estate.biz.application.entity.CommonApplicationEntity
import com.lyun.estate.biz.fang.entity.Fang
import com.lyun.estate.mgt.fang.service.FangProcessMgtService
import com.lyun.estate.mgt.supports.CommonResp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
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
    Fang requestPublish(@RequestParam Long fangId,
                 @RequestParam String applyReason) {
        processMgtService.requestPublish(fangId, applyReason)
    }

    @PostMapping('un-publish')
    Fang requestUnPublish(@RequestParam Long fangId,
                          @RequestParam String applyReason) {
        processMgtService.requestUnPublish(fangId, applyReason)
    }

    @PostMapping('pause')
    Fang requestPause(@RequestParam Long fangId,
               @RequestParam String applyReason) {
        processMgtService.requestPause(fangId, applyReason)
    }

    @PostMapping('apply-public')
    Fang requestApplyPublic(@RequestParam Long fangId,
                     @RequestParam String applyReason) {
        processMgtService.requestPublic(fangId, applyReason)
    }

    @PostMapping('undo-public')
    Fang requestUndoPublic(@RequestParam Long fangId,
                           @RequestParam String applyReason) {
        processMgtService.requestUndoPublic(fangId, applyReason)
    }

    @PostMapping('approve')
    CommonApplicationEntity approveApplication(@RequestParam Long applicationId,
                                               @RequestParam String reviewerComments) {
        processMgtService.approve(applicationId, reviewerComments)
    }

    @PostMapping('reject')
    CommonApplicationEntity rejectApplication(@RequestParam Long applicationId,
                            @RequestParam String reviewerComments) {
        processMgtService.reject(applicationId, reviewerComments)
    }

    @PostMapping('close')
    CommonApplicationEntity closeApplication(@RequestParam Long applicationId,
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
}
