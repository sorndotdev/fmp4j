package com.marketdataapi.fmp4j.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marketdataapi.fmp4j.types.FmpIsin;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDateTime;

public record FmpEtfHolding(
        FmpSymbol symbol,
        FmpSymbol asset,
        String name,
        FmpIsin isin,
        String securityCusip,
        Double sharesNumber,
        Double weightPercentage,
        Double marketValue,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updatedAt)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
