package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpCurrency;
import com.marketdataapi.fmp4j.types.FmpExchange;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpSearchByName(FmpSymbol symbol, String name, FmpCurrency currency, FmpExchange exchange)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
