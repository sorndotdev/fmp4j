package com.marketdataapi.fmp4j.json;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpSymbolDeserializerTest {
    private final JsonParser p = mock(JsonParser.class);
    private final DeserializationContext ctx = mock(DeserializationContext.class);
    private final FmpSymbolDeserializer deserializer = new FmpSymbolDeserializer();

    @Test
    void deserialize_null_symbol_returns_null() throws IOException {
        // given
        var nullStr = (String) null;

        // when
        when(p.getText()).thenReturn(nullStr);
        var result = deserializer.deserialize(p, ctx);

        // then
        assertNull(result);
    }

    @Test
    void deserialize_blank_symbol_returns_null() throws IOException {
        // given
        var blank = "";

        // when
        when(p.getText()).thenReturn(blank);
        var result = deserializer.deserialize(p, ctx);

        // then
        assertNull(result);
    }

    @Test
    void deserialize_whitespace_symbol_returns_null() throws IOException {
        // given
        var whitespace = "   ";

        // when
        when(p.getText()).thenReturn(whitespace);
        var result = deserializer.deserialize(p, ctx);

        // then
        assertNull(result);
    }

    @Test
    void deserialize_invalid_symbol_returns_null() throws IOException {
        // given
        var invalid = "$INVALID";

        // when
        when(p.getText()).thenReturn(invalid);
        var result = deserializer.deserialize(p, ctx);

        // then
        assertNull(result);
    }

    @Test
    void deserialize_valid_symbol_returns_symbol() throws IOException {
        // given
        var symbol = "AAPL";

        // when
        when(p.getText()).thenReturn(symbol);
        var result = deserializer.deserialize(p, ctx);

        // then
        assertEquals(symbol("AAPL"), result);
    }
}
