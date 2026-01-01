package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpCik;
import com.marketdataapi.fmp4j.types.FmpCurrency;
import com.marketdataapi.fmp4j.types.FmpExchange;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpSearchByCik(
        FmpSymbol symbol, String companyName, FmpCik cik, FmpExchange exchange, FmpCurrency currency)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
