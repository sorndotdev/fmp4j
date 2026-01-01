package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.HistoricalChartTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpHistoricalChartTest implements HistoricalChartTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = aHistoricalChart();

        // when
        var after = (FmpHistoricalChart) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = aHistoricalChart();

        // when // then
        verifySerialization(o);
    }
}
