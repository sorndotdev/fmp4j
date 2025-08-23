package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpEtfSectorWeighting;

public interface EtfSectorWeightingTestData {
    default FmpEtfSectorWeighting anEtfSectorWeighting() {
        return new FmpEtfSectorWeighting("SPY", "Utilities", 2.45);
    }
}
