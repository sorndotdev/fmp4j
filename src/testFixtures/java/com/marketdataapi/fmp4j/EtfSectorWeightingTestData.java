package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSector.sector;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpEtfSectorWeighting;

public interface EtfSectorWeightingTestData {
    default FmpEtfSectorWeighting anEtfSectorWeighting() {
        return new FmpEtfSectorWeighting(symbol("SPY"), sector("Utilities"), 2.45);
    }
}
