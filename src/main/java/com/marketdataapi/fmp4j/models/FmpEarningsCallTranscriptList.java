package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpEarningsCallTranscriptList(FmpSymbol symbol, String companyName, Integer noOfTranscripts)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}
