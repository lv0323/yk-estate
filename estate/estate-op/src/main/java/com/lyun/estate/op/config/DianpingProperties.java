package com.lyun.estate.op.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DianpingProperties {

    @Value("${dianping.wechat.loginurl}")
    private String weChatLoginUrl;

    @Value("${dianping.token.secret}")
    private String tokenSecret;

    @Value("${dianping.cache.timelimit}")
    private long cacheTimeLimit;


    public DianpingProperties() {}

    public String getWeChatLoginUrl() {
        return weChatLoginUrl;
    }

    public void setWeChatLoginUrl(String weChatLoginUrl) {
        this.weChatLoginUrl = weChatLoginUrl;
    }


    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public long getCacheTimeLimit() {
        return cacheTimeLimit;
    }

    public void setCacheTimeLimit(long cacheTimeLimit) {
        this.cacheTimeLimit = cacheTimeLimit;
    }

    @PostConstruct
    public void init() {}
}