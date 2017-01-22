package com.lyun.estate.biz.spec.fang.entity;

import com.lyun.estate.biz.spec.fang.def.*;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public class FangDetail {
    private Long id;
    private String title;
    private String desc;
    private HouseType houseType;
    private HouseSubType houseSubType;
    private BizType bizType;
    private String certifAdress;
    private String certifNo;
    private Integer sCounts;
    private Integer tCounts;
    private Orientation orientation;
    private BigDecimal estateArea;
    private BigDecimal totalPrice;
    private PriceUnit priceUnit;
    private BigDecimal unitPrice; //单价
    private BigDecimal transferFee;
    private YN resident;

}
