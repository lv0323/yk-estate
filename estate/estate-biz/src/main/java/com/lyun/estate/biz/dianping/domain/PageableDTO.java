package com.lyun.estate.biz.dianping.domain;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;

/**
 * Created by localuser on 2017/6/14.
 */
public class PageableDTO<T> {
    int total_count;
    int offset;
    int limit;
    List<T> items;

    private PageableDTO(){}

    public PageableDTO(PageBounds page){
        this.offset = page.getOffset();
        this.limit = page.getLimit();
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
