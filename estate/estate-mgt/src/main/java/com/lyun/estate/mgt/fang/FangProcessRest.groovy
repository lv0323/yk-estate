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
    Fang requestPause(@RequestParam Long fangId,
                      @RequestParam String applyReason) {
        // applyReason has no usage for now, may be used in future when the pause action need approval
        return processMgtService.pause(fangId)
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

    @PostMapping('deal')
    Fang deal(@RequestParam Long fangId) {
        processMgtService.deal(fangId)
    }

    @PostMapping('delete')
    CommonResp delete(@RequestParam Long fangId) {
        processMgtService.delete(fangId) ? CommonResp.succeed() : CommonResp.failed()
    }

}
