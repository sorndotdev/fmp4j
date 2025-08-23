package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpEtfSectorWeighting(String symbol, String sector, Double weightPercentage) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
