package com.lyun.estate.mgt.fang

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
    FangProcessMgtService processMgtService;

    @PostMapping('publish')
    def publish(@RequestParam Long fangId) {
        processMgtService.publish(fangId)
    }

    @PostMapping('un-publish')
    def unPublish(@RequestParam Long fangId) {
        processMgtService.unPublish(fangId)
    }

    @PostMapping('deal')
    def deal(@RequestParam Long fangId) {
        processMgtService.deal(fangId)
    }

    @PostMapping('delete')
    def delete(@RequestParam Long fangId) {
        processMgtService.delete(fangId) ? CommonResp.succeed() : CommonResp.failed()
    }


}
