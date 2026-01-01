package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOLS;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpSearchByCik;
import com.marketdataapi.fmp4j.models.FmpSearchByCusip;
import com.marketdataapi.fmp4j.models.FmpSearchByIsin;
import com.marketdataapi.fmp4j.models.FmpSearchByName;
import com.marketdataapi.fmp4j.models.FmpSearchBySymbol;
import com.marketdataapi.fmp4j.models.FmpSearchPressRelease;
import com.marketdataapi.fmp4j.services.FmpSearchByCikService;
import com.marketdataapi.fmp4j.services.FmpSearchByCusipService;
import com.marketdataapi.fmp4j.services.FmpSearchByIsinService;
import com.marketdataapi.fmp4j.services.FmpSearchByNameService;
import com.marketdataapi.fmp4j.services.FmpSearchBySymbolService;
import com.marketdataapi.fmp4j.services.FmpSearchPressReleasesService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.types.FmpCik;
import com.marketdataapi.fmp4j.types.FmpCusip;
import com.marketdataapi.fmp4j.types.FmpIsin;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.List;

public class FmpSearchClient {

    // Alphabetical order
    protected final FmpService<FmpSearchByCik> fmpSearchByCikService;
    protected final FmpService<FmpSearchByCusip> fmpSearchByCusipService;
    protected final FmpService<FmpSearchByIsin> fmpSearchByIsinService;
    protected final FmpService<FmpSearchByName> fmpSearchByNameService;
    protected final FmpService<FmpSearchBySymbol> fmpSearchBySymbolService;
    protected final FmpService<FmpSearchPressRelease> fmpSearchPressReleasesService;

    public FmpSearchClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpSearchByCikService = new FmpSearchByCikService(fmpConfig, fmpHttpClient);
        this.fmpSearchByCusipService = new FmpSearchByCusipService(fmpConfig, fmpHttpClient);
        this.fmpSearchByIsinService = new FmpSearchByIsinService(fmpConfig, fmpHttpClient);
        this.fmpSearchByNameService = new FmpSearchByNameService(fmpConfig, fmpHttpClient);
        this.fmpSearchBySymbolService = new FmpSearchBySymbolService(fmpConfig, fmpHttpClient);
        this.fmpSearchPressReleasesService = new FmpSearchPressReleasesService(fmpConfig, fmpHttpClient);
    }

    public synchronized List<FmpSearchByCik> byCik(FmpCik cik) {
        fmpSearchByCikService.param("cik", cik);
        return fmpSearchByCikService.download();
    }

    public synchronized List<FmpSearchByCusip> byCusip(FmpCusip cusip) {
        fmpSearchByCusipService.param("cusip", cusip);
        return fmpSearchByCusipService.download();
    }

    public synchronized List<FmpSearchByIsin> byIsin(FmpIsin isin) {
        fmpSearchByIsinService.param("isin", isin);
        return fmpSearchByIsinService.download();
    }

    public synchronized List<FmpSearchByName> byName(String query) {
        fmpSearchByNameService.param("query", query);
        return fmpSearchByNameService.download();
    }

    public synchronized List<FmpSearchBySymbol> bySymbol(FmpSymbol query) {
        fmpSearchBySymbolService.param("query", query);
        return fmpSearchBySymbolService.download();
    }

    public synchronized List<FmpSearchPressRelease> pressReleases(FmpSymbol symbol) {
        fmpSearchPressReleasesService.param(PARAM_SYMBOLS, symbol);
        return fmpSearchPressReleasesService.download();
    }
}
