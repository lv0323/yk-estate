package com.lyun.estate.biz.audit.service;

import com.lyun.estate.biz.audit.entity.Audit;
import com.lyun.estate.biz.audit.repo.AuditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
