package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpQuarter;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.io.Serial;
import java.time.LocalDate;

public record FmpEarningsCallTranscriptDate(FmpQuarter quarter, FmpYear fiscalYear, LocalDate date)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
