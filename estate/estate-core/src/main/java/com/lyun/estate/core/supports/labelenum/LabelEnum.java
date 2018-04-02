package com.lyun.estate.core.supports.labelenum;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Jeffrey on 2017-03-06.
 */
@JsonSerialize(using = LabelEnumSerializer.class)

/**
* 此注解用于属性或者getter方法上，用于在序列化时嵌入我们自定义的代码，比如序列化一个double时在其后面限制两位小数点*/
public interface LabelEnum {
    String getLabel();
}
