package dev.sorn.fmp4j.models;

import dev.sorn.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;

public record FmpSearchPressRelease(FmpSymbol symbol, String title, LocalDate date, String text, String url)
        implements FmpModel {

    @Serial
    private static final long serialVersionUID = 1L;
}
