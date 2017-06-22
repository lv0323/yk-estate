package com.lyun.estate.biz.application;

public interface CommonApplicationHandler {
    CommonApplicationEntity create(CommonApplicationEntity.Type type, long applicantId, String applyReason, long domainId);
    void approve(CommonApplicationEntity commonApplicationEntity);
    void reject(CommonApplicationEntity commonApplicationEntity);
    void close(CommonApplicationEntity commonApplicationEntity);
}
