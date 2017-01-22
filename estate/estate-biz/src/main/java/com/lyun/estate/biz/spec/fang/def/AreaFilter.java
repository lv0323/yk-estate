package com.lyun.estate.biz.spec.fang.def;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public enum AreaFilter {
    A_50_M("50以下"),
    A_50_70("50-70"),
    A_70_90("70-90"),
    A_90_110("90-110"),
    A_110_130("110-130"),
    A_130_150("130-150"),
    A_150_200("150-200"),
    A_200_P("200以上"),;
    private String label;

    AreaFilter(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public AreaFilter setLabel(String label) {
        this.label = label;
        return this;
    }
}
