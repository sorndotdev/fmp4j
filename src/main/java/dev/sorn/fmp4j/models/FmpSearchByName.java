package dev.sorn.fmp4j.models;

import dev.sorn.fmp4j.types.FmpCurrency;
import dev.sorn.fmp4j.types.FmpExchange;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpSearchByName(FmpSymbol symbol, String name, FmpCurrency currency, FmpExchange exchange)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 5L;
}
