package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.types.FmpYear.MAX_YEAR_VALUE;
import static dev.sorn.fmp4j.types.FmpYear.MIN_YEAR_VALUE;
import static dev.sorn.fmp4j.types.FmpYear.year;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.sorn.fmp4j.exceptions.FmpInvalidYearException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpYearTest {
    @ParameterizedTest
    @ValueSource(ints = {1000, 1999, 2000, 2028, 2042, 2800, 4200, 9999})
    void valid_year(int year) {
        // given // when
        var y = year(year);

        // then
        assertEquals(year, y.value());
    }

    @Test
    void below_minimum_year() {
        // given
        var y = 999;

        // when // then
        var e = assertThrows(FmpInvalidYearException.class, () -> year(y));
        assertEquals(format("[%d] is below the minimum allowed value [%d]", y, MIN_YEAR_VALUE), e.getMessage());
    }

    @Test
    void exceeds_maximum_year() {
        // given
        var y = 10000;

        // when // then
        var e = assertThrows(FmpInvalidYearException.class, () -> year(y));
        assertEquals(format("[%d] exceeds the maximum allowed value [%d]", y, MAX_YEAR_VALUE), e.getMessage());
    }

    @Test
    void string_value_not_int() {
        // given
        var y = "199X";

        // when // then
        var e = assertThrows(FmpInvalidYearException.class, () -> year(y));
        assertEquals(format("[%s] is not a valid integer value", y), e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 1999, 2028, 2042, 9999})
    void toString_returns_value(int year) {
        // given // when
        var y = year(year);

        // then
        assertEquals(y.toString(), valueOf(year));
    }

    @Test
    void hash_code_value() {
        // given
        var year = 2000;
        var y = year(year);

        // when
        var hc = y.hashCode();

        // then
        assertEquals(year, hc);
    }

    @Test
    void equals_same_true() {
        // given
        var y = year(1999);

        // when
        var eq = y.equals(y);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var y1 = year(1999);
        var y2 = year(1999);

        // when
        var eq = y1.equals(y2);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var y1 = year(1999);
        var y2 = (FmpYear) null;

        // when
        var eq = y1.equals(y2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var y1 = year(1999);
        var y2 = year(2000);

        // when
        var eq = y1.equals(y2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var y1 = year(2000);
        var y2 = 2000;

        // when
        var eq = y1.equals(y2);

        // then
        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var y1 = year("1999");
        var y2 = (FmpYear) null;

        // when // then
        var e = assertThrows(FmpInvalidYearException.class, () -> y1.compareTo(y2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var y1 = year("1999");
        var y2 = year("2000");

        // when
        int cmp = y1.compareTo(y2);

        // then
        assertEquals(-1, cmp);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var y1 = year("2000");
        var y2 = year("1999");

        // when
        int cmp = y1.compareTo(y2);

        // then
        assertEquals(1, cmp);
    }

    @Test
    void compare_to_equal() {
        // given
        var y1 = year("1999");
        var y2 = year("1999");

        // when
        int cmp = y1.compareTo(y2);

        // then
        assertEquals(0, cmp);
    }
}
