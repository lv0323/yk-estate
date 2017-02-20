package com.lyun.estate.mgt.fang;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.entity.Fang;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-02-19.
 */
@RestController
@RequestMapping("fang")
public class FangRest {
    @PostMapping("create")
    public Fang createFang(@RequestParam HouseType houseType,
                           @RequestParam HouseSubType houseSubType,
                           @RequestParam Long xiaoquId,
                           @RequestParam Long buildingId,
                           @RequestParam Long buildingUnitId,
                           @RequestParam String houseNo,
                           @RequestParam Integer floor,
                           @RequestParam Integer floorCounts,
                           String address,
                           String certifNo,
                           Integer sCounts,
                           Integer tCounts,
                           Integer cCounts,
                           Integer wCounts,
                           Integer ytCounts,
                           Orientation orientation,
                           BigDecimal estateArea,
                           BigDecimal realArea,
                           BizType bizType,
                           BigDecimal publishPrice,
                           PriceUnit priceUnit) {
        return null;
    }

}
