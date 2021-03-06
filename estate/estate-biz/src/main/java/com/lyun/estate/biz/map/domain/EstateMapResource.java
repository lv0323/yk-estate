package com.lyun.estate.biz.map.domain;

import com.lyun.estate.biz.support.def.DomainType;

import java.math.BigDecimal;

/**
 * Created by siminglun on 2017-01-18.
 */
public class EstateMapResource {
    private Long id;
    private String name;
    private Integer avgPrice;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private DomainType domainType;
    private String buildingCounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public void setDomainType(DomainType domainType) {
        this.domainType = domainType;
    }

    public String getBuildingCounts() {
        return buildingCounts;
    }

    public void setBuildingCounts(String buildingCounts) {
        this.buildingCounts = buildingCounts;
    }


    @Override
    public String toString() {
        return "EstateMapResource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avgPrice=" + avgPrice +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", domainType=" + domainType +
                ", buildingCounts='" + buildingCounts + '\'' +
                '}';
    }
}
