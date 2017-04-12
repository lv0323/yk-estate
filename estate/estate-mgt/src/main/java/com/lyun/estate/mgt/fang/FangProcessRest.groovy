package com.lyun.estate.mgt.fang

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
    Fang publish(@RequestParam Long fangId) {
        processMgtService.publish(fangId)
    }

    @PostMapping('pause')
    Fang pause(@RequestParam Long fangId) {
        processMgtService.pause(fangId)
    }

    @PostMapping('un-publish')
    Fang unPublish(@RequestParam Long fangId) {
        processMgtService.unPublish(fangId)
    }


    @PostMapping('apply-public')
    Fang applyPublic(@RequestParam Long fangId) {
        processMgtService.applyPublic(fangId)
    }

    @PostMapping('confirm-public')
    Fang confirmPublic(@RequestParam Long fangId) {
        processMgtService.confirmPublic(fangId)
    }

    @PostMapping('reject-public')
    Fang rejectPublic(@RequestParam Long fangId) {
        processMgtService.rejectPublic(fangId)
    }

    @PostMapping('undo-public')
    Fang undoPublic(@RequestParam Long fangId) {
        processMgtService.undoPublic(fangId)
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
