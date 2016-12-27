package com.lyun.estate.rest.supports;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MdcApiListener implements ApiListener {
    @Override
    public void onRequest(HttpServletRequest request, HttpServletResponse response) {
        MDC.put(MdcKey.requestRemoteHost.toString(), request.getRemoteHost());
        MDC.put(MdcKey.requestRemoteUser.toString(), request.getRemoteUser());
        MDC.put(MdcKey.requestXForwardedFor.toString(), request.getHeader("X-Forwarded-For"));
        MDC.put(MdcKey.requestMethod.toString(), request.getMethod());
        MDC.put(MdcKey.requestURI.toString(), request.getRequestURI());
        MDC.put(MdcKey.requestURL.toString(), request.getRequestURL().toString());
        String requestPath = request.getRequestURI();
        String requestQueryString = request.getQueryString();
        if (!StringUtils.isEmpty(requestQueryString)) {
            requestPath += "?" + requestQueryString;
        }
        MDC.put(MdcKey.requestPath.toString(), requestPath);
        MDC.put(MdcKey.requestQueryString.toString(), request.getQueryString());
        MDC.put(MdcKey.requestReferer.toString(), request.getHeader("Referer"));
        MDC.put(MdcKey.requestUserAgent.toString(), request.getHeader("User-Agent"));
        MDC.put(MdcKey.requestLocale.toString(), request.getLocale().toString());
    }
}
