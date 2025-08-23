package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpNews;
import java.util.Set;

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
    protected Set<String> requiredParams() {
        return Set.of("symbols");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of("from", "to", "page", "limit");
    }
}
