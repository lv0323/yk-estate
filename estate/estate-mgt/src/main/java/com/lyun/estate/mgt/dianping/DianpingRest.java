package com.lyun.estate.mgt.dianping;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.biz.dianping.entity.Comment;
import com.lyun.estate.biz.dianping.entity.Corp;
import com.lyun.estate.mgt.dianping.service.DianpingMgtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lyun.estate.biz.dianping.domain.*;

import java.util.List;

@RestController
@RequestMapping("api/op/dianping/")
public class DianpingRest {

    private final DianpingMgtService service;

    private MgtContext mgtContext;

    public DianpingRest(DianpingMgtService service, MgtContext mgtContext) {
        this.service = service;
        this.mgtContext = mgtContext;
    }

    /**根据名字列表创建多个公司
     * 重复创建？
     * */
    @PostMapping("corps")
    public List<Corp> createCorps(@RequestParam("corps") List<String> corpNames, @RequestParam("status")CorpStatus status) {
        return service.createCorps(corpNames, status);
    }
    @GetMapping("corp")
    public Corp getCorp( @RequestParam(value = "corpId") long corpId) {

        return service.getCorp(corpId);
    }


    /**根据 status 查询公司列表 status = new | active | suspend */
    @GetMapping("corps")
    public PageList<Corp> getCorps( @RequestParam(value = "status", required = false) CorpStatus status,
                                    @RequestParam(value = "corpName", required = false) String corpName,
                                    @RequestHeader("X-PAGING")PageBounds pageBounds) {

        return service.getCorps(status, corpName, pageBounds);
    }

//    /**根据 name + status 查询公司列表*/
//    @GetMapping("search")
//    public List<Corp> searchCorps(@RequestParam String corpName) {
//        return service.searchCorps(corpName);
//    }

    /**
     * 通过审核，或激活公司 ：修改公司的状态为active
     * */
    @PostMapping("{corpId}/status/active")
    ResponseEntity activeCorp(@PathVariable("corpId") long corpId) {
        service.activeCorp(corpId);
        return new ResponseEntity("{}",HttpStatus.OK);
    }

    /**审核通不过：删除这个记录*/
    @PostMapping("{corpId}/status/rejected")
    ResponseEntity rejectCorpReview(@PathVariable("corpId") long corpId) {

        service.rejectCorpReview(corpId);

        return new ResponseEntity("{}",HttpStatus.OK);
    }

    /**冻结公司：修改公司的状态为suspend*/
    @PostMapping("{corpId}/status/suspend")
    ResponseEntity suspendCorp(@PathVariable("corpId") long corpId) {
        service.suspendCorp(corpId);
        return new ResponseEntity("{}",HttpStatus.OK);
    }

//    /**合并同名公司*/
//    @PostMapping("{corpIdTo}/{corpIdFrom}")
//    public ResponseEntity mergeCorp(@PathVariable("corpIdTo")long corpIdTo, @PathVariable("corpIdFrom")long corpIdFrom){
//        service.mergeCorps(corpIdTo, corpIdFrom);
//        return new ResponseEntity("{}",HttpStatus.OK);
//    }

    /**查询某个公司的评论列表*/
    @GetMapping(value = "{corpId}/comments")
    public PageList<Comment> getCorpComments(@PathVariable long corpId, @RequestHeader("X-PAGING")PageBounds pageBounds) {
        return service.getCorpComments(corpId, pageBounds);
    }

    /**删除某个公司的某个评论*/
    @GetMapping("{corpId}/comments/{commentId}")
    public ResponseEntity deleteCorpComment(@PathVariable("corpId")long corpId,
                                            @PathVariable("commentId")long commentId){

        service.deleteCorpComment(corpId, commentId);
        return new ResponseEntity("{}",HttpStatus.OK);
    }

    @PostMapping("{corpId}")
    public ResponseEntity putCorpCount(@PathVariable("corpId")long corpId,
                                     @RequestParam("visitCount") long visitCount,
                                     @RequestParam("positiveCount") long positiveCount,
                                     @RequestParam("negativeCount") long negativeCount){
        service.putCorpCount(corpId, visitCount, positiveCount, negativeCount);
        return new ResponseEntity("{}",HttpStatus.OK);
    }
}
