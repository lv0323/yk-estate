package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum Decorate {
    MAO("毛坯"),
    JIAN("简装"),
    ZHONG("中装"),
    JING("精装"),
    HAO("豪装"),;
    private final String label;

    Decorate(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}