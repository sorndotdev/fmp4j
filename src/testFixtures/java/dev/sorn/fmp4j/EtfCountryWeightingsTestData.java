package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpEtfCountryWeighting;

public interface EtfCountryWeightingsTestData {
    default FmpEtfCountryWeighting anEtfCountryWeighting() {
        return new FmpEtfCountryWeighting("United States", "99.51%");
    }
}
