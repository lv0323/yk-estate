package com.lyun.estate.core.supports.labelenum;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
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
        if (value instanceof LabelEnum) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", value.name());
            map.put("label", ((LabelEnum) value).getLabel());
            mapper.writeValue(gen, map);
        } else {
            gen.writeString(value.name());
        }
    }
}
