package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.types.FmpInterval.FIFTEEN_MINUTE;
import static com.marketdataapi.fmp4j.types.FmpInterval.FIVE_MINUTE;
import static com.marketdataapi.fmp4j.types.FmpInterval.FOUR_HOUR;
import static com.marketdataapi.fmp4j.types.FmpInterval.ONE_HOUR;
import static com.marketdataapi.fmp4j.types.FmpInterval.ONE_MINUTE;
import static com.marketdataapi.fmp4j.types.FmpInterval.THIRTY_MINUTE;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_FROM;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_TO;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpHistoricalChart;
import com.marketdataapi.fmp4j.models.FmpHistoricalPriceEodFull;
import com.marketdataapi.fmp4j.models.FmpHistoricalPriceEodLight;
import com.marketdataapi.fmp4j.services.FmpHistoricalChartService;
import com.marketdataapi.fmp4j.services.FmpHistoricalPriceEodFullService;
import com.marketdataapi.fmp4j.services.FmpHistoricalPriceEodLightService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.types.FmpInterval;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FmpChartClient {

    protected final FmpService<FmpHistoricalPriceEodLight> fmpHistoricalPriceEodLightService;
    protected final FmpService<FmpHistoricalPriceEodFull> fmpHistoricalPriceEodFullService;
    protected final FmpService<FmpHistoricalChart> fmpHistoricalChartService1MinService;
    protected final FmpService<FmpHistoricalChart> fmpHistoricalChartService5MinService;
    protected final FmpService<FmpHistoricalChart> fmpHistoricalChartService15MinService;
    protected final FmpService<FmpHistoricalChart> fmpHistoricalChartService30MinService;
    protected final FmpService<FmpHistoricalChart> fmpHistoricalChartService1HourService;
    protected final FmpService<FmpHistoricalChart> fmpHistoricalChartService4HourService;

    public FmpChartClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpHistoricalPriceEodLightService = new FmpHistoricalPriceEodLightService(fmpConfig, fmpHttpClient);
        this.fmpHistoricalPriceEodFullService = new FmpHistoricalPriceEodFullService(fmpConfig, fmpHttpClient);
        this.fmpHistoricalChartService1MinService = new FmpHistoricalChartService(fmpConfig, fmpHttpClient, ONE_MINUTE);
        this.fmpHistoricalChartService5MinService =
                new FmpHistoricalChartService(fmpConfig, fmpHttpClient, FIVE_MINUTE);
        this.fmpHistoricalChartService15MinService =
                new FmpHistoricalChartService(fmpConfig, fmpHttpClient, FIFTEEN_MINUTE);
        this.fmpHistoricalChartService30MinService =
                new FmpHistoricalChartService(fmpConfig, fmpHttpClient, THIRTY_MINUTE);
        this.fmpHistoricalChartService1HourService = new FmpHistoricalChartService(fmpConfig, fmpHttpClient, ONE_HOUR);
        this.fmpHistoricalChartService4HourService = new FmpHistoricalChartService(fmpConfig, fmpHttpClient, FOUR_HOUR);
    }

    public synchronized List<FmpHistoricalPriceEodLight> historicalPriceEodLight(
            FmpSymbol symbol, Optional<LocalDate> from, Optional<LocalDate> to) {
        fmpHistoricalPriceEodLightService.param(PARAM_SYMBOL, symbol);
        from.ifPresent(date -> fmpHistoricalPriceEodLightService.param(PARAM_FROM, date));
        to.ifPresent(date -> fmpHistoricalPriceEodLightService.param(PARAM_TO, date));
        return fmpHistoricalPriceEodLightService.download();
    }

    public synchronized List<FmpHistoricalPriceEodFull> historicalPriceEodFull(
            FmpSymbol symbol, Optional<LocalDate> from, Optional<LocalDate> to) {
        fmpHistoricalPriceEodFullService.param(PARAM_SYMBOL, symbol);
        from.ifPresent(date -> fmpHistoricalPriceEodFullService.param(PARAM_FROM, date));
        to.ifPresent(date -> fmpHistoricalPriceEodFullService.param(PARAM_TO, date));
        return fmpHistoricalPriceEodFullService.download();
    }

    public synchronized List<FmpHistoricalChart> historical(
            FmpSymbol symbol, FmpInterval interval, Optional<LocalDate> from, Optional<LocalDate> to) {
        return switch (interval) {
            case ONE_MINUTE -> {
                fmpHistoricalChartService1MinService.param(PARAM_SYMBOL, symbol);
                from.ifPresent(date -> fmpHistoricalChartService1MinService.param(PARAM_FROM, date));
                to.ifPresent(date -> fmpHistoricalChartService1MinService.param(PARAM_TO, date));
                yield fmpHistoricalChartService1MinService.download();
            }
            case FIVE_MINUTE -> {
                fmpHistoricalChartService5MinService.param(PARAM_SYMBOL, symbol);
                from.ifPresent(date -> fmpHistoricalChartService5MinService.param(PARAM_FROM, date));
                to.ifPresent(date -> fmpHistoricalChartService5MinService.param(PARAM_TO, date));
                yield fmpHistoricalChartService5MinService.download();
            }
            case FIFTEEN_MINUTE -> {
                fmpHistoricalChartService15MinService.param(PARAM_SYMBOL, symbol);
                from.ifPresent(date -> fmpHistoricalChartService15MinService.param(PARAM_FROM, date));
                to.ifPresent(date -> fmpHistoricalChartService15MinService.param(PARAM_TO, date));
                yield fmpHistoricalChartService15MinService.download();
            }
            case THIRTY_MINUTE -> {
                fmpHistoricalChartService30MinService.param(PARAM_SYMBOL, symbol);
                from.ifPresent(date -> fmpHistoricalChartService30MinService.param(PARAM_FROM, date));
                to.ifPresent(date -> fmpHistoricalChartService30MinService.param(PARAM_TO, date));
                yield fmpHistoricalChartService30MinService.download();
            }
            case ONE_HOUR -> {
                fmpHistoricalChartService1HourService.param(PARAM_SYMBOL, symbol);
                from.ifPresent(date -> fmpHistoricalChartService1HourService.param(PARAM_FROM, date));
                to.ifPresent(date -> fmpHistoricalChartService1HourService.param(PARAM_TO, date));
                yield fmpHistoricalChartService1HourService.download();
            }
            case FOUR_HOUR -> {
                fmpHistoricalChartService4HourService.param(PARAM_SYMBOL, symbol);
                from.ifPresent(date -> fmpHistoricalChartService4HourService.param(PARAM_FROM, date));
                to.ifPresent(date -> fmpHistoricalChartService4HourService.param(PARAM_TO, date));
                yield fmpHistoricalChartService4HourService.download();
            }
        };
    }
}
