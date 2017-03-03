package com.lyun.estate.mgt.audit;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.entity.AuditDTO;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.core.supports.resolvers.PageBoundsArgumentResolver;
import com.lyun.estate.mgt.context.MgtContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@RestController("audits")
public class AuditRest {
    private MgtContext mgtContext;
    private AuditService auditService;

    public AuditRest(MgtContext mgtContext, AuditService auditService) {
        this.mgtContext = mgtContext;
        this.auditService = auditService;
    }

    @GetMapping("")
    public PageList<AuditDTO> listBySubjectIdDescOrdered(@RequestParam AuditSubject subject,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                                         @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return auditService.findBySubjectIdDescOrdered(mgtContext.getOperator().getCompanyId(),
                subject,
                startDate,
                Optional.ofNullable(endDate)
                        .map(time -> Date.from(time.toInstant().plusSeconds(LocalTime.MAX.toSecondOfDay())))
                        .orElse(null),
                pageBounds);
    }
}
