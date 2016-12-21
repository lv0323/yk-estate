package com.lyun.estate.biz.filesystem;

import org.apache.ibatis.jdbc.SQL;

public class FsSqlProvider {

    public String insert(FsEntity entity) {
        return new SQL().INSERT_INTO("t_file_descriptor")
                .VALUES("owner_type", "#{ownerType}")
                .VALUES("owner_id", "#{ownerId}")
                .VALUES("custom_type", "#{customType}")
                .VALUES("file_type", "#{fileType}")
                .VALUES("target", "#{target}")
                .VALUES("path", "#{path}").toString();
    }

    public String select(OwnerType ownerType, long ownerId, String customType) {
        SQL sql = new SQL().SELECT("*").FROM("t_file_descriptor")
                .WHERE("is_deleted = false")
                .WHERE("owner_type = #{ownerType}")
                .WHERE("owner_id = #{ownerId}");
        if (customType != null)
            sql.WHERE("custom_type = #{customType}");
        return sql.toString();
    }

    public String selectByPath(String path) {
        return new SQL().SELECT("*").FROM("t_file_descriptor")
                .WHERE("path = #{path}").toString();
    }

    public String delete(long id) {
        return new SQL().DELETE_FROM("t_file_descriptor")
                .WHERE("id = #{id}").toString();
    }
}
