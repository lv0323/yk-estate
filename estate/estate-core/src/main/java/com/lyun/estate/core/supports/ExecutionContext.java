package com.lyun.estate.core.supports;

public interface ExecutionContext {
    String CLIENT_ID = "clientId";
    String USER_ID = "userId";
    String RESOURCE_PATH = "resourcePath";
    String METHOD = "method";
    String USER_ADDRESS = "userAddress";
    String CORRELATION_ID = "correlationId";
    String REQUEST_BASE_URL = "requestBaseUrl";
    String BROWSER_NAME = "browserName";
    String OS_NAME = "osName";
    String APP_VERSION = "appVersion";
    String REQUEST_LOCAL = "requestLocal";

    default String getClientId() {
        return get(CLIENT_ID);
    }

    default void setClientId(String id) {
        set(CLIENT_ID, id);
    }

    default String getUserId() {
        return get(USER_ID);
    }

    default void setUserId(String id) {
        set(USER_ID, id);
    }

    default String getMethod() {
        return get(METHOD);
    }

    default void setMethod(String method) {
        set(METHOD, method);
    }

    default String getResourcePath() {
        return get(RESOURCE_PATH);
    }

    default void setResourcePath(String path) {
        set(RESOURCE_PATH, path);
    }

    default String getUserAddress() {
        return get(USER_ADDRESS);
    }

    default void setUserAddress(String address) {
        set(USER_ADDRESS, address);
    }

    default String getCorrelationId() {
        return get(CORRELATION_ID);
    }

    default void setCorrelationId(String id) {
        set(CORRELATION_ID, id);
    }

    default String getRequestBaseUrl() {
        return get(REQUEST_BASE_URL);
    }

    default void setRequestBaseUrl(String requestBaseUrl) {
        set(REQUEST_BASE_URL, requestBaseUrl);
    }

    default String getOsName() {
        return get(OS_NAME);
    }

    default void setOsName(String name) {
        set(OS_NAME, name);
    }

    default String getBrowserName() {
        return get(BROWSER_NAME);
    }

    default void setBrowserName(String name) {
        set(BROWSER_NAME, name);
    }

    default String getAppVersion() {
        return get(APP_VERSION);
    }

    default void setAppVersion(String appVersion) {
        set(APP_VERSION, appVersion);
    }

    default String getRequestLocal() {
        return get(REQUEST_LOCAL);
    }

    default void setRequestLocal(String requestLocal) {
        set(REQUEST_LOCAL, requestLocal);
    }

    String get(String key);

    void set(String key, String value);

    void remove(String key);

    void clear();
}
