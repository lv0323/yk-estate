package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum HouseSubType {
    DC("多层"),
    GC("高层"),
    XGC("小高层"),
    DCFS("多层复式"),
    GCFS("高层复式"),
    DCYS("多层跃式"),
    GCYS("高层跃式"),
    QL("裙楼"),
    SHY("四合院"),
    PT_GY("普通公寓"),
    SW_GY("商务公寓"),
    JD_GY("酒店公寓"),
    ONE_P("单铺"),
    TWO_P("二铺打通"),
    THREE_P("三铺打通"),
    FOUR_P("四铺打通"),
    MULTI_P("多铺打通"),
    DD_BS("独栋别墅"),
    LT_BS("联体别墅"),
    D_BS("叠墅"),
    C_CZL("纯写字楼"),
    SZ_CZL("商住楼"),
    ZH_CZL("综合楼"),
    CF_CZL("厂房改造"),
    LUTIAN_CW("露天车位"),
    SN_CW("室内车位"),
    DX_CW("地下车位"),
    JKC_CW("架空层车位"),
    LITI_CW("立体车位"),
    QT("其他"),;

    private final String label;

    HouseSubType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
