package dev.sorn.fmp4j.models;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.DividendsCalendarTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpDividendsCalendarTest implements DividendsCalendarTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = aDividendsCalendarRecord();

        // when
        var after = (FmpDividendsCalendar) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = aDividendsCalendarRecord();

        // when // then
        verifySerialization(o);
    }
}
