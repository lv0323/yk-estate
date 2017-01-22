package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.biz.spec.fang.def.*;
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
    private Integer sCounts;
    private Integer tCounts;
    private Integer wCounts;
    private Integer ytCounts;
    private Orientation orientation;
    private BigDecimal estateArea;
    private BigDecimal realArea;
    private BigDecimal totalPrice;
    private BigDecimal bottomPrice;
    private PriceUnit priceUnit;
    private BigDecimal unitPrice; //单价
    private BigDecimal transferFee;
    private YN resident;
    private Integer maxFloor;//总层高
    private StructureType structureType;
    private Integer buildYear;


}
