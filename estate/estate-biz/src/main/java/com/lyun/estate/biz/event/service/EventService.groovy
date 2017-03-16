package com.lyun.estate.biz.event.service

import com.lyun.estate.biz.event.def.EventDefine
import com.lyun.estate.biz.event.entity.Event
import com.lyun.estate.biz.event.entity.EventProcess
import com.lyun.estate.biz.event.repo.EventRepo
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

/**
 * Created by Jeffrey on 2017-03-14.
 */
@Service
class EventService {

    @Autowired
    RabbitTemplate rabbitTemplate

    @Autowired
    RabbitAdmin rabbitAdmin

    @Autowired
    EventRepo eventRepo

    @PostConstruct
    def init() {
        rabbitAdmin.declareExchange(EventDefine.EXCHANGE_FANG_PUBLISH)
        rabbitAdmin.declareExchange(EventDefine.EXCHANGE_FANG_PROCESS)

        rabbitAdmin.declareQueue(EventDefine.QUEUE_FANG_PUBLISH_MESSAGE)
        rabbitAdmin.declareQueue(EventDefine.QUEUE_FANG_PROCESS_MESSAGE)

        rabbitAdmin.declareBinding(EventDefine.BINDING_PUBLISH_MESSAGE)
        rabbitAdmin.declareBinding(EventDefine.BINDING_CHANGE_PRICE_MESSAGE)
    }


    Event produce(Event event) {
        eventRepo.saveEvent(event)
        rabbitTemplate.convertAndSend(EventDefine.EXCHANGE_FANG_PUBLISH.name, EventDefine.KEY_FANG_PUBLISH, event)
        return eventRepo.findEvent(event.getId())
    }

    EventProcess saveProcess(EventProcess eventProcess) {
        eventProcess.setStatus(EventDefine.ProcessStatus.CREATED)
        eventRepo.saveProcess(eventProcess)
        return eventRepo.findProcess(eventProcess.id)
    }

    EventProcess closeProcess(long processId) {
        eventRepo.closeProcess(processId)
        return eventRepo.findProcess(processId)
    }

    EventProcess lockProcess(long processId) {
        return eventRepo.selectProcessForUpdate(processId)
    }


}
