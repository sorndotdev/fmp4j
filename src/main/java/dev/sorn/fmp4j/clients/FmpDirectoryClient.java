package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.*;
import dev.sorn.fmp4j.services.*;

public class FmpDirectoryClient {

    protected final FmpService<FmpStock[]> fmpStockListService;
    protected final FmpService<FmpEtf[]> fmpEtfListService;


    public FmpDirectoryClient(FmpConfig fmpConfig,
                              FmpHttpClient fmpHttpClient) {
        this.fmpStockListService = new FmpStockListService(fmpConfig, fmpHttpClient);
        this.fmpEtfListService = new FmpEtfListService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpStock[] stock() {
        return fmpStockListService.download();
    }

    public synchronized FmpEtf[] etf() {
        return fmpEtfListService.download();
    }
}
