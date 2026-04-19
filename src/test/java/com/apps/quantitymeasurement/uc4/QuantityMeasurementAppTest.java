package com.apps.quantitymeasurement.uc4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(1, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }


    @Test
    public void testInchesEquality() {
        Length l1 = new Length(1, Length.LengthUnit.INCHES);
        Length l2 = new Length(1, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }


    @Test
    public void testFeetInchesComparison() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }


    @Test
    public void testFeetInequality() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(2, Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }


    @Test
    public void testInchesInequality() {
        Length l1 = new Length(1, Length.LengthUnit.INCHES);
        Length l2 = new Length(2, Length.LengthUnit.INCHES);
        assertFalse(l1.equals(l2));
    }


    @Test
    public void testCrossUnitInequality() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(10, Length.LengthUnit.INCHES);
        assertFalse(l1.equals(l2));
    }


    @Test
    public void testMultipleFeetComparison() {
        Length l1 = new Length(2, Length.LengthUnit.FEET);
        Length l2 = new Length(24, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void yardEquals36Inches() {

        Length l1 = new Length(1, Length.LengthUnit.YARDS);
        Length l2 = new Length(36, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {

        Length l1 = new Length(100, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(39.3701, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }


    @Test
    public void threeFeetEqualsOneYard() {

        Length l1 = new Length(3, Length.LengthUnit.FEET);
        Length l2 = new Length(1, Length.LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }


    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        Length l1 = new Length(30.48, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(1, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }


    @Test
    public void yardNotEqualToInches() {
        Length l1 = new Length(1, Length.LengthUnit.YARDS);
        Length l2 = new Length(10, Length.LengthUnit.INCHES);
        assertFalse(l1.equals(l2));
    }


    @Test
    public void referenceEqualitySameObject() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l1));
    }


    @Test
    public void equalsReturnsFalseForNull() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        assertFalse(l1.equals(null));
    }


    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length a = new Length(1, Length.LengthUnit.YARDS);
        Length b = new Length(3, Length.LengthUnit.FEET);
        Length c = new Length(36, Length.LengthUnit.INCHES);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }


    @Test
    public void differentValuesSameUnitNotEqual() {
        Length l1 = new Length(2, Length.LengthUnit.YARDS);
        Length l2 = new Length(3, Length.LengthUnit.YARDS);
        assertFalse(l1.equals(l2));
    }
}
