package com.lyun.estate.op.corp.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.base.Strings;
import com.lyun.estate.op.corp.entity.*;
import com.lyun.estate.op.corp.repo.CommentRepo;
import com.lyun.estate.op.corp.repo.CorpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
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

        if( liked(commentId, userId) ){
            throw new IllegalArgumentException("(comment, userId)=("+commentId + ", "+userId +") already exist, maybe you have liked it");
        }

        commentRepo.like(commentId, userId);
        commentRepo.increaseLike(commentId, userId);

    }

    public void cancelLike(long commentId, long userId){
        if( !liked(commentId, userId) ){
            throw new IllegalArgumentException("(comment, userId)=("+commentId + ", "+userId +") does not exist, maybe you have not liked");
        }
        commentRepo.cancelLike(commentId, userId);
        commentRepo.descreaseLike(commentId, userId);

    }

    public boolean liked(long commentId, long userId){
        if(commentRepo.liked(commentId, userId) > 0){
            return true;
        }
        return false;
    }

    public List<Comment> myComments(long userId, PageBounds pageBounds){
        List<RawComment> raws = commentRepo.myComments(userId, pageBounds.getOffset(), pageBounds.getLimit());

        List<Comment> comments = new ArrayList<>();

        for (RawComment tmp : raws){
            tmp.setUserId(userId);
            Comment comment = new Comment(tmp);

            String[] tags = tmp.getTags().split("_");

            comment.setTags(Arrays.asList(tags));
            comments.add(comment);

        }

        return comments;
    }

}
