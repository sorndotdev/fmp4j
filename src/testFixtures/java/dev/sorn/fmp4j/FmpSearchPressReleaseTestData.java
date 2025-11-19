package dev.sorn.fmp4j;

import static dev.sorn.fmp4j.types.FmpSymbol.symbol;

import dev.sorn.fmp4j.models.FmpSearchPressRelease;
import java.time.LocalDate;

public interface FmpSearchPressReleaseTestData {

    default FmpSearchPressRelease aPressRelease() {
        return new FmpSearchPressRelease(
                symbol("V"),
                "Visa Reports Fiscal Q4 2024 Earnings",
                LocalDate.parse("2024-10-25"),
                "Visa Inc. (NYSE: V) today announced its financial results for the fiscal fourth quarter ended September 30, 2024.",
                "https://investor.visa.com/news/press-releases/2024");
    }
}
