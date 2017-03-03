package com.lyun.estate.biz.spec.fang.mgt.entity;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.Order.Direction;
import com.google.common.collect.Lists;

import java.util.ArrayList;

import static com.github.miemiedev.mybatis.paginator.domain.Order.Direction.ASC;
import static com.github.miemiedev.mybatis.paginator.domain.Order.Direction.DESC;
import static com.lyun.estate.core.repo.SqlSupport.buildOrder;

/**
 * Created by Jeffrey on 2017-01-11.
 */
public enum MgtFangSummaryOrder {
    DEFAULT("默认排序", Lists.newArrayList(buildOrder("create_time", DESC))),
    PUBLISH_PRICE_UP("总价由低到高", Lists.newArrayList(buildOrder("publish_price", Direction.ASC))),
    PUBLISH_PRICE_DOWN("总价由高到低", Lists.newArrayList(buildOrder("publish_price", DESC))),
    UNIT_PRICE_UP("单价价由低到高", Lists.newArrayList(buildOrder("unit_price", Direction.ASC))),
    UNIT_PRICE_DOWN("单价由高到低", Lists.newArrayList(buildOrder("unit_price", DESC))),
    AREA_UP("面积由小到大", Lists.newArrayList(buildOrder("estate_area", Direction.ASC))),
    AREA_DOWN("面积由大到小", Lists.newArrayList(buildOrder("estate_area", DESC))),
    FLOOR_UP("楼层由低到高", Lists.newArrayList(buildOrder("floor", DESC))),
    FLOOR_DOWN("楼层由高到低", Lists.newArrayList(buildOrder("floor", DESC))),
    FOLLOW_TIME_CLOSER("最近更新时间由远及近",
            Lists.newArrayList(buildOrder("mftn", DESC),
                    buildOrder("max_follow_time", Direction.ASC))),
    FOLLOW_TIME_FARTHER("最近更新时间由近及远",
            Lists.newArrayList(buildOrder("mftn", ASC),
                    buildOrder("max_follow_time", DESC))),;

    private final String label;
    private final ArrayList<Order> orders;

    MgtFangSummaryOrder(String label, ArrayList<Order> orders) {
        this.label = label;
        this.orders = orders;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
