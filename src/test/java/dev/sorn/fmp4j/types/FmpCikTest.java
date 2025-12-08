package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.types.FmpCik.FMP_CIK_PATTERN;
import static dev.sorn.fmp4j.types.FmpCik.cik;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.sorn.fmp4j.exceptions.FmpInvalidCikException;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author chat.deepseek.com
 */
class FmpCikTest {
    @Test
    void null_cik_throws() {
        // given
        var value = (String) null;

        // when // then
        var e = assertThrows(FmpInvalidCikException.class, () -> cik(value));
        assertEquals("'value' is required", e.getMessage());
    }

    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = cik("320193");

        // when
        var after = (FmpCik) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void to_string_returns_value() {
        // given
        var c = cik("320193");

        // when
        var str = c.toString();

        // then
        assertEquals("0000320193", str);
    }

    @Test
    void hash_code_value() {
        // given
        var str = "0000320193";
        var c = cik("320193");

        // when
        var hc = c.hashCode();

        // then
        assertEquals(str.hashCode(), hc);
    }

    @Test
    void equals_same_true() {
        // given
        var c = cik("320193");

        // when
        var eq = c.equals(c);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var c1 = cik("320193");
        var c2 = cik("320193");

        // when
        var eq = c1.equals(c2);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var c1 = cik("320193");
        var c2 = (FmpCik) null;

        // when
        var eq = c1.equals(c2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var c1 = cik("320193");
        var c2 = cik("789019");

        // when
        var eq = c1.equals(c2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var c1 = cik("320193");
        var c2 = "0000320193";

        // when
        var eq = c1.equals(c2);

        // then
        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var c1 = cik("320193");
        var c2 = (FmpCik) null;

        // when // then
        var e = assertThrows(FmpInvalidCikException.class, () -> c1.compareTo(c2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var c1 = cik("0000000001");
        var c2 = cik("0000000002");

        // when
        int cmp = c1.compareTo(c2);

        // then
        assertEquals(-1, cmp);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var c1 = cik("0000000002");
        var c2 = cik("0000000001");

        // when
        int cmp = c1.compareTo(c2);

        // then
        assertEquals(1, cmp);
    }

    @Test
    void compare_to_equal() {
        // given
        var c1 = cik("320193");
        var c2 = cik("320193");

        // when
        int cmp = c1.compareTo(c2);

        // then
        assertEquals(0, cmp);
    }

    @ParameterizedTest
    @MethodSource("validCiks")
    void valid_ciks(String value, String expected) {
        // given // when
        var cikObj = cik(value);

        // then
        assertEquals(expected, cikObj.value());
    }

    @ParameterizedTest
    @MethodSource("invalidCiks")
    void invalid_ciks(String value) {
        // given // when
        Function<String, FmpCik> f = FmpCik::cik;

        // then
        var e = assertThrows(FmpInvalidCikException.class, () -> f.apply(value));
        assertEquals(
                format("'value' [%s] does not match pattern [%s]", value, FMP_CIK_PATTERN.pattern()), e.getMessage());
    }

    private static Stream<Arguments> validCiks() {
        return Stream.of(
                Arguments.of("320193", "0000320193"),
                Arguments.of("0000320193", "0000320193"),
                Arguments.of("1", "0000000001"),
                Arguments.of("1234567890", "1234567890"),
                Arguments.of("9999999999", "9999999999"));
    }

    private static Stream<String> invalidCiks() {
        return Stream.of(
                "", // empty string
                " ", // blank string
                "\t", // whitespace chars
                "\n", // whitespace chars
                "\u00A0", // whitespace chars
                "ABCDEFGHIJ", // letters
                "12345678901", // too long (11 digits)
                "123456789A", // contains letter
                "123-456789", // contains dash
                "123.456789", // contains dot
                " 123456789", // leading space
                "123456789 " // trailing space
                );
    }
}
