package com.lyun.estate.rest.event

import com.lyun.estate.biz.event.def.EventDefine
import com.lyun.estate.biz.event.entity.Event
import com.lyun.estate.biz.event.entity.EventProcess
import com.lyun.estate.biz.event.processor.EventProcessor
import com.lyun.estate.biz.event.processor.EventProcessorRouter
import com.lyun.estate.biz.event.processor.HouseCountStatisticsProcessor
import com.lyun.estate.biz.event.service.EventService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

/**
 * Created by Jeffrey on 2017-03-15.
 */
@Service
class HouseCountStatisticsConsumer {
    @Autowired
    EventService eventService

    @Autowired
    EventProcessorRouter eventConsumerRouter

    EventProcessor messageProcessor

    @PostConstruct
    def init() {
        messageProcessor = eventConsumerRouter.getConsumer(HouseCountStatisticsProcessor.PROCESSOR_NAME)
    }

    Logger logger = LoggerFactory.getLogger(HouseCountStatisticsConsumer.class)

    @RabbitListener(queues = EventDefine.QUEUE_NAME_XIAO_QU_COUNT_STATISTICS)
    void listenFangPublishEvent(final Event event) {
        try {
            logger.info("【{}】received from queue 【{}】 ", event, EventDefine.QUEUE_NAME_XIAO_QU_COUNT_STATISTICS)
            EventProcess process = new EventProcess()
                    .setUuid(event.uuid).setType(event.type)
                    .setDomainId(event.domainId).setDomainType(event.domainType)
                    .setContent(event.content).setProcessor(messageProcessor.processorName())

            eventService.saveProcess(process)

            messageProcessor.process(process)

        } catch (Exception e) {
            logger.error("event :${event.uuid} process error in QUEUE_NAME_XIAO_QU_COUNT_STATISTICS ", e)
        }
    }

}
