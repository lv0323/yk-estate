package com.lyun.estate.mgt.supports;

import com.google.common.base.Strings;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.mgt.context.MgtContext;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.regex.Pattern;


@Component
public class AccessLogListener implements ApiListener {
    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    private static final String FORWARDED_FOR_HEADER = "X-Forwarded-For";
    private static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";
    private static final String X_FORWARDED_HOST = "X-Forwarded-Host";
    private static final String X_FORWARDED_PORT = "X-Forwarded-Port";
    private static final String REFERER_HEADER = "Referer";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static Logger logger = LoggerFactory.getLogger(AccessLogListener.class);

    @Autowired
    private MgtContext mgtContext;

    @Autowired
    private Environment environment;


    @Override
    public void onRequest(HttpServletRequest request, HttpServletResponse response) {
        Long accessTime = System.currentTimeMillis();
        mgtContext.setAccessTime(accessTime);

        mgtContext.setCorrelationId(buildCorrelationId(request));

        mgtContext.setUserAddress(request.getRemoteAddr());

        mgtContext.setResourcePath(buildRequestPath(request));
        // parser request base url
        String proto = request.getHeader(X_FORWARDED_PROTO);
        String host = request.getHeader(X_FORWARDED_HOST);
        String port = request.getHeader(X_FORWARDED_PORT);
        if (StringUtils.hasText(proto) && StringUtils.hasText(host) && StringUtils.hasText(port)) {
            String requestBaseUrl = proto + "://" + host + ":" + port;
            mgtContext.setRequestBaseUrl(requestBaseUrl);
        } else {
            mgtContext.setRequestBaseUrl(environment.getRequiredProperty("mgt.baseUrl"));
        }

        String userAgent = request.getHeader(USER_AGENT_HEADER);
        if (!Strings.isNullOrEmpty(userAgent)) {
            try {
                UserAgent ua = new UserAgent(userAgent);
                String browser = Optional
                        .ofNullable(ua.getBrowser())
                        .map(Browser::getName)
                        .orElse("")
                        + Optional
                        .ofNullable(ua.getBrowserVersion())
                        .map(Version::getVersion)
                        .map(a -> ":" + a)
                        .orElse("");

                mgtContext.setBrowserName(browser);
                mgtContext.setOsName(Optional
                        .ofNullable(ua.getOperatingSystem())
                        .map(OperatingSystem::getName)
                        .orElse(null));
                mgtContext.setDeviceType(ua.getOperatingSystem().getDeviceType());

            } catch (Exception e) {
                ExceptionUtil.catching(e);
            }
            logger.info(buildRequestLog(request));
        }
    }

    @Override
    public void onResponse(HttpServletResponse response) {
        Long responseTime = System.currentTimeMillis();
        logger.info(buildResponseLog(response, responseTime - mgtContext.getAccessTime()));
    }

    @Override
    public void onComplete() {
        mgtContext.clear();
    }

    private String buildRequestLog(HttpServletRequest request) {
        StringBuilder accessLog = new StringBuilder();
        append(accessLog, "[" + mgtContext.getCorrelationId() + "]");
        append(accessLog, mgtContext.getUserAddress());
        append(accessLog, request.getHeader(FORWARDED_FOR_HEADER));
        append(accessLog, request.getRemoteUser());
        append(accessLog, request.getMethod());
        append(accessLog, buildRequestPath(request));
        append(accessLog, request.getHeader(REFERER_HEADER));
        append(accessLog, request.getHeader(USER_AGENT_HEADER));
        return accessLog.toString().substring(1);
    }

    private String buildResponseLog(HttpServletResponse response, Long cost) {
        StringBuilder responseLog = new StringBuilder();
        append(responseLog, "[" + mgtContext.getCorrelationId() + "]");
        append(responseLog, Integer.toString(response.getStatus()));
        append(responseLog, response.getHeader("Content-Length"));
        append(responseLog, String.valueOf(cost));
        return responseLog.toString();
    }

    private String buildCorrelationId(HttpServletRequest request) {
        String id = request.getHeader(CORRELATION_ID_HEADER);
        if (StringUtils.isEmpty(id)) {
            id = CommonUtil.getUuid();
        }
        return id;
    }

    private void append(StringBuilder sb, String str) {
        sb.append(" ");
        if (StringUtils.isEmpty(str)) {
            sb.append("-");
        } else if (Pattern.compile("[ \t\n\r]").matcher(str).find()) {
            // if contains whitespaces, escape and quote
            sb.append("\"");
            sb.append(str.replace("\"", "\"\""));
            sb.append("\"");
        } else {
            sb.append(str);
        }
    }

    private String buildRequestPath(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        String requestQueryString = request.getQueryString();
        if (!StringUtils.isEmpty(requestQueryString)) {
            requestPath += "?" + requestQueryString;
        }
        return requestPath;
    }
}
