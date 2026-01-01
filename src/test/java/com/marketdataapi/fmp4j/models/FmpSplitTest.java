package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.SplitsTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpSplitTest implements SplitsTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = aSplit();

        // when
        var after = (FmpSplit) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = aSplit();

        // when // then
        verifySerialization(o);
    }
}
