package com.lyun.estate.biz.spec.fang.def;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.def.HouseType;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-08.
 */
public enum HouseTypeFilter {
    ZZ("住宅", Lists.newArrayList(HouseType.ZZ)),
    GY("公寓", Lists.newArrayList(HouseType.GY)),
    BS("别墅", Lists.newArrayList(HouseType.BS)),
    QT("其他", Lists.newArrayList(HouseType.SP, HouseType.XZL, HouseType.CW, HouseType.OTHER)),;

    private final String label;
    private final List<HouseType> types;

    HouseTypeFilter(String label, List<HouseType> types) {
        this.label = label;
        this.types = types;
    }

    public String getLabel() {
        return label;
    }

    public List<HouseType> getTypes() {
        return types;
    }
}
