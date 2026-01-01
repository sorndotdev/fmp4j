package com.marketdataapi.fmp4j.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.function.Function;

public class FmpJsonBlankAsNullStringDeserializer<T> extends JsonDeserializer<T> {
    private final Function<String, T> factory;

    public FmpJsonBlankAsNullStringDeserializer(Function<String, T> factory) {
        this.factory = factory;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();
        if (text == null || text.isBlank() || text.equals("null")) {
            return null;
        }
        return factory.apply(text);
    }
}
