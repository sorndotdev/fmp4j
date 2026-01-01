package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.types.FmpExchange.TWO;
import static com.marketdataapi.fmp4j.types.FmpExchange.exchange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidExchangeException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class FmpExchangeTest {
    @ParameterizedTest
    @MethodSource
    void valid_exchange(String shortName) {
        // given // when
        var p = exchange(shortName);

        // then
        assertEquals(shortName, p.name());
    }

    private static Stream<String> valid_exchange() {
        return Stream.of(
                "AMEX",
                "AMS",
                "AQS",
                "ASX",
                "ATH",
                "BER",
                "BME",
                "BRU",
                "BSE",
                "BUD",
                "BUE",
                "BVC",
                "CBOE",
                "CNQ",
                "CPH",
                "DFM",
                "DOH",
                "DUB",
                "DUS",
                "DXE",
                "EGX",
                "EURONEXT",
                "HAM",
                "HEL",
                "HKSE",
                "HOSE",
                "ICE",
                "IOB",
                "IST",
                "JKT",
                "JNB",
                "JPX",
                "KLS",
                "KOE",
                "KSC",
                "KUW",
                "LIS",
                "LSE",
                "MCX",
                "MEX",
                "MIL",
                "MUN",
                "NASDAQ",
                "NEO",
                "NSE",
                "NYSE",
                "NZE",
                "OSL",
                "OTC",
                "PAR",
                "PRA",
                "RIS",
                "SAO",
                "SAU",
                "SES",
                "SET",
                "SGO",
                "SHH",
                "SHZ",
                "SIX",
                "STO",
                "STU",
                "TAI",
                "TAL",
                "TLV",
                "TSX",
                "TSXV",
                "TWO",
                "VIE",
                "WSE",
                "XETRA");
    }

    @Test
    void lower_case_code_exchange() {
        // given // when
        var p = exchange("two");

        // then
        assertEquals(TWO.name(), p.name());
    }

    @Test
    void invalid_exchange() {
        // given
        var p = "ANEX";

        // when // then
        var e = assertThrows(FmpInvalidExchangeException.class, () -> exchange(p));
        assertEquals("[ANEX] is not a valid exchange", e.getMessage());
    }

    @Test
    void should_not_null_country_code() {
        var nullCountryCodes = Arrays.stream(FmpExchange.values())
                .map(FmpExchange::countryCode)
                .filter(Objects::isNull)
                .toList();

        assertTrue(nullCountryCodes.isEmpty(), "The list should be empty");
    }

    @Test
    void should_not_null_suffix_symbol() {
        var nullSuffixSymbol = Arrays.stream(FmpExchange.values())
                .map(FmpExchange::suffixSymbol)
                .filter(Objects::isNull)
                .toList();

        assertTrue(nullSuffixSymbol.isEmpty(), "The list should be empty");
    }

    @ParameterizedTest
    @CsvSource({"AMEX,New York Stock Exchange Arca", "AMS,Euronext Amsterdam"})
    void valid_toString(String shortName, String expectedToString) {
        FmpExchange e = exchange(shortName);
        assertEquals(expectedToString, e.toString());
    }
}
