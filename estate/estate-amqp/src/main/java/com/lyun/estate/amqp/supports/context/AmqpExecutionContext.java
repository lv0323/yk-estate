package com.lyun.estate.amqp.supports.context;

import com.lyun.estate.core.supports.ExecutionContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AmqpExecutionContext implements ExecutionContext {
    private final static String prefix = "executionContext.";
    private final static Set<String> keys = new HashSet<>();

    private String CLUSTER_ID = "amqp.clusterId";
    private String MESSAGE_ID = "amqp.messageId";
    private String RECEIVED_ROUTING_KEY = "amqp.received.routing.key";
    private String CONSUMER_QUEUE = "amqp.consumer.queue";

    public String getClusterId() {
        return get(CLUSTER_ID);
    }

    public void setClusterId(String clusterId) {
        set(CLUSTER_ID, clusterId);
    }

    public String getMessageId() {
        return get(MESSAGE_ID);
    }

    public void setMessageId(String messageId) {
        set(MESSAGE_ID, messageId);
    }

    public String getReceivedRoutingKey() {
        return get(RECEIVED_ROUTING_KEY);
    }

    public void setReceivedRoutingKey(String receivedRoutingKey) {
        set(RECEIVED_ROUTING_KEY, receivedRoutingKey);
    }

    public String getConsumerQueue() {
        return get(CONSUMER_QUEUE);
    }

    public void setConsumerQueue(String consumerQueue) {
        set(CONSUMER_QUEUE, consumerQueue);
    }

    @Override
    public String get(String key) {
        return MDC.get(prefix + key);
    }

    @Override
    public void set(String key, String value) {
        String tmp = prefix + key;
        MDC.put(tmp, value);
        keys.add(tmp);
    }

    @Override
    public void remove(String key) {
        MDC.remove(prefix + key);
    }

    @Override
    public void clear() {
        clear(keys);
    }

    private void clear(Set<String> keys) {
        keys.forEach(this::remove);
    }
}
