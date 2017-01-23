package com.lyun.estate.biz.spec.fang.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.entity.FangDescr;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.biz.housedict.entity.Station;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public class FangDetail {
    private Long id;
    private Long licenceId;
    private BizType bizType;
    private String title;
    private BigDecimal totalPrice;
    private PriceUnit priceUnit;
    private HouseType houseType;
    private HouseSubType houseSubType;
    private Integer sCounts;
    private Integer tCounts;
    private Integer cCounts;
    private Integer wCounts;
    private Integer ytCounts;
    private BigDecimal estateArea;
    private BigDecimal realArea;
    private Orientation orientation;
    private List<HouseTag> tags;
    private BigDecimal unitPrice; //单价
    private HouseProcess process;
    private BigDecimal transferPrice;
    private YN resident;
    private Integer floorCount;//总层高
    private FloorType floorType;
    private Decorate decorate;
    private StructureType structureType;
    private Integer buildYear;
    private Showing showing;
    private HeatingType heatingType;
    private Boolean hasElevator;
    private Date certifStart;
    private List<Station> stations;
    private String district;
    private String subDistrict;
    private Long xiaoQuId;
    private String xiaoquName;
    private FangDescr descr;

}
