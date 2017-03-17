package com.lyun.estate.biz.event.def

import com.lyun.estate.core.supports.labelenum.LabelEnum
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange

/**
 * Created by Jeffrey on 2017-03-14.
 */
class EventDefine {

    static final TopicExchange EXCHANGE_FANG_PUBLISH = new TopicExchange('exchange.fang.publish', true, false)
    static final TopicExchange EXCHANGE_FANG_PROCESS = new TopicExchange('exchange.fang.process', true, false)

    public static final String QUEUE_NAME_FANG_PUBLISH_MESSAGE = 'queue.fang.publish.message'
    public static final String QUEUE_NAME_FANG_PROCESS_MESSAGE = 'queue.fang.process.message'

    static final Queue QUEUE_FANG_PUBLISH_MESSAGE = new Queue(QUEUE_NAME_FANG_PUBLISH_MESSAGE, true)
    static final Queue QUEUE_FANG_PROCESS_MESSAGE = new Queue(QUEUE_NAME_FANG_PROCESS_MESSAGE, true)

    public static final String KEY_FANG_PUBLISH = 'fang.publish'
    public static final String KEY_FANG_PROCESS = 'fang.process'

    static final Binding BINDING_PUBLISH_MESSAGE = BindingBuilder
            .bind(QUEUE_FANG_PUBLISH_MESSAGE).to(EXCHANGE_FANG_PUBLISH).with(KEY_FANG_PUBLISH)

    static final Binding BINDING_CHANGE_PRICE_MESSAGE = BindingBuilder
            .bind(QUEUE_FANG_PROCESS_MESSAGE).to(EXCHANGE_FANG_PROCESS).with(KEY_FANG_PROCESS)


    static enum Type {
        FANG_PUBLISH('房源发布'),
        FANG_PROCESS('房源变动')

        final String label

        Type(String label) {
            this.label = label
        }

        String getLabel() {
            return label
        }
    }

    static enum ProcessStatus implements LabelEnum {
        CREATED('创建'),
        CLOSED('已处理')

        final String label

        ProcessStatus(String label) {
            this.label = label
        }

        @Override
        String getLabel() {
            return label
        }
    }
}
