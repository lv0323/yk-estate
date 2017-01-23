package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.HouseTag;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public class FangTag {
    private Long id;
    private Long fangId;
    private HouseTag houseTag;

    public Long getId() {
        return id;
    }

    public FangTag setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangTag setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public HouseTag getHouseTag() {
        return houseTag;
    }

    public FangTag setHouseTag(HouseTag houseTag) {
        this.houseTag = houseTag;
        return this;
    }
}
