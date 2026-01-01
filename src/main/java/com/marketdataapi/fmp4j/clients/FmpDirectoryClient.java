package com.marketdataapi.fmp4j.clients;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpEtf;
import com.marketdataapi.fmp4j.models.FmpStock;
import com.marketdataapi.fmp4j.services.FmpEtfListService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.services.FmpStockListService;
import java.util.List;

public class FmpDirectoryClient {

    // Alphabetical order
    protected final FmpService<FmpEtf> fmpEtfListService;
    protected final FmpService<FmpStock> fmpStockListService;

    public FmpDirectoryClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpEtfListService = new FmpEtfListService(fmpConfig, fmpHttpClient);
        this.fmpStockListService = new FmpStockListService(fmpConfig, fmpHttpClient);
    }

    public synchronized List<FmpEtf> etfs() {
        return fmpEtfListService.download();
    }

    public synchronized List<FmpStock> stocks() {
        return fmpStockListService.download();
    }
}
