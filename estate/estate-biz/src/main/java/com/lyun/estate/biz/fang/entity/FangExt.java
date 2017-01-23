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
    private CertifyAgeType certifyAgeType;
    private String certifAdress;//证件地址
    private String certifNo;//证件编号
    private PropertyType propertyType;
    private TaxesWilling taxesWilling;
    private CommissionWilling commissionWilling;
    private BigDecimal purchasePrice;//万元
    private Date purchaseDate; //上次购入时间
    private YN isOnly;
    private YN mortgage;
    private String note;

}
