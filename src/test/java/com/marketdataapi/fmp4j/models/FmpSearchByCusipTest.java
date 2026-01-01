package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static com.marketdataapi.fmp4j.types.FmpCusip.cusip;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpSearchByCusipTest {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = new FmpSearchByCusip(symbol("AAPL"), "Apple Inc.", cusip("037833100"), 3427916386000L);

        // when
        var after = (FmpSearchByCusip) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = new FmpSearchByCusip(symbol("AAPL"), "Apple Inc.", cusip("037833100"), 3427916386000L);

        // when // then
        verifySerialization(o);
    }
}
