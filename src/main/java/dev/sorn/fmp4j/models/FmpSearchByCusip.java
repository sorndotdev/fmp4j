package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpSearchByCusip(String symbol, String companyName, String cusip, Long marketCap) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
