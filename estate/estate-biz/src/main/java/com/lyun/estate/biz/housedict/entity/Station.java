package com.lyun.estate.biz.housedict.entity;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public class Station {

    private Long id;
    private Long cityId;
    private String abbr;
    private String name;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public Long getId() {
        return id;
    }

    public Station setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public Station setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public Station setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getName() {
        return name;
    }

    public Station setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Station setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public Station setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }
}
