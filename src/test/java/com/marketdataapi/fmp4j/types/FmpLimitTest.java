package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.types.FmpLimit.MAX_LIMIT_VALUE;
import static com.marketdataapi.fmp4j.types.FmpLimit.MIN_LIMIT_VALUE;
import static com.marketdataapi.fmp4j.types.FmpLimit.limit;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidLimitException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpLimitTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 10, 28, 42, 100, 1000})
    void valid_limit(int limit) {
        // given // when
        var lim = limit(limit);

        // then
        assertEquals(limit, lim.value());
    }

    @Test
    void below_minimum_limit() {
        // given
        var limit = 0;

        // when // then
        var e = assertThrows(FmpInvalidLimitException.class, () -> limit(limit));
        assertEquals(format("[%d] is below the minimum allowed value [%d]", limit, MIN_LIMIT_VALUE), e.getMessage());
    }

    @Test
    void exceeds_maximum_limit() {
        // given
        var limit = 1001;

        // when // then
        var e = assertThrows(FmpInvalidLimitException.class, () -> limit(limit));
        assertEquals(format("[%d] exceeds the maximum allowed value [%d]", limit, MAX_LIMIT_VALUE), e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 10, 50})
    void toString_returns_value(int limit) {
        // given // when
        var lim = limit(limit);

        // then
        assertEquals(lim.toString(), valueOf(limit));
    }

    @Test
    void hash_code_value() {
        // given
        var i = 3;
        var lim = limit(i);

        // when
        var hc = lim.hashCode();

        // then
        assertEquals(i, hc);
    }

    @Test
    void equals_same_true() {
        // given
        var lim = limit(10);

        // when
        var eq = lim.equals(lim);

        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var lim1 = limit(10);
        var lim2 = limit(10);

        // when
        var eq = lim1.equals(lim2);

        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var lim1 = limit(3);
        var lim2 = (FmpLimit) null;

        // when
        var eq = lim1.equals(lim2);

        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var lim1 = limit(3);
        var lim2 = limit(5);

        // when
        var eq = lim1.equals(lim2);

        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var lim1 = limit(1);
        var lim2 = 3;

        // when
        var eq = lim1.equals(lim2);

        assertFalse(eq);
    }
}
