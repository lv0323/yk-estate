package com.lyun.estate.biz.dianping.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.lyun.estate.biz.dianping.domain.CorpDetailDTO;
import com.lyun.estate.biz.dianping.domain.CorpStatus;
import com.lyun.estate.biz.dianping.domain.TagCountDTO;
import com.lyun.estate.biz.dianping.entity.Comment;
import com.lyun.estate.biz.dianping.entity.Corp;
import com.lyun.estate.biz.dianping.repo.DianpingRepo;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by localuser on 2017/6/16.
 */
@Service
public class DianpingService {

    @Autowired
    DianpingRepo repo;

    private Corp createCorp(String name, CorpStatus status){

        Corp corp = new Corp();
        corp.setName(name);
        corp.setStatus(status);

        repo.insertCorp( corp );
        return repo.getCorp(corp.getId());
    }

    /**批量创建公司*/
    @Transactional
    public List<Corp> createCorps(List<String> names, final CorpStatus status){

        if(names == null || names.size() == 0){
            throw new EstateException(ExCode.PARAM_NULL, "corps");
        }

        return names.stream()
                .filter( name-> name != null && name.length() > 0 )
                .map( name ->createCorp(name, status) )
                .collect(Collectors.toList());
    }

//    public Corp getCorp(long corpId){
//        Corp corp = repo.getCorp(corpId);
//        checkCorpStatus(corpId, corp);
//        return corp;
//    }
    @Transactional
    public CorpDetailDTO getDetail(long corpId) {

        Corp corp = repo.getCorp(corpId);

        checkCorpStatus(corpId, corp);


        List<String> tagStrs = repo.getTags(corpId);

        CorpDetailDTO detail = new CorpDetailDTO(corp);

        detail.setTags(countTag(tagStrs));

        return detail;
    }


    /**根据 status 查找公司列表*/
    @Transactional
    public PageList<Corp> getCorps(CorpStatus status, String corpName, PageBounds page){

        int offset = page.getOffset();
        int pageIndex = offset / page.getLimit();
        int limit = page.getLimit();

        List<Corp> foundCorps = null;
        int totalCount = 0;

        if(corpName != null && corpName.length() > 0){

            if(status == null){
                foundCorps = repo.searchCorpsWhihtoutStatus(corpName);
            }else{
                foundCorps = repo.searchCorps(corpName, status.name());
            }

            totalCount = foundCorps.size();

        }else{

            if(status == null){
                foundCorps = repo.getCorpsWhithoutStatus(offset, limit);
                totalCount = repo.getCorpCountWhithoutStatus();

            }else{
                foundCorps = repo.getCorps(status.name(), offset, limit);
                totalCount = repo.getCorpCount(status.name());
            }
        }

        Paginator paginator = new Paginator(pageIndex, limit, totalCount);

        PageList<Corp> pageableDTO = new PageList<>(foundCorps, paginator);

        return pageableDTO;
    }

//    /**根据 name 模糊查找公司列表*/
//    public List<Corp> searchCorps(String name){
//
//        if(name == null || name.length() == 0){
//            throw new EstateException(ExCode.PARAM_NULL, "name");
//        }
//        return repo.searchCorps(name);
//    }
    @Transactional
    public boolean rejectCorpReview(long corpId){

        Corp corp = repo.getCorp(corpId);
        checkCorpStatus(corpId, corp);

        if(corp.getStatus() != CorpStatus.NEW){
            throw new EstateException(ExCode.OP_DIANPING_CORP_APPROVED, ""+corpId);
        }

        return repo.deleteCorp(corpId) > 0;
    }

    /**通过审核，或激活公司 ：修改公司的状态为active*/
    @Transactional
    public boolean activeCorp(long corpId){

        Corp corp = repo.getCorp(corpId);
        checkCorpStatus(corpId, corp);

        return repo.activeCorp(corpId) > 0;
    }
    @Transactional
    public boolean suspendCorp(long corpId){

        Corp corp = repo.getCorp(corpId);
        checkCorpStatus(corpId, corp);

        return repo.suspendCorp(corpId) > 0;
    }

