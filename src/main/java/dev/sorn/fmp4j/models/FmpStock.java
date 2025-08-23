package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpStock(String symbol, String companyName) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
