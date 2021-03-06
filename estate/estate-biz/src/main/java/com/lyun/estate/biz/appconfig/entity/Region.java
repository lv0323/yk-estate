package com.lyun.estate.biz.appconfig.entity;

import com.lyun.estate.biz.support.def.DomainType;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-16.
 */
public class Region {
    private Long id;
    private DomainType type;
    private String abbr;
    private String name;
    private String regionBound;
    private List<Region> subs;

    public Long getId() {
        return id;
    }

    public Region setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public Region setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getName() {
        return name;
    }

    public Region setName(String name) {
        this.name = name;
        return this;
    }

    public List<Region> getSubs() {
        return subs;
    }

    public Region setSubs(List<Region> subs) {
        this.subs = subs;
        return this;
    }

    public DomainType getType() {
        return type;
    }

    public Region setType(DomainType type) {
        this.type = type;
        return this;
    }

    public String getRegionBound() {
        return regionBound;
    }

    public Region setRegionBound(String regionBound) {
        this.regionBound = regionBound;
        return this;
    }
}
