package com.lyun.estate.biz.franchiseeapply.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.franchiseeapply.entity.FranchiseeApply;
import com.lyun.estate.biz.franchiseeapply.repository.FranchiseeApplyRepo;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranchiseeApplyService {

    @Autowired
    private FranchiseeApplyRepo franchiseeApplyRepo;

    public FranchiseeApply create(FranchiseeApply apply) {
        ExceptionUtil.checkNotNull("申请信息", apply);
        ExceptionUtil.checkNotNull("姓名", apply.getName());
        ExceptionUtil.checkNotNull("手机", apply.getMobile());
        franchiseeApplyRepo.create(apply);
        return franchiseeApplyRepo.findOne(apply.getId());
    }

    public List<FranchiseeApply> list(PageBounds pageBounds) {
        return franchiseeApplyRepo.list(pageBounds);
    }

}
