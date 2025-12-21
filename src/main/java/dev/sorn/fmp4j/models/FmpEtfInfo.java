package dev.sorn.fmp4j.models;

import dev.sorn.fmp4j.types.FmpCurrency;
import dev.sorn.fmp4j.types.FmpIsin;
import dev.sorn.fmp4j.types.FmpSector;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public record FmpEtfInfo(
        FmpSymbol symbol,
        String name,
        String description,
        FmpIsin isin,
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
        FmpCurrency navCurrency,
        Integer holdingsCount,
        ZonedDateTime updatedAt,
        List<SectorExposure> sectorsList)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 7L;

    // The first parameter is sector information, however FMP API wrongly returns as "industry"
    // API sample return in stable/etf/info/%3Fsymbol=SPY.json
    public record SectorExposure(FmpSector industry, Double weight) implements FmpModel {
        @Serial
        private static final long serialVersionUID = 3L;
    }
}
