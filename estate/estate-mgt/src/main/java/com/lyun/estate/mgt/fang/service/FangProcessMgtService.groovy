package com.lyun.estate.mgt.fang.service

import com.lyun.estate.biz.audit.def.AuditSubject
import com.lyun.estate.biz.audit.service.AuditService
import com.lyun.estate.biz.fang.entity.Fang
import com.lyun.estate.biz.fang.entity.FangInfoOwner
import com.lyun.estate.biz.fang.service.FangProcessService
import com.lyun.estate.biz.support.def.DomainType
import com.lyun.estate.mgt.context.MgtContext
import com.lyun.estate.mgt.context.Operator
import com.lyun.estate.mgt.supports.AuditHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Jeffrey on 2017-03-10.
 */
@Service
class FangProcessMgtService {

    @Autowired
    FangProcessService processService

    @Autowired
    AuditService auditService

    @Autowired
    MgtContext mgtContext

    @Transactional
    Fang publish(long fangId) {
        Operator operator = mgtContext.getOperator()
        FangInfoOwner infoOwner = new FangInfoOwner()
                .setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId())
                .setEmployeeId(operator.getId())

        Fang fang = processService.publish(fangId, infoOwner)

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "上架了授权编号为【" + fang.licenceId + "】的房源")
        )
        return fang
    }

    @Transactional
    Fang unPublish(long fangId) {
        Fang fang = processService.unPublish(fangId)

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "下架了授权编号为【" + fang.licenceId + "】的房源")
        )
        return fang
    }

    @Transactional
    Fang deal(long fangId) {
        Fang fang = processService.deal(fangId)
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "成交了授权编号为【" + fang.licenceId + "】的房源")
        )
        return fang
    }

    @Transactional
    boolean delete(long fangId) {
        Fang fang = processService.delete(fangId)
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "删除了授权房源编号为【" + fang.licenceId + "】信息")
        )
        return true
    }
}
