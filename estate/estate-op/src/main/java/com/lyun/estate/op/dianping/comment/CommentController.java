package com.lyun.estate.op.dianping.comment;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.op.dianping.corp.entity.ActionResultDTO;
import com.lyun.estate.op.dianping.corp.entity.BooleanResponse;
import com.lyun.estate.op.dianping.comment.domain.Comment;
import com.lyun.estate.op.dianping.comment.service.CommentService;
import com.lyun.estate.op.dianping.utils.CorpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by localuser on 2017/5/15.
 */

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultDTO create(@RequestHeader String token,
                                  @RequestParam long corpId,
                                  @RequestParam List<String> tags,
                                  @RequestParam(required = false) String shopfront,
                                  @RequestParam String content
                                 ) {
        long userId = CorpUtil.getUserId(token);

        if(shopfront == null){
            shopfront = "";
        }

        commentService.create(userId, corpId, tags, shopfront, content);
        return new ActionResultDTO().setSuccess(true);
    }

    @PostMapping(value = "/{commentId}/like", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultDTO like(@RequestHeader String token, @PathVariable long commentId){

        long userId = CorpUtil.getUserId(token);

        commentService.like(commentId, userId);

        return new ActionResultDTO().setSuccess(true);
    }
    @PostMapping(value = "/{commentId}/cancel_like", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultDTO cancelLike(@RequestHeader String token, @PathVariable long commentId){
        long userId = CorpUtil.getUserId(token);

        commentService.cancelLike(commentId, userId);

        return new ActionResultDTO().setSuccess(true);
    }

    @GetMapping(value = "/{commentId}/is_like")
    public BooleanResponse liked(@RequestHeader String token, @PathVariable long commentId) {

        long userId = CorpUtil.getUserId(token);
        BooleanResponse response = new BooleanResponse();
        response.setLiked(commentService.liked(commentId, userId));
        return response;
    }
    @GetMapping(value = "/my")
    public List<Comment> my(@RequestHeader String token, @RequestHeader("X-PAGING")PageBounds pageBounds) {

        long userId = CorpUtil.getUserId(token);
        return commentService.myComments(userId, pageBounds);
    }

}
