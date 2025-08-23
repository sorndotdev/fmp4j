package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpEtfAssetExposure(
        String symbol, String asset, Long sharesNumber, Double weightPercentage, Double marketValue)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
