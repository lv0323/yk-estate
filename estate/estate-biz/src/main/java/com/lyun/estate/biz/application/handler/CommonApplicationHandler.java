package com.lyun.estate.biz.application.handler;

import com.lyun.estate.biz.application.entity.CommonApplicationEntity;

public interface CommonApplicationHandler {
    CommonApplicationEntity create(CommonApplicationEntity.Type type, long applicantId, String applyReason, long domainId);
    void approve(CommonApplicationEntity commonApplicationEntity, boolean isForceApprove);
    void reject(CommonApplicationEntity commonApplicationEntity);
    void close(CommonApplicationEntity commonApplicationEntity);
}
