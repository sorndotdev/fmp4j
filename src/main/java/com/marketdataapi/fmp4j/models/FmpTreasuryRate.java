package com.marketdataapi.fmp4j.models;

import java.io.Serial;
import java.time.LocalDate;

public record FmpTreasuryRate(
        LocalDate date,
        Double month1,
        Double month2,
        Double month3,
        Double month6,
        Double year1,
        Double year2,
        Double year3,
        Double year5,
        Double year7,
        Double year10,
        Double year20,
        Double year30)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
