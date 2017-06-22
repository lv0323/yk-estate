package com.lyun.estate.biz.dianping.domain;

import com.lyun.estate.biz.dianping.entity.Corp;

import java.util.List;

/**
 * Created by localuser on 2017/5/15.
 */
public class CorpDetailDTO {


    private long id;
    private String name;
    private CorpStatus status;
    private long positiveCount;
    private long negativeCount;
    private long visitCount;
    private long commentCount;
    private List<TagCountDTO> tags;

    private CorpDetailDTO(){}

    public CorpDetailDTO(Corp corp){
        this.id = corp.getId();
        this.name = corp.getName();
        this.positiveCount = corp.getPositiveCount();
        this.negativeCount = corp.getNegativeCount();
        this.visitCount = corp.getVisitCount();
        this.commentCount = corp.getCommentCount();
        this.status = corp.getStatus();
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPositiveCount() {
        return positiveCount;
    }

    public void setPositiveCount(long positiveCount) {
        this.positiveCount = positiveCount;
    }

    public long getNegativeCount() {
        return negativeCount;
    }

    public void setNegativeCount(long negativeCount) {
        this.negativeCount = negativeCount;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(long visitCount) {
        this.visitCount = visitCount;
    }

    public List<TagCountDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagCountDTO> tags) {
        this.tags = tags;
    }

    public CorpStatus getStatus() {
        return status;
    }

    public void setStatus(CorpStatus status) {
        this.status = status;
    }
}
