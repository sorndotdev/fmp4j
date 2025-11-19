package dev.sorn.fmp4j.models;

import dev.sorn.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;

public record FmpSplitsCalendar(FmpSymbol symbol, LocalDate date, Integer numerator, Integer denominator, String label)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
