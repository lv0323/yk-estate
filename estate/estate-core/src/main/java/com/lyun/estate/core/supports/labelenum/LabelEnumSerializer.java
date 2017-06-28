package com.lyun.estate.core.supports.labelenum;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 16/5/26.
 */
public class LabelEnumSerializer extends StdSerializer<Enum> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(LabelEnumSerializer.class);

    public LabelEnumSerializer() {
        super(Enum.class);
    }

    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) {
        try {
            if (value instanceof LabelEnum) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", value.name());
                map.put("label", ((LabelEnum) value).getLabel());
                mapper.writeValue(gen, map);
            } else {
                gen.writeString(value.name());
            }
        } catch (IOException ignored) {
        }
    }
}
