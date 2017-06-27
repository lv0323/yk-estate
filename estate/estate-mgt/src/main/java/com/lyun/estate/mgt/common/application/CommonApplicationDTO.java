package com.lyun.estate.mgt.common.application;

import com.lyun.estate.biz.application.entity.CommonApplicationEntity;

import java.util.Date;

public class CommonApplicationDTO<T> {
    CommonApplicationEntity application;
    private T domain;

    public CommonApplicationEntity getApplication() {
        return application;
    }

    public CommonApplicationDTO setApplication(CommonApplicationEntity application) {
        this.application = application;
        return this;
    }

    public T getDomain() {
        return domain;
    }

    public CommonApplicationDTO setDomain(T domain) {
        this.domain = domain;
        return this;
    }
}
