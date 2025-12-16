package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.types.FmpSymbol.FMP_SYMBOL_PATTERN;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import dev.sorn.fmp4j.exceptions.FmpInvalidSymbolException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FmpSymbolTest {
    @Test
    void null_symbol_throws() {
        // given
        var value = (String) null;

        // when // then
        var e = assertThrows(FmpInvalidSymbolException.class, () -> symbol(value));
        assertEquals("'value' is required", e.getMessage());
    }

    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = symbol("AAPL");

        // when
        var after = (FmpSymbol) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void to_string_returns_value() {
        // given
        var s = symbol("AAPL");

        // when
        var str = s.toString();

        // then
        assertEquals("AAPL", str);
    }

    @Test
    void hash_code_value() {
        // given
        var str = "AAPL";
        var s = symbol(str);

        // when
        var hc = s.hashCode();

        // then
        assertEquals(str.hashCode(), hc);
    }

    @Test
    void equals_same_true() {
        // given
        var s = symbol("AAPL");

        // when
        var eq = s.equals(s);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var s1 = symbol("AAPL");
        var s2 = symbol("AAPL");

        // when
        var eq = s1.equals(s2);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var s1 = symbol("AAPL");
        var s2 = (FmpSymbol) null;

        // when
        var eq = s1.equals(s2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var s1 = symbol("AAPL");
        var s2 = symbol("MSFT");

        // when
        var eq = s1.equals(s2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var s1 = symbol("AAPL");
        var s2 = "AAPL";

        // when
        var eq = s1.equals(s2);

        // then
        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var s1 = symbol("AAPL");
        var s2 = (FmpSymbol) null;

        // when // then
        var e = assertThrows(FmpInvalidSymbolException.class, () -> s1.compareTo(s2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var s1 = symbol("A");
        var s2 = symbol("B");

        // when
        int cmp = s1.compareTo(s2);

        // then
        assertEquals(-1, cmp);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var s1 = symbol("B");
        var s2 = symbol("A");

        // when
        int cmp = s1.compareTo(s2);

        // then
        assertEquals(1, cmp);
    }

    @Test
    void compare_to_equal() {
        // given
        var s1 = symbol("A");
        var s2 = symbol("A");

        // when
        int cmp = s1.compareTo(s2);

        // then
        assertEquals(0, cmp);
    }

    @ParameterizedTest
    @MethodSource("validSymbols")
    void valid_symbols(String value) {
        // given // when
        var symbol = symbol(value);

        // then
        assertEquals(value, symbol.value());
    }

    @ParameterizedTest
    @MethodSource("invalidSymbols")
    void invalid_symbols(String value) {
        // given // when
        Function<String, FmpSymbol> f = FmpSymbol::symbol;

        // then
        var e = assertThrows(FmpInvalidSymbolException.class, () -> f.apply(value));
        assertEquals(
                format("'value' [%s] does not match pattern [%s]", value, FMP_SYMBOL_PATTERN.pattern()),
                e.getMessage());
    }

    @Disabled("The file is 10MB; excluded from Git")
    @Test
    void check_all_symbols() {
        // How to set up:
        // 1. Download JSON:
        // https://financialmodelingprep.com/stable/financial-statement-symbol-list?apikey=yourapikey
        // 2. Add it to `testFixtures/resources/stable/financial-statement-symbol-list/full.json`
        // 3. Open the file
        // 4. Replace (CMD+R)
        // 5. Enable regex
        // 6. Replace \{[^}]*PARAM_SYMBOL:\s*"([^"]+)"[^}]*\} with "$1"
        // 7. Remove @Disabled
        // 8. Run test

        // given
        var res = testResource("stable/financial-statement-symbol-list/full.json");
        var symbols = FMP_JSON_DESERIALIZER.deserialize(res, String.class);

        var failedSymbols = new ArrayList<String>();
        symbols.forEach(symbol -> {
            try {
                symbol(symbol);
            } catch (Exception e) {
                failedSymbols.add(symbol);
            }
        });

        if (!failedSymbols.isEmpty()) {
            fail("Symbols failed:\n" + String.join("\n", failedSymbols));
        }
    }

    private static Stream<String> validSymbols() {
        return Stream.of(
                "AAPL",
                "BRK.A",
                "TSLA-USD",
                "SHOP.TO",
                "BHP.AX",
                "0005.HK",
                "7203.T",
                "S56.SI",
                "4F.L",
                "MSFT",
                "BF-B",
                "HTOOW",
                "GOGLNE",
                "BAC.PR8",
                "600519.SS",
                "005930.KS",
                "0700.HK",
                "BP.L",
                "SBER.ME",
                "BTC/USD",
                "BTC-USD",
                "ETH-EUR",
                "NYSE:BRK.A",
                "BOOZT-DKK.CO",
                "MORARJEE.NS",
                "CORDSCABLE.BO",
                "PE&OLES.MX",
                "EHMEF",
                "HCL-INSYS.BO",
                "GIG-SDB.ST",
                "IMP-A-SDB.ST",
                "DE000A2NBN90.SG",
                "SYIHRLMESGADKK.CO",
                "SPIMIXMINRISKKLA",
                " GVT&D.NS",
                "A",
                "0",
                "0123456789",
                "ABCDEFGHIJ",
                "ABCDE01234",
                "A1B2",
                "A1B2.XY",
                "A1B2.XYZ1");
    }

    private static Stream<String> invalidSymbols() {
        return Stream.of(
                "", // empty string
                " ", // blank string
                "\t", // whitespace chars
                "\n", // whitespace chars
                "\u00A0", // whitespace chars
                "GOOG!", // illegal char
                "AAPL#", // illegal char
                "MSFT@", // illegal char
                "S56.SI$", // illegal char
                "4F_L", // illegal char
                "GOOＧ", // illegal char (unicode)
                "SBER.ΜE", // illegal char (unicode)
                "ABCDEFGHIJKLMNOPQ", // too long
                "01234567890123456", // too long numeric
                "A1B2.XYZ12", // suffix too long
                "MSFT.", // suffix missing characters
                "ABC.X!", // suffix illegal char
                "ABC.1", // suffix starts with a digit
                "DEF-2A", // suffix starts with a digit
                ".MSFT", // leading separator
                "-MSFT", // leading separator
                "NYSE:", // missing symbol
                "NYSE:.", // missing symbol
                "NASDAQ:-TSLA", // dash after prefix
                "TSLA---USD", // triple dash
                "TSLA--USD", // double dash
                "BRK..A", // double dot
                "LON:BP..L", // double dot
                "BTC//USD", // double slash
                "BRK. A", // internal space
                "TS LA", // internal space
                "NYSE :BRK.A" // internal space
                );
    }
}
