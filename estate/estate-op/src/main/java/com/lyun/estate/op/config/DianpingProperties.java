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


    public DianpingProperties() {
    }

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


    @PostConstruct
    public void init() {
    }
}