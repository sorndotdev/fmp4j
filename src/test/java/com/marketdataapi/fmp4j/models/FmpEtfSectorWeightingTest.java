package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.EtfSectorWeightingTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpEtfSectorWeightingTest implements EtfSectorWeightingTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = anEtfSectorWeighting();

        // when
        var after = (FmpEtfSectorWeighting) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var bs = anEtfSectorWeighting();

        // when // then
        verifySerialization(bs);
    }
}
