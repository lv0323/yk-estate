package com.lyun.estate.biz.fang.domian;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-02-23.
 */
public class MgtFangSummary {
    private Long id;
    private BizType bizType;
    private Long licenceId;
    private String head;
    private Long subDistrictId;
    private String title;
    private BigDecimal publishPrice;
    private PriceUnit priceUnit;
    private BigDecimal unitPrice;
    private Long xiaoQuId;
    private String xiaoQuName;
    private BigDecimal estateArea;
    private YN isOnly;
    private Integer overYears;
    private Showing showing;
    private YN nearLine;
    private HouseProcess process;
    private Integer sCounts;//室
    private Integer tCounts;//厅
    private Integer wCounts;//卫
    private Integer cCounts;//厨
    private Integer ytCounts;//阳台
    private Integer floor;//总层高
    private Integer floorCounts;//总层高
    private Orientation orientation;
    private Decorate decorate;
    private Date createTime;
    private Date delegateStart;
    private String imageURI;
    private List<HouseTag> tags;

    public Long getId() {
        return id;
    }

    public MgtFangSummary setId(Long id) {
        this.id = id;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public MgtFangSummary setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public Long getLicenceId() {
        return licenceId;
    }

    public MgtFangSummary setLicenceId(Long licenceId) {
        this.licenceId = licenceId;
        return this;
    }

    public String getHead() {
        return head;
    }

    public MgtFangSummary setHead(String head) {
        this.head = head;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public MgtFangSummary setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MgtFangSummary setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPublishPrice() {
        return publishPrice;
    }

    public MgtFangSummary setPublishPrice(BigDecimal publishPrice) {
        this.publishPrice = publishPrice;
        return this;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public MgtFangSummary setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public MgtFangSummary setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Long getXiaoQuId() {
        return xiaoQuId;
    }

    public MgtFangSummary setXiaoQuId(Long xiaoQuId) {
        this.xiaoQuId = xiaoQuId;
        return this;
    }

    public String getXiaoQuName() {
        return xiaoQuName;
    }

    public MgtFangSummary setXiaoQuName(String xiaoQuName) {
        this.xiaoQuName = xiaoQuName;
        return this;
    }

    public BigDecimal getEstateArea() {
        return estateArea;
    }

    public MgtFangSummary setEstateArea(BigDecimal estateArea) {
        this.estateArea = estateArea;
        return this;
    }

    public YN getIsOnly() {
        return isOnly;
    }

    public MgtFangSummary setIsOnly(YN isOnly) {
        this.isOnly = isOnly;
        return this;
    }

    public Integer getOverYears() {
        return overYears;
    }

    public MgtFangSummary setOverYears(Integer overYears) {
        this.overYears = overYears;
        return this;
    }

    public Showing getShowing() {
        return showing;
    }

    public MgtFangSummary setShowing(Showing showing) {
        this.showing = showing;
        return this;
    }

    public YN getNearLine() {
        return nearLine;
    }

    public MgtFangSummary setNearLine(YN nearLine) {
        this.nearLine = nearLine;
        return this;
    }

    public HouseProcess getProcess() {
        return process;
    }

    public MgtFangSummary setProcess(HouseProcess process) {
        this.process = process;
        return this;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public MgtFangSummary setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
        return this;
    }

    public Integer gettCounts() {
        return tCounts;
    }

    public MgtFangSummary settCounts(Integer tCounts) {
        this.tCounts = tCounts;
        return this;
    }

    public Integer getwCounts() {
        return wCounts;
    }

    public MgtFangSummary setwCounts(Integer wCounts) {
        this.wCounts = wCounts;
        return this;
    }

    public Integer getcCounts() {
        return cCounts;
    }

    public MgtFangSummary setcCounts(Integer cCounts) {
        this.cCounts = cCounts;
        return this;
    }

    public Integer getYtCounts() {
        return ytCounts;
    }

    public MgtFangSummary setYtCounts(Integer ytCounts) {
        this.ytCounts = ytCounts;
        return this;
    }

    public Integer getFloor() {
        return floor;
    }

    public MgtFangSummary setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public Integer getFloorCounts() {
        return floorCounts;
    }

    public MgtFangSummary setFloorCounts(Integer floorCounts) {
        this.floorCounts = floorCounts;
        return this;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public MgtFangSummary setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public Decorate getDecorate() {
        return decorate;
    }

    public MgtFangSummary setDecorate(Decorate decorate) {
        this.decorate = decorate;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MgtFangSummary setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getDelegateStart() {
        return delegateStart;
    }

    public MgtFangSummary setDelegateStart(Date delegateStart) {
        this.delegateStart = delegateStart;
        return this;
    }

    public String getImageURI() {
        return imageURI;
    }

    public MgtFangSummary setImageURI(String imageURI) {
        this.imageURI = imageURI;
        return this;
    }

    public List<HouseTag> getTags() {
        return tags;
    }

    public MgtFangSummary setTags(List<HouseTag> tags) {
        this.tags = tags;
        return this;
    }
}
