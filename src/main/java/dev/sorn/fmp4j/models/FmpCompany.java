package dev.sorn.fmp4j.models;

import dev.sorn.fmp4j.types.FmpCik;
import dev.sorn.fmp4j.types.FmpCurrency;
import dev.sorn.fmp4j.types.FmpCusip;
import dev.sorn.fmp4j.types.FmpExchange;
import dev.sorn.fmp4j.types.FmpIndustry;
import dev.sorn.fmp4j.types.FmpIsin;
import dev.sorn.fmp4j.types.FmpSector;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;

public record FmpCompany(
        FmpSymbol symbol,
        Double price,
        Long marketCap,
        Double beta,
        Double lastDividend,
        String range,
        Double change,
        Double changePercentage,
        Long volume,
        Long averageVolume,
        String companyName,
        FmpCurrency currency,
        FmpCik cik,
        FmpIsin isin,
        FmpCusip cusip,
        FmpExchange exchange,
        FmpIndustry industry,
        String website,
        String description,
        String ceo,
        FmpSector sector,
        String country,
        String fullTimeEmployees,
        String phone,
        String address,
        String city,
        String state,
        String zip,
        String image,
        LocalDate ipoDate,
        Boolean defaultImage,
        Boolean isEtf,
        Boolean isActivelyTrading,
        Boolean isAdr,
        Boolean isFund)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 9L;
}
