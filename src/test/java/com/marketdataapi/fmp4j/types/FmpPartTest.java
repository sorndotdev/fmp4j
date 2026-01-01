package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.types.FmpPart.MAX_VALUE;
import static com.marketdataapi.fmp4j.types.FmpPart.MIN_VALUE;
import static com.marketdataapi.fmp4j.types.FmpPart.part;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidPartException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpPartTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 954, 241, 741, 2, 15})
    void valid_part(int part) {
        // given // when
        var p = part(part);

        // then
        assertEquals(part, p.value());
    }

    @Test
    void below_minimum_part() {
        // given
        var p = -1;

        // when // then
        var e = assertThrows(FmpInvalidPartException.class, () -> part(p));
        assertEquals(format("[%d] is below the minimum allowed value [%d]", p, MIN_VALUE), e.getMessage());
    }

    @Test
    void exceeds_maximum_year() {
        // given
        var p = 1001;

        // when // then
        var e = assertThrows(FmpInvalidPartException.class, () -> part(p));
        assertEquals(format("[%d] exceeds the maximum allowed value [%d]", p, MAX_VALUE), e.getMessage());
    }

    @Test
    void string_value_not_int() {
        // given
        var p = "199X";

        // when // then
        var e = assertThrows(FmpInvalidPartException.class, () -> part(p));
        assertEquals(format("[%s] is not a valid integer value", p), e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 999, 998, 10, 9})
    void toString_returns_value(int part) {
        // given // when
        var p = part(part);

        // then
        assertEquals(p.toString(), valueOf(part));
    }

    @Test
    void hash_code_value() {
        // given
        var part = 999;
        var p = part(part);

        // when
        var hc = p.hashCode();

        // then
        assertEquals(part, hc);
    }

    @Test
    void equals_same_true() {
        // given
        var p = part(1);

        // when
        var eq = p.equals(p);

        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var p1 = part(1000);
        var p2 = part(1000);

        // when
        var eq = p1.equals(p2);

        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var p1 = part(1000);
        var p2 = (FmpPart) null;

        // when
        var eq = p1.equals(p2);

        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var p1 = part(1000);
        var p2 = part(999);

        // when
        var eq = p1.equals(p2);

        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var p1 = part(999);
        var p2 = 999;

        // when
        var eq = p1.equals(p2);

        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var p1 = part("999");
        var p2 = (FmpPart) null;

        // when // then
        var e = assertThrows(FmpInvalidPartException.class, () -> p1.compareTo(p2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var p1 = part("1");
        var p2 = part("999");

        // when
        int cmp = p1.compareTo(p2);

        // then
        assertEquals(-1, cmp);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var p1 = part("999");
        var p2 = part("1");

        // when
        int cmp = p1.compareTo(p2);

        // then
        assertEquals(1, cmp);
    }

    @Test
    void compare_to_equal() {
        // given
        var p1 = part("1");
        var p2 = part("1");

        // when
        int cmp = p1.compareTo(p2);

        // then
        assertEquals(0, cmp);
    }
}
