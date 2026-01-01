package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpEtfAssetExposure;

public interface EtfAssetExposureTestData {
    default FmpEtfAssetExposure anEtfAssetExposure() {
        return new FmpEtfAssetExposure(symbol("VWUSX"), "NVO", 2143702L, 0.38, 184401246.04);
    }
}
