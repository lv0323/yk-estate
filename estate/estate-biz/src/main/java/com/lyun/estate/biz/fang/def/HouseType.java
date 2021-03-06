package com.lyun.estate.biz.fang.def;

import com.google.common.collect.Lists;
import com.lyun.estate.core.supports.labelenum.LabelEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum HouseType implements LabelEnum {
    ZZ("住宅",
            Lists.newArrayList(HouseSubType.DC,
                    HouseSubType.GC,
                    HouseSubType.XGC,
                    HouseSubType.DCFS,
                    HouseSubType.GCFS,
                    HouseSubType.DCYS,
                    HouseSubType.GCYS,
                    HouseSubType.QL,
                    HouseSubType.SHY)),
    SZ("商住", Lists.newArrayList(HouseSubType.D_SZ)),
    GY("公寓", Lists.newArrayList(HouseSubType.PT_GY, HouseSubType.SW_GY, HouseSubType.JD_GY)),
    SP("商铺",
            Lists.newArrayList(HouseSubType.ONE_P,
                    HouseSubType.TWO_P,
                    HouseSubType.THREE_P,
                    HouseSubType.FOUR_P,
                    HouseSubType.MULTI_P)),
    BS("别墅", Lists.newArrayList(HouseSubType.DD_BS, HouseSubType.LT_BS, HouseSubType.D_BS)),
    XZL("写字楼", Lists.newArrayList(HouseSubType.C_CZL, HouseSubType.SZ_CZL, HouseSubType.ZH_CZL, HouseSubType.CF_CZL)),
    CW("车位",
            Lists.newArrayList(HouseSubType.LUTIAN_CW,
                    HouseSubType.SN_CW,
                    HouseSubType.DX_CW,
                    HouseSubType.JKC_CW,
                    HouseSubType.LITI_CW)),
    OTHER("其他",
            Lists.newArrayList(HouseSubType.QT)),;

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

    public static HouseType parse(String label) {
        return Arrays.stream(HouseType.values()).filter(t -> Objects.equals(t.getLabel(), label)).findFirst().orElse(null);
    }
}
