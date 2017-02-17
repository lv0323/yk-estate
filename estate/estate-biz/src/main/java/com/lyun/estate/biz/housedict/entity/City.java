package com.lyun.estate.biz.housedict.entity;


import java.math.BigDecimal;

public class City {

    private Long id;
    private String abbr;
    private String name;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String viewRegion;
    private String nameKw;

    public Long getId() {
        return id;
    }

    public City setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public City setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public City setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public City setLatitude(BigDecimal latitude) {
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

    public City setViewRegion(String viewRegion) {
        this.viewRegion = viewRegion;
        return this;
    }
}
