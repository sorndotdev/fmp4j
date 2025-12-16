package dev.sorn.fmp4j.clients;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpDividend;
import dev.sorn.fmp4j.models.FmpDividendsCalendar;
import dev.sorn.fmp4j.models.FmpEarning;
import dev.sorn.fmp4j.models.FmpEarningsCalendar;
import dev.sorn.fmp4j.models.FmpIposCalendar;
import dev.sorn.fmp4j.models.FmpIposDisclosure;
import dev.sorn.fmp4j.models.FmpIposProspectus;
import dev.sorn.fmp4j.models.FmpSplit;
import dev.sorn.fmp4j.models.FmpSplitsCalendar;
import dev.sorn.fmp4j.services.FmpDividendService;
import dev.sorn.fmp4j.services.FmpDividendsCalendarService;
import dev.sorn.fmp4j.services.FmpEarningService;
import dev.sorn.fmp4j.services.FmpEarningsCalendarService;
import dev.sorn.fmp4j.services.FmpIposCalendarService;
import dev.sorn.fmp4j.services.FmpIposDisclosureService;
import dev.sorn.fmp4j.services.FmpIposProspectusService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.services.FmpSplitService;
import dev.sorn.fmp4j.services.FmpSplitsCalendarService;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FmpCalendarClient {

    // Alphabetical order
    protected final FmpService<FmpDividend> fmpDividendService;
    protected final FmpService<FmpDividendsCalendar> fmpDividendsCalendarService;
    protected final FmpService<FmpEarning> fmpEarningsService;
    protected final FmpService<FmpEarningsCalendar> fmpEarningsCalendarService;
    protected final FmpService<FmpIposCalendar> fmpIposCalendarService;
    protected final FmpService<FmpIposDisclosure> fmpIposDisclosureService;
    protected final FmpService<FmpIposProspectus> fmpIposProspectusService;
    protected final FmpService<FmpSplit> fmpSplitService;
    protected final FmpService<FmpSplitsCalendar> fmpSplitsCalendarService;

    public FmpCalendarClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpDividendService = new FmpDividendService(fmpConfig, fmpHttpClient);
        this.fmpDividendsCalendarService = new FmpDividendsCalendarService(fmpConfig, fmpHttpClient);
        this.fmpEarningsService = new FmpEarningService(fmpConfig, fmpHttpClient);
        this.fmpEarningsCalendarService = new FmpEarningsCalendarService(fmpConfig, fmpHttpClient);
        this.fmpIposCalendarService = new FmpIposCalendarService(fmpConfig, fmpHttpClient);
        this.fmpIposDisclosureService = new FmpIposDisclosureService(fmpConfig, fmpHttpClient);
        this.fmpIposProspectusService = new FmpIposProspectusService(fmpConfig, fmpHttpClient);
        this.fmpSplitService = new FmpSplitService(fmpConfig, fmpHttpClient);
        this.fmpSplitsCalendarService = new FmpSplitsCalendarService(fmpConfig, fmpHttpClient);
    }

    public synchronized List<FmpDividendsCalendar> dividends() {
        return fmpDividendsCalendarService.download();
    }

    public synchronized List<FmpDividend> dividends(FmpSymbol symbol) {
        fmpDividendService.param(PARAM_SYMBOL, symbol);
        return fmpDividendService.download();
    }

    public synchronized List<FmpEarningsCalendar> earnings() {
        return fmpEarningsCalendarService.download();
    }

    public synchronized List<FmpEarning> earnings(FmpSymbol symbol) {
        fmpEarningsService.param(PARAM_SYMBOL, symbol);
        return fmpEarningsService.download();
    }

    public synchronized List<FmpIposCalendar> ipos(Optional<LocalDate> from, Optional<LocalDate> to) {
        fmpIposCalendarService.param(PARAM_FROM, from);
        fmpIposCalendarService.param(PARAM_TO, to);
        return fmpIposCalendarService.download();
    }

    public synchronized List<FmpIposDisclosure> disclosures(Optional<LocalDate> from, Optional<LocalDate> to) {
        fmpIposDisclosureService.param(PARAM_FROM, from);
        fmpIposDisclosureService.param(PARAM_TO, to);
        return fmpIposDisclosureService.download();
    }

    public synchronized List<FmpIposProspectus> prospectus(Optional<LocalDate> from, Optional<LocalDate> to) {
        fmpIposProspectusService.param(PARAM_FROM, from);
        fmpIposProspectusService.param(PARAM_TO, to);
        return fmpIposProspectusService.download();
    }

    public synchronized List<FmpSplitsCalendar> splits() {
        return fmpSplitsCalendarService.download();
    }

    public synchronized List<FmpSplit> splits(FmpSymbol symbol) {
        fmpSplitService.param(PARAM_SYMBOL, symbol);
        return fmpSplitService.download();
    }
}
