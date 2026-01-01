package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.TestUtils.deserialize;
import static com.marketdataapi.fmp4j.TestUtils.serialize;
import static com.marketdataapi.fmp4j.types.FmpCusip.FMP_CUSIP_PATTERN;
import static com.marketdataapi.fmp4j.types.FmpCusip.cusip;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidCusipException;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author chat.deepseek.com
 */
class FmpCusipTest {
    @Test
    void null_cusip_throws() {
        // given
        var value = (String) null;

        // when // then
        var e = assertThrows(FmpInvalidCusipException.class, () -> cusip(value));
        assertEquals("'value' is required", e.getMessage());
    }

    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = cusip("037833100");

        // when
        var after = (FmpCusip) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void to_string_returns_value() {
        // given
        var c = cusip("037833100");

        // when
        var str = c.toString();

        // then
        assertEquals("037833100", str);
    }

    @Test
    void hash_code_value() {
        // given
        var str = "037833100";
        var c = cusip(str);

        // when
        var hc = c.hashCode();

        // then
        assertEquals(str.hashCode(), hc);
    }

    @Test
    void equals_same_true() {
        // given
        var c = cusip("037833100");

        // when
        var eq = c.equals(c);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var c1 = cusip("037833100");
        var c2 = cusip("037833100");

        // when
        var eq = c1.equals(c2);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var c1 = cusip("037833100");
        var c2 = (FmpCusip) null;

        // when
        var eq = c1.equals(c2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var c1 = cusip("037833100");
        var c2 = cusip("594918104");

        // when
        var eq = c1.equals(c2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var c1 = cusip("037833100");
        var c2 = "037833100";

        // when
        var eq = c1.equals(c2);

        // then
        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var c1 = cusip("037833100");
        var c2 = (FmpCusip) null;

        // when // then
        var e = assertThrows(FmpInvalidCusipException.class, () -> c1.compareTo(c2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var c1 = cusip("037833100");
        var c2 = cusip("594918104");

        // when
        int cmp = c1.compareTo(c2);

        // then
        assertEquals(-5, cmp);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var c1 = cusip("594918104");
        var c2 = cusip("037833100");

        // when
        int cmp = c1.compareTo(c2);

        // then
        assertEquals(5, cmp);
    }

    @Test
    void compare_to_equal() {
        // given
        var c1 = cusip("037833100");
        var c2 = cusip("037833100");

        // when
        int cmp = c1.compareTo(c2);

        // then
        assertEquals(0, cmp);
    }

    @ParameterizedTest
    @MethodSource("validCusips")
    void valid_cusips(String value) {
        // given // when
        var cusip = cusip(value);

        // then
        assertEquals(value, cusip.value());
    }

    @ParameterizedTest
    @MethodSource("invalidCusips")
    void invalid_cusips(String value) {
        // given // when
        Function<String, FmpCusip> f = FmpCusip::cusip;

        // then
        var e = assertThrows(FmpInvalidCusipException.class, () -> f.apply(value));
        assertEquals(
                format("'value' [%s] does not match pattern [%s]", value, FMP_CUSIP_PATTERN.pattern()), e.getMessage());
    }

    private static Stream<String> validCusips() {
        return Stream.of(
                "037833100",
                "594918104",
                "023135106",
                "38259P508",
                "02079K305",
                "88160R101",
                "67066G104",
                "64110L106",
                "30303M102",
                "00287Y109",
                "00206R102",
                "060505104",
                "46625H100",
                "478160104",
                "912909108",
                "459200101",
                "931142103",
                "989817101",
                "747525103",
                "000840400",
                "00B03MLX2",
                "000012027",
                "000023519",
                "001203204",
                "13645T100",
                "000000BHP",
                "1T7593149");
    }

    private static Stream<String> invalidCusips() {
        return Stream.of(
                "", // empty string
                " ", // blank string
                "\t", // whitespace chars
                "\n", // whitespace chars
                "\u00A0", // whitespace chars
                "03783310", // too short (8 chars)
                "0378331005", // too long (10 chars)
                "037833100 ", // trailing space
                " 037833100", // leading space
                "0378 3100", // internal space
                "0378-3100", // dash
                "0378.3100", // dot
                "03783310!", // invalid character
                "03783310a" // lowercase letter (invalid)
                );
    }
}
