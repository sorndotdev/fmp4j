package com.marketdataapi.fmp4j.cfg;

import com.marketdataapi.fmp4j.types.FmpApiKey;

public interface FmpConfig {
    FmpApiKey fmpApiKey();

    String fmpBaseUrl();
}
