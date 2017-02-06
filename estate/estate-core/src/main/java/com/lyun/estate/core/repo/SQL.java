package com.lyun.estate.core.repo;

import org.apache.ibatis.jdbc.AbstractSQL;

public class SQL extends AbstractSQL<SQL> {

    @Override
    public SQL getSelf() {
        return this;
    }

    public SQL SET_IF(String sets, boolean flag) {
        return flag ? SET(sets) : getSelf();
    }

    public SQL VALUES_IF(String columns, String values, boolean flag) {
        return flag ? VALUES(columns, values) : getSelf();
    }

    public SQL WHERE_IF(String conditions, boolean flag) {
        return flag ? WHERE(conditions) : getSelf();
    }

}
