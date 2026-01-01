package com.marketdataapi.fmp4j.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import java.time.LocalDateTime;

public record FmpHistoricalChart(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
        Double open,
        Double low,
        Double high,
        Double close,
        Long volume)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
