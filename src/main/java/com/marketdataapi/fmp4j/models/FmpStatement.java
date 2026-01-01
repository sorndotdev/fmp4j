package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpCurrency;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.time.LocalDate;

public interface FmpStatement {
    LocalDate date();

    FmpSymbol symbol();

    FmpCurrency reportedCurrency();

    FmpYear fiscalYear();

    FmpPeriod period();
}
