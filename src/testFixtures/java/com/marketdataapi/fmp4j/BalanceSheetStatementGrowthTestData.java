package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpPeriod.FY;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.types.FmpYear.year;

import com.marketdataapi.fmp4j.models.FmpBalanceSheetStatementGrowth;
import java.time.LocalDate;

public interface BalanceSheetStatementGrowthTestData {
    default FmpBalanceSheetStatementGrowth aBalanceSheetStatementGrowth() {
        return new FmpBalanceSheetStatementGrowth(
                symbol("AAPL"),
                LocalDate.parse("2024-09-28"),
                year("2024"),
                FY,
                USD,
                -0.0007341898882029034,
                0.11516302627413738,
                0.058744212492892536,
                0.08621792243994425,
                0.15084504817564365,
                -0.02776454576386526,
                0.06562138667929733,
                -0.15992349565984992,
                0.0,
                0.0,
                0.0,
                -0.09015953214513049,
                0.09225857046829487,
                0.5266933370120016,
                0.014238076328719674,
                0.0,
                0.035160515396374756,
                0.1014039066617687,
                0.32087050041121024,
                2.01632838190271,
                0.023322168465450935,
                0.03377722721172706,
                0.21391802240757563,
                -0.10003043628845205,
                0.0,
                0.0,
                -0.09048495373370312,
                -0.09295867814151548,
                0.0,
                0.060574238130816666,
                0.0,
                0.12821763398905328,
                -88.50467289719626,
                0.3737338456164862,
                0.0,
                -0.0836095645737457,
                0.0,
                -0.0836095645737457,
                0.035160515396374756,
                -0.04107194211936368,
                -0.039304446058258696,
                -0.051604320757728944,
                0.13223532601328453,
                0.04307907360930203,
                0.0,
                0.3378272434551309,
                2.01632838190271,
                0.0,
                0.03619047619047619,
                0.0,
                0.0);
    }
}
