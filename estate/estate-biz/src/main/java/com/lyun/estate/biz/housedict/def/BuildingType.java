package com.lyun.estate.biz.housedict.def;

/**
 * Created by Jeffrey on 2017-01-03.
 */
public enum BuildingType {
    TA("塔楼", 0),
    BAN("板楼", 1),
    TA_BAN("塔板结合", 2),
    PING("平房", 3),
    OTHER("未知类型", 4);

    private final String label;
    private final Integer bits;

    BuildingType(String label, Integer bits) {
        this.label = label;
        this.bits = bits;
    }


}
