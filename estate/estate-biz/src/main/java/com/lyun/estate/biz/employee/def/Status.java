package com.lyun.estate.biz.employee.def;

public enum Status {
    WORKING("在职"),
    INTERN("实习"),
    QUIT("离职");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
