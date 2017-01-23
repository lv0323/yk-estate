package com.lyun.estate.biz.keyword.entity;

import com.lyun.estate.biz.spec.common.DomainType;

/**
 * Created by Jeffrey on 2017-01-13.
 */
public class KeywordResp {
    private String resp;
    private DomainType type;
    private String note;
    private Integer count;

    public String getResp() {
        return resp;
    }

    public KeywordResp setResp(String resp) {
        this.resp = resp;
        return this;
    }

    public String getNote() {
        return note;
    }

    public KeywordResp setNote(String note) {
        this.note = note;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public KeywordResp setCount(Integer count) {
        this.count = count;
        return this;
    }


    public DomainType getType() {
        return type;
    }

    public KeywordResp setType(DomainType type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "KeywordResp{" +
                "resp='" + resp + '\'' +
                ", note='" + note + '\'' +
                ", count=" + count +
                '}';
    }
}
