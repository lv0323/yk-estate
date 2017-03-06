package com.lyun.estate.biz.fang.def;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.lyun.estate.core.supports.labelenum.LabelEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jeffrey on 2017-01-03.
 */
public enum StructureType implements LabelEnum {
    TA("塔楼", 1),
    BAN("板楼", 2),
    TA_BAN("塔板结合", 3),
    PING("平房", 4),
    OTHER("未知类型", 5);

    private final String label;
    private final Integer bits;

    StructureType(String label, Integer bits) {
        this.label = label;
        this.bits = bits;
    }

    public static List<Integer> possibleIntValues(StructureType type) {
        List<Integer> result = Lists.newArrayList();
        for (int i = 1; i < 17; i++) {
            if (((i >> (type.bits - 1)) & 0x0001) == 1) {
                result.add(i);
            }
        }
        return result;
    }

    public static List<StructureType> getTypes(Integer structureType) {
        if (structureType == null) {
            return new ArrayList<>();
        }
        List<StructureType> result = Lists.newArrayList();
        Arrays.stream(StructureType.values()).forEach(
                type -> {
                    if (((structureType >> (type.bits - 1)) & 0x0001) == 1) {
                        result.add(type);
                    }
                }
        );
        return result;
    }

    public static String getTypeStr(Integer structureType) {
        return Joiner.on("/")
                .join(getTypes(structureType)
                        .stream()
                        .map(StructureType::getLabel)
                        .collect(Collectors.toList()));
    }

    public String getLabel() {
        return label;
    }

}
