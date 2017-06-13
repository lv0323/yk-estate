package com.lyun.estate.mgt.op.dianping;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.housedict.service.CityService;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.op.dianping.entity.CommentDTO;
import com.lyun.estate.mgt.op.dianping.entity.Corp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lyun.estate.mgt.op.dianping.domain.*;

import java.util.List;

@RestController
@RequestMapping("api/dianping/")
public class DianpingRest {

    private final CityService service;

    private MgtContext mgtContext;

    public DianpingRest(CityService service, MgtContext mgtContext) {
        this.service = service;
        this.mgtContext = mgtContext;
    }

    /**根据名字列表创建多个公司*/
    @CheckToken
    @PostMapping("corps")
    List<Corp> createCorps(@RequestParam("corps") List<String> corpNames) {

        return null;
    }

    /**根据 status 查询公司列表 status = new | active | suspend */
    @GetMapping("corps")
    public List<Corp> getCorps( @RequestParam String status, @RequestHeader("X-PAGING")PageBounds pageBounds) {
        return null;
    }

    /**通过审核：修改公司的状态为active*/
    @CheckToken
    @PatchMapping("{corpId}/status")
    ResponseEntity passReview(@RequestParam("corpId") long corpId, @RequestParam("status")CorpStatus status) {

        return new ResponseEntity(HttpStatus.OK);
    }

    /**审核通不过：删除这个记录*/
    @CheckToken
    @DeleteMapping("{corpId}")
    ResponseEntity deleteCorpThatRejectedByReview(@RequestParam("corpId") long corpId) {

        return new ResponseEntity(HttpStatus.OK);
    }


    /**激活公司：修改公司的状态为active*/
    @CheckToken
    @PatchMapping("{corpId}/status")
    ResponseEntity active(@RequestParam("corpId") long corpId, @RequestParam("status")CorpStatus status) {

        return new ResponseEntity(HttpStatus.OK);
    }

    /**冻结公司：修改公司的状态为suspend*/
    @CheckToken
    @PatchMapping("{corpId}/status")
    ResponseEntity suspend(@RequestParam("corpId") long corpId, @RequestParam("status")CorpStatus status) {

        return new ResponseEntity(HttpStatus.OK);
    }

    /**根据 name + status 查询公司列表*/
    @GetMapping("search")
    public List<Corp> search(@RequestParam String corpName, @RequestParam String status) {

        return null;
    }
    /**查询某个公司的评论列表*/
    @GetMapping(value = "{corpId}/comments")
    public List<CommentDTO> getCorpComments(@PathVariable long corpId, @RequestHeader("X-PAGING")PageBounds pageBounds) {
        return null;
    }

    /**删除某个公司的某个评论*/
    @CheckToken
    @DeleteMapping("{corpId}/comments/{comment}")
    public ResponseEntity deleteCorp(@PathVariable("corpId")long corpId, @PathVariable("comment")long commentId){

        return new ResponseEntity(HttpStatus.OK);
    }

}
