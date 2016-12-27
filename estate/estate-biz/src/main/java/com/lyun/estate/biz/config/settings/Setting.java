package com.lyun.estate.biz.config.settings;

import com.lyun.estate.core.supports.types.YN;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public class Setting {
    private Long id;
    private NameSpace nameSpace;
    private String key;
    private String value;
    private Integer priority;
    private YN is_deleted;

    public Long getId() {
        return id;
    }

    public Setting setId(Long id) {
        this.id = id;
        return this;
    }

    public NameSpace getNameSpace() {
        return nameSpace;
    }

    public Setting setNameSpace(NameSpace nameSpace) {
        this.nameSpace = nameSpace;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Setting setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Setting setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public Setting setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public YN getIs_deleted() {
        return is_deleted;
    }

    public Setting setIs_deleted(YN is_deleted) {
        this.is_deleted = is_deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", nameSpace='" + nameSpace + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", priority=" + priority +
                ", is_deleted=" + is_deleted +
                '}';
    }
}
