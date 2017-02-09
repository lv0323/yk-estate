package com.lyun.estate.core.supports.resolvers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 16/5/26.
 */
public class PageListSerializer extends StdSerializer<PageList> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public PageListSerializer() {
        super(PageList.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void serialize(PageList value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("total", 0);
        map.put("count", 0);
        Paginator paginator = value.getPaginator();
        if (paginator != null) {
            map.put("total", paginator.getTotalCount());
            map.put("count", value.size());
        }
        map.put("items", new ArrayList(value));
        mapper.writeValue(gen, map);
    }
}
