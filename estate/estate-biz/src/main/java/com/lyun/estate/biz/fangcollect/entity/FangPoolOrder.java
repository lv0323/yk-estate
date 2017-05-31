package com.lyun.estate.biz.fangcollect.entity;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.google.common.collect.Lists;

import java.util.ArrayList;

import static com.github.miemiedev.mybatis.paginator.domain.Order.Direction.ASC;
import static com.github.miemiedev.mybatis.paginator.domain.Order.Direction.DESC;
import static com.lyun.estate.core.repo.SqlSupport.buildOrder;

/**
 * Created by Jeffrey on 2017-01-11.
 */
public enum FangPoolOrder {
    DEFAULT("默认排序", Lists.newArrayList(buildOrder("update_time", DESC))),
    PUBLISH_PRICE_UP("总价由低到高", Lists.newArrayList(buildOrder("publish_price", ASC))),
    PUBLISH_PRICE_DOWN("总价由高到低", Lists.newArrayList(buildOrder("publish_price", DESC))),
    UNIT_PRICE_UP("单价由低到高", Lists.newArrayList(buildOrder("unit_price", ASC))),
    UNIT_PRICE_DOWN("单价由高到低", Lists.newArrayList(buildOrder("unit_price", DESC))),
    FLOOR_UP("楼层由低到高", Lists.newArrayList(buildOrder("floor", ASC))),
    FLOOR_DOWN("楼层由高到低", Lists.newArrayList(buildOrder("floor", DESC))),
    AREA_UP("面积由小到大", Lists.newArrayList(buildOrder("estate_area", ASC))),
    AREA_DOWN("面积由大到小", Lists.newArrayList(buildOrder("estate_area", DESC))),;

    private final String label;
    private final ArrayList<Order> orders;

    FangPoolOrder(String label, ArrayList<Order> orders) {
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
