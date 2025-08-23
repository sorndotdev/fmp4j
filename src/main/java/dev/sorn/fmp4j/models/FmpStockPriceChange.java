package dev.sorn.fmp4j.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;

public record FmpStockPriceChange(
        String symbol,
        @JsonProperty(value = "1D") Double _1D,
        @JsonProperty(value = "5D") Double _5D,
        @JsonProperty(value = "1M") Double _1M,
        @JsonProperty(value = "3M") Double _3M,
        @JsonProperty(value = "6M") Double _6M,
        @JsonProperty(value = "ytd") Double ytd,
        @JsonProperty(value = "1Y") Double _1Y,
        @JsonProperty(value = "3Y") Double _3Y,
        @JsonProperty(value = "5Y") Double _5Y,
        @JsonProperty(value = "10Y") Double _10Y,
        Double max)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
