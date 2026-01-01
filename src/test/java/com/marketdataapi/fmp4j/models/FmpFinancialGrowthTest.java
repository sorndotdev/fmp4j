package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.FinancialGrowthTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpFinancialGrowthTest implements FinancialGrowthTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = aFinancialGrowth();

        // when
        var after = (FmpFinancialGrowth) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = aFinancialGrowth();

        // when // then
        verifySerialization(o);
    }
}
