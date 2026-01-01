package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.BalanceSheetStatementTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpBalanceSheetStatementTest implements BalanceSheetStatementTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = anAnnualBalanceSheetStatement();

        // when
        var after = (FmpBalanceSheetStatement) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = anAnnualBalanceSheetStatement();

        // when // then
        verifySerialization(o);
    }
}
