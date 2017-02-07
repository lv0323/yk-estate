package com.lyun.estate.core.supports;

import com.lyun.estate.core.domain.BaseEntity;
import com.lyun.estate.core.repo.SQL;
import org.springframework.util.StringUtils;

public abstract class BaseProvider<T extends BaseEntity> {

    public String exists() {
        return existSQL()
                .WHERE("id=#{id}")
                .WHERE("is_deleted='N'")
                .toString();
    }

    public String findOne() {
        return new SQL().SELECT("*")
                .FROM(getEntityTable())
                .WHERE("id=#{id}")
                .WHERE("is_deleted='N'")
                .toString();
    }

    public String findAll() {
        return new SQL().SELECT("*")
                .FROM(getEntityTable())
                .WHERE("is_deleted='N'")
                .toString();
    }

    public String create(T entity) {
        SQL SQL = new SQL()
                .INSERT_INTO(getEntityTable())
                .VALUES("create_by_id", "#{createById}")
                .VALUES("create_time", "current_timestamp")
                .VALUES("update_by_id", "#{createById}")
                .VALUES("update_time", "current_timestamp")
                .VALUES("owner_id", "#{ownerId}");
        return onCreate(SQL, entity).toString();
    }

    public String update(T entity) {
        SQL SQL = new SQL()
                .UPDATE(getEntityTable())
                .SET("update_by_id=#{updateById}")
                .SET("update_time=current_timestamp")
                .SET_IF("version=#{version}", StringUtils.isEmpty(entity.getVersion()))
                .WHERE("id=#{id}")
                .WHERE("is_deleted='N'");
        return onUpdate(SQL, entity).toString();
    }

    public String delete() {
        return new SQL().DELETE_FROM(getEntityTable())
                .WHERE("id=#{id}")
                .toString();
    }

    public String deleteLogic() {
        return new SQL().UPDATE(getEntityTable())
                .SET("is_delete='Y'")
                .WHERE("id=#{id]")
                .WHERE("is_deleted='N'")
                .toString();
    }

    private SQL existSQL() {
        return new SQL().SELECT("count(1)").FROM(getEntityTable());
    }

    protected abstract String getEntityTable();

    protected abstract SQL onCreate(SQL sqlEx, T entity);

    protected abstract SQL onUpdate(SQL sqlEx, T entity);

}