    @Transactional
    public boolean mergeCorps(long corpIdTo, long corpIdFrom){

        Corp corpTo = repo.getCorp(corpIdTo);
        checkCorpStatus(corpIdFrom, corpTo);

        Corp corpFrom = repo.getCorp(corpIdFrom);
        checkCorpStatus(corpIdFrom, corpFrom);

        repo.updateCorpCount(corpIdTo, corpFrom.getCommentCount(), corpFrom.getVisitCount(), corpFrom.getPositiveCount(), corpFrom.getNegativeCount());

        repo.deleteCorp(corpIdFrom);

        //merge
        repo.moveCorpComment(corpIdTo, corpIdFrom);
        repo.moveCorpVisit(corpIdTo, corpIdFrom);
        repo.moveCorpJudgement(corpIdTo, corpIdFrom);

        return true;
    }

    @Transactional
    public PageList<Comment> getCorpComments(long corpId, PageBounds page){
        if(corpId <= 0 ){
            throw new EstateException(ExCode.PARAM_ILLEGAL, "corpId", ""+corpId);
        }

        int offset = page.getOffset();
        int pageIndex = offset / page.getLimit();
        int limit = page.getLimit();

        int foundCount = repo.getCorpCommentCount(corpId);

        Paginator paginator = new Paginator(pageIndex, limit, foundCount);
        List<Comment> foundComments = repo.getCorpComments(corpId, page.getOffset(), page.getLimit());

        PageList<Comment> pageableDTO = new PageList<>(foundComments, paginator);

        return pageableDTO;

    }

    @Transactional
    public boolean deleteCorpComment(long corpId, long commentId){

        Corp corp = repo.getCorp(corpId);
        checkCorpStatus(corpId, corp);

        checkComment(commentId);


        if( repo.deleteCorpComment(corpId, commentId) > 0){
            repo.decreaseCommentCount(corpId);
        }

        return true;
    }

    public boolean putCorpCount(long corpId, long visitCount, long positiveCount, long negativeCount){
        repo.putCorpCount(corpId, visitCount, positiveCount, negativeCount);

        return true;
    }

    private void checkComment(long commentId){
        if( repo.countComment(commentId) <= 0){
            throw new EstateException(ExCode.NOT_FOUND, ""+commentId, "comment");
        }
    }

    private void checkCorpStatus(long corpIdNeed, Corp corpFound){
        if(corpFound == null){
            throw new EstateException(ExCode.NOT_FOUND, ""+corpIdNeed, "corp");
        }
        if(corpFound.isDeleted()){
            throw new EstateException(ExCode.APPROVAL_APPROVED, "corp " + corpIdNeed + " 已被reject，不能再操作");
        }

    }

    private List<TagCountDTO> countTag(List<String> tagStrs){

        List<TagCountDTO> tagList = new ArrayList<>();
        if(tagStrs == null){
            return tagList;
        }

        HashMap<String, Integer> tagCountMap = new HashMap<>();

        if(tagStrs.size() > 0){
            for(String str : tagStrs){
                String[] tags = str.split("_");

                for (String tag : tags){
                    if(tagCountMap.containsKey(tag)){
                        tagCountMap.put(tag, tagCountMap.get(tag)+1);

                    }else{
                        tagCountMap.put(tag, 1);
                    }
                }
            }
        }

        tagCountMap.forEach((k,v) -> {
            TagCountDTO tc = new TagCountDTO();
            tc.setName(k);
            tc.setCount(v);
            tagList.add(tc);

        });

        tagList.sort((o1, o2)->{
            if(o1.getCount() >= o2.getCount()){
                return -1;
            }
            else {
                return 1;
            }
        });

        return tagList;
    }

}
