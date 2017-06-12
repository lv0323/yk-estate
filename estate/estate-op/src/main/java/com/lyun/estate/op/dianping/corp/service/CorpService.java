package com.lyun.estate.op.dianping.corp.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.base.Strings;
import com.lyun.estate.op.dianping.comment.domain.CommentDTO;
import com.lyun.estate.op.dianping.comment.entity.Comment;
import com.lyun.estate.op.dianping.common.BizRuntimeException;
import com.lyun.estate.op.dianping.common.NoDataFoundException;
import com.lyun.estate.op.dianping.corp.domain.ActionResultDTO;
import com.lyun.estate.op.dianping.corp.domain.CorpDetailDTO;
import com.lyun.estate.op.dianping.corp.entity.*;
import com.lyun.estate.op.dianping.comment.repo.CommentRepo;
import com.lyun.estate.op.dianping.corp.repo.CorpRepo;
import com.lyun.estate.op.dianping.utils.CorpUtil;
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
    public CorpDetailDTO getDetail(long id) {

        Corp corp = corpRepo.get(id);
        if (corp == null) {
            throw new NoDataFoundException();
        }

        corpRepo.increaseVisitCount(id);

        List<String> tagStrs = corpRepo.getTagStrs(id);

        CorpDetailDTO detail = new CorpDetailDTO(corp);

        detail.setTags(CorpUtil.countTag(tagStrs));

        return detail;
    }

    public List<CommentDTO> getCorpComments(long id, String token, PageBounds pageBounds) {
        List<Comment> raws = commentRepo.getCorpComments(id, pageBounds.getOffset(), pageBounds.getLimit());

        List<Long> likes = new ArrayList<>();
        if (token != null) {
            long userId = CorpUtil.getUserId(token);
            likes = commentRepo.getMyLikes(userId);
        }

        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment r : raws) {
            r.setCorpId(id);
            CommentDTO commentDTO = new CommentDTO(r);
            String[] tags = r.getTags().split("_");
            commentDTO.setTags(Arrays.asList(tags));
            commentDTO.setLiked(likes.contains(r.getId()));
            commentDTOS.add(commentDTO);
        }

        return commentDTOS;
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
    public ActionResultDTO good(long corpId, long userId) {
        JudgeStatus status = getMyJudgement(corpId, userId);

        ActionResultDTO arb = new ActionResultDTO();

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
    public ActionResultDTO bad(long corpId, long userId) {
        JudgeStatus status = getMyJudgement(corpId, userId);
        ActionResultDTO arb = new ActionResultDTO();

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
