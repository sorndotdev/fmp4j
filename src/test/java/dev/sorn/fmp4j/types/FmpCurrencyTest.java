package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.types.FmpCurrency.FMP_CURRENCY_PATTERN;
import static dev.sorn.fmp4j.types.FmpCurrency.currency;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.sorn.fmp4j.exceptions.FmpInvalidCurrencyException;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author chat.deepseek.com
 */
class FmpCurrencyTest {
    @Test
    void null_currency_throws() {
        // given
        var value = (String) null;

        // when // then
        var e = assertThrows(FmpInvalidCurrencyException.class, () -> currency(value));
        assertEquals("'value' is required", e.getMessage());
    }

    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = currency("USD");

        // when
        var after = (FmpCurrency) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void to_string_returns_value() {
        // given
        var c = currency("USD");

        // when
        var str = c.toString();

        // then
        assertEquals("USD", str);
    }

    @Test
    void hash_code_value() {
        // given
        var str = "USD";
        var c = currency(str);

        // when
        var hc = c.hashCode();

        // then
        assertEquals(str.hashCode(), hc);
    }

    @Test
    void equals_same_true() {
        // given
        var c = currency("USD");

        // when
        var eq = c.equals(c);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var c1 = currency("USD");
        var c2 = currency("USD");

        // when
        var eq = c1.equals(c2);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var c1 = currency("USD");
        var c2 = (FmpCurrency) null;

        // when
        var eq = c1.equals(c2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var c1 = currency("USD");
        var c2 = currency("EUR");

        // when
        var eq = c1.equals(c2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var c1 = currency("USD");
        var c2 = "USD";

        // when
        var eq = c1.equals(c2);

        // then
        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var c1 = currency("USD");
        var c2 = (FmpCurrency) null;

        // when // then
        var e = assertThrows(FmpInvalidCurrencyException.class, () -> c1.compareTo(c2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var c1 = currency("EUR");
        var c2 = currency("USD");

        // when
        int cmp = c1.compareTo(c2);

        // then
        assertTrue(cmp < 0);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var c1 = currency("USD");
        var c2 = currency("EUR");

        // when
        int cmp = c1.compareTo(c2);

        // then
        assertTrue(cmp > 0);
    }

    @Test
    void compare_to_equal() {
        // given
        var c1 = currency("USD");
        var c2 = currency("USD");

        // when
        int cmp = c1.compareTo(c2);

        // then
        assertEquals(0, cmp);
    }

    @ParameterizedTest
    @MethodSource("validCurrencies")
    void valid_currencies(String value) {
        // given // when
        var currency = currency(value);

        // then
        assertEquals(value.toUpperCase(), currency.value());
    }

    @ParameterizedTest
    @MethodSource("invalidCurrencies")
    void invalid_currencies(String value) {
        // given // when
        Function<String, FmpCurrency> f = FmpCurrency::currency;

        // then
        var e = assertThrows(FmpInvalidCurrencyException.class, () -> f.apply(value));
        assertEquals(
                format("'value' [%s] does not match pattern [%s]", value, FMP_CURRENCY_PATTERN.pattern()),
                e.getMessage());
    }

    private static Stream<String> validCurrencies() {
        return Stream.of(
                "USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD", "MXN", "SGD", "HKD", "NOK", "KRW",
                "TRY", "INR", "RUB", "BRL", "ZAR", "DKK", "PLN", "TWD", "THB", "MYR", "IDR", "HUF", "CZK", "ILS", "CLP",
                "PHP", "AED", "COP", "SAR", "RON", "PEN", "VND", "BDT", "ARS", "EGP", "usd", "eur", "gbp", "jpy", "aud",
                "cad", "chf", "cny", "sek", "nzd");
    }

    private static Stream<String> invalidCurrencies() {
        return Stream.of(
                "", // empty string
                " ", // blank string
                "\t", // whitespace chars
                "\n", // whitespace chars
                "\u00A0", // whitespace chars
                "US", // too short (2 chars)
                "USDD", // too long (4 chars)
                "US$", // invalid character
                "123", // numbers only
                "U S D", // spaces
                "USD ", // trailing space
                " USD" // leading space
                );
    }
}
