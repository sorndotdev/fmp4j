package dev.sorn.fmp4j.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.sorn.fmp4j.exceptions.FmpDeserializationException;
import dev.sorn.fmp4j.http.FmpDeserializer;
import java.io.IOException;
import java.util.List;

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

    public <T> List<T> deserialize(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readerForListOf(clazz).readValue(json);
        } catch (IOException e) {
            throw new FmpDeserializationException(
                    e, "Failed to deserialize JSON to '%s': %s", clazz.getSimpleName(), json);
        }
    }
}
