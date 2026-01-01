package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.types.FmpExchange;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpSearchByNameTest {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = new FmpSearchByName(symbol("AAPL"), "Apple Inc.", USD, FmpExchange.NASDAQ);

        // when
        var after = (FmpSearchByName) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = new FmpSearchByName(symbol("AAPL"), "Apple Inc.", USD, FmpExchange.NASDAQ);

        // when // then
        verifySerialization(o);
    }
}
