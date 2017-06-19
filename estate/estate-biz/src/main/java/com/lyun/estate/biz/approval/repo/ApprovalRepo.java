package com.lyun.estate.biz.approval.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.domain.ApprovalDTO;
import com.lyun.estate.biz.approval.entity.Approval;
import com.lyun.estate.biz.approval.repo.provider.ApprovalSqlProvider;
import org.apache.ibatis.annotations.*;
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

    @SelectProvider(type = ApprovalSqlProvider.class, method = "list")
    PageList<ApprovalDTO> list(@Param("type") ApprovalDefine.Type type,
                               @Param("status") ApprovalDefine.Status status,
                               @Param("applyCompanyId") Long applyCompanyId,
                               @Param("applyDeptId") Long applyDeptId,
                               @Param("applyId") Long applyId,
                               PageBounds pageBounds);

    @Select("SELECT * FROM t_approval WHERE id = #{id} FOR UPDATE;")
    Approval findOneForUpdate(Long id);

    @Update("UPDATE t_approval SET approver_id = #{approverId},status = #{status}, approval_time = now(), update_time = now() WHERE id = #{id}")
    int updateStatus(@Param("id") Long id,
                     @Param("approverId") Long approverId,
                     @Param("status") ApprovalDefine.Status status);

    @Update("UPDATE t_approval SET approver_id = #{approverId},status = #{status},data = #{data}::jsonb, approval_time = now(), update_time = now() WHERE id = #{id}")
    int updateStatusAndData(@Param("id") Long approvalId,
                            @Param("approverId") Long approverId,
                            @Param("data") String data,
                            @Param("status") ApprovalDefine.Status status);
}
