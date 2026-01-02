package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.utils.FmpNumbers.decimal;

import java.math.BigDecimal;

public class FmpMoney extends FmpAmount<FmpMoney> {

    protected FmpMoney(FmpCurrency currency, BigDecimal quantity) {
        super(currency, quantity);
    }

    @Override
    protected FmpMoney create(FmpCurrency currency, BigDecimal quantity) {
        return new FmpMoney(currency, quantity);
    }

    public static FmpMoney money(FmpCurrency currency, BigDecimal quantity) {
        return new FmpMoney(currency, quantity);
    }

    public static FmpMoney zero(FmpCurrency currency) {
        return new FmpMoney(currency, decimal(0L));
    }

    public static FmpMoney unit(FmpCurrency currency) {
        return new FmpMoney(currency, decimal(1L));
    }

    public static FmpMoney thousand(FmpCurrency currency) {
        return new FmpMoney(currency, decimal(1_000L));
    }

    public static FmpMoney million(FmpCurrency currency) {
        return new FmpMoney(currency, decimal(1_000_000L));
    }
}
