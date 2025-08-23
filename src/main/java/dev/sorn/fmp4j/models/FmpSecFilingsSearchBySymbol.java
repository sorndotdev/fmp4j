package dev.sorn.fmp4j.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import java.time.LocalDateTime;

public record FmpSecFilingsSearchBySymbol(
        String symbol,
        String cik,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime filingDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime acceptedDate,
        String formType,
        String link,
        String finalLink)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
