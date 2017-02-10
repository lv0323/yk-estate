package com.lyun.estate.biz.housedict.entity;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-02-06.
 */
public class Line {
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

    public Line setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public Line setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public Line setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getName() {
        return name;
    }

    public Line setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Line setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public Line setLatitude(BigDecimal latitude) {
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
