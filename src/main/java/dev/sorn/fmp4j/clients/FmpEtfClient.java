package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEtfAssetExposure;
import dev.sorn.fmp4j.models.FmpEtfCountryWeighting;
import dev.sorn.fmp4j.models.FmpEtfHolding;
import dev.sorn.fmp4j.models.FmpEtfInfo;
import dev.sorn.fmp4j.models.FmpEtfSectorWeighting;
import dev.sorn.fmp4j.services.FmpEtfAssetExposureService;
import dev.sorn.fmp4j.services.FmpEtfCountryWeightingService;
import dev.sorn.fmp4j.services.FmpEtfHoldingService;
import dev.sorn.fmp4j.services.FmpEtfInfoService;
import dev.sorn.fmp4j.services.FmpEtfSectorWeightingService;
import dev.sorn.fmp4j.services.FmpService;

public class FmpEtfClient {
    protected final FmpService<FmpEtfAssetExposure[]> etfAssetExposureService;
    protected final FmpService<FmpEtfCountryWeighting[]> etfCountryWeightingService;
    protected final FmpService<FmpEtfHolding[]> etfHoldingService;
    protected final FmpService<FmpEtfInfo[]> etfInfoService;
    protected final FmpService<FmpEtfSectorWeighting[]> etfSectorWeightingService;

    public FmpEtfClient(FmpConfig fmpConfig,
                        FmpHttpClient fmpHttpClient) {
        this.etfAssetExposureService = new FmpEtfAssetExposureService(fmpConfig, fmpHttpClient);
        this.etfCountryWeightingService = new FmpEtfCountryWeightingService(fmpConfig, fmpHttpClient);
        this.etfHoldingService = new FmpEtfHoldingService(fmpConfig, fmpHttpClient);
        this.etfInfoService = new FmpEtfInfoService(fmpConfig, fmpHttpClient);
        this.etfSectorWeightingService = new FmpEtfSectorWeightingService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpEtfAssetExposure[] etfAssetExposure(String symbol) {
        etfAssetExposureService.param("symbol", symbol);
        return etfAssetExposureService.download();
    }

    public synchronized FmpEtfCountryWeighting[] etfCountryWeightings(String symbol) {
        etfCountryWeightingService.param("symbol", symbol);
        return etfCountryWeightingService.download();
    }

    public synchronized FmpEtfHolding[] etfHoldings(String symbol) {
        etfHoldingService.param("symbol", symbol);
        return etfHoldingService.download();
    }

    public synchronized FmpEtfInfo[] etfInfo(String symbol) {
        etfInfoService.param("symbol", symbol);
        return etfInfoService.download();
    }

    public synchronized FmpEtfSectorWeighting[] etfSectorWeightings(String symbol) {
        etfSectorWeightingService.param("symbol", symbol);
        return etfSectorWeightingService.download();
    }
}