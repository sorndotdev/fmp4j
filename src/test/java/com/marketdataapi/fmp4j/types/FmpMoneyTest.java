package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.types.FmpCurrency.EUR;
import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpMoney.million;
import static com.marketdataapi.fmp4j.types.FmpMoney.money;
import static com.marketdataapi.fmp4j.types.FmpMoney.thousand;
import static com.marketdataapi.fmp4j.types.FmpMoney.unit;
import static com.marketdataapi.fmp4j.types.FmpMoney.zero;
import static com.marketdataapi.fmp4j.utils.FmpNumbers.decimal;
import static java.util.Objects.hash;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

class FmpMoneyTest {
    @Test
    void creates_money() {
        // given
        var currency = USD;
        var quantity = decimal(1000);

        // when
        var money = money(currency, quantity);

        // then
        assertThat(money.currency()).isEqualTo(currency);
        assertThat(money.quantity()).isEqualTo(decimal(1000L));
    }

    @Test
    void zero_quantity_from_currency() {
        // given
        var currency = USD;

        // when
        var money = zero(currency);

        // then
        assertThat(money.currency()).isEqualTo(USD);
        assertThat(money.quantity()).isEqualTo(decimal(0L));
    }

    @Test
    void unit_quantity_from_currency() {
        // given
        var currency = USD;

        // when
        var money = unit(currency);

        // then
        assertThat(money.currency()).isEqualTo(USD);
        assertThat(money.quantity()).isEqualTo(decimal(1L));
    }

    @Test
    void thousand_from_currency() {
        // given
        var currency = USD;

        // when
        var money = thousand(currency);

        // then
        assertThat(money.currency()).isEqualTo(USD);
        assertThat(money.quantity()).isEqualTo(decimal(1_000L));
    }

    @Test
    void million_from_currency() {
        // given
        var currency = USD;

        // when
        var money = million(currency);

        // then
        assertThat(money.currency()).isEqualTo(USD);
        assertThat(money.quantity()).isEqualTo(decimal(1_000_000L));
    }

    @Test
    void is_positive_returns_true_only_when_positive() {
        // given
        var positive = money(USD, decimal(1));
        var negative = money(USD, decimal(-1));
        var zero = zero(USD);

        // when // then
        assertThat(positive.isPositive()).isTrue();
        assertThat(negative.isPositive()).isFalse();
        assertThat(zero.isPositive()).isFalse();
    }

    @Test
    void is_negative_returns_true_only_when_negative() {
        // given
        var positive = money(USD, decimal(1));
        var negative = money(USD, decimal(-1));
        var zero = zero(USD);

        // when // then
        assertThat(positive.isNegative()).isFalse();
        assertThat(negative.isNegative()).isTrue();
        assertThat(zero.isNegative()).isFalse();
    }

    @Test
    void is_zero_returns_true_only_when_zero() {
        // given
        var positive = money(USD, decimal(1));
        var negative = money(USD, decimal(-1));
        var zero = zero(USD);

        // when // then
        assertThat(positive.isZero()).isFalse();
        assertThat(negative.isZero()).isFalse();
        assertThat(zero.isZero()).isTrue();
    }

