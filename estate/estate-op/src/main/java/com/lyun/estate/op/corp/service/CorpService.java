package com.lyun.estate.op.corp.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.base.Strings;
import com.lyun.estate.op.corp.entity.*;
import com.lyun.estate.op.corp.repo.CommentRepo;
import com.lyun.estate.op.corp.repo.CorpRepo;
import com.lyun.estate.op.utils.CorpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by localuser on 2017/5/15.
 */
@Service
public class CorpService {
    @Autowired
    private CorpRepo corpRepo;

    @Autowired
    private CommentRepo commentRepo;

    public List<Corp> search(String name) {

        List<Corp> corps = new ArrayList<>();

        if (!Strings.isNullOrEmpty(name)) {
            corps.addAll(corpRepo.search(name));
        }

        return corps;
    }

    @Transactional
    public CorpDetail getDetail(long id) {

        Corp corp = corpRepo.get(id);
        if (corp == null) {
            throw new NoDataFoundException();
        }

        corpRepo.increaseVisitCount(id);

        List<String> tagStrs = corpRepo.getTagStrs(id);

        CorpDetail detail = new CorpDetail(corp);

        detail.setTags(CorpUtil.countTag(tagStrs));

        return detail;
    }

    public List<Comment> getCorpComments(long id, String token, PageBounds pageBounds) {
        List<RawComment> raws = commentRepo.getCorpComments(id, pageBounds.getOffset(), pageBounds.getLimit());

        List<Long> likes = new ArrayList<>();
        if (token != null) {
            long userId = CorpUtil.getUserId(token);
            likes = commentRepo.getMyLikes(userId);
        }

        List<Comment> comments = new ArrayList<>();
        for (RawComment r : raws) {
            r.setCorpId(id);
            Comment comment = new Comment(r);
            String[] tags = r.getTags().split("_");
            comment.setTags(Arrays.asList(tags));
            comment.setLiked(likes.contains(r.getId()));
            comments.add(comment);
        }

        return comments;
    }

    public void create(String name, long userId) {

        List<Corp> corps = corpRepo.searchExactSame(name);

        if (corps != null && corps.size() > 0) {
            throw new BizRuntimeException("corp " + name + " already exists");
        }
        corpRepo.create(name, userId);
    }


    public JudgeStatus getMyJudgement(long corpId, long userId) {
        String judgement = corpRepo.getJudgementStatus(corpId, userId);
        if (judgement == null) {
            return JudgeStatus.NOT_YET;
        }

        return JudgeStatus.valueOf(judgement);
    }

    @Transactional
    public ActionResultBean good(long corpId, long userId) {
        JudgeStatus status = getMyJudgement(corpId, userId);

        ActionResultBean arb = new ActionResultBean();

        if (status == JudgeStatus.NOT_YET) {

            corpRepo.createJudgement(corpId, userId, JudgeStatus.GOOD_SELECTED.name());
            corpRepo.increasePositiveCount(corpId);

            arb.setSuccess(true);

        } else if (status == JudgeStatus.GOOD_SELECTED) {

            arb.setSuccess(false).setReason("你已经给过好评了，不能重复好评.");

        } else if (status == JudgeStatus.BAD_SELECTED) {

            corpRepo.updateJudgement(corpId, userId, JudgeStatus.GOOD_SELECTED.name());
            corpRepo.increasePositiveCount(corpId);
            corpRepo.discreaseNegativeCount(corpId);

            arb.setSuccess(true);
        }

        return arb;
    }

    @Transactional
    public ActionResultBean bad(long corpId, long userId) {
        JudgeStatus status = getMyJudgement(corpId, userId);
        ActionResultBean arb = new ActionResultBean();

        if (status == JudgeStatus.NOT_YET) {

            corpRepo.createJudgement(corpId, userId, JudgeStatus.BAD_SELECTED.name());
            corpRepo.increaseNegativeCount(corpId);
            arb.setSuccess(true);

        } else if (status == JudgeStatus.BAD_SELECTED) {
            //do nothing
            arb.setSuccess(false).setReason("你已经给过差评了，不能重复差评.");
        } else if (status == JudgeStatus.GOOD_SELECTED) {
            corpRepo.updateJudgement(corpId, userId, JudgeStatus.BAD_SELECTED.name());
            corpRepo.increaseNegativeCount(corpId);
            corpRepo.discreasePositiveCount(corpId);
            arb.setSuccess(true);
        }

        return arb;

    }

}
