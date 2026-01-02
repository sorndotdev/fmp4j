package com.marketdataapi.fmp4j.types;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.util.Objects.hash;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public abstract class FmpAmount<T extends FmpAmount<T>> implements Comparable<T> {
    protected final FmpCurrency currency;
    protected final BigDecimal quantity;

    protected FmpAmount(FmpCurrency currency, BigDecimal quantity) {
        this.currency = currency;
        this.quantity = quantity;
    }

    protected abstract T create(FmpCurrency currency, BigDecimal quantity);

    public final FmpCurrency currency() {
        return currency;
    }

    public final BigDecimal quantity() {
        return quantity;
    }

    public final boolean isPositive() {
        return quantity.compareTo(ZERO) > 0;
    }

    public final boolean isNegative() {
        return quantity.compareTo(ZERO) < 0;
    }

    public final boolean isZero() {
        return quantity.compareTo(ZERO) == 0;
    }

    public final T abs() {
        return create(currency, quantity.abs());
    }

    public final T neg() {
        return create(currency, quantity.negate());
    }

    public final T add(T that) {
        return perform(that, "add", BigDecimal::add);
    }

    public final T sub(T that) {
        return perform(that, "sub", BigDecimal::subtract);
    }

    public final T mul(T that) {
        return perform(that, "mul", BigDecimal::multiply);
    }

    public final T div(T that) {
        return perform(that, "div", BigDecimal::divide);
    }

    protected final T perform(T that, String opName, BiFunction<BigDecimal, BigDecimal, BigDecimal> op) {
        final var amount = checkThat(that, opName);
        return create(currency, op.apply(this.quantity, amount.quantity));
    }

    protected final T checkThat(T that, String opName) {
        if (!this.currency.equals(that.currency)) {
            throw new IllegalArgumentException(format(
                    "Cannot apply operation [%s] for different currencies [%s] and [%s]",
                    opName, this.currency, that.currency));
        }
        return that;
    }

    public final int compareTo(T amount) {
        final var that = checkThat(amount, "cmp");
        return this.quantity.compareTo(that.quantity);
    }

    @Override
    public String toString() {
        return currency.toString() + " " + quantity;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FmpAmount<?> that)) {
            return false;
        }
        if (!this.currency.equals(that.currency)) {
            return false;
        }
        return this.quantity.compareTo(that.quantity) == 0;
    }

    @Override
    public final int hashCode() {
        return hash(currency.hashCode(), quantity);
    }
}
