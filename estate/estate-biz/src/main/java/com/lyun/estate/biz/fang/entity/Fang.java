package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public class Fang {
    private Long id;
    private HouseSubType houseSubType;
    private BizType bizType;
    private HouseType houseType;
    private Long licenceId;
    private Long xiaoQuId;
    private Integer sCounts;//室
    private Integer tCounts;//厅
    private Integer wCounts;//卫
    private Integer cCounts;//厨
    private Integer ytCounts;//阳台
    private Orientation orientation;
    private Decorate decorate;
    private BigDecimal estateArea;
    private BigDecimal realArea;
    private BigDecimal publishPrice;
    private PriceUnit priceUnit;
    private BigDecimal transferPrice;
    private BigDecimal unitPrice; //单价
    private BigDecimal bottomPrice;
    private HouseProcess process;
    private YN resident;
    private Integer floorCounts;//总层高
    private FloorType floorType;
    private StructureType structureType;
    private Integer buildYear;
    private HeatingType heatingType;
    private YN hasElevator;
    private Date createTime;
    private Date updateTime;
    private YN isDeleted;


}
