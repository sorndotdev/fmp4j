package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpStockPriceChange;

public interface StockPriceChangeTestData {
    default FmpStockPriceChange aStockPriceChange() {
        return new FmpStockPriceChange(
                "AAPL",
                1.27168,
                -1.70047,
                6.35536,
                16.6385,
                -7.24496,
                -6.59832,
                0.40557221,
                36.19566,
                80.96297,
                783.47556,
                177352.27892);
    }
}
