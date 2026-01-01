package com.marketdataapi.fmp4j;

import com.marketdataapi.fmp4j.models.FmpEtfCountryWeighting;

public interface EtfCountryWeightingsTestData {
    default FmpEtfCountryWeighting anEtfCountryWeighting() {
        return new FmpEtfCountryWeighting("United States", "99.51%");
    }
}
