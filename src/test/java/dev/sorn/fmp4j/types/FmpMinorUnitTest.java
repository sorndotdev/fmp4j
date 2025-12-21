package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.types.FmpLimit.limit;
import static dev.sorn.fmp4j.types.FmpMinorUnit.MAX_MINOR_UNIT_VALUE;
import static dev.sorn.fmp4j.types.FmpMinorUnit.MIN_MINOR_UNIT_VALUE;
import static dev.sorn.fmp4j.types.FmpMinorUnit.minorUnit;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.sorn.fmp4j.exceptions.FmpInvalidMinorUnitException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpMinorUnitTest {
    @ParameterizedTest(name = "[{index}]")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24})
    void valid_minor_unit(int limit) {
        // given // when
        var minorUnit = minorUnit(limit);

        // then
        assertThat(minorUnit.value()).isEqualTo(limit);
    }

    @Test
    void below_minimum_minor_unit() {
        // given
        var minorUnit = -1;

        // when // then
        assertThatThrownBy(() -> minorUnit(minorUnit))
                .isInstanceOf(FmpInvalidMinorUnitException.class)
                .hasMessage(format("[%d] is below the minimum allowed value [%d]", minorUnit, MIN_MINOR_UNIT_VALUE));
    }

    @Test
    void exceeds_maximum_minor_unit() {
        // given
        var minorUnit = 25;

        // when // then
        assertThatThrownBy(() -> minorUnit(minorUnit))
                .isInstanceOf(FmpInvalidMinorUnitException.class)
                .hasMessage(format("[%d] exceeds the maximum allowed value [%d]", minorUnit, MAX_MINOR_UNIT_VALUE));
    }

    @ParameterizedTest(name = "[{index}]")
    @ValueSource(ints = {2, 3})
    void toString_returns_value(int limit) {
        // given // when
        var minorUnit = minorUnit(limit);

        // then
        assertThat(minorUnit.toString()).isEqualTo(valueOf(limit));
    }

    @Test
    void hash_code_value() {
        // given
        var i = 3;
        var minorUnit = minorUnit(i);

        // when
        var hc = minorUnit.hashCode();

        // then
        assertThat(hc).isEqualTo(i);
    }

    @Test
    void equals_same_true() {
        // given
        var minorUnit = minorUnit(10);

        // when
        var eq = minorUnit.equals(minorUnit);

        assertThat(eq).isTrue();
    }

    @Test
    void equals_identical_true() {
        // given
        var minorUnit1 = limit(10);
        var minorUnit2 = limit(10);

        // when
        var eq = minorUnit1.equals(minorUnit2);

        assertThat(eq).isTrue();
    }

    @Test
    void equals_null_false() {
        // given
        var minorUnit1 = limit(3);
        var minorUnit2 = (FmpLimit) null;

        // when
        var eq = minorUnit1.equals(minorUnit2);

        assertThat(eq).isFalse();
    }

    @Test
    void equals_different_false() {
        // given
        var minorUnit1 = limit(3);
        var minorUnit2 = limit(5);

        // when
        var eq = minorUnit1.equals(minorUnit2);

        assertThat(eq).isFalse();
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var minorUnit1 = limit(1);
        var minorUnit2 = 3;

        // when
        var eq = minorUnit1.equals(minorUnit2);

        assertThat(eq).isFalse();
    }
}
