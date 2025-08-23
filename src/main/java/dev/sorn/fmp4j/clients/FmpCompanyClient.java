package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCompany;
import dev.sorn.fmp4j.services.FmpCompanyService;
import dev.sorn.fmp4j.services.FmpService;

public class FmpCompanyClient {

    protected final FmpService<FmpCompany[]> fmpCompanyService;

    public FmpCompanyClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpCompanyService = new FmpCompanyService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpCompany[] company(String symbol) {
        fmpCompanyService.param("symbol", symbol);
        return fmpCompanyService.download();
    }
}
