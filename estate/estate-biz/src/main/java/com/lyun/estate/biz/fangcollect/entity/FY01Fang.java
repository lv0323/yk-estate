package com.lyun.estate.biz.fangcollect.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.support.def.BizType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by robin on 17/5/5.
 */
public class FY01Fang {
    private Long id;
    private String url;
    private String thirdPartyId;
    private HouseSubType houseSubType;
    private String header;
    private BizType bizType;
    private String houseTypeStr;
    private String xiaoQuAddr;
    private String xiaoQuName;
    private Integer sCounts;//室
    private Integer tCounts;//厅
    private Integer wCounts;//卫
    private Integer cCounts;//厨
    private Integer ytCounts;//阳台
    private String orientationStr;
    private String decorateStr;
    private BigDecimal estateArea;
    private BigDecimal publishPrice;
    private PriceUnit priceUnit;
    private BigDecimal unitPrice; //单价
    private Integer floor;//层高
    private Integer floorCounts;//总层高
    private Date createTime;
    private String contactName;
    private String contactMobile;
    private String description;
    private String imagePath;
    private Date updataTime;
    private String overview;
    private String extInfo;
    private String mapProcess;

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

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public FY01Fang setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
        return this;
    }

    public HouseSubType getHouseSubType() {
        return houseSubType;
    }

    public void setHouseSubType(HouseSubType houseSubType) {
        this.houseSubType = houseSubType;
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

    public String getHouseTypeStr() {
        return houseTypeStr;
    }

    public void setHouseTypeStr(String houseTypeStr) {
        this.houseTypeStr = houseTypeStr;
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

    public String getOrientationStr() {
        return orientationStr;
    }

    public void setOrientationStr(String orientationStr) {
        this.orientationStr = orientationStr;
    }

    public String getDecorateStr() {
        return decorateStr;
    }

    public void setDecorateStr(String decorateStr) {
        this.decorateStr = decorateStr;
    }

    public BigDecimal getEstateArea() {
        return estateArea;
    }

    public void setEstateArea(BigDecimal estateArea) {
        this.estateArea = estateArea;
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

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getMapProcess() {
        return mapProcess;
    }

    public void setMapProcess(String mapProcess) {
        this.mapProcess = mapProcess;
    }

    public String getXiaoQuAddr() {
        return xiaoQuAddr;
    }

    public void setXiaoQuAddr(String xiaoQuAddr) {
        this.xiaoQuAddr = xiaoQuAddr;
    }

    public String getXiaoQuName() {
        return xiaoQuName;
    }

    public void setXiaoQuName(String xiaoQuName) {
        this.xiaoQuName = xiaoQuName;
    }
}
