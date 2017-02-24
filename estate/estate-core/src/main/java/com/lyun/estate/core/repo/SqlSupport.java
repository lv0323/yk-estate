package com.lyun.estate.core.repo;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.Order.Direction;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jeffrey on 2017-02-15.
 */
public class SqlSupport {

    public static String buildQMEnumsStr(List<? extends Enum<?>> enums) {
        StringBuilder sqlBuilder = new StringBuilder();
        enums.stream().filter(Objects::nonNull).forEach(t -> sqlBuilder.append("'").append(t.name()).append("',"));
        String sql = sqlBuilder.toString();
        return sql.substring(0, sql.length() - 1);
    }

    public static boolean hasNotNullElement(Collection<?> collection) {
        return collection != null && collection.stream().anyMatch(Objects::nonNull);
    }

    public static Order buildOrder(String column, Direction direction) {
        return new Order(column, direction, null);
    }
}
