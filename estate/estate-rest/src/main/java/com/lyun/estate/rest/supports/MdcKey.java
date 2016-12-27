package com.lyun.estate.rest.supports;

public enum MdcKey {
    requestRemoteHost("request.remoteHost"),
    requestRemoteUser("request.remoteUser"),
    requestXForwardedFor("request.xForwardedFor"),
    requestMethod("request.method"),
    requestURI("request.requestURI"),
    requestURL("request.requestURL"),
    requestPath("request.requestPath"),
    requestQueryString("request.queryString"),
    requestUserAgent("request.userAgent"),
    requestReferer("request.referer"),
    requestLocale("request.locale");

    private String keyname;

    MdcKey(String keyname) {
        this.keyname = keyname;
    }

    @Override
    public String toString() {
        return keyname;
    }
}
