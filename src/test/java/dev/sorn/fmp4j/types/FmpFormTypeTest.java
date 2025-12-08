package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.types.FmpFormType.FMP_FORM_TYPE_PATTERN;
import static dev.sorn.fmp4j.types.FmpFormType.FORM_10K;
import static dev.sorn.fmp4j.types.FmpFormType.FORM_10Q;
import static dev.sorn.fmp4j.types.FmpFormType.FORM_8K;
import static dev.sorn.fmp4j.types.FmpFormType.formType;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.sorn.fmp4j.exceptions.FmpInvalidFormTypeException;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FmpFormTypeTest {

    @Test
    void null_form_type_throws() {
        // given
        var value = (String) null;

        // when // then
        var e = assertThrows(FmpInvalidFormTypeException.class, () -> formType(value));
        assertEquals("Form type must not be null or blank.", e.getMessage());
    }

    @Test
    void blank_form_type_throws() {
        // given
        var value = "   ";

        // when // then
        var e = assertThrows(FmpInvalidFormTypeException.class, () -> formType(value));
        assertEquals("Form type must not be null or blank.", e.getMessage());
    }

    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = FORM_10Q;

        // when
        var after = (FmpFormType) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void to_string_returns_value() {
        // given
        var s = FORM_10Q;

        // when
        var str = s.toString();

        // then
        assertEquals("10-Q", str);
    }

    @Test
    void hash_code_value() {
        // given
        var str = "10-Q";
        var ft = formType(str);

        // when
        var hc = ft.hashCode();

        // then
        assertEquals(str.hashCode(), hc);
    }

    @Test
    void equals_identical_true() {
        // given
        var ft1 = FORM_10Q;
        var ft2 = FORM_10Q;

        // when
        var eq = ft1.equals(ft2);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var ft1 = FORM_10Q;
        var ft2 = (FmpFormType) null;

        // when
        var eq = ft1.equals(ft2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var ft1 = FORM_10Q;
        var ft2 = formType("4-K");

        // when
        var eq = ft1.equals(ft2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var ft1 = symbol("10-K");
        var ft2 = "10-K";

        // when
        var eq = ft1.equals(ft2);

        // then
        assertFalse(eq);
    }

    @Test
    void compare_to_null_throws() {
        // given
        var ft1 = FORM_10Q;
        var ft2 = (FmpFormType) null;

        // when // then
        var e = assertThrows(FmpInvalidFormTypeException.class, () -> ft1.compareTo(ft2));
        assertEquals("'that.value' is required", e.getMessage());
    }

    @Test
    void compare_to_less_than() {
        // given
        var ft1 = FORM_10Q;
        var ft2 = formType("4-K");

        // when
        int cmp = ft1.compareTo(ft2);

        // then
        assertEquals(-3, cmp);
    }

    @Test
    void compare_to_greater_than() {
        // given
        var ft1 = formType("4-K");
        var ft2 = FORM_10Q;

        // when
        int cmp = ft1.compareTo(ft2);

        // then
        assertEquals(3, cmp);
    }

    @Test
    void compare_to_equal() {
        // given
        var ft1 = FORM_10Q;
        var ft2 = FORM_10Q;

        // when
        int cmp = ft1.compareTo(ft2);

        // then
        assertEquals(0, cmp);
    }

    @Test
    void equals_same_instance_true() {
        // given
        var ft = FORM_10Q;

        // when
        var eq = ft.equals(ft);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_different_type_false() {
        // given
        var ft = FORM_10Q;
        var other = "10-Q";

        // when
        var eq = ft.equals(other);

        // then
        assertFalse(eq);
    }

    @ParameterizedTest
    @MethodSource("validFormTypes")
    void valid_form_types(String value) {
        // given // when
        var formType = formType(value);

        // then
        assertEquals(value, formType.value());
    }

    @Test
    void valid_form_type_constants() {
        // given // when // then
        assertEquals("10-K", FORM_10K.value());
        assertEquals("10-Q", FORM_10Q.value());
        assertEquals("8-K", FORM_8K.value());
    }

    @ParameterizedTest
    @MethodSource("invalidFormTypes")
    void invalid_form_types(String value) {
        // given // when
        Function<String, FmpFormType> f = FmpFormType::formType;

        // then
        var e = assertThrows(FmpInvalidFormTypeException.class, () -> f.apply(value));
        assertEquals(
                format("'value' [%s] does not match pattern [%s]", value, FMP_FORM_TYPE_PATTERN.pattern()),
                e.getMessage());
    }

    private static Stream<String> validFormTypes() {
        return Stream.of(
                "1-E",
                "1-E/A",
                "1-E AD",
                "1-E AD/A",
                "2-E",
                "2-E/A",
                "10-12B",
                "10-12B/A",
                "10-12G",
                "10-12G/A",
                "10-D",
                "10-D/A",
                "10-K",
                "10-K/A",
                "10-KT",
                "10-KT/A",
                "10-Q",
                "10-Q/A",
                "10-QT",
                "10-QT/A",
                "11-K",
                "11-K/A",
                "11-KT",
                "11-KT/A",
                "13F-HR",
                "13F-HR/A",
                "13F-NT",
                "13F-NT/A",
                "13H",
                "13H-Q",
                "13H-A",
                "13H-I",
                "13H-R",
                "13H-T",
                "144",
                "144/A",
                "15-12B",
                "15-12B/A",
                "15-12G",
                "15-12G/A",
                "15-15D",
                "15-15D/A",
                "15F-12G",
                "15F-12G/A",
                "15F-15D",
                "15F-15D/A",
                "18-K",
                "18-K/A",
                "20-F",
                "20-F/A",
                "20FR12B",
                "20FR12B/A",
                "20FR12G",
                "20FR12G/A",
                "24F-2NT",
                "24F-2NT/A",
                "25",
                "25/A",
                "25-NSE",
                "25-NSE/A",
                "3",
                "3/A",
                "305B2",
                "305B2/A",
                "4",
                "4/A",
                "40-6B",
                "40-6B/A",
                "40-17F2",
                "40-17F2/A",
                "40-17G",
                "40-17G/A",
                "40-202A/A",
                "40-206A/A",
                "40-24B2",
                "40-24B2/A",
                "40-33",
                "40-8B25",
                "40-8F-2",
                "40-APP",
                "40-APP/A",
                "40-F",
                "40-F/A",
                "40-OIP",
                "40-OIP/A",
                "40FR12B",
                "40FR12B/A",
                "40FR12G",
                "40FR12G/A",
                "424A",
                "424B1",
                "424B2",
                "424B3",
                "424B4",
                "424B5",
                "424B7",
                "424B8",
                "425",
                "485APOS",
                "485BPOS",
                "485BXT",
                "486APOS",
                "486BPOS",
                "487",
                "497",
                "497AD",
                "497H2",
                "497J",
                "497K1",
                "497K2",
                "497K3A",
                "497K3B",
                "5",
                "5/A",
                "6-K",
                "6-K/A",
                "8-A12B",
                "8-A12B/A",
                "8-A12G",
                "8-A12G/A",
                "8-K",
                "8-K/A",
                "8-K12B",
                "8-K12B/A",
                "8-K12G3",
                "8-K12G3/A",
                "8-K15D5",
                "8-M",
                "9-M",
                "ADN-MTL",
                "ADV-E",
                "ADV-H-C",
                "ADV-H-T",
                "ADV-NR",
                "ANNLRPT",
                "APP WD",
                "ARS",
                "ARS/A",
                "AW",
                "AW WD",
                "CB",
                "CB/A",
                "CERTAMX",
                "CERTNAS",
                "CERTNYS",
                "CERTPAC",
                "CORRESP",
                "CT ORDER",
                "D",
                "D/A",
                "DEF 14A",
                "DEF 14C",
                "DEFA14C",
                "DEFC14A",
                "DEFC14C",
                "DEFM14A",
                "DEFM14C",
                "DEFN14A",
                "DEFR14A",
                "DEFR14C",
                "DEL AM",
                "DFAN14A",
                "DFRN14A",
                "DSTRBRPT",
                "EFFECT",
                "F-1",
                "F-1/A",
                "F-10",
                "F-10/A",
                "F-10EF",
                "F-10POS",
                "F-1MEF",
                "F-3",
                "F-3/A",
                "F-3ASR",
                "F-3D",
                "F-3DPOS",
                "F-4",
                "F-4 POS",
                "F-4/A",
                "F-6",
                "F-6 POS",
                "F-6/A",
                "F-6EF",
                "F-7",
                "F-7 POS",
                "F-7/A",
                "F-8",
                "F-8/A",
                "F-80",
                "F-9",
                "F-9/A",
                "F-N",
                "F-X",
                "F-X/A",
                "FOCUSN",
                "FOCUSN/A",
                "FWP",
                "G-405",
                "G-405N",
                "G-FIN",
                "G-FIN/A",
                "MSD",
                "MSD/A",
                "MSDW",
                "N-14",
                "N-14 8C",
                "N-14 8C/A",
                "N-14/A",
                "N-14AE",
                "N-14AE/A",
                "N-18F1",
                "N-18F1/A",
                "N-1A",
                "N-1A/A",
                "N-2",
                "N-2/A",
                "N-23C-2",
                "N-23C-2/A",
                "N-23C3A",
                "N-23C3A/A",
                "N-23C3B",
                "N-23C3C",
                "N-23C3C/A",
                "N-2MEF",
                "N-3",
                "N-30B-2",
                "N-30D",
                "N-30D/A",
                "N-4",
                "N-4/A",
                "N-54A",
                "N-54C",
                "N-54C/A",
                "N-6",
                "N-6/A",
                "N-6F",
                "N-8A",
                "N-8A/A",
                "N-8B-2",
                "N-8B-2/A",
                "N-8F",
                "N-8F/A",
                "N-CSR",
                "N-CSR/A",
                "N-CSRS",
                "N-CSRS/A",
                "N-MFP",
                "N-PX",
                "N-PX/A",
                "N-Q",
                "N-Q/A",
                "NO ACT",
                "NSAR-A",
                "NSAR-A/A",
                "NSAR-AT",
                "NSAR-B",
                "NSAR-B/A",
                "NSAR-BT",
                "NSAR-BT/A",
                "NSAR-U",
                "NSAR-U/A",
                "NT 10-K",
                "NT 10-K/A",
                "NT 10-Q",
                "NT 10-Q/A",
                "NT 11-K",
                "NT 15D2",
                "NT 20-F",
                "NT 20-F/A",
                "NT-NCSR",
                "NT-NCSR/A",
                "NT-NSAR",
                "NT-NSAR/A",
                "NTFNCSR",
                "NTFNSAR",
                "NTN 10K",
                "NTN 10Q",
                "NTN 20F",
                "POS 8C",
                "POS AM",
                "POS AMI",
                "POS EX",
                "POS462B",
                "POS462C",
                "POSASR",
                "PRE 14A",
                "PRE 14C",
                "PREC14A",
                "PREC14C",
                "PREM14A",
                "PREM14C",
                "PREN14A",
                "PRER14A",
                "PRER14C",
                "PRRN14A",
                "PX14A6G",
                "PX14A6N",
                "QRTLYRPT",
                "REG-NR",
                "REGDEX",
                "REGDEX/A",
                "RW",
                "RW WD",
                "S-1",
                "S-1/A",
                "S-11",
                "S-11/A",
                "S-11MEF",
                "S-1MEF",
                "S-3",
                "S-3/A",
                "S-3ASR",
                "S-3D",
                "S-3DPOS",
                "S-3MEF",
                "S-4",
                "S-4 POS",
                "S-4/A",
                "S-4EF",
                "S-4EF/A",
                "S-4MEF",
                "S-6",
                "S-6/A",
                "S-8",
                "S-8 POS",
                "S-B",
                "S-B/A",
                "S-BMEF",
                "SB-1",
                "SB-1/A",
                "SB-2",
                "SB-2/A",
                "SB-2MEF",
                "SC 13D",
                "SC 13D/A",
                "SC 13E3",
                "SC 13E3/A",
                "SC 13G",
                "SC 13G/A",
                "SC 14D9",
                "SC 14D9/A",
                "SC 14F1",
                "SC 14F1/A",
                "SC TO-C",
                "SC TO-I",
                "SC TO-I/A",
                "SC TO-T",
                "SC TO-T/A",
                "SC13E4F",
                "SC13E4F/A",
                "SC14D1F",
                "SC14D1F/A",
                "SC14D9C",
                "SC14D9F",
                "SC14D9F/A",
                "SE",
                "SP 15D2",
                "SUPPL",
                "T-3",
                "T-3/A",
                "TA-1",
                "TA-1/A",
                "TA-2",
                "TA-2/A",
                "TA-W",
                "TTW",
                "UNDER",
                "UPLOAD",
                "WDL-REQ",
                "X-17A-5",
                "X-17A-5/A");
    }

    private static Stream<String> invalidFormTypes() {
        return Stream.of("10-abc", "10#12B", "-10A", "10--12B", "10-12B/B", " 10-12B", "10_12B");
    }
}
