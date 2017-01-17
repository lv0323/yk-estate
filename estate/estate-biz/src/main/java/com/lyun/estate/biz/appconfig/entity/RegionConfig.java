package com.lyun.estate.biz.appconfig.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-16.
 */
public class RegionConfig {
    private List<Region> regions;
    private Date lastUpdatedTime;

    public List<Region> getRegions() {
        return regions;
    }

    public RegionConfig setRegions(List<Region> regions) {
        this.regions = regions;
        return this;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public RegionConfig setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
        return this;
    }
}
