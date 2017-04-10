package com.lyun.estate.biz.message.entity;

import com.lyun.estate.biz.fang.def.HouseProcess;
import com.lyun.estate.biz.fang.def.Orientation;
import com.lyun.estate.biz.fang.def.PriceUnit;
import com.lyun.estate.biz.spec.fang.domain.FangForTag;
import com.lyun.estate.biz.support.def.BizType;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-03-16.
 */
public class FangChangePriceBean extends FangForTag {
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
    private String messageContent;

    public Long getId() {
        return id;
    }

    public FangChangePriceBean setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public FangChangePriceBean setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public FangChangePriceBean setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FangChangePriceBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public FangChangePriceBean setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public BigDecimal getPublishPrice() {
        return publishPrice;
    }

    public FangChangePriceBean setPublishPrice(BigDecimal publishPrice) {
        this.publishPrice = publishPrice;
        return this;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public FangChangePriceBean setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public FangChangePriceBean setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public FangChangePriceBean setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
        return this;
    }

    public Integer gettCounts() {
        return tCounts;
    }

    public FangChangePriceBean settCounts(Integer tCounts) {
        this.tCounts = tCounts;
        return this;
    }

    public BigDecimal getEstateArea() {
        return estateArea;
    }

    public FangChangePriceBean setEstateArea(BigDecimal estateArea) {
        this.estateArea = estateArea;
        return this;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public FangChangePriceBean setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public String getXiaoQuName() {
        return xiaoQuName;
    }

    public FangChangePriceBean setXiaoQuName(String xiaoQuName) {
        this.xiaoQuName = xiaoQuName;
        return this;
    }

    public HouseProcess getProcess() {
        return process;
    }

    public FangChangePriceBean setProcess(HouseProcess process) {
        this.process = process;
        return this;
    }

    public String getImageURI() {
        return imageURI;
    }

    public FangChangePriceBean setImageURI(String imageURI) {
        this.imageURI = imageURI;
        return this;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public FangChangePriceBean setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        return this;
    }
}
