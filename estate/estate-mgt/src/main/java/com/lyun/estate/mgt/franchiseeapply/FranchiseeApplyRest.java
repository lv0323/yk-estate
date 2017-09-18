package com.lyun.estate.mgt.franchiseeapply;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.franchiseeapply.entity.FranchiseeApply;
import com.lyun.estate.biz.franchiseeapply.service.FranchiseeApplyService;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("franchisee-apply")
public class FranchiseeApplyRest {

    @Autowired
    FranchiseeApplyService franchiseeApplyService;

    @GetMapping("list")
    List<FranchiseeApply> list(@RequestHeader(required = false,
            name = PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return franchiseeApplyService.list(pageBounds);
    }
}
