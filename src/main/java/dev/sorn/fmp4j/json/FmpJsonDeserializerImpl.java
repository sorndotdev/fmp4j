package dev.sorn.fmp4j.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;

public final class FmpJsonDeserializerImpl implements FmpJsonDeserializer {
    public static final FmpJsonDeserializerImpl FMP_JSON_DESERIALIZER = new FmpJsonDeserializerImpl();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .registerModule(new JavaTimeModule());

    private FmpJsonDeserializerImpl() {
        // prevent direct instantiation
    }

    public <T> T fromJson(String json, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new FmpJsonException(
                    e, "Failed to deserialize JSON to '%s': %s", type.getType().getTypeName(), json);
        }
    }
}
