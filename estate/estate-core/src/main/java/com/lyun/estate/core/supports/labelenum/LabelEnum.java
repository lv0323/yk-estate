package com.lyun.estate.core.supports.labelenum;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Jeffrey on 2017-03-06.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public interface LabelEnum {
    String getLabel();
}
