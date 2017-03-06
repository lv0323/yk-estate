package com.lyun.estate.biz.fang.domian;

import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.houselicence.entity.HouseLicenceDTO;
import com.lyun.estate.biz.support.def.BizType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeffrey on 2017-03-03.
 */
public class MgtFangTiny {
    private Long id;
    private BizType bizType;
    private HouseType houseType;
    private HouseSubType houseSubType;
    private Long licenceId;
    private HouseLicenceDTO houseLicence;
    private Long xiaoQuId;
    private Integer sCounts;//室
    private Integer tCounts;//厅
    private Integer wCounts;//卫
    private Integer cCounts;//厨
    private Integer ytCounts;//阳台
    private BigDecimal estateArea;
    private BigDecimal realArea;
    private BigDecimal publishPrice;
    private PriceUnit priceUnit;
    private BigDecimal transferPrice;
    private BigDecimal unitPrice;
    private BigDecimal bottomPrice;
    private HouseProcess process;
    private Integer floor;
    private Date createTime;
    private Date publishTime;

    public Long getId() {
        return id;
    }

    public MgtFangTiny setId(Long id) {
        this.id = id;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public MgtFangTiny setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public MgtFangTiny setHouseType(HouseType houseType) {
        this.houseType = houseType;
        return this;
    }

    public HouseSubType getHouseSubType() {
        return houseSubType;
    }

    public MgtFangTiny setHouseSubType(HouseSubType houseSubType) {
        this.houseSubType = houseSubType;
        return this;
    }

    public Long getLicenceId() {
        return licenceId;
    }

    public MgtFangTiny setLicenceId(Long licenceId) {
        this.licenceId = licenceId;
        return this;
    }

    public HouseLicenceDTO getHouseLicence() {
        return houseLicence;
    }

    public MgtFangTiny setHouseLicence(HouseLicenceDTO houseLicence) {
        this.houseLicence = houseLicence;
        return this;
    }

    public Long getXiaoQuId() {
        return xiaoQuId;
    }

    public MgtFangTiny setXiaoQuId(Long xiaoQuId) {
        this.xiaoQuId = xiaoQuId;
        return this;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public MgtFangTiny setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
        return this;
    }

    public Integer gettCounts() {
        return tCounts;
    }

    public MgtFangTiny settCounts(Integer tCounts) {
        this.tCounts = tCounts;
        return this;
    }

    public Integer getwCounts() {
        return wCounts;
    }

    public MgtFangTiny setwCounts(Integer wCounts) {
        this.wCounts = wCounts;
        return this;
    }

    public Integer getcCounts() {
        return cCounts;
    }

    public MgtFangTiny setcCounts(Integer cCounts) {
        this.cCounts = cCounts;
        return this;
    }

    public Integer getYtCounts() {
        return ytCounts;
    }

    public MgtFangTiny setYtCounts(Integer ytCounts) {
        this.ytCounts = ytCounts;
        return this;
    }

    public BigDecimal getEstateArea() {
        return estateArea;
    }

    public MgtFangTiny setEstateArea(BigDecimal estateArea) {
        this.estateArea = estateArea;
        return this;
    }

    public BigDecimal getRealArea() {
        return realArea;
    }

    public MgtFangTiny setRealArea(BigDecimal realArea) {
        this.realArea = realArea;
        return this;
    }

    public BigDecimal getPublishPrice() {
        return publishPrice;
    }

    public MgtFangTiny setPublishPrice(BigDecimal publishPrice) {
        this.publishPrice = publishPrice;
        return this;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public MgtFangTiny setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
        return this;
    }

    public BigDecimal getTransferPrice() {
        return transferPrice;
    }

    public MgtFangTiny setTransferPrice(BigDecimal transferPrice) {
        this.transferPrice = transferPrice;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public MgtFangTiny setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public BigDecimal getBottomPrice() {
        return bottomPrice;
    }

    public MgtFangTiny setBottomPrice(BigDecimal bottomPrice) {
        this.bottomPrice = bottomPrice;
        return this;
    }

    public HouseProcess getProcess() {
        return process;
    }

    public MgtFangTiny setProcess(HouseProcess process) {
        this.process = process;
        return this;
    }

    public Integer getFloor() {
        return floor;
    }

    public MgtFangTiny setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MgtFangTiny setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public MgtFangTiny setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("bizType", bizType)
                .add("houseType", houseType)
                .add("houseSubType", houseSubType)
                .add("licenceId", licenceId)
                .add("houseLicence", houseLicence)
                .add("xiaoQuId", xiaoQuId)
                .add("sCounts", sCounts)
                .add("tCounts", tCounts)
                .add("wCounts", wCounts)
                .add("cCounts", cCounts)
                .add("ytCounts", ytCounts)
                .add("estateArea", estateArea)
                .add("realArea", realArea)
                .add("publishPrice", publishPrice)
                .add("priceUnit", priceUnit)
                .add("transferPrice", transferPrice)
                .add("unitPrice", unitPrice)
                .add("bottomPrice", bottomPrice)
                .add("process", process)
                .add("floor", floor)
                .add("createTime", createTime)
                .add("publishTime", publishTime)
                .toString();
    }
}
