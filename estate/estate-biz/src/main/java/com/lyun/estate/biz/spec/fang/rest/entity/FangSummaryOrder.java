package com.lyun.estate.biz.spec.fang.rest.entity;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.google.common.collect.Lists;

import java.util.ArrayList;

import static com.github.miemiedev.mybatis.paginator.domain.Order.Direction.ASC;
import static com.github.miemiedev.mybatis.paginator.domain.Order.Direction.DESC;
import static com.lyun.estate.core.repo.SqlSupport.buildOrder;

/**
 * Created by Jeffrey on 2017-01-11.
 */
public enum FangSummaryOrder {
    DEFAULT("默认排序", Lists.newArrayList(buildOrder("id", ASC))),
    TIME_DESC("最新发布", Lists.newArrayList(buildOrder("publish_time", DESC))),
    PUBLISH_PRICE_UP("总价由低到高", Lists.newArrayList(buildOrder("publish_price", ASC))),
    PUBLISH_PRICE_DOWN("总价由高到低", Lists.newArrayList(buildOrder("publish_price", DESC))),
    UNIT_PRICE_UP("单价价由低到高", Lists.newArrayList(buildOrder("unit_price", ASC))),
    UNIT_PRICE_DOWN("单价由高到低", Lists.newArrayList(buildOrder("unit_price", DESC))),
    AREA_UP("面积由小到大", Lists.newArrayList(buildOrder("estate_area", ASC))),
    AREA_DOWN("面积由大到小", Lists.newArrayList(buildOrder("estate_area", DESC))),;

    private final String label;
    private final ArrayList<Order> orders;

    FangSummaryOrder(String label, ArrayList<Order> orders) {
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
