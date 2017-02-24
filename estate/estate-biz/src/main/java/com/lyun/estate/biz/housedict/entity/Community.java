package com.lyun.estate.biz.housedict.entity;

/**
 * Created by Jeffrey on 2017-01-12.
 */
public class Community {
    private Long id;
    private String name;
    private String alias;
    private String nameKw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNameKw() {
        return nameKw;
    }

    public void setNameKw(String nameKw) {
        this.nameKw = nameKw;
    }
}
