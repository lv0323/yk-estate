package com.lyun.estate.op.dianping.comment;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.op.dianping.corp.domain.ActionResultDTO;
import com.lyun.estate.op.dianping.corp.domain.LikedDTO;
import com.lyun.estate.op.dianping.comment.domain.CommentDTO;
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
    @Autowired
    private CorpUtil util;


    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultDTO create(@RequestHeader String token,
                                  @RequestParam long corpId,
                                  @RequestParam List<String> tags,
                                  @RequestParam(required = false) String shopfront,
                                  @RequestParam String content
                                 ) {
        long userId = util.getUserId(token);

        if(shopfront == null){
            shopfront = "";
        }

        commentService.create(userId, corpId, tags, shopfront, content);
        return new ActionResultDTO().setSuccess(true);
    }

    @PostMapping(value = "/{commentId}/like", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultDTO like(@RequestHeader String token, @PathVariable long commentId){

        long userId = util.getUserId(token);

        commentService.like(commentId, userId);

        return new ActionResultDTO().setSuccess(true);
    }
    @PostMapping(value = "/{commentId}/cancel_like", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultDTO cancelLike(@RequestHeader String token, @PathVariable long commentId){
        long userId = util.getUserId(token);

        commentService.cancelLike(commentId, userId);

        return new ActionResultDTO().setSuccess(true);
    }

    @GetMapping(value = "/{commentId}/is_like")
    public LikedDTO liked(@RequestHeader String token, @PathVariable long commentId) {

        long userId = util.getUserId(token);
        LikedDTO response = new LikedDTO();
        response.setLiked(commentService.liked(commentId, userId));
        return response;
    }
    @GetMapping(value = "/my")
    public List<CommentDTO> my(@RequestHeader String token, @RequestHeader("X-PAGING")PageBounds pageBounds) {

        long userId = util.getUserId(token);
        return commentService.myComments(userId, pageBounds);
    }

}
