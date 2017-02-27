package com.lyun.estate.biz.audit.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.entity.Audit;
import com.lyun.estate.biz.audit.entity.AuditDTO;
import com.lyun.estate.biz.audit.repo.AuditRepo;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-15.
 */
@Service
public class AuditService {

    @Autowired
    private AuditRepo auditRepo;

    public Audit save(Audit audit) {
        auditRepo.save(audit);
        return auditRepo.findOne(audit.getId());
    }

    public PageList<AuditDTO> findBySubject(Long companyId, AuditSubject subject, Date startTime, Date endTime,
                                            PageBounds pageBounds) {

        ExceptionUtil.checkNotNull("公司编号", companyId);
        ExceptionUtil.checkNotNull("主题", subject);

        return auditRepo.findBySubject(companyId, subject, startTime, endTime, pageBounds);
    }

}
