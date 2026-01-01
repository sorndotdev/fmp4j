package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpPartialQuote(FmpSymbol symbol, Double price, Double change, Long volume) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
