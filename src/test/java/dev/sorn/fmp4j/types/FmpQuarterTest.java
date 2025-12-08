package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.types.FmpQuarter.MAX_QUARTER_VALUE;
import static dev.sorn.fmp4j.types.FmpQuarter.MIN_QUARTER_VALUE;
import static dev.sorn.fmp4j.types.FmpQuarter.quarter;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.sorn.fmp4j.exceptions.FmpInvalidQuarterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpQuarterTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void valid_quarters(int quarter) {
        // given // when
        var q = quarter(quarter);

        // then
        assertEquals(quarter, q.value());
    }

    @Test
    void below_minimum_quarter() {
        // given
        var q = 0;

        // when // then
        var e = assertThrows(FmpInvalidQuarterException.class, () -> quarter(q));
        assertEquals(format("[%d] is below the minimum allowed value [%d]", q, MIN_QUARTER_VALUE), e.getMessage());
    }

    @Test
    void exceeds_maximum_quarter() {
        // given
        var q = 5;

        // when // then
        var e = assertThrows(FmpInvalidQuarterException.class, () -> quarter(q));
        assertEquals(format("[%d] exceeds the maximum allowed value [%d]", q, MAX_QUARTER_VALUE), e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void toString_returns_value(int quarter) {
        // given // when
        var q = quarter(quarter);

        // then
        assertEquals(valueOf(quarter), q.toString());
    }

    @Test
    void hash_code_value() {
        // given
        var quarter = 4;
        var q = quarter(quarter);

        // when
        var hc = q.hashCode();

        // then
        assertEquals(quarter, hc);
    }

    @Test
    void equals_same_true() {
        // given
        var q = quarter(1);

        // when
        var eq = q.equals(q);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var q1 = quarter(3);
        var q2 = quarter(3);

        // when
        var eq = q1.equals(q2);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var q1 = quarter(2);
        var q2 = (FmpQuarter) null;

        // when
        var eq = q1.equals(q2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var q1 = quarter(2);
        var q2 = quarter(3);

        // when
        var eq = q1.equals(q2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var q1 = quarter(1);
        var q2 = 1;

        // when
        var eq = q1.equals(q2);

        // then
        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var q1 = quarter(4);
        var q2 = (FmpQuarter) null;

        // when // then
        var e = assertThrows(FmpInvalidQuarterException.class, () -> q1.compareTo(q2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var q1 = quarter(1);
        var q2 = quarter(2);

        // when
        int cmp = q1.compareTo(q2);

        // then
        assertEquals(-1, cmp);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var q1 = quarter(2);
        var q2 = quarter(1);

        // when
        int cmp = q1.compareTo(q2);

        // then
        assertEquals(1, cmp);
    }

    @Test
    void compare_to_equal() {
        // given
        var q1 = quarter(1);
        var q2 = quarter(1);

        // when
        int cmp = q1.compareTo(q2);

        // then
        assertEquals(0, cmp);
    }
}
