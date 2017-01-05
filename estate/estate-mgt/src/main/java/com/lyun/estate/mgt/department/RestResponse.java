package com.lyun.estate.mgt.department;

import java.util.HashMap;
import java.util.Map;

public class RestResponse {

    private Map<String, Object> map = new HashMap<>();

    public RestResponse add(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public Object get() {
        return map;
    }
}
