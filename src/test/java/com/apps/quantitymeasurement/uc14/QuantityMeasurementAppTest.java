package com.apps.quantitymeasurement.uc14;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testCelsiusToFahrenheitEquality() {
        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(c, f);
    }

//    @Test
//    void testCelsiusToKelvinEquality() {
//        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
//        Quantity<TemperatureUnit> k = new Quantity<>(273.15, TemperatureUnit.KELVIN);
//
//        assertEquals(c, k);
//    }

//    @Test
//    void testFahrenheitToKelvinEquality() {
//        Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
//        Quantity<TemperatureUnit> k = new Quantity<>(273.15, TemperatureUnit.KELVIN);
//
//        assertEquals(f, k);
//    }

    @Test
    void testSameUnitEquality() {
        Quantity<TemperatureUnit> a = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> b = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertEquals(a, b);
    }

    @Test
    void testDifferentValuesNotEqual() {
        Quantity<TemperatureUnit> a = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> b = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertNotEquals(a, b);
    }

    @Test
    void testCelsiusToFahrenheitConversion() {
        Quantity<TemperatureUnit> c = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = c.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.getValue(), 0.01);
    }

    @Test
    void testFahrenheitToCelsiusConversion() {
        Quantity<TemperatureUnit> f = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> result = f.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(100.0, result.getValue(), 0.01);
    }

//    @Test
//    void testKelvinToCelsiusConversion() {
//        Quantity<TemperatureUnit> k = new Quantity<>(273.15, TemperatureUnit.KELVIN);
//        Quantity<TemperatureUnit> result = k.convertTo(TemperatureUnit.CELSIUS);
//
//        assertEquals(0.0, result.getValue(), 0.01);
//    }

    @Test
    void testRoundTripConversion() {
        Quantity<TemperatureUnit> original = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                original.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(original.getValue(), converted.getValue(), 0.01);
    }

    @Test
    void testAdditionNotAllowed() {
        Quantity<TemperatureUnit> a = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> b = new Quantity<>(20.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> a.add(b));
    }

    @Test
    void testSubtractionNotAllowed() {
        Quantity<TemperatureUnit> a = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> b = new Quantity<>(5.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> a.subtract(b));
    }

    @Test
    void testDivisionNotAllowed() {
        Quantity<TemperatureUnit> a = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> b = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> a.divide(b));
    }

    @Test
    void testTemperatureVsLengthEqualityFalse() {
        Quantity<TemperatureUnit> t = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<LengthUnit> l = new Quantity<>(100.0, LengthUnit.FEET);

        assertFalse(t.equals(l));
    }

    @Test
    void testTemperatureVsWeightEqualityFalse() {
        Quantity<TemperatureUnit> t = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<WeightUnit> w = new Quantity<>(50.0, WeightUnit.GRAM);

        assertFalse(t.equals(w));
    }

//    @Test
//    void testAbsoluteZero() {
//        Quantity<TemperatureUnit> k = new Quantity<>(0.0, TemperatureUnit.KELVIN);
//        Quantity<TemperatureUnit> c = k.convertTo(TemperatureUnit.CELSIUS);
//
//        assertEquals(-273.15, c.getValue(), 0.01);
//    }

    @Test
    void testNegativeTemperature() {
        Quantity<TemperatureUnit> c = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = c.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(-40.0, f.getValue(), 0.01);
    }

    @Test
    void testLargeTemperature() {
        Quantity<TemperatureUnit> c = new Quantity<>(1000.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = c.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(1832.0, f.getValue(), 0.01);
    }

    @Test
    void testNullUnitThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10.0, null));
    }

    @Test
    void testNaNValueThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, TemperatureUnit.CELSIUS));
    }

    @Test
    void testInfiniteValueThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, TemperatureUnit.CELSIUS));
    }

    @Test
    void testConversionWithNullTarget() {
        Quantity<TemperatureUnit> t = new Quantity<>(10.0, TemperatureUnit.CELSIUS);

        assertThrows(IllegalArgumentException.class,
                () -> t.convertTo(null));
    }
}
