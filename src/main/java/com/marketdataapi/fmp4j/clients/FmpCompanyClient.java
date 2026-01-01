package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpCompany;
import com.marketdataapi.fmp4j.services.FmpCompanyService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.List;

public class FmpCompanyClient {

    // Alphabetical order
    protected final FmpService<FmpCompany> fmpCompanyService;

    public FmpCompanyClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpCompanyService = new FmpCompanyService(fmpConfig, fmpHttpClient);
    }

    public synchronized List<FmpCompany> bySymbol(FmpSymbol symbol) {
        fmpCompanyService.param(PARAM_SYMBOL, symbol);
        return fmpCompanyService.download();
    }
}
