package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEtf;
import dev.sorn.fmp4j.models.FmpStock;
import dev.sorn.fmp4j.services.FmpEtfListService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.services.FmpStockListService;

public class FmpDirectoryClient {

    protected final FmpService<FmpStock[]> fmpStockListService;
    protected final FmpService<FmpEtf[]> fmpEtfListService;

    public FmpDirectoryClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpStockListService = new FmpStockListService(fmpConfig, fmpHttpClient);
        this.fmpEtfListService = new FmpEtfListService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpStock[] stocks() {
        return fmpStockListService.download();
    }

    public synchronized FmpEtf[] etfs() {
        return fmpEtfListService.download();
    }
}
