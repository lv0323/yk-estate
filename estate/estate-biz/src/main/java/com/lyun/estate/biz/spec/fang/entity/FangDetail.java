package com.lyun.estate.biz.spec.fang.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.entity.FangDescr;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.biz.housedict.entity.Station;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public class FangDetail {
    private Long id;
    private Long licenceId;
    private BizType bizType;
    private String title;
    private BigDecimal totalPrice;
    private PriceUnit priceUnit;
    private HouseType houseType;
    private HouseSubType houseSubType;
    private Integer sCounts;
    private Integer tCounts;
    private Integer cCounts;
    private Integer wCounts;
    private Integer ytCounts;
    private BigDecimal estateArea;
    private BigDecimal realArea;
    private Orientation orientation;
    private List<HouseTag> tags;
    private BigDecimal unitPrice; //单价
    private HouseProcess process;
    private BigDecimal transferPrice;
    private YN resident;
    private Integer floorCount;//总层高
    private FloorType floorType;
    private Decorate decorate;
    private StructureType structureType;
    private Integer buildYear;
    private Showing showing;
    private HeatingType heatingType;
    private Boolean hasElevator;
    private Date purchase_date;
    private List<Station> stations;
    private String district;
    private String subDistrict;
    private Long xiaoQuId;
    private String xiaoQuName;
    private FangDescr descr;

    public Long getId() {
        return id;
    }

    public FangDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getLicenceId() {
        return licenceId;
    }

    public FangDetail setLicenceId(Long licenceId) {
        this.licenceId = licenceId;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public FangDetail setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FangDetail setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public FangDetail setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public FangDetail setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
        return this;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public FangDetail setHouseType(HouseType houseType) {
        this.houseType = houseType;
        return this;
    }

    public HouseSubType getHouseSubType() {
        return houseSubType;
    }

    public FangDetail setHouseSubType(HouseSubType houseSubType) {
        this.houseSubType = houseSubType;
        return this;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public FangDetail setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
        return this;
    }

    public Integer gettCounts() {
        return tCounts;
    }

    public FangDetail settCounts(Integer tCounts) {
        this.tCounts = tCounts;
        return this;
    }

    public Integer getcCounts() {
        return cCounts;
    }

    public FangDetail setcCounts(Integer cCounts) {
        this.cCounts = cCounts;
        return this;
    }

    public Integer getwCounts() {
        return wCounts;
    }

    public FangDetail setwCounts(Integer wCounts) {
        this.wCounts = wCounts;
        return this;
    }

    public Integer getYtCounts() {
        return ytCounts;
    }

    public FangDetail setYtCounts(Integer ytCounts) {
        this.ytCounts = ytCounts;
        return this;
    }

    public BigDecimal getEstateArea() {
        return estateArea;
    }

    public FangDetail setEstateArea(BigDecimal estateArea) {
        this.estateArea = estateArea;
        return this;
    }

    public BigDecimal getRealArea() {
        return realArea;
    }

    public FangDetail setRealArea(BigDecimal realArea) {
        this.realArea = realArea;
        return this;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public FangDetail setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public List<HouseTag> getTags() {
        return tags;
    }

    public FangDetail setTags(List<HouseTag> tags) {
        this.tags = tags;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public FangDetail setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public HouseProcess getProcess() {
        return process;
    }

    public FangDetail setProcess(HouseProcess process) {
        this.process = process;
        return this;
    }

    public BigDecimal getTransferPrice() {
        return transferPrice;
    }

    public FangDetail setTransferPrice(BigDecimal transferPrice) {
        this.transferPrice = transferPrice;
        return this;
    }

    public YN getResident() {
        return resident;
    }

    public FangDetail setResident(YN resident) {
        this.resident = resident;
        return this;
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public FangDetail setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
        return this;
    }

    public FloorType getFloorType() {
        return floorType;
    }

    public FangDetail setFloorType(FloorType floorType) {
        this.floorType = floorType;
        return this;
    }

    public Decorate getDecorate() {
        return decorate;
    }

    public FangDetail setDecorate(Decorate decorate) {
        this.decorate = decorate;
        return this;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public FangDetail setStructureType(StructureType structureType) {
        this.structureType = structureType;
        return this;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public FangDetail setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
        return this;
    }

    public Showing getShowing() {
        return showing;
    }

    public FangDetail setShowing(Showing showing) {
        this.showing = showing;
        return this;
    }

    public HeatingType getHeatingType() {
        return heatingType;
    }

    public FangDetail setHeatingType(HeatingType heatingType) {
        this.heatingType = heatingType;
        return this;
    }

    public Boolean getHasElevator() {
        return hasElevator;
    }

    public FangDetail setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
        return this;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public FangDetail setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
        return this;
    }

    public List<Station> getStations() {
        return stations;
    }

    public FangDetail setStations(List<Station> stations) {
        this.stations = stations;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public FangDetail setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public FangDetail setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
        return this;
    }

    public Long getXiaoQuId() {
        return xiaoQuId;
    }

    public FangDetail setXiaoQuId(Long xiaoQuId) {
        this.xiaoQuId = xiaoQuId;
        return this;
    }

    public String getXiaoQuName() {
        return xiaoQuName;
    }

    public FangDetail setXiaoQuName(String xiaoQuName) {
        this.xiaoQuName = xiaoQuName;
        return this;
    }

    public FangDescr getDescr() {
        return descr;
    }

    public FangDetail setDescr(FangDescr descr) {
        this.descr = descr;
        return this;
    }
}
