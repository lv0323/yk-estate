package com.lyun.estate.biz.employee.def;

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
