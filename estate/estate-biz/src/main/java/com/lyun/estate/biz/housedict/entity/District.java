package com.lyun.estate.biz.housedict.entity;


import java.math.BigDecimal;

public class District {

    private Long id;
    private Long cityId;
    private String abbr;
    private String name;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String nameKw;

    public Long getId() {
        return id;
    }

    public District setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public District setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public District setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getName() {
        return name;
    }

    public District setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public District setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public District setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getNameKw() {
        return nameKw;
    }

    public void setNameKw(String nameKw) {
        this.nameKw = nameKw;
    }
}
