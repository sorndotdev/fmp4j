package dev.sorn.fmp4j.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.sorn.fmp4j.deserialize.FmpDeserializer;
import java.io.IOException;

public final class FmpJsonDeserializer implements FmpDeserializer {
    public static final FmpJsonDeserializer FMP_JSON_DESERIALIZER = new FmpJsonDeserializer();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .registerModule(new JavaTimeModule())
            .registerModule(new FmpJsonModule());

    private FmpJsonDeserializer() {
        // prevent direct instantiation
    }

    public <T> T deserialize(String json, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new FmpJsonException(
                    e, "Failed to deserialize JSON to '%s': %s", type.getType().getTypeName(), json);
        }
    }
}
