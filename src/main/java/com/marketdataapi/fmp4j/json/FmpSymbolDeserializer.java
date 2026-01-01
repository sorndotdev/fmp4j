package com.marketdataapi.fmp4j.json;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.marketdataapi.fmp4j.exceptions.FmpInvalidSymbolException;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.IOException;

public class FmpSymbolDeserializer extends JsonDeserializer<FmpSymbol> {
    @Override
    public FmpSymbol deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        String value = p.getText();
        if (value == null || value.trim().isEmpty()) {
            return null; // or skip
        }
        try {
            return symbol(value);
        } catch (FmpInvalidSymbolException e) {
            System.err.printf("Invalid symbol skipped: %s%n", value);
            return null;
        }
    }
}
