package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.QuoteTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpFullQuoteTest implements QuoteTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = aFullQuote();

        // when
        var after = (FmpFullQuote) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = aFullQuote();

        // when // then
        verifySerialization(o);
    }
}
