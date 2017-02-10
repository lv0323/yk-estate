package com.lyun.estate.biz.spec.xiaoqu.entity;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class XiaoQuSummary {
    private Long id;
    private Long cityId;
    private String name;
    private String district;
    private String subDistrict;
    private Integer buildedYear;
    private String structure;
    private Integer avgPrice;
    private String imageURI;

    public Long getId() {
        return id;
    }

    public XiaoQuSummary setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public XiaoQuSummary setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getName() {
        return name;
    }

    public XiaoQuSummary setName(String name) {
        this.name = name;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public XiaoQuSummary setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public XiaoQuSummary setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
        return this;
    }

    public Integer getBuildedYear() {
        return buildedYear;
    }

    public XiaoQuSummary setBuildedYear(Integer buildedYear) {
        this.buildedYear = buildedYear;
        return this;
    }

    public String getImageURI() {
        return imageURI;
    }

    public XiaoQuSummary setImageURI(String imageURI) {
        this.imageURI = imageURI;
        return this;
    }

    public String getStructure() {
        return structure;
    }

    public XiaoQuSummary setStructure(String structure) {
        this.structure = structure;
        return this;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public XiaoQuSummary setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
        return this;
    }

    @Override
    public String toString() {
        return "XiaoQuSummary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", district='" + district + '\'' +
                ", subDistrict='" + subDistrict + '\'' +
                ", buildedYear=" + buildedYear +
                ", imageURI='" + imageURI + '\'' +
                '}';
    }
}
