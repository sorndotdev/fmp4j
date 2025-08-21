package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpQuote;
import dev.sorn.fmp4j.models.FmpShortQuote;
import dev.sorn.fmp4j.services.FmpQuoteService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.services.FmpShortQuoteService;

public class FmpQuoteClient {
    protected final FmpService<FmpQuote[]> quoteService;
    protected final FmpService<FmpShortQuote[]> shortQuoteService;

    public FmpQuoteClient(FmpConfig fmpConfig,
                          FmpHttpClient fmpHttpClient) {
        this.quoteService = new FmpQuoteService(fmpConfig, fmpHttpClient);
        this.shortQuoteService = new FmpShortQuoteService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpQuote[] quotes(String symbol) {
        quoteService.param("symbol", symbol);
        return quoteService.download();
    }

    public synchronized FmpShortQuote[] shortQuotes(String symbol) {
        shortQuoteService.param("symbol", symbol);
        return shortQuoteService.download();
    }
}