package com.lyun.estate.biz.housedict.domain;

/**
 * Created by Jeffrey on 2017-02-22.
 */
public class XiaoQuOption {
    private Long xiaoQuId;
    private String xiaoQuName;

    public Long getXiaoQuId() {
        return xiaoQuId;
    }

    public XiaoQuOption setXiaoQuId(Long xiaoQuId) {
        this.xiaoQuId = xiaoQuId;
        return this;
    }

    public String getXiaoQuName() {
        return xiaoQuName;
    }

    public XiaoQuOption setXiaoQuName(String xiaoQuName) {
        this.xiaoQuName = xiaoQuName;
        return this;
    }
}
