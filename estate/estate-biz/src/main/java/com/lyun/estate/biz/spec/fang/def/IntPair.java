package com.lyun.estate.biz.spec.fang.def;

import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jeffrey on 2017-02-09.
 */
public class IntPair {

    private Integer min;
    private Integer max;

    public static IntPair fromIntPairStr(String intPairStr) {
        if (intPairStr != null) {
            Pattern pattern = Pattern.compile("^(\\d*)\\.(\\d*)$");
            Matcher matcher = pattern.matcher(intPairStr);
            if (matcher.matches()) {
                Integer min = Strings.isNullOrEmpty(matcher.group(1)) ? null : Integer.valueOf(matcher.group(1));
                Integer max = Strings.isNullOrEmpty(matcher.group(2)) ? null : Integer.valueOf(matcher.group(2));
                return new IntPair().setMin(min).setMax(max);
            }
        }
        return null;
    }

    public Integer getMin() {
        return min;
    }

    public IntPair setMin(Integer min) {
        this.min = min;
        return this;
    }

    public Integer getMax() {
        return max;
    }

    public IntPair setMax(Integer max) {
        this.max = max;
        return this;
    }

    @Override
    public String toString() {
        return "IntPair{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
