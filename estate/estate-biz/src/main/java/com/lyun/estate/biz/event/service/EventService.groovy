package com.lyun.estate.biz.event.service

import com.lyun.estate.biz.event.def.EventDefine
import com.lyun.estate.biz.event.entity.Event
import com.lyun.estate.biz.event.entity.EventProcess
import com.lyun.estate.biz.event.repo.EventRepo
import com.lyun.estate.core.supports.exceptions.EstateException
import com.lyun.estate.core.supports.exceptions.ExCode
import com.lyun.estate.core.supports.exceptions.ExceptionUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    Logger logger = LoggerFactory.getLogger(EventService.class)

    @PostConstruct
    def init() {
        rabbitAdmin.declareExchange(EventDefine.EXCHANGE_FANG_PUBLISH)
        rabbitAdmin.declareExchange(EventDefine.EXCHANGE_FANG_PROCESS)

        rabbitAdmin.declareQueue(EventDefine.QUEUE_FANG_PUBLISH_MESSAGE)
        rabbitAdmin.declareQueue(EventDefine.QUEUE_FANG_PROCESS_MESSAGE)

        rabbitAdmin.declareBinding(EventDefine.BINDING_PUBLISH_MESSAGE)
        rabbitAdmin.declareBinding(EventDefine.BINDING_CHANGE_PRICE_MESSAGE)
    }


    Event produce(Event event) throws EstateException {
        try {
            ExceptionUtil.checkNotNull("事件", event)
            ExceptionUtil.checkNotNull("事件类型", event.type)
            eventRepo.saveEvent(event)
            if (event.type == EventDefine.Type.FANG_PUBLISH) {
                rabbitTemplate.convertAndSend(
                        EventDefine.EXCHANGE_FANG_PUBLISH.name,
                        EventDefine.KEY_FANG_PUBLISH,
                        event)
            } else if (event.type == EventDefine.Type.FANG_PROCESS) {
                rabbitTemplate.convertAndSend(
                        EventDefine.EXCHANGE_FANG_PROCESS.name,
                        EventDefine.KEY_FANG_PROCESS,
                        event)
            } else {
                logger.error("事件类型错误：{}，无法发布", event.type)
            }
            return eventRepo.findEvent(event.getId())
        } catch (Exception e) {
            logger.error("event produce error:" + event.toString(), e)
            throw new EstateException(ExCode.EVENT_PRODUCE_ERROR, event)
        }
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
