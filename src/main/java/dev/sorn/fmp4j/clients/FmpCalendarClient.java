package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpDividend;
import dev.sorn.fmp4j.models.FmpDividendsCalendar;
import dev.sorn.fmp4j.models.FmpEarning;
import dev.sorn.fmp4j.models.FmpEarningsCalendar;
import dev.sorn.fmp4j.services.FmpDividendService;
import dev.sorn.fmp4j.services.FmpDividendsCalendarService;
import dev.sorn.fmp4j.services.FmpEarningService;
import dev.sorn.fmp4j.services.FmpEarningsCalendarService;
import dev.sorn.fmp4j.services.FmpService;

public class FmpCalendarClient {

    protected final FmpService<FmpDividend[]> fmpDividendService;
    protected final FmpService<FmpDividendsCalendar[]> fmpDividendsCalendarService;
    protected final FmpService<FmpEarning[]> fmpEarningsService;
    protected final FmpService<FmpEarningsCalendar[]> fmpEarningsCalendarService;


    public FmpCalendarClient(FmpConfig fmpConfig,
                             FmpHttpClient fmpHttpClient) {
        this.fmpDividendService = new FmpDividendService(fmpConfig, fmpHttpClient);
        this.fmpDividendsCalendarService = new FmpDividendsCalendarService(fmpConfig, fmpHttpClient);
        this.fmpEarningsService = new FmpEarningService(fmpConfig, fmpHttpClient);
        this.fmpEarningsCalendarService = new FmpEarningsCalendarService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpDividendsCalendar[] dividendsCalendar() {
        return fmpDividendsCalendarService.download();
    }

    public synchronized FmpDividend[] dividends(String symbol) {
        fmpDividendService.param("symbol", symbol);
        return fmpDividendService.download();
    }

    public synchronized FmpEarningsCalendar[] earningsCalendar() {
        return fmpEarningsCalendarService.download();
    }

    public synchronized FmpEarning[] earnings(String symbol) {
        fmpEarningsService.param("symbol", symbol);
        return fmpEarningsService.download();
    }
}