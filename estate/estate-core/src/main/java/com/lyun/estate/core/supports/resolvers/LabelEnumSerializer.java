package com.lyun.estate.core.supports.resolvers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 16/5/26.
 */
public class LabelEnumSerializer extends StdSerializer<Enum> {
    ObjectMapper mapper = new ObjectMapper();

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
            gen.writeString(value.name());
        }
    }


    private class NameLabelObject {
        private String name;
        private String label;

        NameLabelObject(String name, String label) {
            this.name = name;
            this.label = label;
        }

        public String getName() {
            return name;
        }

        public NameLabelObject setName(String name) {
            this.name = name;
            return this;
        }

        public String getLabel() {
            return label;
        }

        public NameLabelObject setLabel(String label) {
            this.label = label;
            return this;
        }
    }
}
