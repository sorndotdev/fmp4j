package com.marketdataapi.fmp4j.types;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidPriceException;
import java.math.BigDecimal;

public class FmpPrice extends FmpAmount<FmpPrice> {

    protected FmpPrice(FmpCurrency currency, BigDecimal quantity) {
        super(currency, quantity);
        if (isNegative()) {
            throw new FmpInvalidPriceException("Price cannot be negative");
        }
    }

    public static FmpPrice price(FmpCurrency currency, BigDecimal quantity) {
        return new FmpPrice(currency, quantity);
    }

    @Override
    protected FmpPrice create(FmpCurrency currency, BigDecimal quantity) {
        return new FmpPrice(currency, quantity);
    }
}
