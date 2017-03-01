package com.lyun.estate.biz.housedict.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class SubDistrict {

    private Long id;
    private String abbr;
    private String name;
    private BigDecimal longitude;
    private BigDecimal latitude;
    @JsonIgnore
    private String viewRegion;
    @JsonIgnore
    private String nameKw;

    public Long getId() {
        return id;
    }

    public SubDistrict setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public SubDistrict setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubDistrict setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public SubDistrict setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public SubDistrict setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getNameKw() {
        return nameKw;
    }

    public void setNameKw(String nameKw) {
        this.nameKw = nameKw;
    }

    public String getViewRegion() {
        return viewRegion;
    }

    public SubDistrict setViewRegion(String viewRegion) {
        this.viewRegion = viewRegion;
        return this;
    }
}
