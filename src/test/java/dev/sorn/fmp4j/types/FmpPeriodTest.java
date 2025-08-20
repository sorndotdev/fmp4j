package dev.sorn.fmp4j.types;

import dev.sorn.fmp4j.exceptions.FmpInvalidPeriodException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static dev.sorn.fmp4j.types.FmpPeriod.period;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FmpPeriodTest {
    @ParameterizedTest
    @CsvSource({
        "annual",
        "quarter",
        "Q1",
        "Q2",
        "Q3",
        "Q4",
        "FY",
    })
    void valid_period(String value) {
        // when
        var p = period(value);

        // then
        assertEquals(value, p.value());
    }

    @Test
    void invalid_period() {
        // given
        var p = "abc";

        // when // then
        var e = assertThrows(FmpInvalidPeriodException.class, () -> period(p));
        assertEquals("[abc] is not a valid period", e.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
        "annual",
        "quarter",
        "Q1",
        "Q2",
        "Q3",
        "Q4",
        "FY",
    })
    void toString_returns_value(String value) {
        // given
        var p = period(value);

        // when
        var str = p.toString();

        // then
        assertEquals(value, str);
    }
}