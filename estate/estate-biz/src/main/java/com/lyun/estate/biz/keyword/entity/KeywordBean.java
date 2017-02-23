package com.lyun.estate.biz.keyword.entity;

import com.lyun.estate.biz.support.def.DomainType;

/**
 * Created by Jeffrey on 2017-01-06.
 */
public class KeywordBean {
    private Long id;
    private DomainType domainType;
    private String name;
    private String alias;
    private String keyword;

    public Long getId() {
        return id;
    }

    public KeywordBean setId(Long id) {
        this.id = id;
        return this;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public KeywordBean setDomainType(DomainType domainType) {
        this.domainType = domainType;
        return this;
    }

    public String getName() {
        return name;
    }

    public KeywordBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public KeywordBean setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public KeywordBean setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    @Override
    public String toString() {
        return "KeywordBean{" +
                "id=" + id +
                ", domainType=" + domainType +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
