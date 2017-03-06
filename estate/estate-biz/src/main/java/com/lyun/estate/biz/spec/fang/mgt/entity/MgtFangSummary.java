package com.lyun.estate.biz.spec.fang.mgt.entity;

import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.fang.def.HouseProcess;
import com.lyun.estate.biz.fang.def.Orientation;
import com.lyun.estate.biz.fang.def.PriceUnit;
import com.lyun.estate.biz.fang.domian.FangInfoOwnerDTO;
import com.lyun.estate.biz.spec.fang.domain.FangForTag;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-23.
 */
public class MgtFangSummary extends FangForTag {
    private Long id;
    private BizType bizType;
    private Long licenceId;
    private String head;
    private Long subDistrictId;
    private BigDecimal publishPrice;
    private PriceUnit priceUnit;
    private BigDecimal unitPrice;
    private Long xiaoQuId;
    private String xiaoQuName;
    private BigDecimal estateArea;
    private BigDecimal realArea;
    private HouseProcess process;
    private Integer sCounts;//室
    private Integer tCounts;//厅
    private Integer wCounts;//卫
    private Integer cCounts;//厨
    private Integer ytCounts;//阳台
    private Integer floor;//总层高
    private Integer floorCounts;//总层高
    private Orientation orientation;
    private Date createTime;
    private Date publishTime;
    private Date delegateStart;
    private String imageURI;
    private FangInfoOwnerDTO infoOwner;

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

    public Date getPublishTime() {
        return publishTime;
    }

    public MgtFangSummary setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    public FangInfoOwnerDTO getInfoOwner() {
        return infoOwner;
    }

    public MgtFangSummary setInfoOwner(FangInfoOwnerDTO infoOwner) {
        this.infoOwner = infoOwner;
        return this;
    }

    public BigDecimal getRealArea() {
        return realArea;
    }

    public MgtFangSummary setRealArea(BigDecimal realArea) {
        this.realArea = realArea;
        return this;
    }
}
