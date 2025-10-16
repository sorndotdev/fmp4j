package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpNews;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;

public class FmpNewsService extends FmpService<FmpNews[]> {
    protected final String type;

    public FmpNewsService(FmpConfig cfg, FmpHttpClient http, String type) {
        super(cfg, http, typeRef(FmpNews[].class));
        this.type = type;
    }

    @Override
    protected String relativeUrl() {
        return "/news/" + type;
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbols", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of("from", LocalDate.class,
                "to", LocalDate.class,
                "page", FmpPage.class ,
                "limit", FmpLimit.class);
    }
}
