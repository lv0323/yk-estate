package com.lyun.estate.biz.event.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 2017-03-15.
 */
@Service
public class EventProcessorRouter {
    @Autowired
    EventProcessor[] eventProcessors;

    Map<String, EventProcessor> routerMap = new HashMap<>();

    @PostConstruct
    private void init() {
        Arrays.stream(eventProcessors).forEach(it ->
                routerMap.put(it.processorName(), it)
        );
    }

    public EventProcessor getConsumer(String consumerName) {
        return routerMap.get(consumerName);
    }
}
