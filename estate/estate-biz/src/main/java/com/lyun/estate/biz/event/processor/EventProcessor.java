package com.lyun.estate.biz.event.processor;

import com.lyun.estate.biz.event.entity.EventProcess;

/**
 * Created by Jeffrey on 2017-03-14.
 */
public interface EventProcessor {

    String processorName();

    EventProcess process(EventProcess eventConsume);
}