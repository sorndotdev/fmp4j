package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.*;
import dev.sorn.fmp4j.services.*;

import java.util.Optional;

public class FmpChartClient {

    protected final FmpService<FmpHistoricalPriceEodLight[]> fmpHistoricalPriceEodLightService;
    protected final FmpService<FmpHistoricalPriceEodFull[]> fmpHistoricalPriceEodFullService;
    protected final FmpService<FmpHistoricalChart[]> fmpHistoricalChartService1MinService;
    protected final FmpService<FmpHistoricalChart[]> fmpHistoricalChartService5MinService;
    protected final FmpService<FmpHistoricalChart[]> fmpHistoricalChartService15MinService;
    protected final FmpService<FmpHistoricalChart[]> fmpHistoricalChartService30MinService;
    protected final FmpService<FmpHistoricalChart[]> fmpHistoricalChartService1HourService;
    protected final FmpService<FmpHistoricalChart[]> fmpHistoricalChartService4HourService;


    public FmpChartClient(FmpConfig fmpConfig,
                          FmpHttpClient fmpHttpClient) {
        this.fmpHistoricalPriceEodLightService = new FmpHistoricalPriceEodLightService(fmpConfig, fmpHttpClient);
        this.fmpHistoricalPriceEodFullService = new FmpHistoricalPriceEodFullService(fmpConfig, fmpHttpClient);
        this.fmpHistoricalChartService1MinService = new FmpHistoricalChartService(fmpConfig, fmpHttpClient, "1min");
        this.fmpHistoricalChartService5MinService = new FmpHistoricalChartService(fmpConfig, fmpHttpClient, "5min");
        this.fmpHistoricalChartService15MinService = new FmpHistoricalChartService(fmpConfig, fmpHttpClient, "15min");
        this.fmpHistoricalChartService30MinService = new FmpHistoricalChartService(fmpConfig, fmpHttpClient, "30min");
        this.fmpHistoricalChartService1HourService = new FmpHistoricalChartService(fmpConfig, fmpHttpClient, "1hour");
        this.fmpHistoricalChartService4HourService = new FmpHistoricalChartService(fmpConfig, fmpHttpClient, "4hour");
    }

    public synchronized FmpHistoricalPriceEodLight[] historicalPriceEodLight(String symbol, Optional<String> from, Optional<String> to) {
        fmpHistoricalPriceEodLightService.param("symbol", symbol);
        from.ifPresent(date -> fmpHistoricalPriceEodLightService.param("from", date));
        to.ifPresent(date -> fmpHistoricalPriceEodLightService.param("to", date));
        return fmpHistoricalPriceEodLightService.download();
    }

    public synchronized FmpHistoricalPriceEodFull[] historicalPriceEodFull(String symbol, Optional<String> from, Optional<String> to) {
        fmpHistoricalPriceEodFullService.param("symbol", symbol);
        from.ifPresent(date -> fmpHistoricalPriceEodFullService.param("from", date));
        to.ifPresent(date -> fmpHistoricalPriceEodFullService.param("to", date));
        return fmpHistoricalPriceEodFullService.download();
    }

    public synchronized FmpHistoricalChart[] historicalCharts(String interval, String symbol, Optional<String> from, Optional<String> to) {
        return switch (interval) {
            case "1min" -> {
                fmpHistoricalChartService1MinService.param("symbol", symbol);
                from.ifPresent(date -> fmpHistoricalChartService1MinService.param("from", date));
                to.ifPresent(date -> fmpHistoricalChartService1MinService.param("to", date));
                yield fmpHistoricalChartService1MinService.download();
            }
            case "5min" -> {
                fmpHistoricalChartService5MinService.param("symbol", symbol);
                from.ifPresent(date -> fmpHistoricalChartService5MinService.param("from", date));
                to.ifPresent(date -> fmpHistoricalChartService5MinService.param("to", date));
                yield fmpHistoricalChartService5MinService.download();
            }
            case "15min" -> {
                fmpHistoricalChartService15MinService.param("symbol", symbol);
                from.ifPresent(date -> fmpHistoricalChartService15MinService.param("from", date));
                to.ifPresent(date -> fmpHistoricalChartService15MinService.param("to", date));
                yield fmpHistoricalChartService15MinService.download();
            }
            case "30min" -> {
                fmpHistoricalChartService30MinService.param("symbol", symbol);
                from.ifPresent(date -> fmpHistoricalChartService30MinService.param("from", date));
                to.ifPresent(date -> fmpHistoricalChartService30MinService.param("to", date));
                yield fmpHistoricalChartService30MinService.download();
            }
            case "1hour" -> {
                fmpHistoricalChartService1HourService.param("symbol", symbol);
                from.ifPresent(date -> fmpHistoricalChartService1HourService.param("from", date));
                to.ifPresent(date -> fmpHistoricalChartService1HourService.param("to", date));
                yield fmpHistoricalChartService1HourService.download();
            }
            case "4hour" -> {
                fmpHistoricalChartService4HourService.param("symbol", symbol);
                from.ifPresent(date -> fmpHistoricalChartService4HourService.param("from", date));
                to.ifPresent(date -> fmpHistoricalChartService4HourService.param("to", date));
                yield fmpHistoricalChartService4HourService.download();
            }
            default -> throw new IllegalStateException("Unexpected interval: " + interval);
        };
    }
}