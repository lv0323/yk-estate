package com.lyun.estate.op.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DianpingProperties {

    public String getWeChatLoginUrl() {
        return weChatLoginUrl;
    }

    public void setWeChatLoginUrl(String weChatLoginUrl) {
        this.weChatLoginUrl = weChatLoginUrl;
    }

    @Value("${dianping.wechat.loginurl}")
    private String weChatLoginUrl;

    public DianpingProperties() {
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    @Value("${dianping.token.secret}")
    private String tokenSecret;

    @PostConstruct
    public void init() {
//        System.out.println("================== " + weChatLoginUrl + "================== ");
//        System.out.println("================== " + tokenSecret + "================== ");
    }
}