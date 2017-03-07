package com.lyun.estate.biz.customer.entity;


import com.google.common.base.MoreObjects;

import static com.lyun.estate.biz.customer.def.CustomerDefine.Source;

/**
 * Created by Jeffrey on 2017-03-06.
 */
public class CustomerTiny {
    private Long id;
    private String name;
    private Source source;
    private String mobile;

    public Long getId() {
        return id;
    }

    public CustomerTiny setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerTiny setName(String name) {
        this.name = name;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public CustomerTiny setSource(Source source) {
        this.source = source;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CustomerTiny setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("source", source)
                .add("mobile", mobile)
                .toString();
    }
}
