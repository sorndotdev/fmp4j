package dev.sorn.fmp4j.models;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.QuoteTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpPartialQuoteTest implements QuoteTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = aPartialQuote();

        // when
        var after = (FmpPartialQuote) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = aPartialQuote();

        // when // then
        verifySerialization(o);
    }
}
