package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpNews;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;

public interface NewsTestData {
    default FmpNews aCryptoNews() {
        return new FmpNews(
                "BTCUSD",
                LocalDate.parse("2025-08-22").atTime(LocalTime.parse("16:45:47")),
                "Decrypt",
                "Public Keys: Ethereum Treasuries Soar, Bitcoin ETFs' $1 Billion Bleed, Crypto IPO Chatter",
                URI.create(
                        "https://images.financialmodelingprep.com/news/public-keys-ethereum-treasuries-soar-bitcoin-etfs-1-billion-20250822.jpeg"),
                URI.create("decrypt.co"),
                "Fed hints spark crypto rally as Bitcoin ETFs bleed $1B, but Ethereum bounces back. Plus, Figure Technologies is eyeing an IPO.",
                URI.create("https://decrypt.co/336496/public-keys-ethereum-treasuries-bitcoin-etfs-ipo-chatter"));
    }

    default FmpNews aForexNews() {
        return new FmpNews(
                "EURUSD",
                LocalDate.parse("2025-08-22").atTime(LocalTime.parse("13:06:13")),
                "FXEmpire",
                "U.S. Dollar Dives After Powell's Dovish Comments: Analysis For EUR/USD, GBP/USD, USD/CAD, USD/JPY",
                URI.create(
                        "https://images.financialmodelingprep.com/news/us-dollar-dives-after-powells-dovish-comments-analysis-for-20250822.jpg"),
                URI.create("fxempire.com"),
                "Powell signaled that Fed was ready to cut rates in September.",
                URI.create(
                        "https://www.fxempire.com/forecasts/article/u-s-dollar-dives-after-powells-dovish-comments-analysis-for-eur-usd-gbp-usd-usd-cad-usd-jpy-1543428"));
    }

    default FmpNews aStockNews() {
        return new FmpNews(
                "AAPL",
                LocalDate.parse("2025-08-22").atTime(LocalTime.parse("16:23:55")),
                "CNBC",
                "Google shares rise on report of Apple using Gemini for Siri",
                URI.create(
                        "https://images.financialmodelingprep.com/news/google-shares-rise-on-report-of-apple-using-gemini-20250822.jpg"),
                URI.create("cnbc.com"),
                "Alphabet shares rose 3% on a Friday report that Apple is in talks to incorporate Gemini technology for a revamped version of Siri, Apple's voice assistant. The talks come as the two tech giants face an uncertain future as its deals for search placement is at risk pending a regulation decision.",
                URI.create(
                        "https://www.cnbc.com/2025/08/22/google-shares-rise-on-report-of-apple-using-gemini-for-siri.html"));
    }
}
