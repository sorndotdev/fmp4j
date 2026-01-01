package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static com.marketdataapi.fmp4j.types.FmpPeriod.FY;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.types.FmpYear.year;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class FmpEarningsCallTranscriptLatestTest {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = new FmpEarningsCallTranscriptLatest(symbol("XYZ"), FY, year(1999), LocalDate.parse("1999-12-31"));

        // when
        var after = (FmpEarningsCallTranscriptLatest) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = new FmpEarningsCallTranscriptLatest(symbol("XYZ"), FY, year(1999), LocalDate.parse("1999-12-31"));

        // when // then
        verifySerialization(o);
    }
}
