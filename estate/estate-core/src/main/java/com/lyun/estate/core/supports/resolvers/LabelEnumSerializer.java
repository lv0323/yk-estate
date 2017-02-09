package com.lyun.estate.core.supports.resolvers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 16/5/26.
 */
public class LabelEnumSerializer extends StdSerializer<Enum> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public LabelEnumSerializer() {
        super(Enum.class);
    }

    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            Field labelField = value.getClass().getDeclaredField("label");
            labelField.setAccessible(true);
            Map<String, Object> map = new HashMap<>();
            map.put("name", value.name());
            map.put("label", labelField.get(value));
            mapper.writeValue(gen, map);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            ExceptionUtil.catching(e);
            gen.writeString(value.name());
        }
    }
}
