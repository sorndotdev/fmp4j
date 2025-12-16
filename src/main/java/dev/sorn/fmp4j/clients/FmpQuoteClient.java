package dev.sorn.fmp4j.clients;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpFullQuote;
import dev.sorn.fmp4j.models.FmpPartialQuote;
import dev.sorn.fmp4j.models.FmpStockPriceChange;
import dev.sorn.fmp4j.services.FmpPartialQuoteService;
import dev.sorn.fmp4j.services.FmpQuoteService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.services.FmpStockPriceChangeService;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.List;

public class FmpQuoteClient {
    protected final FmpService<FmpFullQuote> quoteService;
    protected final FmpService<FmpPartialQuote> shortQuoteService;
    protected final FmpService<FmpStockPriceChange> stockPriceChangeService;

    public FmpQuoteClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.quoteService = new FmpQuoteService(fmpConfig, fmpHttpClient);
        this.shortQuoteService = new FmpPartialQuoteService(fmpConfig, fmpHttpClient);
        this.stockPriceChangeService = new FmpStockPriceChangeService(fmpConfig, fmpHttpClient);
    }

    public synchronized List<FmpFullQuote> full(FmpSymbol symbol) {
        quoteService.param(PARAM_SYMBOL, symbol);
        return quoteService.download();
    }

    public synchronized List<FmpPartialQuote> partial(FmpSymbol symbol) {
        shortQuoteService.param(PARAM_SYMBOL, symbol);
        return shortQuoteService.download();
    }

    public synchronized List<FmpStockPriceChange> priceChange(FmpSymbol symbol) {
        stockPriceChangeService.param(PARAM_SYMBOL, symbol);
        return stockPriceChangeService.download();
    }
}
