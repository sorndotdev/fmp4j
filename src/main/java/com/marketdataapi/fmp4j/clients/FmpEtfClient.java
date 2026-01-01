package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpEtfAssetExposure;
import com.marketdataapi.fmp4j.models.FmpEtfCountryWeighting;
import com.marketdataapi.fmp4j.models.FmpEtfHolding;
import com.marketdataapi.fmp4j.models.FmpEtfInfo;
import com.marketdataapi.fmp4j.models.FmpEtfSectorWeighting;
import com.marketdataapi.fmp4j.services.FmpEtfAssetExposureService;
import com.marketdataapi.fmp4j.services.FmpEtfCountryWeightingService;
import com.marketdataapi.fmp4j.services.FmpEtfHoldingService;
import com.marketdataapi.fmp4j.services.FmpEtfInfoService;
import com.marketdataapi.fmp4j.services.FmpEtfSectorWeightingService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.List;

public class FmpEtfClient {
    // Alphabetical order
    protected final FmpService<FmpEtfAssetExposure> etfAssetExposureService;
    protected final FmpService<FmpEtfCountryWeighting> etfCountryWeightingService;
    protected final FmpService<FmpEtfHolding> etfHoldingService;
    protected final FmpService<FmpEtfInfo> etfInfoService;
    protected final FmpService<FmpEtfSectorWeighting> etfSectorWeightingService;

    public FmpEtfClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.etfAssetExposureService = new FmpEtfAssetExposureService(fmpConfig, fmpHttpClient);
        this.etfCountryWeightingService = new FmpEtfCountryWeightingService(fmpConfig, fmpHttpClient);
        this.etfHoldingService = new FmpEtfHoldingService(fmpConfig, fmpHttpClient);
        this.etfInfoService = new FmpEtfInfoService(fmpConfig, fmpHttpClient);
        this.etfSectorWeightingService = new FmpEtfSectorWeightingService(fmpConfig, fmpHttpClient);
    }

    public synchronized List<FmpEtfAssetExposure> assetExposure(FmpSymbol symbol) {
        etfAssetExposureService.param(PARAM_SYMBOL, symbol);
        return etfAssetExposureService.download();
    }

    public synchronized List<FmpEtfCountryWeighting> countryWeightings(FmpSymbol symbol) {
        etfCountryWeightingService.param(PARAM_SYMBOL, symbol);
        return etfCountryWeightingService.download();
    }

    public synchronized List<FmpEtfHolding> holdings(FmpSymbol symbol) {
        etfHoldingService.param(PARAM_SYMBOL, symbol);
        return etfHoldingService.download();
    }

    public synchronized List<FmpEtfInfo> info(FmpSymbol symbol) {
        etfInfoService.param(PARAM_SYMBOL, symbol);
        return etfInfoService.download();
    }

    public synchronized List<FmpEtfSectorWeighting> sectorWeightings(FmpSymbol symbol) {
        etfSectorWeightingService.param(PARAM_SYMBOL, symbol);
        return etfSectorWeightingService.download();
    }
}
