package com.apps.quantitymeasurement.uc13;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {


    @Test
    void testAdd_WorksCorrectly() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testSubtract_WorksCorrectly() {
        Quantity<LengthUnit> q1 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(1.0, result.getValue());
    }

    @Test
    void testDivide_WorksCorrectly() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals(2.0, q1.divide(q2));
    }

    @Test
    void testNullOperand_AllOperations() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.add(null));
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }

    @Test
    void testCrossCategory_AllOperations() {
        Quantity<LengthUnit> len = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> wt = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> len.add((Quantity) wt));
        assertThrows(IllegalArgumentException.class, () -> len.subtract((Quantity) wt));
        assertThrows(IllegalArgumentException.class, () -> len.divide((Quantity) wt));
    }

    @Test
    void testInvalidValues() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(Double.NaN, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(q2));
    }

    @Test
    void testDivideByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testRounding_Add() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.333, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.333, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(2.67, result.getValue());
    }

    @Test
    void testDivide_NoRounding() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(10.0 / 3.0, q1.divide(q2));
    }

    @Test
    void testImplicitUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testExplicitUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.INCHES);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testImmutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        q1.add(q2);
        assertEquals(1.0, q1.getValue());
    }

    @Test
    void testChaining() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(2.0, LengthUnit.FEET);
        double result = q1.add(q2).subtract(q3).divide(q2);
        assertEquals((10 + 5 - 2) / 5.0, result);
    }

    @Test
    void testEquality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testConversion() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue());
    }
}
