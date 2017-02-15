package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public class Fang {
    private Long id;
    private HouseSubType houseSubType;
    private BizType bizType;
    private HouseType houseType;
    private Long licenceId;
    private Long xiaoQuId;
    private Integer sCounts;//室
    private Integer tCounts;//厅
    private Integer wCounts;//卫
    private Integer cCounts;//厨
    private Integer ytCounts;//阳台
    private Orientation orientation;
    private Decorate decorate;
    private BigDecimal estateArea;
    private BigDecimal realArea;
    private BigDecimal publishPrice;
    private PriceUnit priceUnit;
    private BigDecimal transferPrice;
    private BigDecimal unitPrice; //单价
    private BigDecimal bottomPrice;
    private HouseProcess process;
    private YN resident;
    private Integer floorCounts;//总层高
    private FloorType floorType;
    private StructureType structureType;
    private Integer buildYear;
    private HeatingType heatingType;
    private YN hasElevator;
    private Date createTime;
    private Date updateTime;
    private YN isDeleted;
    private String nameKw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HouseSubType getHouseSubType() {
        return houseSubType;
    }

    public void setHouseSubType(HouseSubType houseSubType) {
        this.houseSubType = houseSubType;
    }

    public BizType getBizType() {
        return bizType;
    }

    public void setBizType(BizType bizType) {
        this.bizType = bizType;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public Long getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(Long licenceId) {
        this.licenceId = licenceId;
    }

    public Long getXiaoQuId() {
        return xiaoQuId;
    }

    public void setXiaoQuId(Long xiaoQuId) {
        this.xiaoQuId = xiaoQuId;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public void setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
    }

    public Integer gettCounts() {
        return tCounts;
    }

    public void settCounts(Integer tCounts) {
        this.tCounts = tCounts;
    }

    public Integer getwCounts() {
        return wCounts;
    }

    public void setwCounts(Integer wCounts) {
        this.wCounts = wCounts;
    }

    public Integer getcCounts() {
        return cCounts;
    }

    public void setcCounts(Integer cCounts) {
        this.cCounts = cCounts;
    }

    public Integer getYtCounts() {
        return ytCounts;
    }

    public void setYtCounts(Integer ytCounts) {
        this.ytCounts = ytCounts;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Decorate getDecorate() {
        return decorate;
    }

    public void setDecorate(Decorate decorate) {
        this.decorate = decorate;
    }

    public BigDecimal getEstateArea() {
        return estateArea;
    }

    public void setEstateArea(BigDecimal estateArea) {
        this.estateArea = estateArea;
    }

    public BigDecimal getRealArea() {
        return realArea;
    }

    public void setRealArea(BigDecimal realArea) {
        this.realArea = realArea;
    }

    public BigDecimal getPublishPrice() {
        return publishPrice;
    }

    public void setPublishPrice(BigDecimal publishPrice) {
        this.publishPrice = publishPrice;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
    }

    public BigDecimal getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(BigDecimal transferPrice) {
        this.transferPrice = transferPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getBottomPrice() {
        return bottomPrice;
    }

    public void setBottomPrice(BigDecimal bottomPrice) {
        this.bottomPrice = bottomPrice;
    }

    public HouseProcess getProcess() {
        return process;
    }

    public void setProcess(HouseProcess process) {
        this.process = process;
    }

    public YN getResident() {
        return resident;
    }

    public void setResident(YN resident) {
        this.resident = resident;
    }

    public Integer getFloorCounts() {
        return floorCounts;
    }

    public void setFloorCounts(Integer floorCounts) {
        this.floorCounts = floorCounts;
    }

    public FloorType getFloorType() {
        return floorType;
    }

    public void setFloorType(FloorType floorType) {
        this.floorType = floorType;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public void setStructureType(StructureType structureType) {
        this.structureType = structureType;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
    }

    public HeatingType getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(HeatingType heatingType) {
        this.heatingType = heatingType;
    }

    public YN getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(YN hasElevator) {
        this.hasElevator = hasElevator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public YN getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(YN isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getNameKw() {
        return nameKw;
    }

    public void setNameKw(String nameKw) {
        this.nameKw = nameKw;
    }
}
