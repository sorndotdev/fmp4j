package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSecFilingsSearchBySymbol;
import dev.sorn.fmp4j.services.FmpSecFilingsSearchBySymbolService;
import dev.sorn.fmp4j.services.FmpService;
import java.util.Optional;

public class FmpSecFilingsSearchClient {
    protected static final int FIRST_PAGE = 0;
    protected static final int DEFAULT_LIMIT = 100;
    protected final FmpService<FmpSecFilingsSearchBySymbol[]> fmpSecFilingsSearchBySymbol;

    public FmpSecFilingsSearchClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpSecFilingsSearchBySymbol = new FmpSecFilingsSearchBySymbolService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpSecFilingsSearchBySymbol[] bySymbol(
            String symbol, String from, String to, Optional<Integer> page, Optional<Integer> limit) {
        fmpSecFilingsSearchBySymbol.param("symbol", symbol);
        fmpSecFilingsSearchBySymbol.param("from", from);
        fmpSecFilingsSearchBySymbol.param("to", to);
        fmpSecFilingsSearchBySymbol.param("page", page.orElse(FIRST_PAGE));
        fmpSecFilingsSearchBySymbol.param("limit", limit.orElse(DEFAULT_LIMIT));
        return fmpSecFilingsSearchBySymbol.download();
    }
}
