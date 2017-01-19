package com.lyun.estate.biz.fang.def;

import com.google.common.collect.Lists;

import java.util.List;

import static com.lyun.estate.biz.fang.def.HouseSubType.*;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum HouseType {
    ZZ("住宅", Lists.newArrayList(DC, GC, XGC, DCFS, GCFS, DCYS, GCYS, QL, SHY)),
    GY("公寓", Lists.newArrayList(PT_GY, SW_GY, JD_GY)),
    SP("商铺", Lists.newArrayList(ONE_P, TWO_P, THREE_P, FOUR_P, MULTI_P)),
    BS("别墅", Lists.newArrayList(DD_BS, LT_BS, D_BS)),
    XZL("写字楼", Lists.newArrayList(C_CZL, SZ_CZL, ZH_CZL, CF_CZL)),
    CW("车位", Lists.newArrayList(LUTIAN_CW, SN_CW, DX_CW, JKC_CW, LITI_CW)),
    OTHER("其他", Lists.newArrayList(DC, GC, XGC, DCFS, GCFS, DCYS, GCYS, QL, SHY, QT)),;

    private final String label;
    private final List<HouseSubType> subTypes;

    HouseType(String label, List<HouseSubType> subTypes) {
        this.label = label;
        this.subTypes = subTypes;
    }

    public String getLabel() {
        return label;
    }

    public List<HouseSubType> getSubTypes() {
        return subTypes;
    }
}
