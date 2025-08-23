package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpFullQuote;
import dev.sorn.fmp4j.models.FmpPartialQuote;
import dev.sorn.fmp4j.models.FmpStockPriceChange;
import dev.sorn.fmp4j.services.FmpQuoteService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.services.FmpShortQuoteService;
import dev.sorn.fmp4j.services.FmpStockPriceChangeService;

public class FmpQuoteClient {
    protected final FmpService<FmpFullQuote[]> quoteService;
    protected final FmpService<FmpPartialQuote[]> shortQuoteService;
    protected final FmpService<FmpStockPriceChange[]> stockPriceChangeService;

    public FmpQuoteClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.quoteService = new FmpQuoteService(fmpConfig, fmpHttpClient);
        this.shortQuoteService = new FmpShortQuoteService(fmpConfig, fmpHttpClient);
        this.stockPriceChangeService = new FmpStockPriceChangeService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpFullQuote[] full(String symbol) {
        quoteService.param("symbol", symbol);
        return quoteService.download();
    }

    public synchronized FmpPartialQuote[] partial(String symbol) {
        shortQuoteService.param("symbol", symbol);
        return shortQuoteService.download();
    }

    public synchronized FmpStockPriceChange[] priceChange(String symbol) {
        stockPriceChangeService.param("symbol", symbol);
        return stockPriceChangeService.download();
    }
}
