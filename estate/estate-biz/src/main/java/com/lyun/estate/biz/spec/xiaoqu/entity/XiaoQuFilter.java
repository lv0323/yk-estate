package com.lyun.estate.biz.spec.xiaoqu.entity;

import com.lyun.estate.biz.houselicence.def.StructureType;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class XiaoQuFilter {
    private String district;
    private String subDistrict;
    private StructureType structureType;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minYear;
    private Integer maxYear;
    private String keyword;

    public String getDistrict() {
        return district;
    }

    public XiaoQuFilter setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public XiaoQuFilter setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public XiaoQuFilter setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public XiaoQuFilter setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public XiaoQuFilter setStructureType(StructureType structureType) {
        this.structureType = structureType;
        return this;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public XiaoQuFilter setMinYear(Integer minYear) {
        this.minYear = minYear;
        return this;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public XiaoQuFilter setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public XiaoQuFilter setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    @Override
    public String toString() {
        return "XiaoQuFilter{" +
                "district='" + district + '\'' +
                ", subDistrict='" + subDistrict + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", structureType=" + structureType +
                ", minYear=" + minYear +
                ", maxYear=" + maxYear +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
