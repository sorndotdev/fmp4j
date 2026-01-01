package com.marketdataapi.fmp4j.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marketdataapi.fmp4j.types.FmpCik;
import com.marketdataapi.fmp4j.types.FmpFormType;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDateTime;

public record FmpSecFilingsSearchBySymbol(
        FmpSymbol symbol,
        FmpCik cik,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime filingDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime acceptedDate,
        FmpFormType formType,
        String link,
        String finalLink)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
