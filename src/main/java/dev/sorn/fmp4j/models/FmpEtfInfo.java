package dev.sorn.fmp4j.models;

import java.io.Serial;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public record FmpEtfInfo(
        String symbol,
        String name,
        String description,
        String isin,
        String assetClass,
        String securityCusip,
        String domicile,
        String website,
        String etfCompany,
        Double expenseRatio,
        Long assetsUnderManagement,
        Long avgVolume,
        LocalDate inceptionDate,
        Double nav,
        String navCurrency,
        Integer holdingsCount,
        ZonedDateTime updatedAt,
        List<SectorExposure> sectorsList)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;

    public record SectorExposure(String industry, Double weight) implements FmpModel {
        @Serial
        private static final long serialVersionUID = 1L;
    }
}
