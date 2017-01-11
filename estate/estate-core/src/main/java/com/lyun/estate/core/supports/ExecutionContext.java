package com.lyun.estate.core.supports;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Component
public class ExecutionContext {
    private static final String prefix = "executionContext.";

    // user specification context
    private static final String CLIENT_ID = "clientId";
    private static final String USER_ID = "userId";
    private static final String CORRELATION_ID = "correlationId";
    private static final String REQUEST_LOCALE = "requestLocale";

    public String getClientId() {
        return get(CLIENT_ID);
    }

    public void setClientId(String id) {
        set(CLIENT_ID, id);
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

    // request context
    private static final String USER_ADDRESS = "userAddress";
    private static final String BROWSER_NAME = "browserName";
    private static final String OS_NAME = "osName";
    private static final String APP_VERSION = "appVersion";
    private static final String REMOTE_USER = "remoteUser";
    private static final String X_FORWARDED_FOR = "xForwardedFor";
    private static final String REMOTE_HOST = "remoteHost";
    private static final String REQUEST_BASE_URL = "requestBaseUrl";
    private static final String REQUEST_METHOD = "requestMethod";
    private static final String REQUEST_URL = "requestUrl";
    private static final String REQUEST_URI = "requestUri";
    private static final String REQUEST_PATH = "requestPath";
    private static final String REQUEST_QUERY_STRING = "requestQueryString";
    private static final String REQUEST_USER_AGENT = "requestUserAgent";
    private static final String REQUEST_REFERER = "requestReferer";

    public String getUserAddress() {
        return get(USER_ADDRESS);
    }

    public void setUserAddress(String address) {
        set(USER_ADDRESS, address);
    }

    public String getBrowserName() {
        return get(BROWSER_NAME);
    }

    public void setBrowserName(String name) {
        set(BROWSER_NAME, name);
    }

    public String getOsName() {
        return get(OS_NAME);
    }

    public void setOsName(String name) {
        set(OS_NAME, name);
    }

    public String getAppVersion() {
        return get(APP_VERSION);
    }

    public void setAppVersion(String appVersion) {
        set(APP_VERSION, appVersion);
    }

    public String getRemoteUser() {
        return get(REMOTE_USER);
    }

    public void setRemoteUser(String remoteUser) {
        set(REMOTE_USER, remoteUser);
    }

    public String getxForwardedFor() {
        return get(X_FORWARDED_FOR);
    }

    public void setxForwardedFor(String xForwardedFor) {
        set(X_FORWARDED_FOR, xForwardedFor);
    }

    public String getRemoteHost() {
        return get(REMOTE_HOST);
    }

    public void setRemoteHost(String remoteHost) {
        set(REMOTE_HOST, remoteHost);
    }

    public String getRequestMethod() {
        return get(REQUEST_METHOD);
    }

    public void setRequestMethod(String requestMethod) {
        set(REQUEST_METHOD, requestMethod);
    }

    public String getRequestUrl() {
        return get(REQUEST_URL);
    }

    public void setRequestUrl(String requestUrl) {
        set(REQUEST_URL, requestUrl);
    }

    public String getRequestUri() {
        return get(REQUEST_URI);
    }

    public void setRequestUri(String requestUri) {
        set(REQUEST_URI, requestUri);
    }

    public String getRequestPath() {
        return get(REQUEST_PATH);
    }

    public void setRequestPath(String requestPath) {
        set(REQUEST_PATH, requestPath);
    }

    public String getRequestQueryString() {
        return get(REQUEST_QUERY_STRING);
    }

    public void setRequestQueryString(String requestQueryString) {
        set(REQUEST_QUERY_STRING, requestQueryString);
    }

    public String getRequestUseragent() {
        return get(REQUEST_USER_AGENT);
    }

    public void setRequestUserAgent(String requestUserAgent) {
        set(REQUEST_USER_AGENT, requestUserAgent);
    }

    public String getRequestReferer() {
        return get(REQUEST_REFERER);
    }

    public void setRequestReferer(String requestReferer) {
        set(REQUEST_REFERER, requestReferer);
    }

    public String getRequestBaseUrl() {
        return get(REQUEST_BASE_URL);
    }

    public void setRequestBaseUrl(String requestBaseUrl) {
        set(REQUEST_BASE_URL, requestBaseUrl);
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
