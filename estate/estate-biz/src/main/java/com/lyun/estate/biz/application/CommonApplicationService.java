package com.lyun.estate.biz.application;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.biz.application.handler.CommonApplicationHandler;
import com.lyun.estate.biz.application.handler.FangTagApplicationHandler;
import com.lyun.estate.biz.application.handler.HouseProcessApplicationHandler;
import com.lyun.estate.biz.application.handler.HouseSubProcessApplicationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.lyun.estate.biz.application.repo.CommonApplicationRepo;

@Service
public class CommonApplicationService {

    @Autowired
    private HouseProcessApplicationHandler houseProcessApplicationHandler;
    @Autowired
    private HouseSubProcessApplicationHandler houseSubProcessApplicationHandler;
    @Autowired
    private FangTagApplicationHandler fangTagApplicationHandler;

    @Autowired
    private CommonApplicationRepo commonApplicationRepo;

    public CommonApplicationEntity create(CommonApplicationEntity.Type type, long applicantId, String applyReason, long domainId) {
        CommonApplicationHandler handler = getApplicationHandler(type).get();
        CommonApplicationEntity commonApplicationEntity = handler.create(type, applicantId, applyReason, domainId);

        commonApplicationRepo.create(commonApplicationEntity);

        return commonApplicationEntity;
    }

    @Transactional
    public int approve(long applicationId, long reviewerId, String reviewerComments, boolean isForceApprove) {
        CommonApplicationEntity commonApplicationEntity = getApplicationEntity(applicationId, reviewerId, reviewerComments);

        getApplicationHandler(commonApplicationEntity.getType()).ifPresent(handler -> handler.approve(commonApplicationEntity, isForceApprove));

        return commonApplicationRepo.updateStatusById(applicationId, CommonApplicationEntity.Status.APPROVED, reviewerId, reviewerComments);
    }

    @Transactional
    public int reject(long applicationId, long reviewerId, String reviewerComments) {
        CommonApplicationEntity commonApplicationEntity = getApplicationEntity(applicationId, reviewerId, reviewerComments);

        getApplicationHandler(commonApplicationEntity.getType()).ifPresent(handler -> handler.reject(commonApplicationEntity));

        return commonApplicationRepo.updateStatusById(applicationId, CommonApplicationEntity.Status.REJECTED, reviewerId, reviewerComments);
    }

    @Transactional
    public int close(long applicationId, long reviewerId, String reviewerComments) {
        CommonApplicationEntity commonApplicationEntity = getApplicationEntity(applicationId, reviewerId, reviewerComments);

        getApplicationHandler(commonApplicationEntity.getType()).ifPresent(handler -> handler.close(commonApplicationEntity));

        return commonApplicationRepo.updateStatusById(applicationId, CommonApplicationEntity.Status.CLOSED, reviewerId, reviewerComments);
    }

    public List<CommonApplicationEntity> findApplications(List<CommonApplicationEntity.Type> types, long id, long applicantId, List<CommonApplicationEntity.Status> status, Date startTime, Date endTime, PageBounds pageBounds) {
        return commonApplicationRepo.findApplications(types, id, applicantId, status, startTime, endTime, pageBounds);
    }

    private CommonApplicationEntity getApplicationEntity(long applicationId, long reviewerId, String reviewerComments) {
        CommonApplicationEntity commonApplicationEntity = commonApplicationRepo.findOneById(applicationId);

        commonApplicationEntity
                .setReviewerComments(reviewerComments)
                .setReviewerId(reviewerId);

        return commonApplicationEntity;
    }

    private Optional<CommonApplicationHandler> getApplicationHandler(CommonApplicationEntity.Type type) {
        switch (type) {
            case PAUSE_HOUSE:
                return Optional.of(houseProcessApplicationHandler);
            case PUBLISH_HOUSE:
                return Optional.of(houseProcessApplicationHandler);
            case UN_PUBLISH_HOUSE:
                return Optional.of(houseProcessApplicationHandler);
            case SUCCESS_HOUSE:
                return Optional.of(houseProcessApplicationHandler);
            case UN_PUBLIC_HOUSE:
                return Optional.of(houseSubProcessApplicationHandler);
            case PUBLIC_HOUSE:
                return Optional.of(houseSubProcessApplicationHandler);
            case FANG_TAG_APPROVAL:
                return Optional.of(fangTagApplicationHandler);
            default:
                return Optional.empty();
        }
    }

}
