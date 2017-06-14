package com.lyun.estate.biz.approval.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.entity.Approval;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-06-14.
 */
@Repository
public interface ApprovalRepo {

    int create(Approval approval);

    Approval findOne(Long id);

    PageList<Approval> list(ApprovalDefine.Type type, ApprovalDefine.Status status,
                            Long applyCompanyId, Long applyDeptId, Long applyId,
                            PageBounds pageBounds);

    Approval findOneForUpdate(Long id);
}
