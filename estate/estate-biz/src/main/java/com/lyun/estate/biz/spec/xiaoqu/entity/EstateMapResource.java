package com.lyun.estate.biz.spec.xiaoqu.entity;

import com.lyun.estate.biz.spec.common.DomainType;

import java.math.BigDecimal;
import java.util.List;

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
    private String positionBorder;

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

    public String getPositionBorder() {
        return positionBorder;
    }

    public void setPositionBorder(String positionBorder) {
        this.positionBorder = positionBorder;
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
                ", positionBorder='" + positionBorder + '\'' +
                '}';
    }
}
