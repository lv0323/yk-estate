package com.lyun.estate.biz.application;

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
        return null;
    }

    @Override
    public void approve(CommonApplicationEntity commonApplicationEntity) {
        long fangId = commonApplicationEntity.getDomainId();
        HouseSubProcess houseProcessFrom = HouseSubProcess.valueOf(commonApplicationEntity.getDomainFrom());
        HouseSubProcess houseProcessTo = HouseSubProcess.valueOf(commonApplicationEntity.getDomainTo());

        Fang fang = mgtFangService.getFangBase(fangId);
        if (fang.getSubProcess() != houseProcessFrom) {
            // todo
            return;
        }

        switch (houseProcessTo) {
            case PUBLIC:
                fangProcessService.confirmPublic(fangId);
                break;
            case NONE:
                fangProcessService.undoPublic(fangId);
                break;
            default:
                break;
        }
    }

    @Override
    public void reject(CommonApplicationEntity commonApplicationEntity) {
        // do nothing
        return;
    }

    @Override
    public void close(CommonApplicationEntity commonApplicationEntity) {
        // do nothing
        return;
    }

}
