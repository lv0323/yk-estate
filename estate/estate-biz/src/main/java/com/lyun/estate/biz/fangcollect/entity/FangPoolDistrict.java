package com.lyun.estate.biz.fangcollect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by robin on 17/5/5.
 */
public class FangPoolDistrict {

    private Long id;
    private Long cityId;
    private String abbr;
    private String name;
    @JsonIgnore
    private String nameKw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKw() {
        return nameKw;
    }

    public void setNameKw(String nameKw) {
        this.nameKw = nameKw;
    }
}
