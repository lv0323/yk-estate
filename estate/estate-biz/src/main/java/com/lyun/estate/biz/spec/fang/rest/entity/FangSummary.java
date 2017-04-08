package com.lyun.estate.biz.spec.fang.rest.entity;

import com.lyun.estate.biz.fang.def.HouseProcess;
import com.lyun.estate.biz.fang.def.Orientation;
import com.lyun.estate.biz.fang.def.PriceUnit;
import com.lyun.estate.biz.spec.fang.domain.FangForTag;
import com.lyun.estate.biz.support.def.BizType;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public class FangSummary extends FangForTag {
    private Long id;
    private Long cityId;
    private Long subDistrictId;
    private String title;
    private BizType bizType;
    private BigDecimal publishPrice;
    private PriceUnit priceUnit;
    private BigDecimal unitPrice;
    private Integer sCounts;
    private Integer tCounts;
    private BigDecimal estateArea;
    private Orientation orientation;
    private String xiaoQuName;
    private HouseProcess process;
    private String imageURI;

    public Long getId() {
        return id;
    }

    public FangSummary setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public FangSummary setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public FangSummary setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FangSummary setTitle(String title) {
        this.title = title;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public FangSummary setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public BigDecimal getPublishPrice() {
        return publishPrice;
    }

    public FangSummary setPublishPrice(BigDecimal publishPrice) {
        this.publishPrice = publishPrice;
        return this;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public FangSummary setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public FangSummary setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public FangSummary setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
        return this;
    }

    public Integer gettCounts() {
        return tCounts;
    }

    public FangSummary settCounts(Integer tCounts) {
        this.tCounts = tCounts;
        return this;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public FangSummary setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public BigDecimal getEstateArea() {
        return estateArea;
    }

    public FangSummary setEstateArea(BigDecimal estateArea) {
        this.estateArea = estateArea;
        return this;
    }

    public String getXiaoQuName() {
        return xiaoQuName;
    }

    public FangSummary setXiaoQuName(String xiaoQuName) {
        this.xiaoQuName = xiaoQuName;
        return this;
    }

    public String getImageURI() {
        return imageURI;
    }

    public FangSummary setImageURI(String imageURI) {
        this.imageURI = imageURI;
        return this;
    }

    public HouseProcess getProcess() {
        return process;
    }

    public FangSummary setProcess(HouseProcess process) {
        this.process = process;
        return this;
    }
}
