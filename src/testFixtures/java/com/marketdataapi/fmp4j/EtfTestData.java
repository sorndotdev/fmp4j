package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpEtf;

public interface EtfTestData {
    default FmpEtf anEtf() {
        return new FmpEtf(symbol("GULF"), "WisdomTree Middle East Dividend Fund");
    }
}
