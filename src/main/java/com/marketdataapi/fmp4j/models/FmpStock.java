package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpStock(FmpSymbol symbol, String companyName) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
