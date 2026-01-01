package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.types.FmpLimit.limit;
import static com.marketdataapi.fmp4j.types.FmpPage.page;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_FROM;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOLS;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_TO;
import static java.util.Optional.empty;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpNews;
import com.marketdataapi.fmp4j.services.FmpNewsService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpPage;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FmpNewsClient {

    // Alphabetical order
    protected FmpService<FmpNews> fmpCryptoNewsService;
    protected FmpService<FmpNews> fmpForexNewsService;
    protected FmpService<FmpNews> fmpStockNewsService;

    public FmpNewsClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpCryptoNewsService = new FmpNewsService(fmpConfig, fmpHttpClient, "crypto");
        this.fmpForexNewsService = new FmpNewsService(fmpConfig, fmpHttpClient, "forex");
        this.fmpStockNewsService = new FmpNewsService(fmpConfig, fmpHttpClient, "stock");
    }

    public synchronized List<FmpNews> crypto(Set<FmpSymbol> symbols) {
        return crypto(symbols, empty(), empty(), empty(), empty());
    }

    public synchronized List<FmpNews> forex(Set<FmpSymbol> symbols) {
        return forex(symbols, empty(), empty(), empty(), empty());
    }

    public synchronized List<FmpNews> stock(Set<FmpSymbol> symbols) {
        return stock(symbols, empty(), empty(), empty(), empty());
    }

    public synchronized List<FmpNews> crypto(
            Set<FmpSymbol> symbols,
            Optional<LocalDate> from,
            Optional<LocalDate> to,
            Optional<FmpPage> page,
            Optional<FmpLimit> limit) {
        return news(fmpCryptoNewsService, symbols, from, to, page, limit);
    }

    public synchronized List<FmpNews> forex(
            Set<FmpSymbol> symbols,
            Optional<LocalDate> from,
            Optional<LocalDate> to,
            Optional<FmpPage> page,
            Optional<FmpLimit> limit) {
        return news(fmpForexNewsService, symbols, from, to, page, limit);
    }

    public synchronized List<FmpNews> stock(
            Set<FmpSymbol> symbols,
            Optional<LocalDate> from,
            Optional<LocalDate> to,
            Optional<FmpPage> page,
            Optional<FmpLimit> limit) {
        return news(fmpStockNewsService, symbols, from, to, page, limit);
    }

    protected synchronized List<FmpNews> news(
            FmpService<FmpNews> service,
            Set<FmpSymbol> symbols,
            Optional<LocalDate> from,
            Optional<LocalDate> to,
            Optional<FmpPage> page,
            Optional<FmpLimit> limit) {
        service.param(PARAM_SYMBOLS, symbols);
        from.ifPresent(date -> service.param(PARAM_FROM, date));
        to.ifPresent(date -> service.param(PARAM_TO, date));
        service.param(PARAM_PAGE, page.orElse(page(0)));
        service.param(PARAM_LIMIT, limit.orElse(limit(100)));
        return service.download();
    }
}
