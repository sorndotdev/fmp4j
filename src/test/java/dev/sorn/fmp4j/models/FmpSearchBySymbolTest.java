package dev.sorn.fmp4j.models;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpSearchBySymbolTest {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = new FmpSearchBySymbol("AAPL", "Apple Inc.", "USD", "NASDAQ Global Select", "NASDAQ");

        // when
        var after = (FmpSearchBySymbol) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = new FmpSearchBySymbol("AAPL", "Apple Inc.", "USD", "NASDAQ Global Select", "NASDAQ");

        // when // then
        verifySerialization(o);
    }
}
