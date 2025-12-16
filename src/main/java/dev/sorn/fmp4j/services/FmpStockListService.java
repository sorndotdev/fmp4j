package dev.sorn.fmp4j.services;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpStock;
import java.util.Map;

public class FmpStockListService extends FmpService<FmpStock> {
    public FmpStockListService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpStock.class);
    }

    @Override
    protected String relativeUrl() {
        return "/stock-list";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of();
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }

    @Override
    protected boolean filter(FmpStock stocks) {
        if (stocks.symbol() == null) {
            return false;
        }
        return true;
    }
}
