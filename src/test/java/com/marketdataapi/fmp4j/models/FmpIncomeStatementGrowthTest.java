package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.IncomeStatementGrowthTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpIncomeStatementGrowthTest implements IncomeStatementGrowthTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = anIncomeStatementGrowth();

        // when
        var after = (FmpIncomeStatementGrowth) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = anIncomeStatementGrowth();

        // when // then
        verifySerialization(o);
    }
}
