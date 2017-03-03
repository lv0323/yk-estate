package com.lyun.estate.biz.spec.xiaoqu.rest.def;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by Jeffrey on 2017-01-11.
 */
public enum XQSummaryOrder {
    DEFAULT("默认排序", Lists.newArrayList(new Order("ranking", Order.Direction.DESC, null))),
    PRICE_UP("均价由低到高", Lists.newArrayList(new Order("ap_null", Order.Direction.ASC, null),
            new Order("avg_price", Order.Direction.ASC, null))),
    PRICE_DOWN("均价由高到地", Lists.newArrayList(new Order("ap_null", Order.Direction.ASC, null),
            new Order("avg_price", Order.Direction.DESC, null)
    )),;

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
