package com.lyun.estate.biz.approval.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.domain.ApprovalDTO;
import com.lyun.estate.biz.approval.entity.Approval;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-06-14.
 */
@Repository
public interface ApprovalRepo {

    @Insert("INSERT INTO t_approval (apply_id, type, status, data) VALUES (#{applyId}, #{type}, 'CREATED', #{data}::jsonb);")
    @Options(useGeneratedKeys = true)
    int create(Approval approval);

    @Select("SELECT * FROM t_approval WHERE id = #{id};")
    Approval findOne(Long id);

    PageList<ApprovalDTO> list(ApprovalDefine.Type type, ApprovalDefine.Status status,
                               Long applyCompanyId, Long applyDeptId, Long applyId,
                               PageBounds pageBounds);

    Approval findOneForUpdate(Long id);
}
