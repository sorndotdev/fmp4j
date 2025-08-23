package dev.sorn.fmp4j.models;

import java.io.Serial;
import java.time.LocalDate;

public record FmpEarning(
        String symbol,
        LocalDate date,
        Double epsActual,
        Double epsEstimated,
        Long revenueActual,
        Long revenueEstimated,
        LocalDate lastUpdated)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
