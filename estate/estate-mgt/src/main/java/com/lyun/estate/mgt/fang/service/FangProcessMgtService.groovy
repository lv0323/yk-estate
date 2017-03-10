package com.lyun.estate.mgt.fang.service

import com.lyun.estate.biz.fang.service.FangProcessService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Jeffrey on 2017-03-10.
 */
@Service
class FangProcessMgtService {

    @Autowired
    FangProcessService processService

    def publish(long fangId) {
        processService.publish(fangId)
    }

    def unPublish(long fangId) {
        processService.unPublish(fangId)
    }

    def deal(long fangId) {
        processService.deal(fangId)
    }

    boolean delete(long fangId) {
        processService.delete(fangId)
    }
}
