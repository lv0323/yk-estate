package com.lyun.estate.biz.application.handler;

import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.biz.fang.def.HouseSubProcess;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.service.FangProcessService;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseSubProcessApplicationHandler implements CommonApplicationHandler {

    @Autowired
    private FangProcessService fangProcessService;

    @Autowired
    private MgtFangService mgtFangService;

    @Override
    public CommonApplicationEntity create(CommonApplicationEntity.Type type, long applicantId, String applyReason, long domainId) {
        long fangId = domainId;
        Fang fang = mgtFangService.getFangBase(fangId);
        String domainFrom = fang.getSubProcess() == null ? HouseSubProcess.NONE.name() : fang.getSubProcess().name();
        String domainTo = "";

        switch (type) {
            case PUBLIC_HOUSE:
                domainTo = HouseSubProcess.PUBLIC.name();
                break;
            case UN_PUBLIC_HOUSE:
                domainTo = HouseSubProcess.NONE.name();
                break;
            default:
                throw new RuntimeException("invalid type");
        }

        String finalDomainTo = domainTo;

        return new CommonApplicationEntity() {{
            setStatus(Status.NEW);
            setType(type);
            setApplicantId(applicantId);
            setApplyReason(applyReason);
            setDomainFrom(domainFrom);
            setDomainTo(finalDomainTo);
            setDomainId(domainId);
        }};
    }

    @Override
    public void approve(CommonApplicationEntity commonApplicationEntity, boolean isForceApprove) {
        long fangId = commonApplicationEntity.getDomainId();
        HouseSubProcess houseSubProcessFrom = HouseSubProcess.valueOf(commonApplicationEntity.getDomainFrom());

        Fang fang = mgtFangService.getFangBase(fangId);

        HouseSubProcess currentSubProcess = fang.getSubProcess() == null ? HouseSubProcess.NONE: fang.getSubProcess();

        /**
         * isForceApprove == true means we will approve the application even when the HouseSubProcess has been changed.
         * we need to check the currentHouseSubProcess when isForceApprove == false
         */
        if (!isForceApprove) {
            if (houseSubProcessFrom != currentSubProcess) {
                throw new RuntimeException("HouseSubProcess is already changed, cannot approve this application");
            }
        }

        switch (commonApplicationEntity.getType()) {
            case PUBLIC_HOUSE:
                fangProcessService.confirmPublic(fangId);
                break;
            case UN_PUBLIC_HOUSE:
                fangProcessService.undoPublic(fangId);
                break;
            default:
                break;
        }
    }

    @Override
    public void reject(CommonApplicationEntity commonApplicationEntity) {
        // do nothing, no need to change fang's subProcess for now
    }

    @Override
    public void close(CommonApplicationEntity commonApplicationEntity) {
        // do nothing, no need to change fang's subProcess for now
    }

}
