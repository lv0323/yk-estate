package com.lyun.estate.op.corp;

import com.lyun.estate.op.corp.entity.ActionResultBean;
import com.lyun.estate.op.corp.service.CommentService;
import com.lyun.estate.op.utils.CorpUtil;
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
    public ActionResultBean create(@RequestHeader String token,
                                   @RequestParam long corpId,
                                   @RequestParam List<String> tags,
                                   @RequestParam String shopfront,
                                   @RequestParam String content
                                 ) {
        long userId = CorpUtil.getUserId(token);

        commentService.create(userId, corpId, tags, shopfront, content);
        return new ActionResultBean().setSuccess(true);
    }

    @PostMapping(value = "/{commentId}/like", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultBean like(@RequestHeader String token, @PathVariable long commentId){

        long userId = CorpUtil.getUserId(token);

        commentService.like(commentId, userId);

        return new ActionResultBean().setSuccess(true);
    }
    @PostMapping(value = "/{commentId}/cancel_like", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultBean cancelLike(@RequestHeader String token, @PathVariable long commentId){
        long userId = CorpUtil.getUserId(token);

        commentService.cancelLike(commentId, userId);

        return new ActionResultBean().setSuccess(true);
    }

}
