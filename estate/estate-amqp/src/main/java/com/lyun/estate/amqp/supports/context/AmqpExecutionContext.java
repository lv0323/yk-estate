package com.lyun.estate.amqp.supports.context;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Component
public class AmqpExecutionContext {
    private static final String prefix = "executionContext.";

    private static final String CLIENT = "client";
    private static final String USER_ID = "userId";
    private static final String CORRELATION_ID = "correlationId";
    private static final String REQUEST_LOCALE = "requestLocale";

    public String getClient() {
        return get(CLIENT);
    }

    public void setClient(String client) {
        set(CLIENT, client);
    }

    public String getUserId() {
        return get(USER_ID);
    }

    public void setUserId(String id) {
        set(USER_ID, id);
    }

    public String getCorrelationId() {
        return get(CORRELATION_ID);
    }

    public void setCorrelationId(String id) {
        set(CORRELATION_ID, id);
    }

    public String getRequestLocale() {
        return get(REQUEST_LOCALE);
    }

    public void setRequestLocale(String requestLocale) {
        set(REQUEST_LOCALE, requestLocale);
    }

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


    private Set<String> keys = new HashSet<>();

    public String get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return MDC.get(prefix + key);
    }

    public void set(String key, String value) {
        String tmpKey = prefix + key;
        if (!keys.contains(tmpKey)) {
            keys.add(tmpKey);
        }
        if (!StringUtils.isEmpty(value)) {
            MDC.put(tmpKey, value);
        }
    }

    public void remove(String key) {
        String tempKey = prefix + key;
        MDC.remove(tempKey);
        keys.remove(tempKey);
    }

    public void clear() {
        keys.forEach(this::remove);
    }

}
