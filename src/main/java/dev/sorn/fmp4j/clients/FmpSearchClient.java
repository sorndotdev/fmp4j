package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchByCusip;
import dev.sorn.fmp4j.models.FmpSearchByIsin;
import dev.sorn.fmp4j.models.FmpSearchByName;
import dev.sorn.fmp4j.models.FmpSearchBySymbol;
import dev.sorn.fmp4j.services.FmpSearchByCusipService;
import dev.sorn.fmp4j.services.FmpSearchByIsinService;
import dev.sorn.fmp4j.services.FmpSearchByNameService;
import dev.sorn.fmp4j.services.FmpSearchBySymbolService;
import dev.sorn.fmp4j.services.FmpService;

public class FmpSearchClient {

    protected final FmpService<FmpSearchByName[]> fmpSearchByNameService;
    protected final FmpService<FmpSearchBySymbol[]> fmpSearchBySymbolService;
    protected final FmpService<FmpSearchByIsin[]> fmpSearchByIsinService;
    protected final FmpService<FmpSearchByCusip[]> fmpSearchByCusipService;


    public FmpSearchClient(FmpConfig fmpConfig,
                           FmpHttpClient fmpHttpClient) {
        this.fmpSearchByIsinService = new FmpSearchByIsinService(fmpConfig, fmpHttpClient);
        this.fmpSearchByNameService = new FmpSearchByNameService(fmpConfig, fmpHttpClient);
        this.fmpSearchBySymbolService = new FmpSearchBySymbolService(fmpConfig, fmpHttpClient);
        this.fmpSearchByCusipService = new FmpSearchByCusipService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpSearchByIsin[] byIsin(String isin) {
        fmpSearchByIsinService.param("isin", isin);
        return fmpSearchByIsinService.download();
    }

    public synchronized FmpSearchByName[] byName(String query) {
        fmpSearchByNameService.param("query", query);
        return fmpSearchByNameService.download();
    }

    public synchronized FmpSearchByCusip[] byCusip(String cusip) {
        fmpSearchByCusipService.param("cusip", cusip);
        return fmpSearchByCusipService.download();
    }

    public synchronized FmpSearchBySymbol[] bySymbol(String query) {
        fmpSearchBySymbolService.param("query", query);
        return fmpSearchBySymbolService.download();
    }

}
