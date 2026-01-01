package com.marketdataapi.fmp4j.models;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.IposCalendarTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class FmpIposCalendarTest implements IposCalendarTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = anIposCalendarRecord();

        // when
        var after = (FmpIposCalendar) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = anIposCalendarRecord();

        // when // then
        verifySerialization(o);
    }
}
