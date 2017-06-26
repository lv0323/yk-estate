package com.lyun.estate.biz.application.handler;

import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.fang.def.HouseProcess;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.biz.fang.service.FangProcessService;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseProcessApplicationHandler implements CommonApplicationHandler {

    @Autowired
    private FangProcessService fangProcessService;

    @Autowired
    private MgtFangService mgtFangService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public CommonApplicationEntity create(CommonApplicationEntity.Type type, long applicantId, String applyReason, long domainId) {
        long fangId = domainId;
        Fang fang = mgtFangService.getFangBase(fangId);
        String domainFrom = fang.getProcess().name();
        String domainTo = "";

        switch (type) {
            case PUBLISH_HOUSE:
                domainTo = HouseProcess.PUBLISH.name();
                break;
            case UN_PUBLISH_HOUSE:
                domainTo = HouseProcess.UN_PUBLISH.name();
                break;
            case PAUSE_HOUSE:
                domainTo = HouseProcess.PAUSE.name();
                break;
            case SUCCESS_HOUSE:
                domainTo = HouseProcess.SUCCESS.name();
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
        HouseProcess houseProcessFrom = HouseProcess.valueOf(commonApplicationEntity.getDomainFrom());

        Fang fang = mgtFangService.getFangBase(fangId);

        /**
         * isForceApprove == true means we will approve the application even when the HouseProcess has been changed.
         * we need to check the currentHouseProcess when isForceApprove == false
         */
        if (!isForceApprove) {
            if (fang.getProcess() != houseProcessFrom) {
                throw new RuntimeException("HouseProcess is already changed, cannot approve this application");
            }
        }

        switch (commonApplicationEntity.getType()) {
            case UN_PUBLISH_HOUSE:
                fangProcessService.unPublish(fangId);
                break;
            case PAUSE_HOUSE:
                fangProcessService.pause(fangId);
                break;
            case PUBLISH_HOUSE:
                Employee employee = employeeService.findById(commonApplicationEntity.getApplicantId());
                FangInfoOwner infoOwner = new FangInfoOwner() {{
                    setCompanyId(employee.getCompanyId());
                    setEmployeeId(employee.getId());
                    setDepartmentId(employee.getDepartmentId());
                }};

                fangProcessService.publish(fangId, infoOwner);
                break;
            case SUCCESS_HOUSE:
                fangProcessService.deal(fangId);
                break;
            default:
                break;
        }


    }

    @Override
    public void reject(CommonApplicationEntity commonApplicationEntity) {
        // no need to change the houseProcess, do nothing for now
    }

    @Override
    public void close(CommonApplicationEntity commonApplicationEntity) {
        // no need to change the houseProcess, do nothing for now
    }

}
