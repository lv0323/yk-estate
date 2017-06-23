package com.lyun.estate.biz.application.handler;

import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import org.springframework.stereotype.Service;

@Service
public class FangTagApplicationHandler implements CommonApplicationHandler {

    @Override
    public CommonApplicationEntity create(CommonApplicationEntity.Type type, long applicantId, String applyReason, long domainId) {
        return null;
    }

    @Override
    public void approve(CommonApplicationEntity commonApplicationEntity) {
        // add tag if needed
    }

    @Override
    public void reject(CommonApplicationEntity commonApplicationEntity) {
        // do things when reject
    }

    @Override
    public void close(CommonApplicationEntity commonApplicationEntity) {
        // do things when close
    }

}
