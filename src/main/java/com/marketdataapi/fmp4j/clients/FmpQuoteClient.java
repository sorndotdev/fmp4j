package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpFullQuote;
import com.marketdataapi.fmp4j.models.FmpPartialQuote;
import com.marketdataapi.fmp4j.models.FmpStockPriceChange;
import com.marketdataapi.fmp4j.services.FmpPartialQuoteService;
import com.marketdataapi.fmp4j.services.FmpQuoteService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.services.FmpStockPriceChangeService;
import com.marketdataapi.fmp4j.types.FmpSymbol;
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
