package com.lyun.estate.op.dianping.user.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class WeChatProperty {

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Value("${dianping.wechat.loginurl}")
    private String loginUrl;

    public WeChatProperty() {
    }

    @PostConstruct
    public void init() {
//        System.out.println("================== " + loginUrl + "================== ");
    }
}