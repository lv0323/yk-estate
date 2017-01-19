package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public class Fang {
    private Long id;
    private String title;
    private HouseType houseType;
    private HouseSubType houseSubType;
    private BizType bizType;
    private String certifAdress;
    private String certifNo;
    private Integer sNum;
    private Integer tNum;
    private Integer wNum;
    private Integer ytNum;
    private Orientation orientation;
    private BigDecimal estateArea;
    private BigDecimal realArea;
    private BigDecimal totalPrice;
    private BigDecimal bottomPrice;
    private PriceUnit priceUnit;
    private BigDecimal squarePrice;//计算值
    private BigDecimal transferFee;
    private YN resident;


}
