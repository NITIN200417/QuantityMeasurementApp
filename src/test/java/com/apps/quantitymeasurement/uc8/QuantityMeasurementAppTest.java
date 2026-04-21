package com.apps.quantitymeasurement.uc8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testConversion() {
        Length l = new Length(1.0, LengthUnit.FEET);

        Length result = l.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    void testAddition() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void testAddWithTargetUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.YARDS);

        assertEquals(0.67, result.getValue(), 0.01);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(Double.NaN, LengthUnit.FEET);
        });
    }
}