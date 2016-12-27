package com.lyun.estate.biz.file.repository.provider;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.entity.FileDescription;
import org.apache.ibatis.jdbc.SQL;

public class FileSqlProvider {

    private static final String TABLE_NAME = "t_file_description";

    public String insert(FileDescription entity) {
        return new SQL().INSERT_INTO(TABLE_NAME)
                .VALUES("owner_id", "#{ownerId}")
                .VALUES("owner_type", "#{ownerType}")
                .VALUES("custom_type", "#{customType}")
                .VALUES("file_type", "#{fileType}")
                .VALUES("file_process", "#{fileProcess}")
                .VALUES("target", "#{target}")
                .VALUES("path", "#{path}").toString();
    }

    public String select(Long ownerId, OwnerType ownerType, CustomType customType, FileProcess fileProcess) {
        SQL sql = new SQL().SELECT("*").FROM(TABLE_NAME)
                .WHERE("is_deleted = false")
                .WHERE("owner_id = #{ownerId}")
                .WHERE("owner_type = #{ownerType}");
        if (customType != null)
            sql.WHERE("custom_type = #{customType}");
        if (fileProcess != null)
            sql.WHERE("file_process = #{fileProcess}");
        return sql.toString();
    }

    public String delete(Long id) {
        return new SQL().UPDATE(TABLE_NAME)
                .SET("is_deleted = true")
                .WHERE("id = #{id}").toString();
    }
}
