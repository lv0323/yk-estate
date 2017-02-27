package com.lyun.estate.biz.xiaoqu.entity;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class XiaoQu {
    private Long id;
    private Long communityId;
    private BigDecimal ranking;
    private Integer avgPrice;
    private Integer sellHouseCount;
    private Integer rentHouseCount;
    private String address;

    public Long getId() {
        return id;
    }

    public XiaoQu setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public XiaoQu setCommunityId(Long communityId) {
        this.communityId = communityId;
        return this;
    }

    public BigDecimal getRanking() {
        return ranking;
    }

    public XiaoQu setRanking(BigDecimal ranking) {
        this.ranking = ranking;
        return this;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public XiaoQu setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
        return this;
    }

    public Integer getSellHouseCount() {
        return sellHouseCount;
    }

    public XiaoQu setSellHouseCount(Integer sellHouseCount) {
        this.sellHouseCount = sellHouseCount;
        return this;
    }

    public Integer getRentHouseCount() {
        return rentHouseCount;
    }

    public XiaoQu setRentHouseCount(Integer rentHouseCount) {
        this.rentHouseCount = rentHouseCount;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public XiaoQu setAddress(String address) {
        this.address = address;
        return this;
    }
}
