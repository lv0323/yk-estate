package com.lyun.estate.biz.employee.def;

public enum Status {
    WORKING("正式"),
    TRAINING("试用"),
    LEAVING("请假"),
    INTERN("实习");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
