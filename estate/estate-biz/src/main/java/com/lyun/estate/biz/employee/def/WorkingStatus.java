package com.lyun.estate.biz.employee.def;

public enum WorkingStatus {
    WORKING("正式"),
    TRAINING("试用"),
    LEAVING("请假"),
    INTERN("实习");

    private String status;

    WorkingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
