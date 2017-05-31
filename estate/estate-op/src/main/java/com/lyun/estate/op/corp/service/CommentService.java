package com.lyun.estate.op.corp.service;

import com.google.common.base.Strings;
import com.lyun.estate.op.corp.entity.*;
import com.lyun.estate.op.corp.repo.CommentRepo;
import com.lyun.estate.op.corp.repo.CorpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by localuser on 2017/5/15.
 */
@Service
public class CommentService {
    @Autowired
    private CorpRepo corpRepo;

    @Autowired
    private CommentRepo commentRepo;

    public void create(long userId, long corpId, List<String> tags, String shopfront, String content){

        String tagStr = String.join("_", tags);
        commentRepo.create(userId, corpId, tagStr, shopfront, content);

        corpRepo.increaseCommentCount(corpId);
    }

    public void like(long commentId, long userId){
        if(commentRepo.liked(commentId, userId) <= 0){
            commentRepo.like(commentId, userId);
            commentRepo.increaseLike(commentId, userId);
            return;
        }
        throw new IllegalArgumentException("comment_like (comment, userId)=("+commentId + ", "+userId +") liked");
    }

    public void cancelLike(long commentId, long userId){
        commentRepo.cancelLike(commentId, userId);
        commentRepo.descreaseLike(commentId, userId);
    }

    public boolean liked(long commentId, long userId){
        if(commentRepo.liked(commentId, userId) <= 0){
            return false;
        }
        return true;
    }

}
