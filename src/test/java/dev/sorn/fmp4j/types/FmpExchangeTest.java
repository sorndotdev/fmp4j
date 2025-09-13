package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.types.FmpExchange.exchange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.sorn.fmp4j.exceptions.FmpInvalidExchangeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FmpExchangeTest {
    @ParameterizedTest
    @CsvSource({
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
        "XETRA"
    })
    void valid_exchange(String shortName) {
        // when
        var p = exchange(shortName);

        // then
        assertEquals(shortName, p.value());
    }

    @Test
    void invalid_exchange() {
        // given
        var p = "ANEX";

        // when // then
        var e = assertThrows(FmpInvalidExchangeException.class, () -> exchange(p));
        assertEquals("[ANEX] is not a valid exchange", e.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"AMEX,AMEX:New York Stock Exchange Arca:US:N/A", "AMS,AMS:Euronext Amsterdam:NL:.AS"})
    void valid_toString(String shortName, String expectedToString) {
        FmpExchange e = exchange(shortName);
        assertEquals(expectedToString, e.toString());
    }
}
