package com.lyun.estate.biz.employee.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

@JsonSerialize(using = LabelEnumSerializer.class)
public enum Gender {
    M("男"),
    F("女");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
