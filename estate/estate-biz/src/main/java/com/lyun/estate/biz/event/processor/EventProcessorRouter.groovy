package com.lyun.estate.biz.event.processor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

/**
 * Created by Jeffrey on 2017-03-15.
 */
@Service
class EventProcessorRouter {
    @Autowired
    EventProcessor[] eventProcessors

    Map<String, EventProcessor> routerMap = new HashMap<>()

    @PostConstruct
    def init() {
        Arrays.stream(eventProcessors).forEach(
                { routerMap.put(it.processorName(), it) }
        )
    }

    EventProcessor getConsumer(String consumerName) {
        routerMap.get(consumerName)
    }
}
