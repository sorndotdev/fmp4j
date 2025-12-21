package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.utils.FmpNumbers.decimal;
import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.util.Objects.hash;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class FmpMoney implements Comparable<FmpMoney> {
    private final FmpCurrency currency;
    private final BigDecimal quantity;

    private FmpMoney(FmpCurrency currency, BigDecimal quantity) {
        this.currency = currency;
        this.quantity = quantity;
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

    public FmpCurrency currency() {
        return currency;
    }

    public BigDecimal quantity() {
        return quantity;
    }

    public boolean isPositive() {
        return quantity.compareTo(ZERO) > 0;
    }

    public boolean isNegative() {
        return quantity.compareTo(ZERO) < 0;
    }

    public boolean isZero() {
        return quantity.compareTo(ZERO) == 0;
    }

    public FmpMoney abs() {
        return new FmpMoney(currency, quantity.abs());
    }

    public FmpMoney neg() {
        return new FmpMoney(currency, quantity.negate());
    }

    public FmpMoney add(FmpMoney that) {
        return perform(that, "add", BigDecimal::add);
    }

    public FmpMoney sub(FmpMoney that) {
        return perform(that, "sub", BigDecimal::subtract);
    }

    public FmpMoney mul(FmpMoney that) {
        return perform(that, "mul", BigDecimal::multiply);
    }

    public FmpMoney div(FmpMoney that) {
        return perform(that, "div", BigDecimal::divide);
    }

    public int compareTo(FmpMoney money) {
        final var that = checkThat(money, "cmp");
        return this.quantity.compareTo(that.quantity);
    }

    @Override
    public String toString() {
        return currency.toString() + " " + quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FmpMoney that)) {
            return false;
        }
        if (!this.currency.equals(that.currency)) {
            return false;
        }
        return this.quantity.compareTo(that.quantity) == 0;
    }

    @Override
    public int hashCode() {
        return hash(currency.hashCode(), quantity);
    }

    private FmpMoney perform(FmpMoney that, String opName, BiFunction<BigDecimal, BigDecimal, BigDecimal> op) {
        final var money = checkThat(that, opName);
        return new FmpMoney(currency, op.apply(this.quantity, money.quantity));
    }

    private FmpMoney checkThat(FmpMoney that, String opName) {
        if (!this.currency.equals(that.currency)) {
            throw new IllegalArgumentException(format(
                    "Cannot apply operation [%s] for different currencies [%s] and [%s]",
                    opName, this.currency, that.currency));
        }
        return that;
    }
}
