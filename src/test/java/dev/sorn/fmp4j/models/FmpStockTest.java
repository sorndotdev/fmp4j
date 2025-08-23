package dev.sorn.fmp4j.models;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpStockTest {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = new FmpStock("AAPL", "Apple Inc.");

        // when
        var after = (FmpStock) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = new FmpStock("AAPL", "Apple Inc.");

        // when // then
        verifySerialization(o);
    }
}