    @Test
    void equals_returns_true_when_equal() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(1));

        // when
        var eq = money1.equals(money2);

        // then
        assertThat(eq).isTrue();
    }

    @Test
    void equals_returns_false_when_not_equal() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(2));

        // when
        var eq = money1.equals(money2);

        // then
        assertThat(eq).isFalse();
    }

    @Test
    void abs_of_positive_returns_positive() {
        // given
        var money = money(USD, decimal(1));

        // when
        var abs = money.abs();

        // then
        assertThat(abs).isEqualTo(money(USD, decimal(1)));
    }

    @Test
    void abs_of_negative_returns_positive() {
        // given
        var money = money(USD, decimal(-1));

        // when
        var abs = money.abs();

        // then
        assertThat(abs).isEqualTo(money(USD, decimal(1)));
    }

    @Test
    void abs_of_zero_returns_zero() {
        // given
        var money = zero(USD);

        // when
        var abs = money.abs();

        // then
        assertThat(abs).isEqualTo(zero(USD));
    }

    @Test
    void abs_of_negative_zero_returns_zero() {
        // given
        var money = money(USD, decimal(-0));

        // when
        var abs = money.abs();

        // then
        assertThat(abs).isEqualTo(zero(USD));
    }

    @Test
    void neg_of_positive_returns_negative() {
        // given
        var money = money(USD, decimal(1));

        // when
        var neg = money.neg();

        // then
        assertThat(neg).isEqualTo(money(USD, decimal(-1)));
    }

    @Test
    void neg_of_negative_returns_positive() {
        // given
        var money = money(USD, decimal(-1));

        // when
        var neg = money.neg();

        // then
        assertThat(neg).isEqualTo(money(USD, decimal(1)));
    }

    @Test
    void neg_of_zero_returns_zero() {
        // given
        var money = zero(USD);

        // when
        var neg = money.neg();

        // then
        assertThat(neg).isEqualTo(money);
    }

    @Test
    void add_positive_with_positive() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(2));

        // when
        var sum = money1.add(money2);

        // then
        assertThat(sum).isEqualTo(money(USD, decimal(3)));
    }

    @Test
    void add_positive_with_negative() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(-2));

        // when
        var sum = money1.add(money2);

        // then
        assertThat(sum).isEqualTo(money(USD, decimal(-1)));
    }

    @Test
    void add_negative_with_positive() {
        // given
        var money1 = money(USD, decimal(-1));
        var money2 = money(USD, decimal(2));

        // when
        var sum = money1.add(money2);

        // then
        assertThat(sum).isEqualTo(money(USD, decimal(1)));
    }

    @Test
    void add_positive_with_zero() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(0));

        // when
        var sum = money1.add(money2);

        // then
        assertThat(sum).isEqualTo(money1);
    }

    @Test
    void add_negative_with_zero() {
        // given
        var money1 = money(USD, decimal(-1));
        var money2 = money(USD, decimal(0));

        // when
        var sum = money1.add(money2);

        // then
        assertThat(sum).isEqualTo(money1);
    }

    @Test
    void add_different_units_throws() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(EUR, decimal(1));

        // when
        BiFunction<FmpMoney, FmpMoney, FmpMoney> f = FmpMoney::add;

        // then
        assertThatThrownBy(() -> f.apply(money1, money2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot apply operation [add] for different currencies [USD] and [EUR]");
    }

    @Test
    void sub_positive_with_positive() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(2));

        // when
        var diff = money1.sub(money2);

        // then
        assertThat(diff).isEqualTo(money(USD, decimal(-1)));
    }

    @Test
    void sub_positive_with_negative() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(-2));

        // when
        var diff = money1.sub(money2);

        // then
        assertThat(diff).isEqualTo(money(USD, decimal(3)));
    }

    @Test
    void sub_negative_with_positive() {
        // given
        var money1 = money(USD, decimal(-1));
        var money2 = money(USD, decimal(2));

        // when
        var diff = money1.sub(money2);

        // then
        assertThat(diff).isEqualTo(money(USD, decimal(-3)));
    }

    @Test
    void sub_positive_with_zero() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(0));

        // when
        var diff = money1.sub(money2);

        // then
        assertThat(diff).isEqualTo(money1);
    }

    @Test
    void sub_negative_with_zero() {
        // given
        var money1 = money(USD, decimal(-1));
        var money2 = money(USD, decimal(0));

        // when
        var diff = money1.sub(money2);

        // then
        assertThat(diff).isEqualTo(money1);
    }

    @Test
    void sub_different_units_throws() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(EUR, decimal(1));

        // when
        BiFunction<FmpMoney, FmpMoney, FmpMoney> f = FmpMoney::sub;

        // then
        assertThatThrownBy(() -> f.apply(money1, money2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot apply operation [sub] for different currencies [USD] and [EUR]");
    }

    @Test
    void mul_positive_with_positive() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(2));

        // when
        var product = money1.mul(money2);

        // then
        assertThat(product).isEqualTo(money(USD, decimal(2)));
    }

    @Test
    void mul_negative_with_negative() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(-2));

        // when
        var product = money1.mul(money2);

        // then
        assertThat(product).isEqualTo(money(USD, decimal(-2)));
    }

    @Test
    void mul_different_units_throws() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(EUR, decimal(1));

        // when
        BiFunction<FmpMoney, FmpMoney, FmpMoney> f = FmpMoney::mul;

        // then
        assertThatThrownBy(() -> f.apply(money1, money2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot apply operation [mul] for different currencies [USD] and [EUR]");
    }

    @Test
    void div_positive_with_positive() {
        // given
        var money1 = money(USD, decimal(420));
        var money2 = money(USD, decimal(28));

        // when
        var quotient = money1.div(money2);

        // then
        assertThat(quotient).isEqualTo(money(USD, decimal(15)));
    }

    @Test
    void div_positive_with_negative() {
        // given
        var money1 = money(USD, decimal(20));
        var money2 = money(USD, decimal(-10));

        // when
        var quotient = money1.div(money2);

        // then
        assertThat(quotient).isEqualTo(money(USD, decimal(-2)));
    }

    @Test
    void div_positive_by_zero_amount_throws() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = zero(USD);

        // when
        BiFunction<FmpMoney, FmpMoney, FmpMoney> f = FmpMoney::div;

        // then
        assertThatThrownBy(() -> f.apply(money1, money2))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Division by zero");
    }

    @Test
    void div_different_units_throws() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(EUR, decimal(1));

        // when
        BiFunction<FmpMoney, FmpMoney, FmpMoney> f = FmpMoney::div;

        // then
        assertThatThrownBy(() -> f.apply(money1, money2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot apply operation [div] for different currencies [USD] and [EUR]");
    }

    @Test
    void to_string_returns_currency_code_space_quantity() {
        // given
        var money = money(USD, decimal(42));

        // when
        var str = money.toString();

        // then
        assertThat(str).isEqualTo("USD 42.00");
    }

    @Test
    void hash_code_is_hash_of_currency_and_quantity() {
        // given
        var money = money(USD, decimal(42));

        // when
        var hc = money.hashCode();

        // then
        assertThat(hc).isEqualTo(hash(USD, decimal(42L)));
    }

    @Test
    void equals_same_true() {
        // given
        var money = money(USD, decimal(42));

        // when
        var eq = money.equals(money);

        // then
        assertThat(eq).isTrue();
    }

    @Test
    void equals_identical_true() {
        // given
        var money1 = money(USD, decimal(42));
        var money2 = money(USD, decimal(42));

        // when
        var eq = money1.equals(money2);

        // then
        assertThat(eq).isTrue();
    }

    @Test
    void equals_null_false() {
        // given
        var money1 = money(USD, decimal(13));
        var money2 = (FmpMoney) null;

        // when
        var eq = money1.equals(money2);

        // then
        assertThat(eq).isFalse();
    }

    @Test
    void equals_different_currency_false() {
        // given
        var money1 = money(USD, decimal(13));
        var money2 = money(EUR, decimal(13));

        // when
        var eq = money1.equals(money2);

        // then
        assertThat(eq).isFalse();
    }

    @Test
    void equals_different_quantity_false() {
        // given
        var money1 = money(USD, decimal(13));
        var money2 = money(USD, decimal(23));

        // when
        var eq = money1.equals(money2);

        // then
        assertThat(eq).isFalse();
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var money1 = money(USD, decimal(13));
        var money2 = mock(Object.class);

        // when
        var eq = money1.equals(money2);

        // then
        assertThat(eq).isFalse();
    }

    @Test
    void compare_to_greater_than() {
        // given
        var money1 = money(USD, decimal(2));
        var money2 = money(USD, decimal(1));

        // when
        var cmp = money1.compareTo(money2);

        // then
        assertThat(cmp).isEqualTo(1);
    }

    @Test
    void compare_to_less_than() {
        // given
        var money1 = money(USD, decimal(1));
        var money2 = money(USD, decimal(2));

        // when
        var cmp = money1.compareTo(money2);

        // then
        assertThat(cmp).isEqualTo(-1);
    }

    @Test
    void compare_to_equal() {
        // given
        var money1 = money(USD, decimal(42));
        var money2 = money(USD, decimal(42));

        // when
        var cmp = money1.compareTo(money2);

        // then
        assertThat(cmp).isEqualTo(0);
    }

    @Test
    void compare_to_different_units_throws() {
        // given
        var money1 = money(USD, decimal(13));
        var money2 = money(EUR, decimal(13));

        // when
        BiFunction<FmpMoney, FmpMoney, Integer> f = Comparable::compareTo;

        // then
        assertThatThrownBy(() -> f.apply(money1, money2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot apply operation [cmp] for different currencies [USD] and [EUR]");
    }
}
