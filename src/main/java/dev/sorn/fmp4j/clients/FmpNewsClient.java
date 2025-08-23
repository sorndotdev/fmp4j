package dev.sorn.fmp4j.clients;

import static java.lang.String.join;
import static java.util.Optional.empty;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpNews;
import dev.sorn.fmp4j.services.FmpNewsService;
import dev.sorn.fmp4j.services.FmpService;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public class FmpNewsClient {
    protected FmpService<FmpNews[]> fmpCryptoNewsService;
    protected FmpService<FmpNews[]> fmpForexNewsService;
    protected FmpService<FmpNews[]> fmpStockNewsService;

    public FmpNewsClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpCryptoNewsService = new FmpNewsService(fmpConfig, fmpHttpClient, "crypto");
        this.fmpForexNewsService = new FmpNewsService(fmpConfig, fmpHttpClient, "forex");
        this.fmpStockNewsService = new FmpNewsService(fmpConfig, fmpHttpClient, "stock");
    }

    public synchronized FmpNews[] crypto(Set<String> symbols) {
        return crypto(symbols, empty(), empty(), empty(), empty());
    }

    public synchronized FmpNews[] forex(Set<String> symbols) {
        return forex(symbols, empty(), empty(), empty(), empty());
    }

    public synchronized FmpNews[] stock(Set<String> symbols) {
        return stock(symbols, empty(), empty(), empty(), empty());
    }

    public synchronized FmpNews[] crypto(
            Set<String> symbols,
            Optional<LocalDate> from,
            Optional<LocalDate> to,
            Optional<Integer> page,
            Optional<Integer> limit) {
        return news(fmpCryptoNewsService, symbols, from, to, page, limit);
    }

    public synchronized FmpNews[] forex(
            Set<String> symbols,
            Optional<LocalDate> from,
            Optional<LocalDate> to,
            Optional<Integer> page,
            Optional<Integer> limit) {
        return news(fmpForexNewsService, symbols, from, to, page, limit);
    }

    public synchronized FmpNews[] stock(
            Set<String> symbols,
            Optional<LocalDate> from,
            Optional<LocalDate> to,
            Optional<Integer> page,
            Optional<Integer> limit) {
        return news(fmpStockNewsService, symbols, from, to, page, limit);
    }

    protected synchronized FmpNews[] news(
            FmpService<FmpNews[]> service,
            Set<String> symbols,
            Optional<LocalDate> from,
            Optional<LocalDate> to,
            Optional<Integer> page,
            Optional<Integer> limit) {
        service.param("symbols", join(",", symbols));
        from.ifPresent(date -> service.param("from", date));
        to.ifPresent(date -> service.param("to", date));
        service.param("page", page.orElse(0));
        service.param("limit", limit.orElse(100));
        return service.download();
    }
}
