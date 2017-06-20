package com.lyun.estate.op.dianping.comment.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.op.config.DianpingProperties;
import com.lyun.estate.op.dianping.comment.domain.CommentDTO;
import com.lyun.estate.op.dianping.comment.entity.Comment;
import com.lyun.estate.op.dianping.comment.repo.CommentRepo;
import com.lyun.estate.op.dianping.common.BizRuntimeException;
import com.lyun.estate.op.dianping.corp.repo.CorpRepo;
import com.lyun.estate.op.dianping.utils.CommentWriteCache;
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
public class CommentService {
    @Autowired
    private CorpRepo corpRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private DianpingProperties properties;

    @Autowired
    private CommentWriteCache commentWriteCache;

    @Transactional
    public void create(long userId, long corpId, List<String> tags, String shopfront, String content) {

        String key = "" + userId + "_" + corpId;

        if (commentWriteCache.get(key).isPresent()) {
            throw new BizRuntimeException("3分钟内不能重复评论同一个公司");
        }

        String tagStr = String.join("_", tags);
        commentRepo.create(userId, corpId, tagStr, shopfront, content);
        corpRepo.increaseCommentCount(corpId);

        commentWriteCache.put(key, System.currentTimeMillis());
    }

    public void like(long commentId, long userId) {

        if (liked(commentId, userId)) {
            throw new IllegalArgumentException("(comment, userId)=(" + commentId + ", " + userId + ") already exist, maybe you have liked it");
        }

        commentRepo.like(commentId, userId);
        commentRepo.increaseLike(commentId);

    }

    public void cancelLike(long commentId, long userId) {
        if (!liked(commentId, userId)) {
            throw new IllegalArgumentException("(comment, userId)=(" + commentId + ", " + userId + ") does not exist, maybe you have not liked");
        }
        commentRepo.cancelLike(commentId, userId);
        commentRepo.descreaseLike(commentId);

    }

    public boolean liked(long commentId, long userId) {
        if (commentRepo.liked(commentId, userId) > 0) {
            return true;
        }
        return false;
    }

    public List<CommentDTO> myComments(long userId, PageBounds pageBounds) {
        List<Comment> raws = commentRepo.myComments(userId, pageBounds.getOffset(), pageBounds.getLimit());

        List<CommentDTO> commentDTOS = new ArrayList<>();

        for (Comment tmp : raws) {
            tmp.setUserId(userId);
            CommentDTO commentDTO = new CommentDTO(tmp);

            String[] tags = tmp.getTags().split("_");

            commentDTO.setTags(Arrays.asList(tags));
            commentDTOS.add(commentDTO);

        }

        return commentDTOS;
    }

}
