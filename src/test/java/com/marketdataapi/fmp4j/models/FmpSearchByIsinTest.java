package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static com.marketdataapi.fmp4j.types.FmpIsin.isin;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpSearchByIsinTest {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = new FmpSearchByIsin(symbol("AAPL"), "Apple Inc.", isin("US0378331005"), 3427916386000L);

        // when
        var after = (FmpSearchByIsin) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = new FmpSearchByIsin(symbol("AAPL"), "Apple Inc.", isin("US0378331005"), 3427916386000L);

        // when // then
        verifySerialization(o);
    }
}
