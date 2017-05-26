package com.lyun.estate.biz.fangcollect.entity;


import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fangcollect.def.FangOrigin;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by robin on 17/5/5.
 */
public class FangPool {
    private Long id;
    private String url;
    private Long thirdPartyId;
    private String header;
    private BizType bizType;
    private Long cityId;
    private String xiaoQuName;
    private String address;
    private HouseType houseType;
    private HouseSubType houseSubType;
    private Integer sCounts;//室
    private Integer tCounts;//厅
    private Integer wCounts;//卫
    private Integer cCounts;//厨
    private Integer ytCounts;//阳台
    private Orientation orientation;
    private String decorate;
    private BigDecimal estateArea;
    private BigDecimal realArea;
    private BigDecimal publishPrice;
    private PriceUnit priceUnit;
    private BigDecimal unitPrice; //单价
    private Integer floor;//层高
    private Integer floorCounts;//总层高
    private FloorType floorType;
    private String structureType;
    private Integer buildYear;
    private String heatingType;
    private Date createTime;
    private String contactName;
    private String contactMobile;
    private String description;
    private String imagePath;
    private Date updateTime;
    private String extInfo;
    private FangOrigin fangOrigin;
    private Long districtId;
    private YN hasElevator;
    private YN resident;

    public Integer getcCounts() {
        return cCounts;
    }

    public void setcCounts(Integer cCounts) {
        this.cCounts = cCounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(Long thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public BizType getBizType() {
        return bizType;
    }

    public void setBizType(BizType bizType) {
        this.bizType = bizType;
    }

    public Long getCityId() {return cityId;}

    public void setCityId(Long cityId) {this.cityId = cityId;}

    public String getXiaoQuName() {
        return xiaoQuName;
    }

    public void setXiaoQuName(String xiaoQuName) {
        this.xiaoQuName = xiaoQuName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public HouseSubType getHouseSubType() {
        return houseSubType;
    }

    public void setHouseSubType(HouseSubType houseSubType) {
        this.houseSubType = houseSubType;
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

    public String getDecorate() {
        return decorate;
    }

    public void setDecorate(String decorate) {
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
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

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
    }

    public String getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public FangOrigin getFangOrigin() {
        return fangOrigin;
    }

    public void setFangOrigin(FangOrigin fangOrigin) {
        this.fangOrigin = fangOrigin;
    }

    public Long getDistrictId() {return districtId;}

    public void setDistrictId(Long districtId) {this.districtId = districtId;}

    public YN getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(YN hasElevator) {
        this.hasElevator = hasElevator;
    }

    public YN getResident() {
        return resident;
    }

    public void setResident(YN resident) {
        this.resident = resident;
    }
}
