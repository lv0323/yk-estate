package com.lyun.estate.mgt.supports;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class RestResponse {

    private Map<String, Object> map = new HashMap<>();

    public RestResponse add(String key, Object... value) {
        map.put(key, value.length == 1 ? value[0] : value);
        return this;
    }

    public Object get() {
        return map;
    }
}
