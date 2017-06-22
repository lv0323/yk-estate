package com.lyun.estate.biz.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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

    public int create(CommonApplicationEntity commonApplicationEntity) {
        return commonApplicationRepo.create(commonApplicationEntity);
    }

    @Transactional
    public void approve(long applicationId, String reviewerComments, long reviewerId) {
        CommonApplicationEntity commonApplicationEntity = getApplicationEntity(applicationId, reviewerComments, reviewerId);

        getApplicationHandler(commonApplicationEntity.getType()).ifPresent(handler -> handler.approve(commonApplicationEntity));

        commonApplicationRepo.updateStatusById(applicationId, CommonApplicationEntity.Status.APPROVED, reviewerId, reviewerComments);
    }

    @Transactional
    public void reject(long applicationId, String reviewerComments, long reviewerId) {
        CommonApplicationEntity commonApplicationEntity = getApplicationEntity(applicationId, reviewerComments, reviewerId);

        getApplicationHandler(commonApplicationEntity.getType()).ifPresent(handler -> handler.reject(commonApplicationEntity));

        commonApplicationRepo.updateStatusById(applicationId, CommonApplicationEntity.Status.REJECTED, reviewerId, reviewerComments);
    }

    @Transactional
    public void close(long applicationId, String reviewerComments, long reviewerId) {
        CommonApplicationEntity commonApplicationEntity = getApplicationEntity(applicationId, reviewerComments, reviewerId);

        getApplicationHandler(commonApplicationEntity.getType()).ifPresent(handler -> handler.close(commonApplicationEntity));

        commonApplicationRepo.updateStatusById(applicationId, CommonApplicationEntity.Status.CLOSED_BY_APPLICANT, reviewerId, reviewerComments);
    }

    private CommonApplicationEntity getApplicationEntity(long applicationId, String reviewerComments, long reviewerId) {
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
            case HOUSE_SUB_PROCESS_APPROVAL:
                return Optional.of(houseSubProcessApplicationHandler);
            case FANG_TAG_APPROVAL:
                return Optional.of(fangTagApplicationHandler);
            default:
                return Optional.empty();
        }
    }

}
