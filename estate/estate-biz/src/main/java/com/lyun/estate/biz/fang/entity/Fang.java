package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.def.StructureType;
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
    private Integer floor;//总层高
    private Integer floorCounts;//总层高
    private FloorType floorType;
    private StructureType structureType;
    private Integer buildYear;
    private HeatingType heatingType;
    private YN hasElevator;
    private Date createTime;
    private Date updateTime;
    private Date publishTime;
    private Boolean isDeleted;
    private String nameKw;

    public Long getId() {
        return id;
    }

    public Fang setId(Long id) {
        this.id = id;
        return this;
    }

    public HouseSubType getHouseSubType() {
        return houseSubType;
    }

    public Fang setHouseSubType(HouseSubType houseSubType) {
        this.houseSubType = houseSubType;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public Fang setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public Fang setHouseType(HouseType houseType) {
        this.houseType = houseType;
        return this;
    }

    public Long getLicenceId() {
        return licenceId;
    }

    public Fang setLicenceId(Long licenceId) {
        this.licenceId = licenceId;
        return this;
    }

    public Long getXiaoQuId() {
        return xiaoQuId;
    }

    public Fang setXiaoQuId(Long xiaoQuId) {
        this.xiaoQuId = xiaoQuId;
        return this;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public Fang setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
        return this;
    }

    public Integer gettCounts() {
        return tCounts;
    }

    public Fang settCounts(Integer tCounts) {
        this.tCounts = tCounts;
        return this;
    }

    public Integer getwCounts() {
        return wCounts;
    }

    public Fang setwCounts(Integer wCounts) {
        this.wCounts = wCounts;
        return this;
    }

    public Integer getcCounts() {
        return cCounts;
    }

    public Fang setcCounts(Integer cCounts) {
        this.cCounts = cCounts;
        return this;
    }

    public Integer getYtCounts() {
        return ytCounts;
    }

    public Fang setYtCounts(Integer ytCounts) {
        this.ytCounts = ytCounts;
        return this;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Fang setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public Decorate getDecorate() {
        return decorate;
    }

    public Fang setDecorate(Decorate decorate) {
        this.decorate = decorate;
        return this;
    }

    public BigDecimal getEstateArea() {
        return estateArea;
    }

    public Fang setEstateArea(BigDecimal estateArea) {
        this.estateArea = estateArea;
        return this;
    }

    public BigDecimal getRealArea() {
        return realArea;
    }

    public Fang setRealArea(BigDecimal realArea) {
        this.realArea = realArea;
        return this;
    }

    public BigDecimal getPublishPrice() {
        return publishPrice;
    }

    public Fang setPublishPrice(BigDecimal publishPrice) {
        this.publishPrice = publishPrice;
        return this;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public Fang setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
        return this;
    }

    public BigDecimal getTransferPrice() {
        return transferPrice;
    }

    public Fang setTransferPrice(BigDecimal transferPrice) {
        this.transferPrice = transferPrice;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Fang setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public BigDecimal getBottomPrice() {
        return bottomPrice;
    }

    public Fang setBottomPrice(BigDecimal bottomPrice) {
        this.bottomPrice = bottomPrice;
        return this;
    }

    public HouseProcess getProcess() {
        return process;
    }

    public Fang setProcess(HouseProcess process) {
        this.process = process;
        return this;
    }

    public YN getResident() {
        return resident;
    }

    public Fang setResident(YN resident) {
        this.resident = resident;
        return this;
    }

    public Integer getFloorCounts() {
        return floorCounts;
    }

    public Fang setFloorCounts(Integer floorCounts) {
        this.floorCounts = floorCounts;
        return this;
    }

    public FloorType getFloorType() {
        return floorType;
    }

    public Fang setFloorType(FloorType floorType) {
        this.floorType = floorType;
        return this;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public Fang setStructureType(StructureType structureType) {
        this.structureType = structureType;
        return this;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public Fang setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
        return this;
    }

    public HeatingType getHeatingType() {
        return heatingType;
    }

    public Fang setHeatingType(HeatingType heatingType) {
        this.heatingType = heatingType;
        return this;
    }

    public YN getHasElevator() {
        return hasElevator;
    }

    public Fang setHasElevator(YN hasElevator) {
        this.hasElevator = hasElevator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Fang setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Fang setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public Fang setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Fang setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public String getNameKw() {
        return nameKw;
    }

    public Fang setNameKw(String nameKw) {
        this.nameKw = nameKw;
        return this;
    }

    public Integer getFloor() {
        return floor;
    }

    public Fang setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    @Override
    public String toString() {
        return "Fang{" +
                "id=" + id +
                ", houseSubType=" + houseSubType +
                ", bizType=" + bizType +
                ", houseType=" + houseType +
                ", licenceId=" + licenceId +
                ", xiaoQuId=" + xiaoQuId +
                ", sCounts=" + sCounts +
                ", tCounts=" + tCounts +
                ", wCounts=" + wCounts +
                ", cCounts=" + cCounts +
                ", ytCounts=" + ytCounts +
                ", orientation=" + orientation +
                ", decorate=" + decorate +
                ", estateArea=" + estateArea +
                ", realArea=" + realArea +
                ", publishPrice=" + publishPrice +
                ", priceUnit=" + priceUnit +
                ", transferPrice=" + transferPrice +
                ", unitPrice=" + unitPrice +
                ", bottomPrice=" + bottomPrice +
                ", process=" + process +
                ", resident=" + resident +
                ", floor=" + floor +
                ", floorCounts=" + floorCounts +
                ", floorType=" + floorType +
                ", structureType=" + structureType +
                ", buildYear=" + buildYear +
                ", heatingType=" + heatingType +
                ", hasElevator=" + hasElevator +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", publishTime=" + publishTime +
                ", isDeleted=" + isDeleted +
                ", nameKw='" + nameKw + '\'' +
                '}';
    }
}
