package com.lyun.estate.mgt.dianping.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.dianping.service.DianpingService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.dianping.domain.CorpStatus;
import com.lyun.estate.biz.dianping.entity.Comment;
import com.lyun.estate.biz.dianping.entity.Corp;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by localuser on 2017/6/13.
 */
@Service
public class DianpingMgtService {

    @Autowired
    DianpingService service;

    @Autowired
    PermissionCheckService permissionChecker;

    /**批量创建公司*/
    @Transactional
    public List<Corp> createCorps(List<String> names, final CorpStatus status){

        permissionChecker.checkExist(Permission.OP_MANAGE_XY);
        return service.createCorps(names, status);
    }

    /**根据 status 查找公司列表*/
    @Transactional
    public PageList<Corp> getCorps(CorpStatus status, String corpName, PageBounds page){

        return service.getCorps(status, corpName, page);
    }

    public Corp getCorp(long corpId){

        return service.getCorp(corpId);
    }

    /**根据 name 模糊查找公司列表*/
//    public List<Corp> searchCorps(String name){
//
//        return service.searchCorps(name);
//    }
    @Transactional
    public boolean rejectCorpReview(long corpId){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);

        return service.rejectCorpReview(corpId);
    }

    /**通过审核，或激活公司 ：修改公司的状态为active*/
    @Transactional
    public boolean activeCorp(long corpId){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);

        return service.activeCorp(corpId);
    }
    @Transactional
    public boolean suspendCorp(long corpId){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);

        return service.suspendCorp(corpId);
    }

    @Transactional
    public boolean mergeCorps(long corpIdTo, long corpIdFrom){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);

        return service.mergeCorps(corpIdTo, corpIdFrom);
    }

    @Transactional
    public PageList<Comment> getCorpComments(long corpId, PageBounds page){
        return service.getCorpComments(corpId, page);
    }

    @Transactional
    public boolean deleteCorpComment(long corpId, long commentId){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);

        return service.deleteCorpComment(corpId, commentId);
    }

    public boolean putCorpCount(long corpId, long visitCount, long positiveCount, long negativeCount){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);

        return service.putCorpCount(corpId, visitCount, positiveCount, negativeCount);
    }
}
