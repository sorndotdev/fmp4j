package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.types.FmpIsin.FMP_ISIN_PATTERN;
import static dev.sorn.fmp4j.types.FmpIsin.isin;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.sorn.fmp4j.exceptions.FmpInvalidIsinException;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author chat.deepseek.com
 */
class FmpIsinTest {

    @Test
    void null_isin_throws() {
        // given
        var value = (String) null;
        // when // then
        var e = assertThrows(FmpInvalidIsinException.class, () -> isin(value));
        assertEquals("'value' is required", e.getMessage());
    }

    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = isin("US0378331005");
        // when
        var after = (FmpIsin) deserialize(serialize(before));
        // then
        assertEquals(before, after);
    }

    @Test
    void to_string_returns_value() {
        // given
        var i = isin("US0378331005");
        // when
        var str = i.toString();
        // then
        assertEquals("US0378331005", str);
    }

    @Test
    void hash_code_value() {
        // given
        var str = "US0378331005";
        var i = isin(str);
        // when
        var hc = i.hashCode();
        // then
        assertEquals(str.hashCode(), hc);
    }

    @Test
    void equals_same_true() {
        // given
        var i = isin("US0378331005");
        // when
        var eq = i.equals(i);
        // then
        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var i1 = isin("US0378331005");
        var i2 = isin("US0378331005");
        // when
        var eq = i1.equals(i2);
        // then
        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var i1 = isin("US0378331005");
        var i2 = (FmpIsin) null;
        // when
        var eq = i1.equals(i2);
        // then
        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var i1 = isin("US0378331005");
        var i2 = isin("US5949181045");
        // when
        var eq = i1.equals(i2);
        // then
        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var i1 = isin("US0378331005");
        var i2 = "US0378331005";
        // when
        var eq = i1.equals(i2);
        // then
        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var i1 = isin("US0378331005");
        var i2 = (FmpIsin) null;
        // when // then
        var e = assertThrows(FmpInvalidIsinException.class, () -> i1.compareTo(i2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var i1 = isin("US0378331005");
        var i2 = isin("US5949181045");
        // when
        int cmp = i1.compareTo(i2);
        // then
        assertEquals(-5, cmp);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var i1 = isin("US5949181045");
        var i2 = isin("US0378331005");
        // when
        int cmp = i1.compareTo(i2);
        // then
        assertEquals(5, cmp);
    }

    @Test
    void compare_to_equal() {
        // given
        var i1 = isin("US0378331005");
        var i2 = isin("US0378331005");
        // when
        int cmp = i1.compareTo(i2);
        // then
        assertEquals(0, cmp);
    }

    @Test
    void is_valid_luhn_covers_both_branches() {
        var left = isin("US0378331005");
        assertEquals("US0378331005", left.value());
        var right = isin("GB00B03MLX29");
        assertEquals("GB00B03MLX29", right.value());
    }

    @ParameterizedTest
    @MethodSource("validIsins")
    void valid_isins(String value) {
        // given // when
        var isin = isin(value);
        // then
        assertEquals(value, isin.value());
    }

    @ParameterizedTest
    @MethodSource("invalidIsins")
    void invalid_isins(String value) {
        // given // when
        Function<String, FmpIsin> f = FmpIsin::isin;
        // then
        var e = assertThrows(FmpInvalidIsinException.class, () -> f.apply(value));
        assertEquals(
                format("'value' [%s] does not match pattern [%s]", value, FMP_ISIN_PATTERN.pattern()), e.getMessage());
    }

    @ParameterizedTest
    @MethodSource("invalidIsinBecauseCheckDigit")
    void invalid_isins_due_to_check_digit(String value) {
        // given // when
        Function<String, FmpIsin> f = FmpIsin::isin;
        // then
        var e = assertThrows(FmpInvalidIsinException.class, () -> f.apply(value));
        assertEquals(format("'value' [%s] has invalid check digit", value), e.getMessage());
    }

    private static Stream<String> validIsins() {
        return Stream.of(
                "US0378331005",
                "US5949181045",
                "US0231351067",
                "US38259P5089",
                "US02079K3059",
                "US88160R1014",
                "US67066G1040",
                "US64110L1061",
                "US30303M1027",
                "US00287Y1091",
                "US00206R1023",
                "US0605051046",
                "US46625H1005",
                "US4781601046",
                "US9129091081",
                "US4592001014",
                "US9311421039",
                "US9898171015",
                "US7475251036",
                "US0378331005",
                "DE0008404005",
                "GB00B03MLX29",
                "FR0000120271",
                "NL0000235190",
                "CH0012032048",
                "CA13645T1003",
                "AU000000BHP4",
                "SG1T75931496");
    }

    private static Stream<String> invalidIsins() {
        return Stream.of(
                "", // empty string
                " ", // blank string
                "\t", // whitespace chars
                "\n", // whitespace chars
                "\u00A0", // whitespace chars
                "US037833100", // too short (11 chars)
                "US03783310055", // too long (13 chars)
                "US037833100", // missing check digit
                "US037833100A", // invalid check digit (non-numeric)
                "U0378331005", // country code too short
                "12378331005", // country code not letters
                "U$0378331005", // invalid country code character
                "US037833100!", // invalid character in NSIN
                "US0378 31005", // space in NSIN
                "US0378-331005", // dash in NSIN
                "US0378.331005", // dot in NSIN
                "US0378331005 ", // trailing space
                " US0378331005", // leading space
                "us0378331005" // lowercase letters
                );
    }

    private static Stream<String> invalidIsinBecauseCheckDigit() {
        return Stream.of(
                "USA378331005",
                "US0378331004",
                "DE0008404004",
                "GB00B03MLX28",
                "FR0000120270",
                "CH0012032047",
                "JP3435000001",
                "CA13645T1002",
                "AU000000BHP3",
                "SG1T75931495",
                "HK0000069691");
    }
}
