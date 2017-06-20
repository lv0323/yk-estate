package com.lyun.estate.biz.report.engine;

/**
 * Created by jesse on 2017/1/25.
 */
public class ReportHeader {
    private String name;
    private Class type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ReportHeader{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
