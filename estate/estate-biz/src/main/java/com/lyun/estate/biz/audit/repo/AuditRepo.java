package com.lyun.estate.biz.audit.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.entity.Audit;
import com.lyun.estate.biz.audit.repo.provider.AuditSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-15.
 */
public interface AuditRepo {
    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO t_audit (company_id, department_id, operator_id, subject, target_id, domain_type, content) " +
            "VALUES (#{companyId}, #{departmentId}, #{operatorId}, #{subject}, #{targetId}, #{domainType}, #{content})")
    int save(Audit audit);

    @Select("SELECT * FROM t_audit WHERE id = #{id}")
    Audit findOne(Long id);

    @SelectProvider(type = AuditSqlProvider.class, method = "findBySubject")
    PageList<Audit> findBySubject(@Param("companyId") Long companyId, @Param("subject") AuditSubject subject,
                                  @Param("startTime") Date startTime,
                                  @Param("endTime") Date endTime, PageBounds pageBounds);
}
