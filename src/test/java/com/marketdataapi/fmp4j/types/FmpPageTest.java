package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.types.FmpPage.MAX_VALUE;
import static com.marketdataapi.fmp4j.types.FmpPage.MIN_VALUE;
import static com.marketdataapi.fmp4j.types.FmpPage.page;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidPageException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpPageTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 954, 241, 741, 2, 15, 9999})
    void valid_page(int page) {
        // given // when
        var p = page(page);

        // then
        assertEquals(page, p.value());
    }

    @Test
    void below_minimum_page() {
        // given
        var p = -1;

        // when // then
        var e = assertThrows(FmpInvalidPageException.class, () -> page(p));
        assertEquals(format("[%d] is below the minimum allowed value [%d]", p, MIN_VALUE), e.getMessage());
    }

    @Test
    void exceeds_maximum_page() {
        // given
        var p = 10001;

        // when // then
        var e = assertThrows(FmpInvalidPageException.class, () -> page(p));
        assertEquals(format("[%d] exceeds the maximum allowed value [%d]", p, MAX_VALUE), e.getMessage());
    }

    @Test
    void string_value_not_int() {
        // given
        var p = "199X";

        // when // then
        var e = assertThrows(FmpInvalidPageException.class, () -> page(p));
        assertEquals(format("[%s] is not a valid integer value", p), e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 999, 998, 10, 9999})
    void toString_returns_value(int page) {
        // given // when
        var p = page(page);

        // then
        assertEquals(p.toString(), valueOf(page));
    }

    @Test
    void hash_code_value() {
        // given
        var page = 999;
        var p = page(page);

        // when
        var hc = p.hashCode();

        // then
        assertEquals(page, hc);
    }

    @Test
    void equals_same_true() {
        // given
        var p = page(1);

        // when
        var eq = p.equals(p);

        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var p1 = page(1000);
        var p2 = page(1000);

        // when
        var eq = p1.equals(p2);

        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var p1 = page(1000);
        var p2 = (FmpPage) null;

        // when
        var eq = p1.equals(p2);

        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var p1 = page(1000);
        var p2 = page(999);

        // when
        var eq = p1.equals(p2);

        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var p1 = page(999);
        var p2 = 999;

        // when
        var eq = p1.equals(p2);

        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var p1 = page("999");
        var p2 = (FmpPage) null;

        // when // then
        var e = assertThrows(FmpInvalidPageException.class, () -> p1.compareTo(p2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var p1 = page("1");
        var p2 = page("999");

        // when
        int cmp = p1.compareTo(p2);

        // then
        assertEquals(-1, cmp);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var p1 = page("999");
        var p2 = page("1");

        // when
        int cmp = p1.compareTo(p2);

        // then
        assertEquals(1, cmp);
    }

    @Test
    void compare_to_equal() {
        // given
        var p1 = page("1");
        var p2 = page("1");

        // when
        int cmp = p1.compareTo(p2);

        // then
        assertEquals(0, cmp);
    }
}
