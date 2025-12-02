package dev.sorn.fmp4j.clients;

import static dev.sorn.fmp4j.types.FmpLimit.limit;
import static dev.sorn.fmp4j.types.FmpPage.page;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSecFilingsSearchBySymbol;
import dev.sorn.fmp4j.services.FmpSecFilingsSearchBySymbolService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Optional;

public class FmpSecFilingsSearchClient {
    protected static final FmpPage DEFAULT_PAGE = page(0);
    protected static final FmpLimit DEFAULT_LIMIT = limit(100);
    protected final FmpService<FmpSecFilingsSearchBySymbol[]> fmpSecFilingsSearchBySymbol;

    public FmpSecFilingsSearchClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpSecFilingsSearchBySymbol = new FmpSecFilingsSearchBySymbolService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpSecFilingsSearchBySymbol[] bySymbol(
            FmpSymbol symbol, LocalDate from, LocalDate to, Optional<FmpPage> page, Optional<FmpLimit> limit) {
        fmpSecFilingsSearchBySymbol.param(PARAM_SYMBOL, symbol);
        fmpSecFilingsSearchBySymbol.param(PARAM_FROM, from);
        fmpSecFilingsSearchBySymbol.param(PARAM_TO, to);
        fmpSecFilingsSearchBySymbol.param(PARAM_PAGE, page.orElse(DEFAULT_PAGE));
        fmpSecFilingsSearchBySymbol.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return fmpSecFilingsSearchBySymbol.download();
    }
}
