package dev.sorn.fmp4j.models;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpSearchByNameTest {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = new FmpSearchByName("AAPL", "Apple Inc.", "USD", "NASDAQ Global Select", "NASDAQ");

        // when
        var after = (FmpSearchByName) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = new FmpSearchByName("AAPL", "Apple Inc.", "USD", "NASDAQ Global Select", "NASDAQ");

        // when // then
        verifySerialization(o);
    }
}
