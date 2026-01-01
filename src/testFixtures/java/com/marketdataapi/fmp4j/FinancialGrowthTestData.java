package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpPeriod.FY;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.types.FmpYear.year;

import com.marketdataapi.fmp4j.models.FmpFinancialGrowth;
import java.time.LocalDate;

public interface FinancialGrowthTestData {
    default FmpFinancialGrowth aFinancialGrowth() {
        return new FmpFinancialGrowth(
                symbol("AAPL"),
                LocalDate.parse("2023-09-30"),
                year("2023"),
                FY,
                USD,
                -0.028004605303199367,
                -0.00956775304188966,
                -0.06336758608256715,
                -0.04300174987650393,
                -0.028135426790777834,
                0.001626016260162567,
                0.0032733224222585224,
                -0.029090594249629207,
                -0.031439280320331865,
                0.042731773310903005,
                -0.0950299219818094,
                0.0008698220967636053,
                0.28002426202992314,
                -0.00048759053734178114,
                0.26318344802395743,
                -0.06453804347826086,
                0.13957563521389663,
                -0.006455726468478521,
                -0.10641314393905404,
                2.6905240454238135,
                0.816842026998005,
                0.5388164047163655,
                2.389734710129544,
                0.7972760681074091,
                0.5101800197949481,
                3.3097019469393576,
                1.051263292954884,
                0.8620241100283557,
                -0.1722339004490628,
                -0.26978894458196756,
                0.048266631608940624,
                1.3405599978590987,
                0.3795229029287352,
                0.17601279383408341,
                null,
                null,
                null,
                null,
                null);
    }
}
