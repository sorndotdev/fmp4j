package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpQuarter.quarter;
import static com.marketdataapi.fmp4j.types.FmpYear.year;

import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscript;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscriptDate;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscriptLatest;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscriptList;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public interface EarningsCallTestData {
    default FmpEarningsCallTranscriptDate anEarningsCallDate() {
        final var quarter = quarter(ThreadLocalRandom.current().nextInt(1, 4));
        final var year = year(ThreadLocalRandom.current().nextInt(2000, 2024));
        final var date = LocalDate.of(
                year.value(), quarter.value() * 3, ThreadLocalRandom.current().nextInt(1, 28));
        return new FmpEarningsCallTranscriptDate(quarter, year, date);
    }

    default FmpEarningsCallTranscript anEarningsCallTranscript() {
        final var symbol = FmpSymbol.symbol("AAPL");
        final var year = year(2023);
        final var period = FmpPeriod.QUARTER;
        final var date = LocalDate.of(2023, 8, 1);
        final var content = "This is a sample earnings call transcript content.";
        return new FmpEarningsCallTranscript(symbol, period, year, date, content);
    }

    default FmpEarningsCallTranscriptLatest anEarningsCallTranscriptLatest() {
        final var symbol = FmpSymbol.symbol("AAPL");
        final var period = FmpPeriod.QUARTER;
        final var year = year(ThreadLocalRandom.current().nextInt(2000, 2024));
        final var date = LocalDate.of(
                year.value(),
                ThreadLocalRandom.current().nextInt(1, 12),
                ThreadLocalRandom.current().nextInt(1, 28));
        return new FmpEarningsCallTranscriptLatest(symbol, period, year, date);
    }

    default FmpEarningsCallTranscriptList anEarningsCallTranscriptList() {
        final var symbol = FmpSymbol.symbol("AAPL");
        final var companyName = "Apple Inc.";
        final var noOfTranscripts = ThreadLocalRandom.current().nextInt(1, 100);
        return new FmpEarningsCallTranscriptList(symbol, companyName, noOfTranscripts);
    }
}
