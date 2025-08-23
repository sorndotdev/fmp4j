package dev.sorn.fmp4j.models;

import java.io.Serial;
import java.time.LocalDate;

public record FmpDividendsCalendar(
        String symbol,
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
    private static final long serialVersionUID = 1L;
}
