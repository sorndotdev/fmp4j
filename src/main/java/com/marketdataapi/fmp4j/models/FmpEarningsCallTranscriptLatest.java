package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.io.Serial;
import java.time.LocalDate;

public record FmpEarningsCallTranscriptLatest(FmpSymbol symbol, FmpPeriod period, FmpYear fiscalYear, LocalDate date)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
