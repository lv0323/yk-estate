//package com.lyun.estate.mgt.op.dianping.domain;
//
//import com.lyun.estate.mgt.op.dianping.entity.Corp;
//
//import java.util.List;
//
///**
// * Created by localuser on 2017/5/15.
// */
//public class CorpDetail {
//
//
//    private long id;
//    private String name;
//    private long positiveCount;
//    private long negativeCount;
//    private long visitCount;
//    private long commentCount;
//    private List<TagCount> tags;
//
//    private CorpDetail(){
//    }
//
//    public CorpDetail(Corp corp){
//        this.id = corp.getId();
//        this.name = corp.getName();
//        this.positiveCount = corp.getPositiveCount();
//        this.negativeCount = corp.getNegativeCount();
//        this.visitCount = corp.getVisitCount();
//        this.commentCount = corp.getCommentCount();
//    }
//
//    public long getCommentCount() {
//        return commentCount;
//    }
//
//    public void setCommentCount(long commentCount) {
//        this.commentCount = commentCount;
//    }
//
//
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public long getPositiveCount() {
//        return positiveCount;
//    }
//
//    public void setPositiveCount(long positiveCount) {
//        this.positiveCount = positiveCount;
//    }
//
//    public long getNegativeCount() {
//        return negativeCount;
//    }
//
//    public void setNegativeCount(long negativeCount) {
//        this.negativeCount = negativeCount;
//    }
//
//    public long getVisitCount() {
//        return visitCount;
//    }
//
//    public void setVisitCount(long visitCount) {
//        this.visitCount = visitCount;
//    }
//
//    public List<TagCount> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<TagCount> tags) {
//        this.tags = tags;
//    }
//}
