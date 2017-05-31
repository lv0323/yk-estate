package com.lyun.estate.op.corp;

import com.lyun.estate.op.corp.entity.BizIllegalArgumentException;
import com.lyun.estate.op.corp.entity.*;
import com.lyun.estate.op.corp.service.CorpService;
import com.lyun.estate.op.utils.CorpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by localuser on 2017/5/15.
 */

@RestController
@RequestMapping("/corps")
public class CorpController {

    @Autowired
    private CorpService corpService;

    @GetMapping("/search")
    public List<Corp> search(@RequestParam String corpName) {

        return corpService.search(corpName);
    }

    @GetMapping(value = "/{corpId}")
    public CorpDetail getDetail(@PathVariable long corpId) {

        return corpService.getDetail(corpId);

    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultBean create(@RequestHeader String token, @RequestParam String name) {

        long userId = CorpUtil.getUserId(token);
        corpService.create(name, userId);

        return new ActionResultBean().setSuccess(true);

    }

    @GetMapping(value = "/{corpId}/comments")
    public List<Comment> getComments(@PathVariable long corpId) {

        return corpService.getComments(corpId);

    }

    @GetMapping(value = "/{corpId}/judgement_my")
    public JudgeStatus getJudgementStatus(@RequestHeader String token, @PathVariable long corpId) {

        long userId = CorpUtil.getUserId(token);

        return corpService.getMyJudgement(corpId, userId);
    }

    @PostMapping(value = "/{corpId}/judge_good", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultBean good(@RequestHeader String token, @PathVariable long corpId) {

        long userId = CorpUtil.getUserId(token);

        corpService.good(corpId, userId);
        return new ActionResultBean().setSuccess(true);

    }
    @PostMapping(value = "/{corpId}/judge_bad", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultBean bad(@RequestHeader String token, @PathVariable long corpId) {

        long userId = CorpUtil.getUserId(token);
        corpService.bad(corpId, userId);
        return new ActionResultBean().setSuccess(true);

    }
}
























