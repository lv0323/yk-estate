package com.lyun.estate.mgt.op.dianping.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.op.dianping.domain.CorpStatus;
import com.lyun.estate.mgt.op.dianping.domain.PageableDTO;
import com.lyun.estate.mgt.op.dianping.entity.Comment;
import com.lyun.estate.mgt.op.dianping.entity.Corp;
import com.lyun.estate.mgt.op.dianping.repo.DianpingRepo;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by localuser on 2017/6/13.
 */
@Service
public class DianpingService {

    @Autowired
    DianpingRepo repo;

    @Autowired
    PermissionCheckService permissionChecker;

    @Autowired
    Util util;

    /**批量创建公司*/
    public boolean createCorps(List<String> names){

        permissionChecker.checkExist(Permission.OP_MANAGE_XY);

        if(names == null || names.size() == 0){
            throw new EstateException(ExCode.PARAM_NULL, "corps");
        }

        String values = util.getInsertValues( names );

        repo.insertCorps( values );

        return true;
    }


//    public Integer getCorpCount(CorpStatus status){
//        return 0;
//    }

    /**根据 status 查找公司列表*/
    @Transactional
    public PageableDTO<Corp> getCorps(CorpStatus status, PageBounds page){

        PageableDTO<Corp> pageableDTO = new PageableDTO<>(page);

        pageableDTO.setItems(repo.getCorps(status.name(), page.getOffset(), page.getLimit()));

        pageableDTO.setTotal_count(repo.getCorpCount(status.name()));

        return pageableDTO;
    }

    /**根据 name 模糊查找公司列表*/
    public List<Corp> searchCorps(String name){

        if(name == null || name.length() == 0){
            throw new EstateException(ExCode.PARAM_NULL, "name");
        }
        return repo.searchCorps(name);
    }
    @Transactional
    public boolean rejectCorpReview(long corpId){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);

        if( !util.existCorp(corpId)) {
            throw new EstateException(ExCode.NOT_FOUND, ""+corpId, "corp");
        }
        return repo.deleteCorp(corpId) > 0;
    }

    /**通过审核，或激活公司 ：修改公司的状态为active*/
    @Transactional
    public boolean activeCorp(long corpId){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);
        if( !util.existCorp(corpId)) {
            throw new EstateException(ExCode.NOT_FOUND, ""+corpId, "corp");
        }
        return repo.activeCorp(corpId) > 0;
    }
    @Transactional
    public boolean suspendCorp(long corpId){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);
        if( !util.existCorp(corpId)) {
            throw new EstateException(ExCode.NOT_FOUND, ""+corpId, "corp");
        }
        return repo.suspendCorp(corpId) > 0;
    }

    @Transactional
    public boolean mergeCorps(long corpIdTo, long corpIdFrom){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);

        if( !util.existCorp(corpIdTo)) {
            throw new EstateException(ExCode.NOT_FOUND, ""+corpIdTo, "corp");
        }

        if( !util.existCorp(corpIdFrom)) {
            throw new EstateException(ExCode.NOT_FOUND, ""+corpIdFrom, "corp");
        }

        Corp corp = repo.getCorp(corpIdFrom);
        repo.updateCorpCount(corpIdTo, corp.getVisitCount(), corp.getPositiveCount(), corp.getNegativeCount());
        repo.deleteCorp(corpIdFrom);

        //merge
        repo.moveCorpComment(corpIdTo, corpIdFrom);
        repo.moveCorpVisit(corpIdTo, corpIdFrom);
        repo.moveCorpJudgement(corpIdTo, corpIdFrom);

        return true;
    }

//    public Integer getCorpCommentCount(long corpId){
//        return 0;
//    }

    @Transactional
    public PageableDTO<Comment> getCorpComments(long corpId, PageBounds page){
        PageableDTO<Comment> pageableDTO = new PageableDTO<>(page);

        pageableDTO.setItems(repo.getCorpComments(corpId, page.getOffset(), page.getLimit()));

        pageableDTO.setTotal_count(repo.getCorpCommentCount(corpId));

        return pageableDTO;

    }

    @Transactional
    public boolean deleteCorpComment(long corpId, long commentId){
        permissionChecker.checkExist(Permission.OP_MANAGE_XY);
        if( !util.existCorp(corpId)) {
            throw new EstateException(ExCode.NOT_FOUND, ""+corpId, "corp");
        }
        if( !util.existComment(commentId)) {
            throw new EstateException(ExCode.NOT_FOUND, ""+commentId, "comment");
        }
        repo.deleteCorpComment(corpId, commentId);
        repo.decreaseCommentCount(corpId);

        return true;
    }


    @Component
    private static class Util{

        @Autowired
        DianpingRepo repo;

        /**
         * INSERT INTO tbl_name (a,b,c) VALUES (1,2,3),(4,5,6),(7,8,9);
         *
         * 根据list里的元素生成VALUES后面的字符串
         * */
        String getInsertValues(List<String> names){

            final String status = CorpStatus.ACTIVIE.name();
            final StringBuilder sb = new StringBuilder();
            names.stream()
                    .filter( it-> it != null && it.length() > 0 )
                    .forEach(t-> sb.append("("+status +", "+t+"),"));

            String values = sb.toString();

            return values.substring(0, values.length() - 1);
        }

        boolean existCorp(long corpId){
            return repo.existCorp(corpId) > 0;
        }

        boolean existComment(long commentId){
            return repo.countComment(commentId) > 0;
        }
    }

}
