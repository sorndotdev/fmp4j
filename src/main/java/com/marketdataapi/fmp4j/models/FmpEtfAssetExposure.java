package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpEtfAssetExposure(
        FmpSymbol symbol, String asset, Long sharesNumber, Double weightPercentage, Double marketValue)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
