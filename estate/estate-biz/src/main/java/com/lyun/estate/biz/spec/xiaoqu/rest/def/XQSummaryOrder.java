package com.lyun.estate.biz.spec.xiaoqu.rest.def;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.google.common.collect.Lists;

import java.util.ArrayList;

import static com.github.miemiedev.mybatis.paginator.domain.Order.Direction.ASC;
import static com.github.miemiedev.mybatis.paginator.domain.Order.Direction.DESC;
import static com.lyun.estate.core.repo.SqlSupport.buildOrder;

/**
 * Created by Jeffrey on 2017-01-11.
 */
public enum XQSummaryOrder {
    DEFAULT("默认排序", Lists.newArrayList(buildOrder("id", ASC))),
    SELL_COUNT_DOWN("售房数从高到低", Lists.newArrayList(buildOrder("sell_house_count", DESC))),
    PRICE_UP("均价由低到高", Lists.newArrayList(buildOrder("ap_null", ASC), buildOrder("avg_price", ASC))),
    PRICE_DOWN("均价由高到地",
            Lists.newArrayList(buildOrder("ap_null", ASC), buildOrder("avg_price", DESC))),;

    private final String label;
    private final ArrayList<Order> orders;

    XQSummaryOrder(String label, ArrayList<Order> orders) {
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
