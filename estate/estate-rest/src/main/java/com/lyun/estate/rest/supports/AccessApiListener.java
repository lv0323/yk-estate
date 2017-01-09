package com.lyun.estate.rest.supports;

import com.google.common.base.Strings;
import com.lyun.estate.core.supports.ExecutionContext;
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
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AccessApiListener implements ApiListener {
    private static Logger logger = LoggerFactory.getLogger(AccessApiListener.class);
    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    private static final String FORWARDED_FOR_HEADER = "X-Forwarded-For";
    private static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";
    private static final String X_FORWARDED_HOST = "X-Forwarded-Host";
    private static final String X_FORWARDED_PORT = "X-Forwarded-Port";
    private static final String REFERER_HEADER = "Referer";
    private static final String USER_AGENT_HEADER = "User-Agent";

    @Autowired
    ExecutionContext executionContext;
    @Autowired
    Environment environment;

    @Override
    public void onRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-TIMESTAMP", String.valueOf((new Date().toInstant().toEpochMilli() / 1000)));
        executionContext.setCorrelationId(buildCorrelationId(request));
        executionContext.setRemoteHost(request.getRemoteHost());
        executionContext.setRemoteUser(request.getRemoteUser());
        executionContext.setxForwardedFor(request.getHeader("X-Forwarded-For"));
        executionContext.setRequestMethod(request.getMethod());
        executionContext.setRequestUrl(request.getRequestURL().toString());
        String requestPath = request.getRequestURI();
        executionContext.setRequestUri(request.getRequestURI());
        String requestQueryString = request.getQueryString();
        if (!StringUtils.isEmpty(requestQueryString)) {
            executionContext.setRequestQueryString(requestQueryString);
            requestPath += "?" + requestQueryString;
        }
        executionContext.setRequestPath(requestPath);
        executionContext.setRequestReferer(request.getHeader("Referer"));
        executionContext.setRequestUserAgent(request.getHeader("User-Agent"));
        executionContext.setRequestLocale(request.getLocale().toString());

        String userAddress = StringUtils.isEmpty(request.getHeader(FORWARDED_FOR_HEADER)) ? request.getRemoteHost() : request.getHeader(FORWARDED_FOR_HEADER);
        if (StringUtils.hasText(userAddress) && userAddress.indexOf(',') > 0) {
            userAddress = userAddress.substring(0, userAddress.indexOf(','));
        }
        executionContext.setUserAddress(userAddress);

        // parse request base url
        String proto = request.getHeader(X_FORWARDED_PROTO);
        String host = request.getHeader(X_FORWARDED_HOST);
        String port = request.getHeader(X_FORWARDED_PORT);
        if (StringUtils.hasText(proto) && StringUtils.hasText(host) && StringUtils.hasText(port)) {
            String requestBaseUrl = proto + "://" + host + ":" + port;
            executionContext.setRequestBaseUrl(requestBaseUrl);
        } else {
            executionContext.setRequestBaseUrl(environment.getRequiredProperty("core.baseUrl"));
        }
        String userAgent = request.getHeader(USER_AGENT_HEADER);
        if (!Strings.isNullOrEmpty(userAgent)) {
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
            executionContext.setBrowserName(browser);
            executionContext.setOsName(Optional
                    .ofNullable(ua.getOperatingSystem())
                    .map(OperatingSystem::getName)
                    .orElse(null));
            Matcher matcher = Pattern.compile("ykestate\\/(\\d+(\\.\\d+){1,2})")
                    .matcher(userAgent);
            if (matcher.matches()) {
                executionContext.setAppVersion("iOS/" + matcher.group(1));
            }
        }
        logger.info(buildRequestLog(request));
    }

    @Override
    public void onResponse(HttpServletResponse response) {
        logger.info(buildResponseLog(response));
    }

    @Override
    public void onComplete() {
        executionContext.clear();
    }

    private String buildRequestLog(HttpServletRequest request) {
        StringBuilder accessLog = new StringBuilder();
        append(accessLog, request.getRemoteHost());
        append(accessLog, request.getHeader(FORWARDED_FOR_HEADER));
        append(accessLog, request.getRemoteUser());
        append(accessLog, request.getMethod());
        append(accessLog, executionContext.getRequestPath());
        append(accessLog, request.getHeader(REFERER_HEADER));
        append(accessLog, request.getHeader(USER_AGENT_HEADER));
        return accessLog.toString().substring(1);
    }

    private String buildResponseLog(HttpServletResponse response) {
        StringBuilder responseLog = new StringBuilder();
        responseLog.append(Integer.toString(response.getStatus()));
        append(responseLog, response.getHeader("Content-Length"));
        append(responseLog, response.getHeader("ETag"));
        return responseLog.toString();
    }

    private String buildCorrelationId(HttpServletRequest request) {
        String id = request.getHeader(CORRELATION_ID_HEADER);
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
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
}
