package com.lyun.estate.biz.audit.repo;

import com.lyun.estate.biz.audit.entity.Audit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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
}
