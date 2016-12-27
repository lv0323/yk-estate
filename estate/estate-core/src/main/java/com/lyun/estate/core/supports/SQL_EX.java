package com.lyun.estate.core.supports;

import org.apache.ibatis.jdbc.AbstractSQL;

public class SQL_EX extends AbstractSQL<SQL_EX> {

    @Override
    public SQL_EX getSelf() {
        return this;
    }

    public SQL_EX VALUES_IF(String columns, String values, boolean flag) {
        if (flag) {
            return VALUES(columns, values);
        }
        return getSelf();
    }

    public SQL_EX SET_IF(String sets, boolean flag) {
        if (flag) {
            return SET(sets);
        }
        return getSelf();
    }

    public SQL_EX WHERE_IF(String conditions, boolean flag){
        if(flag){
            return WHERE(conditions);
        }
        return getSelf();
    }

}
