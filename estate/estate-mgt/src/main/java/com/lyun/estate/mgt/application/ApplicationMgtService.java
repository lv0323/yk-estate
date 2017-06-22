package com.lyun.estate.mgt.application;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.application.CommonApplicationEntity;
import com.lyun.estate.biz.application.CommonApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicationMgtService {

    @Autowired
    private CommonApplicationService commonApplicationService;


    public CommonApplicationEntity create(CommonApplicationEntity.Type type, long applicantId, String applyReason, long domainId) {
        return commonApplicationService.create(type, applicantId, applyReason, domainId);
    }

    public void approve(long applicationId, long reviewerId, String reviewerComments) {
        commonApplicationService.approve(applicationId, reviewerId, reviewerComments);
    }

    public void reject(long applicationId, long reviewerId, String reviewerComments) {
        commonApplicationService.reject(applicationId, reviewerId, reviewerComments);
    }

    public void close(long applicationId, long reviewerId, String reviewerComments) {
        commonApplicationService.close(applicationId, reviewerId, reviewerComments);
    }

    public List<CommonApplicationEntity> findApplications(CommonApplicationEntity.Type type, long id, long applicantId, CommonApplicationEntity.Status status, Date startTime, Date endTime, PageBounds pageBounds) {
        return commonApplicationService.findApplications(type, id, applicantId, status, startTime, endTime, pageBounds);
    }

}
