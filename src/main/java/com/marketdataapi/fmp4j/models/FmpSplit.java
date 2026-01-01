package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;

public record FmpSplit(FmpSymbol symbol, LocalDate date, Integer numerator, Integer denominator, String label)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
