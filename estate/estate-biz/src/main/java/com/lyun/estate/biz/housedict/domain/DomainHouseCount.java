package com.lyun.estate.biz.housedict.domain;

/**
 * Created by Jeffrey on 2017-03-28.
 */
public class DomainHouseCount {
    private Long domainId;
    private Integer sellHouseCount;
    private Integer rentHouseCount;

    public Long getDomainId() {
        return domainId;
    }

    public DomainHouseCount setDomainId(Long domainId) {
        this.domainId = domainId;
        return this;
    }

    public Integer getSellHouseCount() {
        return sellHouseCount;
    }

    public DomainHouseCount setSellHouseCount(Integer sellHouseCount) {
        this.sellHouseCount = sellHouseCount;
        return this;
    }

    public Integer getRentHouseCount() {
        return rentHouseCount;
    }

    public DomainHouseCount setRentHouseCount(Integer rentHouseCount) {
        this.rentHouseCount = rentHouseCount;
        return this;
    }
}
