package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.TreasuryRatesTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpTreasuryRatesTest implements TreasuryRatesTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = aFmpTreasuryRate();

        // when
        var after = (FmpTreasuryRate) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = aFmpTreasuryRate();

        // when // then
        verifySerialization(o);
    }
}
