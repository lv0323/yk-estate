package com.lyun.estate.op.corp.service;

import com.google.common.base.Strings;
import com.lyun.estate.op.corp.entity.*;
import com.lyun.estate.op.corp.repo.CommentRepo;
import com.lyun.estate.op.corp.repo.CorpRepo;
import com.lyun.estate.op.utils.CorpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by localuser on 2017/5/15.
 */
@Service
public class CorpService {
    @Autowired
    private CorpRepo corpRepo;

    @Autowired
    private CommentRepo commentRepo;

    public List<Corp> search(String name){

        List<Corp> corps = new ArrayList<>();

        if(!Strings.isNullOrEmpty(name)){
            corps.addAll(corpRepo.search(name));
        }

        return corps;
    }
    @Transactional
    public CorpDetail getDetail(long id){

        Corp corp = corpRepo.get(id);
        if(corp == null){
            throw new NoDataFoundException();
        }

        corpRepo.increaseVisitCount(id);

        List<String> tagStrs = corpRepo.getTagStrs(id);

        CorpDetail detail = new CorpDetail(corp);

        detail.setTags( CorpUtil.countTag(tagStrs) );

        return detail;
    }

    public List<Comment> getComments(long id){
        List<RawComment> raws = commentRepo.getComments(id);

        List<Comment> comments = new ArrayList<>();

        for (RawComment tmp : raws){
            Comment comment = new Comment(tmp);

            String[] tags = tmp.getTags().split("_");

            comment.setTags(Arrays.asList(tags));
            comments.add(comment);

        }

        return comments;
    }

    public void create(String name, long userId){
        corpRepo.create(name, userId);
    }


    public JudgeStatus getMyJudgement(long corpId, long userId){
        String judgement = corpRepo.getJudgementStatus(corpId, userId);
        if(judgement == null){
            return JudgeStatus.NOT_YET;
        }

        return JudgeStatus.valueOf(judgement);
    }
    @Transactional
    public void good(long corpId, long userId){
        JudgeStatus status = getMyJudgement(corpId, userId);

        if(status == JudgeStatus.NOT_YET){

            corpRepo.createJudgement(corpId, userId, JudgeStatus.GOOD_SELECTED.name());
            corpRepo.increasePositiveCount(corpId);

        }else if(status == JudgeStatus.GOOD_SELECTED){
            //do nothing
        } else if(status == JudgeStatus.BAD_SELECTED){
            corpRepo.updateJudgement(corpId, userId, JudgeStatus.GOOD_SELECTED.name());
            corpRepo.increasePositiveCount(corpId);
            corpRepo.discreaseNegativeCount(corpId);
        }

    }
    @Transactional
    public void bad(long corpId, long userId){
        JudgeStatus status = getMyJudgement(corpId, userId);

        if(status == JudgeStatus.NOT_YET){

            corpRepo.createJudgement(corpId, userId, JudgeStatus.BAD_SELECTED.name());
            corpRepo.increasePositiveCount(corpId);

        }else if(status == JudgeStatus.BAD_SELECTED){
            //do nothing
        } else if(status == JudgeStatus.GOOD_SELECTED){
            corpRepo.updateJudgement(corpId, userId, JudgeStatus.BAD_SELECTED.name());
            corpRepo.increaseNegativeCount(corpId);
            corpRepo.discreasePositiveCount(corpId);
        }

    }

}
