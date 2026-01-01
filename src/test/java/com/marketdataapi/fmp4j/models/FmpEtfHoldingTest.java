package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.EtfHoldingTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpEtfHoldingTest implements EtfHoldingTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = anEtfHolding();

        // when
        var after = (FmpEtfHolding) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = anEtfHolding();

        // when // then
        verifySerialization(o);
    }
}
