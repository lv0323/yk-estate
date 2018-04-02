package com.lyun.estate.mgt.context;

import com.lyun.estate.core.supports.context.BaseContext;
import eu.bitwalker.useragentutils.DeviceType;
import org.springframework.stereotype.Component;

@Component /*把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/> */
public class MgtContext extends BaseContext {

    public static final String OPERATOR = "operator";

    public static final String CORRELATION_ID = "correlationId";

    public static final String RESOURCE_PATH = "resourcePath";

    public static final String METHOD = "method";

    public static final String USER_ADDRESS = "userAddress";

    public static final String REQUEST_BASE_URL = "requestBaseUrl";

    public static final String BROWSER_NAME = "browserName";

    public static final String OS_NAME = "osName";

    public static final String DEVICE_TYPE = "deviceType";

    public static final String ACCESS_TIME = "accessTime";


    public Long getAccessTime() {
        return (Long) get(ACCESS_TIME);
    }

    public void setAccessTime(Long accessTime) {
        put(ACCESS_TIME, accessTime);
    }


    public Operator getOperator() {
        return (Operator) get(OPERATOR);
    }

    public void setOperator(Operator operator) {
        put(OPERATOR, operator);
    }

    public String getMethod() {
        return (String) get(METHOD);
    }

    public void setMethod(String method) {
        put(METHOD, method);
    }

    public String getResourcePath() {
        return (String) get(RESOURCE_PATH);
    }

    public void setResourcePath(String path) {
        put(RESOURCE_PATH, path);
    }

    public String getUserAddress() {
        return (String) get(USER_ADDRESS);
    }

    public void setUserAddress(String address) {
        put(USER_ADDRESS, address);
    }

    public String getCorrelationId() {
        return (String) get(CORRELATION_ID);
    }

    public void setCorrelationId(String id) {
        put(CORRELATION_ID, id);
    }

    public String getRequestBaseUrl() {
        return (String) get(REQUEST_BASE_URL);
    }

    public void setRequestBaseUrl(String requestBaseUrl) {
        put(REQUEST_BASE_URL, requestBaseUrl);
    }

    public String getOsName() {
        return (String) get(OS_NAME);
    }

    public void setOsName(String name) {
        put(OS_NAME, name);
    }

    public String getBrowserName() {
        return (String) get(BROWSER_NAME);
    }

    public void setBrowserName(String name) {
        put(BROWSER_NAME, name);
    }

    public DeviceType getDeviceType() {
        return (DeviceType) get(DEVICE_TYPE);
    }

    public void setDeviceType(DeviceType deviceType) {
        put(DEVICE_TYPE, deviceType);
    }
}
