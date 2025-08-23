package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpEtfAssetExposure;

public interface EtfAssetExposureTestData {
    default FmpEtfAssetExposure anEtfAssetExposure() {
        return new FmpEtfAssetExposure("VWUSX", "NVO", 2143702L, 0.38, 184401246.04);
    }
}
