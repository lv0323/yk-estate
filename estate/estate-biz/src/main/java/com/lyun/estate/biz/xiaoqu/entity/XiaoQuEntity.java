package com.lyun.estate.biz.xiaoqu.entity;

import java.math.BigDecimal;
import java.util.Date;

public class XiaoQuEntity {
    private Long id;
    private Long communityId;
    private BigDecimal ranking;
    private Integer avgPrice;
    private Integer sellHouseCount;
    private Integer rentHouseCount;

    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public XiaoQuEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public XiaoQuEntity setCommunityId(Long communityId) {
        this.communityId = communityId;
        return this;
    }

    public BigDecimal getRanking() {
        return ranking;
    }

    public XiaoQuEntity setRanking(BigDecimal ranking) {
        this.ranking = ranking;
        return this;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public XiaoQuEntity setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
        return this;
    }

    public Integer getSellHouseCount() {
        return sellHouseCount;
    }

    public XiaoQuEntity setSellHouseCount(Integer sellHouseCount) {
        this.sellHouseCount = sellHouseCount;
        return this;
    }

    public Integer getRentHouseCount() {
        return rentHouseCount;
    }

    public XiaoQuEntity setRentHouseCount(Integer rentHouseCount) {
        this.rentHouseCount = rentHouseCount;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public XiaoQuEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public XiaoQuEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
