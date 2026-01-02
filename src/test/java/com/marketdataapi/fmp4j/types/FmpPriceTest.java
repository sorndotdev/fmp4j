package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpPrice.price;
import static com.marketdataapi.fmp4j.utils.FmpNumbers.decimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidPriceException;
import org.junit.jupiter.api.Test;

class FmpPriceTest {

    @Test
    void creates_price() {
        // given
        var currency = USD;
        var quantity = decimal(1000);

        // when
        var money = price(currency, quantity);

        // then
        assertThat(money.currency()).isEqualTo(currency);
        assertThat(money.quantity()).isEqualTo(decimal(1000L));
    }

    @Test
    void throws_when_negative_price() {
        // given
        var currency = USD;
        var quantity = decimal(-1);

        // when // then
        assertThatThrownBy(() -> price(currency, quantity))
                .isInstanceOf(FmpInvalidPriceException.class)
                .hasMessage("Price cannot be negative");
    }
}
