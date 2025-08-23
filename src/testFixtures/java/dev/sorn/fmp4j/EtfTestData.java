package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpEtf;

public interface EtfTestData {
    default FmpEtf anEtf() {
        return new FmpEtf("GULF", "WisdomTree Middle East Dividend Fund");
    }
}
