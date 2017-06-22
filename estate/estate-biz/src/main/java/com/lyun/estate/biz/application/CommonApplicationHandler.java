package com.lyun.estate.biz.application;

public interface CommonApplicationHandler {
    void approve(CommonApplicationEntity commonApplicationEntity);
    void reject(CommonApplicationEntity commonApplicationEntity);
    void close(CommonApplicationEntity commonApplicationEntity);
}
