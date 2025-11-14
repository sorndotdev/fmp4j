package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCompany;
import dev.sorn.fmp4j.services.FmpCompaniesService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.types.FmpPart;

public class FmpBulkClient {

    // Alphabetical order
    protected final FmpService<FmpCompany[]> fmpByPartService;

    public FmpBulkClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpByPartService = new FmpCompaniesService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpCompany[] byPart(FmpPart part) {
        fmpByPartService.param("part", part);
        return fmpByPartService.download();
    }
}
