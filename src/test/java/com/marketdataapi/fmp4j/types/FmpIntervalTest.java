package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.types.FmpInterval.interval;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidIntervalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FmpIntervalTest {
    @ParameterizedTest
    @CsvSource({
        "1min", "5min", "15min", "30min", "1hour", "4hour",
    })
    void valid_interval(String value) {
        // when
        var p = interval(value);

        // then
        assertEquals(value, p.value());
        assertEquals(value, p.toString());
    }

    @Test
    void invalid_period() {
        // given
        var p = "2min";

        // when // then
        var e = assertThrows(FmpInvalidIntervalException.class, () -> interval(p));
        assertEquals("[2min] is not a valid interval", e.getMessage());
    }
}
