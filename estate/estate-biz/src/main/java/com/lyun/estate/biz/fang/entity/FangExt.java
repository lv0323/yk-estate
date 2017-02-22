package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public class FangExt {
    private Long id;
    private Long fangId;
    private HouseLevel level;
    private Showing showing;
    private DelegateType delegateType;
    private Date delegateStart;
    private Date delegateEnd;
    private HouseStatus status;
    private HouseSource source;
    private CertifType certifType;
    private String certifAdress;//证件地址
    private String certifNo;//证件编号
    private PropertyType propertyType;
    private TaxesWilling taxesWilling;
    private CommissionWilling commissionWilling;
    private BigDecimal purchasePrice;//万元
    private Date purchaseDate; //上次购入时间
    private YN isOnly;
    private Integer overYears;
    private YN mortgage;
    private String note;

    public Long getId() {
        return id;
    }

    public FangExt setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangExt setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public HouseLevel getLevel() {
        return level;
    }

    public FangExt setLevel(HouseLevel level) {
        this.level = level;
        return this;
    }

    public Showing getShowing() {
        return showing;
    }

    public FangExt setShowing(Showing showing) {
        this.showing = showing;
        return this;
    }

    public DelegateType getDelegateType() {
        return delegateType;
    }

    public FangExt setDelegateType(DelegateType delegateType) {
        this.delegateType = delegateType;
        return this;
    }

    public Date getDelegateStart() {
        return delegateStart;
    }

    public FangExt setDelegateStart(Date delegateStart) {
        this.delegateStart = delegateStart;
        return this;
    }

    public Date getDelegateEnd() {
        return delegateEnd;
    }

    public FangExt setDelegateEnd(Date delegateEnd) {
        this.delegateEnd = delegateEnd;
        return this;
    }

    public HouseStatus getStatus() {
        return status;
    }

    public FangExt setStatus(HouseStatus status) {
        this.status = status;
        return this;
    }

    public HouseSource getSource() {
        return source;
    }

    public FangExt setSource(HouseSource source) {
        this.source = source;
        return this;
    }

    public CertifType getCertifType() {
        return certifType;
    }

    public FangExt setCertifType(CertifType certifType) {
        this.certifType = certifType;
        return this;
    }

    public String getCertifAdress() {
        return certifAdress;
    }

    public FangExt setCertifAdress(String certifAdress) {
        this.certifAdress = certifAdress;
        return this;
    }

    public String getCertifNo() {
        return certifNo;
    }

    public FangExt setCertifNo(String certifNo) {
        this.certifNo = certifNo;
        return this;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public FangExt setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public TaxesWilling getTaxesWilling() {
        return taxesWilling;
    }

    public FangExt setTaxesWilling(TaxesWilling taxesWilling) {
        this.taxesWilling = taxesWilling;
        return this;
    }

    public CommissionWilling getCommissionWilling() {
        return commissionWilling;
    }

    public FangExt setCommissionWilling(CommissionWilling commissionWilling) {
        this.commissionWilling = commissionWilling;
        return this;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public FangExt setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
        return this;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public FangExt setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public YN getIsOnly() {
        return isOnly;
    }

    public FangExt setIsOnly(YN isOnly) {
        this.isOnly = isOnly;
        return this;
    }

    public Integer getOverYears() {
        return overYears;
    }

    public FangExt setOverYears(Integer overYears) {
        this.overYears = overYears;
        return this;
    }

    public YN getMortgage() {
        return mortgage;
    }

    public FangExt setMortgage(YN mortgage) {
        this.mortgage = mortgage;
        return this;
    }

    public String getNote() {
        return note;
    }

    public FangExt setNote(String note) {
        this.note = note;
        return this;
    }
}
