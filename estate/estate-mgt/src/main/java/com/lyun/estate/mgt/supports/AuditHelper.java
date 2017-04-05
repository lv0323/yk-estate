package com.lyun.estate.mgt.supports;

import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.entity.Audit;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;

/**
 * Created by Jeffrey on 2017-04-05.
 */
public class AuditHelper {
    public static Audit build(MgtContext mgtContext, AuditSubject subject, Long targetId, DomainType domainType,
                              String content) {
        return new Audit()
                .setCompanyId(mgtContext.getOperator().getCompanyId())
                .setDepartmentId(mgtContext.getOperator().getDepartmentId())
                .setOperatorId(mgtContext.getOperator().getId())
                .setIp(mgtContext.getUserAddress())
                .setSubject(subject)
                .setTargetId(targetId)
                .setDomainType(domainType)
                .setContent(content);
    }

    public static String operatorName(MgtContext mgtContext) {
        return "【" + mgtContext.getOperator().getDepartmentName()
                + "--" + mgtContext.getOperator().getName() + "】";
    }
}
