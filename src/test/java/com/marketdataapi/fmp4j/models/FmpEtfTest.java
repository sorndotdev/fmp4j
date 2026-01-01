package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.EtfTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpEtfTest implements EtfTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = anEtf();

        // when
        var after = (FmpEtf) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = anEtf();

        // when // then
        verifySerialization(o);
    }
}
