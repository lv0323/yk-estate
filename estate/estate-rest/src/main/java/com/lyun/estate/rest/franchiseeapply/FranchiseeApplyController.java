package com.lyun.estate.rest.franchiseeapply;

import com.lyun.estate.biz.franchiseeapply.entity.FranchiseeApply;
import com.lyun.estate.biz.franchiseeapply.service.FranchiseeApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("franchisee-apply")
public class FranchiseeApplyController {

    @Autowired
    FranchiseeApplyService franchiseeApplyService;

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    FranchiseeApply create(FranchiseeApply apply) {
        return franchiseeApplyService.create(apply);
    }
}
