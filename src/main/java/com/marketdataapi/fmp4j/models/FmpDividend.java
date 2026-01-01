package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;

public record FmpDividend(
        FmpSymbol symbol,
        LocalDate date,
        LocalDate recordDate,
        LocalDate paymentDate,
        LocalDate declarationDate,
        Double adjDividend,
        Double dividend,
        Double yield,
        String frequency)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
