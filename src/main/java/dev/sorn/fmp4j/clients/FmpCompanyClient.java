package dev.sorn.fmp4j.clients;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCompany;
import dev.sorn.fmp4j.services.FmpCompanyService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.types.FmpSymbol;

public class FmpCompanyClient {

    // Alphabetical order
    protected final FmpService<FmpCompany[]> fmpCompanyService;

    public FmpCompanyClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpCompanyService = new FmpCompanyService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpCompany[] bySymbol(FmpSymbol symbol) {
        fmpCompanyService.param(PARAM_SYMBOL, symbol);
        return fmpCompanyService.download();
    }
}
