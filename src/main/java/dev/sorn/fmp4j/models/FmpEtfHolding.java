package dev.sorn.fmp4j.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import java.time.LocalDateTime;

public record FmpEtfHolding(
        String symbol,
        String asset,
        String name,
        String isin,
        String securityCusip,
        Double sharesNumber,
        Double weightPercentage,
        Double marketValue,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updatedAt)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
