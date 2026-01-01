package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpExchange.NASDAQ;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpFullQuote;
import com.marketdataapi.fmp4j.models.FmpPartialQuote;

public interface QuoteTestData {
    default FmpFullQuote aFullQuote() {
        return new FmpFullQuote(
                symbol("AAPL"),
                "Apple Inc.",
                232.8,
                2.1008,
                4.79,
                44489128L,
                226.65,
                233.13,
                260.1,
                164.08,
                3500823120000L,
                240.2278,
                219.98755,
                NASDAQ,
                227.2,
                228.01,
                1738702801L);
    }

    default FmpPartialQuote aPartialQuote() {
        return new FmpPartialQuote(symbol("AAPL"), 231.59, -1.19, 54864147L);
    }
}
