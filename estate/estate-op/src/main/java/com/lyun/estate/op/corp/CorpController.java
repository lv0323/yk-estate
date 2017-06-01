package com.lyun.estate.op.corp;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.op.corp.entity.*;
import com.lyun.estate.op.corp.service.CorpService;
import com.lyun.estate.op.utils.CorpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Comment> getCorpComments(@PathVariable long corpId, @RequestHeader("X-PAGING")PageBounds pageBounds) {

        return corpService.getCorpComments(corpId, pageBounds);

    }

    @GetMapping(value = "/{corpId}/judgement_my")
    public JudgeStateResponse getJudgementStatus(@RequestHeader String token, @PathVariable long corpId) {

        long userId = CorpUtil.getUserId(token);

        JudgeStateResponse response = new JudgeStateResponse();
        response.setStatus(corpService.getMyJudgement(corpId, userId).toString());

        return response;
    }

    @PostMapping(value = "/{corpId}/judge_good", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultBean good(@RequestHeader String token, @PathVariable long corpId) {

        long userId = CorpUtil.getUserId(token);

        return corpService.good(corpId, userId);

    }
    @PostMapping(value = "/{corpId}/judge_bad", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultBean bad(@RequestHeader String token, @PathVariable long corpId) {

        long userId = CorpUtil.getUserId(token);
        return corpService.bad(corpId, userId);
    }
}
























