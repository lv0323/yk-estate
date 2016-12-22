package com.lyun.estate.core.repo;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;

/**
 * Created by Jeffrey on 16/6/2.
 */
public class OffsetPageBounds extends PageBounds {

    private int offset;

    public OffsetPageBounds(int offset, int limit, List<Order> orders) {
        super((offset / limit) + 1, limit, orders);
        this.offset = offset;
    }

    public OffsetPageBounds(int offset, int limit, List<Order> orders, boolean containsTotalCount) {
        super((offset / limit) + 1, limit, orders, containsTotalCount);
        this.offset = offset;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    public OffsetPageBounds setOffset(int offset) {
        this.offset = offset;
        return this;
    }
}
