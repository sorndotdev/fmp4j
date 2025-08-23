package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpEtf(String symbol, String name) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
