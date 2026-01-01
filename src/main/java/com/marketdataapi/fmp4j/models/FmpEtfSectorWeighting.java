package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSector;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpEtfSectorWeighting(FmpSymbol symbol, FmpSector sector, Double weightPercentage) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
