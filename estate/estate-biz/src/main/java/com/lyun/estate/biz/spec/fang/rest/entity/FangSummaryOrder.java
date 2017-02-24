package com.lyun.estate.biz.spec.fang.rest.entity;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by Jeffrey on 2017-01-11.
 */
public enum FangSummaryOrder {
    DEFAULT("默认排序", Lists.newArrayList(new Order("ranking", Order.Direction.DESC, null))),
    TIME_DESC("最新发布", Lists.newArrayList(new Order("publish_time", Order.Direction.DESC, null))),
    PUBLISH_PRICE_UP("总价由低到高", Lists.newArrayList(new Order("publish_price", Order.Direction.ASC, null))),
    PUBLISH_PRICE_DOWN("总价由高到低", Lists.newArrayList(new Order("publish_price", Order.Direction.DESC, null))),
    UNIT_PRICE_UP("单价价由低到高", Lists.newArrayList(new Order("unit_price", Order.Direction.ASC, null))),
    UNIT_PRICE_DOWN("单价由高到低", Lists.newArrayList(new Order("unit_price", Order.Direction.DESC, null))),
    AREA_UP("面积由小到大", Lists.newArrayList(new Order("estate_area", Order.Direction.ASC, null))),
    AREA_DOWN("面积由大到小", Lists.newArrayList(new Order("estate_area", Order.Direction.DESC, null))),;

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