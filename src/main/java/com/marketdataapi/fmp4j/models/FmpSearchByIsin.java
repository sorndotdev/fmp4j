package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpIsin;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpSearchByIsin(FmpSymbol symbol, String name, FmpIsin isin, Long marketCap) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
